package com.vbs.demo.repositories;
import com.vbs.demo.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUsername(String username);

    User findByEmail(String value);

    List<User> findByRole(String customer, Sort sort);


    List<User> findByUsernameContainingIgnoreCaseAndRole(String keyword, String customer);
}
