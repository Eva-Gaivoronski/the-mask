package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Week;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepository extends CrudRepository<Week, Integer> {
}
