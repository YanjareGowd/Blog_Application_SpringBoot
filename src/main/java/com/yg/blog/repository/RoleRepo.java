package com.yg.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yg.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
