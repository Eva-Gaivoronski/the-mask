package com.maskSchedule.maskSchedule.data;

import org.springframework.data.repository.CrudRepository;
import com.maskSchedule.maskSchedule.models.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer>{
    Users findByUsername(String username);

}
