package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Integer> {
}
