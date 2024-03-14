package com.bytesmart.webadmin.controller;

import com.bytesmart.apisystem.domain.BytesmartDept;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.security.annotation.InnerAuth;
import com.bytesmart.common.security.annotation.RequiresPermissions;
import com.bytesmart.webadmin.domain.BytesmartTasks;
import com.bytesmart.webadmin.service.IBytesmartTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class BytesmartTasksContoller extends BaseController {

    @Autowired
    private IBytesmartTasksService tasksService;

//    @RequiresPermissions("webadmin:task:list ")
    @GetMapping("/list")
    public AjaxResult list(BytesmartTasks tasks)
    {
        List<BytesmartTasks> list = tasksService.selectTaskList(tasks);
        return success(list);
    }

    //@RequiresPermissions("webadmin:task:query")
    @GetMapping(value = { "/", "/{taskId}" })
    public AjaxResult getInfo(@PathVariable Long taskId)
    {
        return success(tasksService.selectTaskBytaskId(taskId));
    }

    @InnerAuth
    @GetMapping(value = { "/", "/{taskTitle}" })
    public AjaxResult getInfo(@PathVariable String taskTitle)
    {
        return success(tasksService.selectTaskByTaskTitle(taskTitle));
    }

//    @InnerAuth
//    @GetMapping(value = { "/", "/{taskTitle}" })
//    public AjaxResult getInfo(@PathVariable Long initiatorId)
//    {
//        return success(tasksService.selectTaskByinitiatorId(initiatorId));
//    }


}
