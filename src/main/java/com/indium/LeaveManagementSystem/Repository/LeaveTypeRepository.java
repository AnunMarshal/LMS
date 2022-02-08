package com.indium.LeaveManagementSystem.Repository;

import com.indium.LeaveManagementSystem.Model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTypeRepository extends JpaRepository<LeaveType,Integer> {

}
