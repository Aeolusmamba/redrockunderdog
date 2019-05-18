package com.example.demo.dao;

import com.example.demo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Userdao extends JpaRepository<userBean,Integer>{
userBean findByUsernameAndPassword(String username,String password);
}
