package com.twp.ajavaproj.repo;


import com.twp.ajavaproj.entites.user;
import org.springframework.data.jpa.repository.JpaRepository;
public interface userrepo extends JpaRepository<user, Long> {
    user findByUsername(String name);

}