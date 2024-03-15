package com.bytesmart.webtask.service.impl;

import com.bytesmart.webtask.domain.BytesmartTasks;
import com.bytesmart.webtask.mapper.BytesmartTasksMapper;
import com.bytesmart.webtask.service.IBytesmartTasksService;
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





}

