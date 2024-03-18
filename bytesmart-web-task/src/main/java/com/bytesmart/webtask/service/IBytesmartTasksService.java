package com.bytesmart.webtask.service;

import com.bytesmart.webtask.domain.BytesmartTasks;

import java.util.List;

public interface IBytesmartTasksService {
    // 单条查询
    public List<BytesmartTasks> getTaskByInitiatorList(Long employeeId);


}
