package com.bytesmart.webadmin.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.webadmin.domain.BytesmartTasks;

import java.util.List;

public interface BytesmartTasksMapper {

//    查询所有
    public List<BytesmartTasks> selectTaskList(BytesmartTasks tasks);

//    public List<BytesmartTasks> selectTaskAll();

//    根据id查询
    public BytesmartTasks selectTaskBytaskId(Long taskId);

//    按任务主题查询
    public BytesmartTasks selectTaskByTaskTitle(String taskTitle);

//    按发起人ID查询
    public BytesmartTasks selectTaskByinitiatorId(Long initiatorId);

//    更新被分配的任务
//    public int updateTask(BytesmartTasks tasks);

    //通过用发起人ID查任务
//    public List<BytesmartPost> selectTaskListByInitiatorId(Long initiatorId);


}
