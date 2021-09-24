package com.cognizant.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.authorization.model.UserDetail;

/**
 *  This is a Repository interface which is extending JpaRepository for UserDetail Model.
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
}
