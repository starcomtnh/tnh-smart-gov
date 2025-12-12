package com.tnh.baseware.core.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "payment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentProviderProperties {
    private List<ProviderProperties> providers;

    @Getter
    @Setter
    public static class ProviderProperties {
        private String name;
        private String signatureAlgorithm;
        // Đường dẫn đến public key của nhà cung cấp (vd: Vietinbank)
        private String keyPath;
        // Đường dẫn đến private key của bạn (nên là file .p12)
        private String privateKeystorePath;
        // Mật khẩu cho file private key
        private String privateKeystorePassword;
        private String baseUrl; // Base URL cho API của nhà cung cấp
        private String clientSecret;
        private String clientId;
    }
}
