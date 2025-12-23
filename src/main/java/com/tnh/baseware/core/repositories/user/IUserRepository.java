package com.tnh.baseware.core.repositories.user;

import com.tnh.baseware.core.entities.user.User;
import com.tnh.baseware.core.repositories.IGenericRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends IGenericRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    boolean existsByUsernameOrPhoneOrEmailOrIdn(String username, String phone, String email, String idn);

    boolean existsByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByIdn(String idn);

    Page<User> findDistinctByOrganizations_Organization_IdAndOrganizations_ActiveTrue(UUID organizationId,
            Pageable pageable);

    List<User> findDistinctByOrganizations_Organization_IdAndOrganizations_ActiveTrue(UUID organizationId);

    List<User> findDistinctByOrganizations_Organization_IdAndRoles_Id(UUID organizationId, UUID roleId);

    // find all users without organization
    List<User> findDistinctByOrganizations_Organization_IdIsNull();

}
