package com.indium.LeaveManagementSystem.Controller;

import com.indium.LeaveManagementSystem.DTO.LeaveDetailDto;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.Model.LeaveDetail;
import com.indium.LeaveManagementSystem.Service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class LeaveController {

    private static final Logger log= LoggerFactory.getLogger(LeaveController.class.getName());
    @Autowired
    LeaveService leaveTypeService;

    @PostMapping("/addLeaveType")
    public LeaveTypeDto addLeaveType(@RequestBody LeaveTypeDto leaveType){
        return leaveTypeService.createLeaveType(leaveType);
    }

    @GetMapping("/getLeaveType/{id}")
    public LeaveTypeDto getLeaveTypeByID(@PathVariable int id) throws IOException {
        return leaveTypeService.getLeaveTypeByID(id);
    }

    @GetMapping("/getLeaveTypes")
    public List<LeaveTypeDto> getLeaveTypes() throws IOException {
        return leaveTypeService.getLeaveTypes();
    }

    @PostMapping("/updateLeaveType")
    public LeaveTypeDto updateLeaveType(@RequestBody LeaveTypeDto leaveType){
        return leaveTypeService.updateLeaveType(leaveType);
    }

    @GetMapping("/deleteLeaveType/{id}")
    public LeaveTypeDto deleteLeaveType(@PathVariable int id){
        return leaveTypeService.deleteLeaveType(id);
    }

//============================================================================================

    @PostMapping(value = "/addLeaveDetail",produces = MediaType.APPLICATION_JSON_VALUE)
    public LeaveDetailDto addLeaveDetail(@RequestBody LeaveDetailDto leavedetail) {
        return leaveTypeService.createLeaveDetail(leavedetail);
    }
    @GetMapping("/LeaveDetail/{id}")
    public LeaveDetailDto getLeaveDetailByID(@PathVariable int id) throws IOException {
        return leaveTypeService.getLeaveDetailByID(id);
    }
    @GetMapping("/getLeaveDetail")
    public List<LeaveDetailDto> getLeaveDetail()throws IOException {
        return leaveTypeService.getLeaveDetail();
    }
    @PostMapping("/updateLeaveDetail")
    public LeaveDetailDto updateLeaveDetail(@RequestBody LeaveDetailDto leavedetail){
        return leaveTypeService.updateLeaveDetail(leavedetail);
    }
    @GetMapping("/RejectLeaveDetail/{id}")
    public LeaveDetailDto deleteLeavedetail(@PathVariable int id){
        return leaveTypeService.deleteLeaveDetail(id);
    }
}