package com.bytesmart.webadmin.service;

import com.bytesmart.webadmin.domain.BytesmartTasks;

import java.util.List;

public interface IBytesmartTasksService {
    //查询
    public List<BytesmartTasks> selectTaskList(BytesmartTasks tasks);

    //单条查询
    public BytesmartTasks selectTaskBytaskId(Long taskId);

    //按任务主题查询
    public BytesmartTasks selectTaskByTaskTitle(String taskTitle);

    //按发起人ID查询
//    public BytesmartTasks selectTaskByinitiatorId(Long initiatorId);


}
