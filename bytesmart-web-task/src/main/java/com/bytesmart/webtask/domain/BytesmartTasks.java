package com.bytesmart.webtask.domain;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.List;

public class BytesmartTasks{
    private static final long serialVersionUID = 1L;

    private Long taskId;
    private String taskTitle;
    private String taskDescr;
    private String taskProgress;
    private String status;
    private Long initiatorId;
    private LocalDate enddate;
    private LocalDate startdate;
    private LocalDate taskAf;

    private LocalDate updateTime;
    private LocalDate createTime;
    private String remark;


    // 描述当前任务分配哪些员工
    private List<BytesmartEmployee> employeeList;

    // 员工组
    private Long[] employeeIds;

    public List<BytesmartEmployee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<BytesmartEmployee> employeeList) {
        this.employeeList = employeeList;
    }

    public Long[] getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(Long[] employeeIds) {
        this.employeeIds = employeeIds;
    }


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescr() {
        return taskDescr;
    }

    public void setTaskDescr(String taskDescr) {
        this.taskDescr = taskDescr;
    }

    public String getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(String taskProgress) {
        this.taskProgress = taskProgress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
        this.initiatorId = initiatorId;
    }

    public LocalDate getEnddate() {
        return enddate;
    }

    public void setEnddate(LocalDate enddate) {
        this.enddate = enddate;
    }

    public LocalDate getStartdate() {
        return startdate;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public LocalDate getTaskAf() {
        return taskAf;
    }

    public void setTaskAf(LocalDate taskAf) {
        this.taskAf = taskAf;
    }

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("taskId", getTaskId())
                .append("taskTitle", getTaskTitle())
                .append("taskDescr", getTaskDescr())
                .append("status", getStatus())
                .append("initiatorId", getInitiatorId())
                .append("enddate",getEnddate())
                .append("startdate",getStartdate())
                .append("taskAf",getTaskAf())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("employeeList", getEmployeeList())
                .append("employeeIds", getEmployeeIds())
                .toString();
    }


}
