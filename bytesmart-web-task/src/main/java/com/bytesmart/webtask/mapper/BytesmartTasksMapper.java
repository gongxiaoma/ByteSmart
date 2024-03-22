package com.bytesmart.webtask.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.webtask.domain.BytesmartTasks;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartTasksMapper {
     //根据任务主题查询发起人发起的任务
//     public BytesmartTasks selectInitiatorTaskByTitle(String taskTitle);

    // 单条查询
    public List<BytesmartTasks> getTaskByInitiatorList(@Param("bytesmartTasks") BytesmartTasks bytesmartTasks, @Param("employeeId") Long employeeId);

    //根据任务ID查询被分配人信息
//    public List<BytesmartEmployee> getAssignedList(BytesmartEmployee bytesmartEmployee, Long taskId);
}
