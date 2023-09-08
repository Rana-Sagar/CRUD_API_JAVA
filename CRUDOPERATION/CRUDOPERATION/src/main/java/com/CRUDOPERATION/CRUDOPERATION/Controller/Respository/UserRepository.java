package com.CRUDOPERATION.CRUDOPERATION.Controller.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CRUDOPERATION.CRUDOPERATION.Model.User;

public interface UserRepository extends JpaRepository<User,Long> {
 
}
