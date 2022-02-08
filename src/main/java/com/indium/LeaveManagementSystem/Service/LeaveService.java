package com.indium.LeaveManagementSystem.Service;

import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.Model.LeaveType;
import com.indium.LeaveManagementSystem.Repository.LeaveTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class LeaveService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveService.class.getName());
    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeDto createLeaveType(LeaveTypeDto leaveTypeRequest) {

        LeaveTypeDto response = new LeaveTypeDto();
        com.indium.LeaveManagementSystem.Model.LeaveType leaveType=new com.indium.LeaveManagementSystem.Model.LeaveType();

        if(leaveTypeRequest != null) {

            leaveType.setId(leaveTypeRequest.getId());
            leaveType.setType(leaveTypeRequest.getType());
            leaveType.setCreatedAt(System.currentTimeMillis());
            leaveType.setStatus("Active");

            leaveTypeRepository.save(leaveType);

            response.setStatus("Successfully Added");

        }else{
            response.setStatus("Data is Null");
        }
        return response;
    }

    public LeaveTypeDto getLeaveTypeByID(int id){

        LeaveTypeDto response = new LeaveTypeDto();
        Optional<LeaveType> result=leaveTypeRepository.findById(id);

        if(result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            response.setId(result.get().getId());
            response.setType(result.get().getType());

            response.setStatus(result.get().getStatus());

        }else{
            response.setStatus("Error while getting details");
        }
        return response;
    }

    public LeaveTypeDto updateLeaveType(LeaveTypeDto leaveTypeDto){
        LeaveTypeDto response = new LeaveTypeDto();
        com.indium.LeaveManagementSystem.Model.LeaveType leaveType=new com.indium.LeaveManagementSystem.Model.LeaveType();
        Optional<com.indium.LeaveManagementSystem.Model.LeaveType> result= leaveTypeRepository.findById(leaveTypeDto.getId());

        if(result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            leaveType.setId(leaveTypeDto.getId());
            leaveType.setType(leaveTypeDto.getType());
            leaveType.setCreatedAt(System.currentTimeMillis());
            leaveType.setStatus("Active");

            leaveTypeRepository.save(leaveType);

            response.setStatus("Successfully Updated");

        }else{
            response.setStatus("Error while updating");
        }


        return response;

    }

    public LeaveTypeDto deleteLeaveType(int id){
        LeaveTypeDto response = new LeaveTypeDto();
        com.indium.LeaveManagementSystem.Model.LeaveType leaveType=new com.indium.LeaveManagementSystem.Model.LeaveType();
        Optional<com.indium.LeaveManagementSystem.Model.LeaveType> result= leaveTypeRepository.findById(id);

        if(result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            leaveType.setId(result.get().getId());
            leaveType.setType(result.get().getType());
            leaveType.setCreatedAt(System.currentTimeMillis());
            leaveType.setStatus("Deleted");

            leaveTypeRepository.save(leaveType);

            response.setStatus("Leave Type deleted successfully");

        }else{
            response.setStatus("Error while deleting");
        }
        return response;
    }


}