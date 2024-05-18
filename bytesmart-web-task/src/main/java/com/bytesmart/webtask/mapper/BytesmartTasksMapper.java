package com.bytesmart.webtask.mapper;

import com.bytesmart.webtask.domain.BytesmartTasks;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartTasksMapper {

    // 查询
    public List<BytesmartTasks> getTaskByInitiatorList(@Param("bytesmartTasks") BytesmartTasks bytesmartTasks, @Param("employeeId") Long employeeId);
    /*
    *
    * 发起人 新增任务*/
    public int insertTask(BytesmartTasks bytesmartTasks);


}
