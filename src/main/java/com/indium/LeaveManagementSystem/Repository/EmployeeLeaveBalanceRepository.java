package com.indium.LeaveManagementSystem.Repository;

import com.indium.LeaveManagementSystem.Model.EmployeeLeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLeaveBalanceRepository extends JpaRepository<EmployeeLeaveBalance,Integer> {

}
