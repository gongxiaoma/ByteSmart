package com.bytesmart.webtask.controller;

import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.webtask.domain.BytesmartTasks;
import com.bytesmart.webtask.service.IBytesmartTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/task")
public class BytesmartTasksController extends BaseController {

    @Autowired
    private IBytesmartTasksService bytesmartTasksService;

    @GetMapping("/list")
    public TableDataInfo list(BytesmartTasks tasks)
    {
        startPage();
        List<BytesmartTasks> list = bytesmartTasksService.selectTaskList(tasks);
        System.out.println("ABCD");
        return getDataTable(list);
    }

    //@RequiresPermissions("webtask:tasks:query")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable Long taskId)
    {
        return success(bytesmartTasksService.selectTaskBytaskId(taskId));
    }

}
