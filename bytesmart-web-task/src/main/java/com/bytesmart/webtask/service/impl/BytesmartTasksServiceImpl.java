package com.bytesmart.webtask.service.impl;

import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webtask.domain.BytesmartTasks;
import com.bytesmart.webtask.domain.BytesmartTasksAssigned;
import com.bytesmart.webtask.mapper.BytesmartTasksMapper;
import com.bytesmart.webtask.service.IBytesmartTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BytesmartTasksServiceImpl implements IBytesmartTasksService {

    @Autowired
    private BytesmartTasksMapper tasksMapper;

    @Autowired
    private BytesmartGetEmployeeService bytesmartGetEmployeeService;

    @Override
    public List<BytesmartTasks> getTaskByInitiatorList(BytesmartTasks bytesmartTasks, Long employeeId) {
        ArrayList<BytesmartTasks> list = new ArrayList<BytesmartTasks>();
        List<BytesmartTasks> taskByInitiatorList = tasksMapper.getTaskByInitiatorList(bytesmartTasks, employeeId);
        System.out.println(taskByInitiatorList);
        for (BytesmartTasks task : taskByInitiatorList) {
            List<BytesmartTasksAssigned> bytesmartTasksAssignedList = task.getBytesmartTasksAssignedList();
            for (BytesmartTasksAssigned assigned : bytesmartTasksAssignedList){
                Long assignedId = assigned.getAssignedId();
                String assignedName = bytesmartGetEmployeeService.getEmployee(assignedId).getEmployee().getEmployeeName();
                String assignedGender = bytesmartGetEmployeeService.getEmployee(assignedId).getEmployee().getEmployeeGender();
                String assignedDept = bytesmartGetEmployeeService.getEmployee(assignedId).getEmployee().getDept().getDeptName();
//                Long postId = bytesmartGetEmployeeService.getEmployee(assignedId).getEmployee().getPostId();
                assigned.setAssignedName(assignedName);
                assigned.setAssignedGender(assignedGender);
                assigned.setAssigneDept(assignedDept);
                System.out.println(assignedDept);
            }
            String employeeName = bytesmartGetEmployeeService.getEmployee(employeeId).getEmployee().getEmployeeName();
            task.setInitiatorName(employeeName);
            list.add(task);
            System.out.println(task);


        }

        return list;
      }



}

