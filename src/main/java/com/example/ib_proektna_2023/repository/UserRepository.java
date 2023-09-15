package com.example.ib_proektna_2023.repository;
import com.example.ib_proektna_2023.model.User;
import com.example.ib_proektna_2023.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByType(UserType type);

    User findByUsername(String username);
}
