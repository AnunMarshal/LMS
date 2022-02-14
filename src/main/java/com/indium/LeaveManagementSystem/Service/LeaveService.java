package com.indium.LeaveManagementSystem.Service;

import com.indium.LeaveManagementSystem.DTO.LeaveDetailDto;
import com.indium.LeaveManagementSystem.DTO.LeaveTypeDto;
import com.indium.LeaveManagementSystem.Model.EmployeeDetails;
import com.indium.LeaveManagementSystem.Model.LeaveDetail;
import com.indium.LeaveManagementSystem.Model.LeaveType;
import com.indium.LeaveManagementSystem.Repository.EmployeeDetailsRepository;
import com.indium.LeaveManagementSystem.Repository.LeaveDetailRepository;
import com.indium.LeaveManagementSystem.Repository.LeaveTypeRepository;
import com.indium.LeaveManagementSystem.utils.MessageConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LeaveService {

    private static final Logger log = LoggerFactory.getLogger(LeaveService.class.getName());
    @Autowired
    LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private LeaveDetailRepository eldrepository;

    @Autowired
    private EmployeeDetailsRepository employeeDetailsRepository;

    public ResponseEntity<String> createLeaveType(LeaveTypeDto leaveTypeRequest) {

        LeaveTypeDto response = new LeaveTypeDto();
        LeaveType leaveType = new LeaveType();

        if (leaveTypeRequest != null) {

            leaveType.setId(leaveTypeRequest.getId());
            leaveType.setType(leaveTypeRequest.getType());
            leaveType.setCreatedAt(System.currentTimeMillis());
            leaveType.setStatus("Active");

            leaveTypeRepository.save(leaveType);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_ADDED);

            return new ResponseEntity<String>(MessageConstants.LEAVETYPE_ADDED, headers, HttpStatus.OK);

            //response.setStatus("Successfully Added");

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_ADD, HttpStatus.BAD_REQUEST);
            //response.setStatus("Data is Null");
        }
        //return response;
    }

    public ResponseEntity<LeaveTypeDto> getLeaveTypeByID(int id) {

        LeaveTypeDto response = new LeaveTypeDto();
        Optional<LeaveType> result = leaveTypeRepository.findById(id);

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            response.setId(result.get().getId());
            response.setType(result.get().getType());
            response.setStatus(result.get().getStatus());
            response.setCreatedAt(result.get().getCreatedAt().getTime());

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_BY_ID);

            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(response);
        } else {
            return new ResponseEntity(MessageConstants.BAD_REQUEST_GET, HttpStatus.BAD_REQUEST);
            //response.setStatus("Error while getting details");
        }
        //return response;
    }


    public ResponseEntity<List<LeaveTypeDto>> getLeaveTypes() {
        List<LeaveTypeDto> response = new ArrayList<LeaveTypeDto>();

        List<LeaveType> leaveTypeList = leaveTypeRepository.findAll().stream().filter(e ->
                !e.getStatus().equals("Deleted")).collect(Collectors.toList());

        log.info("************7 " + leaveTypeList.toString());


        for (LeaveType leaveType : leaveTypeList) {
            LeaveTypeDto leaveTypeDto = new LeaveTypeDto();

            log.info("EmpId ******* " + leaveTypeDto.getId());

            leaveTypeDto.setId(leaveType.getId());
            leaveTypeDto.setType(leaveType.getType());
            leaveTypeDto.setStatus(leaveType.getStatus());
            leaveTypeDto.setCreatedAt(leaveType.getCreatedAt().getTime());

            log.info("leaveTypeDto ********* " + leaveTypeDto.toString());

            response.add(leaveTypeDto);
        }
        return ResponseEntity.ok(response);
    }


    public ResponseEntity<String> updateLeaveType(LeaveTypeDto leaveTypeDto) {
        LeaveTypeDto response = new LeaveTypeDto();
        LeaveType leaveType = new LeaveType();
        Optional<LeaveType> result = leaveTypeRepository.findById(leaveTypeDto.getId());

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            leaveType.setId(leaveTypeDto.getId());
            leaveType.setType(leaveTypeDto.getType());
            leaveType.setUpdatedAt(System.currentTimeMillis());
            leaveType.setCreatedAt(result.get().getCreatedAt().getTime());
            leaveType.setStatus("Active");

            leaveTypeRepository.save(leaveType);

            //response.setStatus("Successfully Updated");

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_UPDATED);

            return new ResponseEntity<String>(MessageConstants.LEAVETYPE_UPDATED, headers, HttpStatus.OK);

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_UPDATE, HttpStatus.BAD_REQUEST);
            //response.setStatus("Error while updating");
        }
        //return response;
    }

    public ResponseEntity<String> deleteLeaveType(int id) {
        LeaveTypeDto response = new LeaveTypeDto();
        LeaveType leaveType = new LeaveType();
        Optional<LeaveType> result = leaveTypeRepository.findById(id);

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            leaveType.setId(result.get().getId());
            leaveType.setType(result.get().getType());
            leaveType.setCreatedAt(result.get().getCreatedAt().getTime());
            leaveType.setUpdatedAt(System.currentTimeMillis());
            leaveType.setStatus("Deleted");

            leaveTypeRepository.save(leaveType);

            HttpHeaders headers = new HttpHeaders();
            headers.add("desc", MessageConstants.LEAVETYPE_DELETED);

            return new ResponseEntity<String>(MessageConstants.LEAVETYPE_DELETED, headers, HttpStatus.OK);
            //response.setStatus("Leave Type deleted successfully");

        } else {
            return new ResponseEntity<String>(MessageConstants.BAD_REQUEST_DELETE, HttpStatus.BAD_REQUEST);
            //response.setStatus("Error while deleting");
        }
        //return response;
    }
    //======================================================================================


    public LeaveDetailDto createLeaveDetail(LeaveDetailDto leaveDetailDto) {

        Optional<EmployeeDetails> employeeDetail= employeeDetailsRepository
                .findById(leaveDetailDto.getEmployeeDetails().getEmpId());

        Optional<LeaveType> leaveType= leaveTypeRepository
                .findById(leaveDetailDto.getLeaveType().getId());

        //Todo - Validate employee and leave type

        LeaveDetailDto response = new LeaveDetailDto();
        LeaveDetail leaveDetail = new LeaveDetail();
        //log.info("Add*leavedetail"+LeaveDetailRequest.getFromDate());
        if (leaveDetailDto != null  && employeeDetail.get().getStatus().equals(MessageConstants.ACTIVE)
                && leaveType.get().getStatus().equals(MessageConstants.ACTIVE) ) {

            leaveDetail.setId(leaveDetailDto.getId());
            leaveDetail.setEmployeeDetails(leaveDetailDto.getEmployeeDetails());
            leaveDetail.setManager(leaveDetailDto.getManager());
            leaveDetail.setLeaveType(leaveDetailDto.getLeaveType());
            leaveDetail.setFromDate(leaveDetailDto.getFromDate());
            leaveDetail.setToDate(leaveDetailDto.getToDate());
            log.info("No of Days----------> " + leaveDetailDto.getNoofDays());
            leaveDetail.setNoofDays(leaveDetailDto.getNoofDays());
            leaveDetail.setReason(leaveDetailDto.getReason());
            leaveDetail.setCreatedAt(System.currentTimeMillis());
            leaveDetail.setStatus("Pending");
            eldrepository.save(leaveDetail);
            response.setStatus("Successfully Added");

        } else {
            response.setStatus("Data is Null");
        }
        return response;
    }

    public LeaveDetailDto getLeaveDetailByID(int id) throws IOException {
        LeaveDetailDto response = new LeaveDetailDto();

        Optional<LeaveDetail> result = eldrepository.findById(id);

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            response.setId(result.get().getId());
            response.setEmployeeDetails(result.get().getEmployeeDetails());
            response.setManager(result.get().getManager());
            response.setLeaveType(result.get().getLeaveType());
            response.setFromDate(result.get().getFromDate());
            response.setToDate(result.get().getToDate());
            response.setNoofDays(result.get().getNoofDays());
            response.setReason(result.get().getReason());
            response.setCreatedAt(System.currentTimeMillis());

        } else {
            response.setStatus("Data is Null");
        }
        return response;
    }

    public List<LeaveDetailDto> getLeaveDetail() {

        List<LeaveDetailDto> response = new ArrayList<LeaveDetailDto>();
        log.info("********202");
        List<LeaveDetail> leaveDetailList = eldrepository.findAll().stream().filter(e ->
                e.getStatus().equals("Pending")).collect(Collectors.toList());

        // List<LeaveDetail>  leaveDetailList=eldrepository.findAll();

        log.info("************getall" + leaveDetailList.toString());

        for (LeaveDetail leaveDetail : leaveDetailList) {
            LeaveDetailDto leaveDetailDto = new LeaveDetailDto();

            log.info("EmpId ******* " + leaveDetailDto.getId());

            leaveDetailDto.setId(leaveDetail.getId());
            leaveDetailDto.setEmployeeDetails(leaveDetail.getEmployeeDetails());
            leaveDetailDto.setManager(leaveDetail.getManager());
            leaveDetailDto.setLeaveType(leaveDetail.getLeaveType());
            leaveDetailDto.setFromDate(leaveDetail.getFromDate());
            leaveDetailDto.setToDate(leaveDetail.getToDate());
            leaveDetailDto.setNoofDays(leaveDetail.getNoofDays());
            leaveDetailDto.setReason(leaveDetail.getReason());
            leaveDetailDto.setStatus(leaveDetail.getStatus());
            leaveDetailDto.setCreatedAt(leaveDetail.getCreatedAt().getTime());

            log.info("leaveDetailDto ********* " + leaveDetailDto.toString());

            response.add(leaveDetailDto);
        }
        return response;
    }

    public LeaveDetailDto updateLeaveDetail(LeaveDetailDto LeaveDetailDto) {
        LeaveDetailDto response = new LeaveDetailDto();
        LeaveDetail LeaveDetail = new LeaveDetail();
        Optional<LeaveDetail> result = eldrepository.findById(LeaveDetailDto.getId());

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {

            LeaveDetail.setId(LeaveDetailDto.getId());
            LeaveDetail.setEmployeeDetails(LeaveDetailDto.getEmployeeDetails());
            LeaveDetail.setManager(LeaveDetailDto.getManager());
            LeaveDetail.setLeaveType(LeaveDetailDto.getLeaveType());
            LeaveDetail.setFromDate(LeaveDetailDto.getFromDate());
            LeaveDetail.setToDate(LeaveDetailDto.getToDate());
            LeaveDetail.setNoofDays(LeaveDetailDto.getNoofDays());
            LeaveDetail.setReason(LeaveDetailDto.getReason());
            LeaveDetail.setCreatedAt(System.currentTimeMillis());
            eldrepository.save(LeaveDetail);
            response.setStatus("Successfully Added");

        } else {
            response.setStatus("Error while updating");
        }
        return response;
    }

    public LeaveDetailDto deleteLeaveDetail(int id) {
        LeaveDetailDto response = new LeaveDetailDto();
        LeaveDetail LeaveDetail = new LeaveDetail();
        Optional<LeaveDetail> result = eldrepository.findById(id);

        if (result.isPresent() && !result.get().getStatus().equals("Deleted")) {
            LeaveDetail.setId(result.get().getId());
            LeaveDetail.setEmployeeDetails(result.get().getEmployeeDetails());
            LeaveDetail.setManager(result.get().getManager());
            LeaveDetail.setLeaveType(result.get().getLeaveType());
            LeaveDetail.setFromDate(result.get().getFromDate());
            LeaveDetail.setToDate(result.get().getToDate());
            LeaveDetail.setNoofDays(result.get().getNoofDays());
            LeaveDetail.setReason(result.get().getReason());
            LeaveDetail.setCreatedAt(System.currentTimeMillis());
            eldrepository.save(LeaveDetail);
            response.setStatus("Successfully Added");


            eldrepository.save(LeaveDetail);

            response.setStatus("Leave detail deleted successfully");

        } else {
            response.setStatus("Error while deleting");
        }
        return response;
    }


}