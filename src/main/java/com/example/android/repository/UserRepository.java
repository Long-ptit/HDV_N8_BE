package com.example.android.repository;

import com.example.android.model.Good;
import com.example.android.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
