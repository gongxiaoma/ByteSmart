package com.bytesmart.webtask.service;

import com.bytesmart.webtask.domain.BytesmartTasks;

import java.util.List;

public interface IBytesmartTasksService {
    //    查询
    public List<BytesmartTasks> selectTaskList(BytesmartTasks tasks);

    //    单条查询
    public BytesmartTasks selectTaskBytaskId(Long taskId);
}
