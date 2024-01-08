package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Month;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthRepository extends CrudRepository <Month, Integer> {
}
