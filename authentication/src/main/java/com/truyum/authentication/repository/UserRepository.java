package com.truyum.authentication.repository;

import com.truyum.authentication.model.UserLoginCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserLoginCredential, String> {
}
