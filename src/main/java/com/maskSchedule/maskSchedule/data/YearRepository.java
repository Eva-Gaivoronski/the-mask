package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Year;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearRepository extends CrudRepository<Year, Integer> {
}
