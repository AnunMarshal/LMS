package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.EmployeeDetailsDto;
import com.indium.LeaveManagementSystem.DTO.EmployeeLeaveBalanceDTO;
import com.indium.LeaveManagementSystem.DTO.ManagerActionDto;
import com.indium.LeaveManagementSystem.Model.EmployeeLeaveBalance;
import com.indium.LeaveManagementSystem.Service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController
public class EmployeeController {
    private static final Logger LOGGER= LoggerFactory.getLogger(EmployeeController.class.getName());
    @Autowired
    private EmployeeService service;

    @PostMapping("/addEmployeeDetails")
    public ResponseEntity<String> addEmplyeeDetails(@RequestBody EmployeeDetailsDto employeeDetails){
        LOGGER.info("In method addEmplyeeDetails");
        return service.createEmployeeDetails(employeeDetails);
    }

    @GetMapping("/employeeDetails/{id}")
    public ResponseEntity<EmployeeDetailsDto> getEmployeeDetailsByID(@PathVariable int id) throws IOException {
        return service.getEmployeeDetailsByID(id);
    }

    @GetMapping("/getEmployeeDetails")
    public ResponseEntity<List<EmployeeDetailsDto>> getEmployeeDetails() throws IOException {
        return service.getEmployeeDetails();
    }


    @PutMapping("/updateEmployeeDetails")
    public ResponseEntity<String> updateEmplyeeDetails(@RequestBody EmployeeDetailsDto employeeDetailsRequest){
        return service.updateEmployeeDetails(employeeDetailsRequest);
    }

    @DeleteMapping("/deleteEmployeeDetails/{id}")
    public ResponseEntity<String> deleteEmployeeDetails(@PathVariable int id){
        return service.deleteEmployeeDetails(id);
    }

    @PostMapping("/addLeaveBalance")
    public ResponseEntity<String> addLeaveBalance(@RequestBody EmployeeLeaveBalanceDTO balance) {
        LOGGER.info("in method addleaveBalance");
        return service.createEmployeeLeaveBalance(balance);
    }

   @GetMapping("/getLeaveBalance")
   public ResponseEntity<List<EmployeeLeaveBalanceDTO>> getLeaveBalance(){
       return service.getLeaveBalance();
   }

   /*@GetMapping("/LeaveBalanceById/{id}")
    public Optional<EmployeeLeaveBalance> findLeaveBalanceById(@PathVariable int id) {
        return service.getLeaveBalanceById(id);
    }*/

    @GetMapping("/getLeaveBalanceByEmp/{id}")
    public ResponseEntity<List<EmployeeLeaveBalanceDTO>> findLeaveBalanceByEmp(@PathVariable int id) {
        return service.getLeaveBalanceByEmp(id);
    }
    @PutMapping("/updateLeaveBalanace")
    public ResponseEntity<String> updateLeaveBalance(@RequestBody EmployeeLeaveBalanceDTO leaveBalanceDTO) {
        return service.updateLeaveBalance(leaveBalanceDTO);
    }
    @DeleteMapping("/deleteLeaveBalance/{id}")
    public ResponseEntity<String> deleteLeaveBalance(@PathVariable int id){
       return  service.deleteLeaveBalance(id);
    }

    @PostMapping("/ManagerAction")
    public ResponseEntity<String> managerAction(@RequestBody ManagerActionDto managerActionDto){
        return service.managerAction(managerActionDto);
    }

}