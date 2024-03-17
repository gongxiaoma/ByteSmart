package com.bytesmart.webtask.service.impl;

import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.webtask.domain.BytesmartTasks;
import com.bytesmart.webtask.domain.BytesmartTasksAssigned;
import com.bytesmart.webtask.mapper.BytesmartTasksMapper;
import com.bytesmart.webtask.service.IBytesmartTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BytesmartTasksServiceImpl implements IBytesmartTasksService {

    @Autowired
    private BytesmartTasksMapper tasksMapper;

    @Autowired
    private BytesmartGetEmployeeService bytesmartGetEmployeeService;

    @Override
    public BytesmartTasks selectTaskByInitiator(Long employeeId){
        BytesmartTasks bytesmartTasks = tasksMapper.selectTaskByInitiator(employeeId);
        String employeeName = bytesmartGetEmployeeService.getEmployee(employeeId).getEmployee().getEmployeeName();
        bytesmartTasks.setInitiatorName(employeeName);
        return bytesmartTasks;
    }
}

