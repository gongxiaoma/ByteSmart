package com.bytesmart.webtask.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.webtask.domain.BytesmartTasks;

import java.util.List;

public interface IBytesmartTasksService {
    // 单条查询
    public List<BytesmartTasks> getTaskByInitiatorList(BytesmartTasks bytesmartTasks, Long employeeId);

//    public int insertTask(BytesmartTasks bytesmartTasks);





}
