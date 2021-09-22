package com.cognizant.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.authorization.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
}
