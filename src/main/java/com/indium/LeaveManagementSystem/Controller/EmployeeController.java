package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


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


}
