package com.liaozibo.study.spring.boot.validation.dao;

import com.liaozibo.study.spring.boot.validation.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {}
