package com.bytesmart.webtask.controller;

import com.bytesmart.apisystem.RemoteEmployee;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.SysUser;
import com.bytesmart.apisystem.model.LoginEmployee;
import com.bytesmart.apisystem.model.LoginUser;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.constant.SecurityConstants;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.common.core.enums.UserStatus;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.core.web.page.TableDataInfo;
import com.bytesmart.springsecurity.utils.WebSecurityUtils;
import com.bytesmart.webtask.domain.BytesmartTasks;
import com.bytesmart.webtask.service.IBytesmartTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    RemoteEmployee remoteEmployee;

    // （先做这个）这个方法是根据用户id查询自己发起的任务，用于用户在前端打开“发起的”页面获取自己被发起的任务，这个不设计到中间表


    //@RequiresPermissions("webtask:tasks:query")
    @GetMapping("/initiator")
    public TableDataInfo getTaskByInitiatorList(BytesmartTasks bytesmartTasks)
    {
        startPage();
//        Long employeeId = 25L;
        Long employeeId = WebSecurityUtils.getUserId();
        List<BytesmartTasks> list = bytesmartTasksService.getTaskByInitiatorList(bytesmartTasks, employeeId);
        return getDataTable(list);
    }


    // 这个方法是根据用户id查询自己被指派的任务，用于用户在前端打开“被指派”页面获取自己被指派的任务，这个涉及到中间表
    //@RequiresPermissions("webtask:tasks:query")
//    @GetMapping("/assigned")
//    public AjaxResult getTaskByAssignedList()
//    {
//        Long employeeId = 24L;
////        Long employeeId = WebSecurityUtils.getUserId();
//        return success(bytesmartTasksService.selectTaskBytaskId(employeeId));
//    }



}
