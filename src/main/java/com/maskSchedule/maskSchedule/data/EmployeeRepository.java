package com.maskSchedule.maskSchedule.data;

import com.maskSchedule.maskSchedule.models.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
}
