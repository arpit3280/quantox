package com.quantox.repository;

import com.quantox.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u where u.username = :userName and u.password = :passwordHash")
    User getByNameAndPass(@Param("userName") String userName, @Param("passwordHash") String passwordHash);
}
