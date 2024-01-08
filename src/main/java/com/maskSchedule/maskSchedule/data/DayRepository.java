package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.sql.rowset.CachedRowSet;
@Repository
public interface DayRepository extends CrudRepository<Day, Integer> {
}
