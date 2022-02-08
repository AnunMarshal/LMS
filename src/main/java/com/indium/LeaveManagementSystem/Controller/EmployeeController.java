package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.EmployeeLeaveBalanceDTO;
import com.indium.LeaveManagementSystem.Model.EmployeeLeaveBalance;
import com.indium.LeaveManagementSystem.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class EmployeeController {
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class.getName());
    @Autowired
    private EmployeeService service;

    @PostMapping("/addEmployeeDetails")
    public EmployeeDetailsDto addEmplyeeDetails(@RequestBody EmployeeDetailsDto employeeDetails){
        LOGGER.info("In method addEmplyeeDetails");
        return service.createEmployeeDetails(employeeDetails);
    }

    @GetMapping("/employeeDetails/{id}")
    public EmployeeDetailsDto getEmployeeDetailsByID(@PathVariable int id) throws IOException {
        return service.getEmployeeDetailsByID(id);
    }

    @PostMapping("/updateEmployeeDetails")
    public EmployeeDetailsDto updateEmplyeeDetails(@RequestBody EmployeeDetailsDto employeeDetailsRequest){
        return service.updateEmployeeDetails(employeeDetailsRequest);
    }

    @GetMapping("/deleteEmployeeDetails/{id}")
    public EmployeeDetailsDto deleteEmployeeDetails(@PathVariable int id){
        return service.deleteEmployeeDetails(id);
    }

    @PostMapping("/addLeaveBalance")
    public EmployeeLeaveBalanceDTO addLeaveBalance(@RequestBody EmployeeLeaveBalance balance) {
        LOGGER.info("in method addleaveBalance");
        return service.createEmployeeLeaveBalance(balance);
    }
    @GetMapping("/getLeaveBalance")
    public List<EmployeeLeaveBalance> getLeaveBalance(){
        return service.getLeaveBalance();
    }

    @GetMapping("/LeaveBalanceById/{id}")
    public EmployeeLeaveBalance findLeaveBalanceById(@PathVariable int id) {
        return service.getLeaveBalanceById(id);
    }
    @PutMapping("/update")
    public EmployeeLeaveBalance updateLeaveBalance(@RequestBody EmployeeLeaveBalance leaveBalance) {//int id
        return service.updateLeaveBalance(leaveBalance);
        // return service.updateLeaveBalance(findLeaveBalanceById(id));
    }
    @DeleteMapping("/deleteLeaveBalance/{id}")
    public void deleteLeaveBalance(@PathVariable int id){
        service.deleteLeaveBalance(id);

    }
}


