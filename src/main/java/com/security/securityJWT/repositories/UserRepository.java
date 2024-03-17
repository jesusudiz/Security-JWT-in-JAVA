package com.security.securityJWT.repositories;

import com.security.securityJWT.models.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);
    @Query("select name from UserEntity name where name.username=?1")
    Optional<UserEntity> getName(String username);
}
