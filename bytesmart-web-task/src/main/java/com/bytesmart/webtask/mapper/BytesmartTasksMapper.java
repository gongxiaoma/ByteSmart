package com.bytesmart.webtask.mapper;

import com.bytesmart.webtask.domain.BytesmartTasks;

import java.util.List;

public interface BytesmartTasksMapper {
    // 单条查询
    public List<BytesmartTasks> getTaskByInitiatorList(Long employeeId);
}
