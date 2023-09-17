package com.example.Project_IB.Repository;

import com.example.Project_IB.Model.User;
import com.example.Project_IB.Model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByUserType(UserType userType);
}
