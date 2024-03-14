package com.bytesmart.webadmin.service.impl;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.webadmin.domain.BytesmartPost;
import com.bytesmart.webadmin.domain.BytesmartTasks;
import com.bytesmart.webadmin.mapper.BytesmartTasksMapper;
import com.bytesmart.webadmin.service.IBytesmartTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BytesmartTasksServiceImpl implements IBytesmartTasksService {

    @Autowired
    private BytesmartTasksMapper tasksMapper;

    @Override
    public List<BytesmartTasks> selectTaskList(BytesmartTasks tasks){
        return tasksMapper.selectTaskList(tasks);
    }

    @Override
    public BytesmartTasks selectTaskBytaskId(Long taskId){
        return tasksMapper.selectTaskBytaskId(taskId);
    }

    @Override
    public BytesmartTasks selectTaskByTaskTitle(String taskTitle){
        return tasksMapper.selectTaskByTaskTitle(taskTitle);
    }

//    @Override
//    public BytesmartTasks selectTaskByinitiatorId(Long taskId){
//        return tasksMapper.selectTaskBytaskId(taskId);
//    }


}
