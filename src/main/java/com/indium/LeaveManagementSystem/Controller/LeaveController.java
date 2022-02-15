package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.LeaveDetailDto;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.DTO.RolesDto;
import com.indium.LeaveManagementSystem.Service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@RestController
public class LeaveController {

    private static final Logger log= LoggerFactory.getLogger(LeaveController.class.getName());
    @Autowired
    LeaveService leaveTypeService;

    @PostMapping("/addLeaveType")
    public ResponseEntity<String> addLeaveType(@RequestBody LeaveTypeDto leaveType){
        return leaveTypeService.createLeaveType(leaveType);
    }

    @GetMapping("/getLeaveType/{id}")
    public ResponseEntity<LeaveTypeDto> getLeaveTypeByID(@PathVariable int id) throws IOException {
        return leaveTypeService.getLeaveTypeByID(id);
    }

    @GetMapping("/getLeaveTypes")
    public ResponseEntity<List<LeaveTypeDto>> getLeaveTypes() throws IOException {
        return leaveTypeService.getLeaveTypes();
    }

    @PutMapping("/updateLeaveType")
    public ResponseEntity<String> updateLeaveType(@RequestBody LeaveTypeDto leaveType){
        return leaveTypeService.updateLeaveType(leaveType);
    }

    @DeleteMapping("/deleteLeaveType/{id}")
    public ResponseEntity<String> deleteLeaveType(@PathVariable int id){
        return leaveTypeService.deleteLeaveType(id);
    }

    @PostMapping(value = "/addLeaveDetail",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addLeaveDetail(@RequestBody LeaveDetailDto leavedetail) {
        return leaveTypeService.createLeaveDetail(leavedetail);
    }
    @GetMapping("/LeaveDetail/{id}")
    public ResponseEntity<LeaveDetailDto> getLeaveDetailByID(@PathVariable int id) throws IOException {
        return leaveTypeService.getLeaveDetailByID(id);
    }
    @GetMapping("/getLeaveDetail")
    public ResponseEntity<List<LeaveDetailDto>>getLeaveDetail()throws IOException {
        return leaveTypeService.getLeaveDetail();
    }
    @PutMapping("/updateLeaveDetail")
    public ResponseEntity<String> updateLeaveDetail(@RequestBody LeaveDetailDto leavedetail){
        return leaveTypeService.updateLeaveDetail(leavedetail);
    }
    @GetMapping("/DeleteLeaveDetail/{id}")
    public ResponseEntity<String> deleteLeavedetail(@PathVariable int id){
        return leaveTypeService.deleteLeaveDetail(id);
    }
    //==================================================================================

}