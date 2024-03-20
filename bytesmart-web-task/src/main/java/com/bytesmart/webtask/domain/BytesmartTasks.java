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
    private Long taskProgress;
    private Long status;
    private Long initiatorId;
    private String initiatorName;
    private String assigenName;
    private LocalDate enddate;
    private LocalDate startdate;
    private LocalDate taskAf;
    private LocalDate updateTime;
    private LocalDate createTime;
    private String remark;


    // 描述当前任务分配哪些员工，后面删除
    private List<BytesmartEmployee> employeeList;

    private List<BytesmartTasksAssigned> bytesmartTasksAssignedList;

    public List<BytesmartTasksAssigned> getBytesmartTasksAssignedList() {
        return bytesmartTasksAssignedList;
    }

    public void setBytesmartTasksAssignedList(List<BytesmartTasksAssigned> bytesmartTasksAssignedList) {
        this.bytesmartTasksAssignedList = bytesmartTasksAssignedList;
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

    public Long getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(Long taskProgress) {
        this.taskProgress = taskProgress;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(Long initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getAssigenName() {
        return assigenName;
    }

    public void setAssigenName(String assigenName) {
        this.assigenName = assigenName;
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
                .append("initiatorName", getInitiatorName())
                .append("assigenName", getAssigenName())
                .append("enddate",getEnddate())
                .append("startdate",getStartdate())
                .append("taskAf",getTaskAf())
                .append("updateTime", getUpdateTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("bytesmartTasksAssignedList",getBytesmartTasksAssignedList())
                .toString();
    }

}
