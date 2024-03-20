package com.bytesmart.webtask.service;

import com.bytesmart.webtask.domain.BytesmartTasks;

import java.util.List;

public interface IBytesmartTasksService {
    // 单条查询
    public List<BytesmartTasks> getTaskByInitiatorList(BytesmartTasks bytesmartTasks, Long employeeId);

    //根据任务主题查询发起人发起的任务
    public BytesmartTasks selectInitiatorTaskByTitle(String taskTitle);


}
