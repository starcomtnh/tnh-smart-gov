package com.tnh.baseware.core.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnh.baseware.core.dtos.user.TenantContextDTO;
import com.tnh.baseware.core.properties.SecurityUriProperties;
import com.tnh.baseware.core.securities.JwtTokenService;
import com.tnh.baseware.core.services.MessageService;
import com.tnh.baseware.core.services.user.imp.PrivilegeCacheService;
import com.tnh.baseware.core.services.user.imp.TenantService;
import com.tnh.baseware.core.utils.LogStyleHelper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@Order(1)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TenantFilter extends BasewareCoreFilter {

    TenantService tenantService;
    JwtTokenService jwtTokenService;

    public TenantFilter(SecurityUriProperties securityUriProperties,
                        MessageService messageService,
                        ObjectMapper objectMapper,
                        PrivilegeCacheService privilegeCacheService, TenantService tenantService, JwtTokenService jwtTokenService) {
        super(securityUriProperties, messageService, objectMapper, privilegeCacheService);
        this.tenantService = tenantService;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        var requestURI = request.getRequestURI();

        if (isBypassUri(requestURI)) {
            log.debug(LogStyleHelper.debug("Bypassing tenant filter for URI: {}"), requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        var tokenOpt = getAccessToken(request);
        if (tokenOpt.isEmpty()) {
            log.warn(LogStyleHelper.warn("No JWT token found, rejecting unauthorized request to: {}"), requestURI);
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "error.unauthorized");
            return;
        }

        var tenantId = jwtTokenService.extractTenantId(tokenOpt.get());
        if (tenantId.isEmpty()) {
            log.warn(LogStyleHelper.warn("Missing tenant ID in JWT token for request to: {}"), requestURI);
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "error.tenant.missing");
            return;
        }

        var tenantOpt = tenantService.findByNameAndActiveTrue(tenantId.get());
        if (tenantOpt.isEmpty()) {
            log.warn(LogStyleHelper.warn("Tenant not found or inactive: {} for request to: {}"),
                    tenantId, requestURI);
            sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "error.tenant.not.found");
            return;
        }

        var tenant = tenantOpt.get();
        var tenantContext = TenantContextDTO.builder()
                .tenantId(tenant.getName())
                .schemaName(tenant.getSchemaName())
                .build();

        TenantContext.setTenant(tenantContext);
        log.debug(LogStyleHelper.debug("Set tenant context: {} (schema: {}) for request to: {}"),
                tenant.getName(), tenant.getSchemaName(), requestURI);

        try {
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
            log.debug(LogStyleHelper.debug("Cleared tenant context after processing request to: {}"), requestURI);
        }
    }
}
