package com.maskSchedule.maskSchedule.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.maskSchedule.maskSchedule.models.Shift;

@Repository
public interface ShiftRepository extends CrudRepository<Shift, Integer> {

}
