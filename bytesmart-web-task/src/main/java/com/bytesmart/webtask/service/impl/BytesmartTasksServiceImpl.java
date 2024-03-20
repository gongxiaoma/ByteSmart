package com.bytesmart.webtask.service.impl;

import com.bytesmart.apisystem.model.LoginEmployee;
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
        for (BytesmartTasks task : taskByInitiatorList) {
            String employeeName = bytesmartGetEmployeeService.getEmployee(employeeId).getEmployee().getEmployeeName();
            task.setInitiatorName(employeeName);
            list.add(task);
        }
        return list;
      }

    public BytesmartTasks selectInitiatorTaskByTitle(String taskTitle){
        return tasksMapper.selectInitiatorTaskByTitle(taskTitle);
    }

}

