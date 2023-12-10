package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
}
