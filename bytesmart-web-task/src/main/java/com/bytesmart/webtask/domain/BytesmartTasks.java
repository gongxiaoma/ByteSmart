package com.bytesmart.webtask.domain;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.annotation.Excel;
import com.bytesmart.common.core.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDate;
import java.util.Date;
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


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date enddate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startdate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date taskAf;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String remark;

    // 描述当前任务分配哪些员工，后面删除
    private List<BytesmartEmployee> employeeList;

    private List<BytesmartTasksAssigned> bytesmartTasksAssignedList;

    private Long assignedId;

    private Long[] assignedIds;


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

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getTaskAf() {
        return taskAf;
    }

    public void setTaskAf(Date taskAf) {
        this.taskAf = taskAf;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public List<BytesmartTasksAssigned> getBytesmartTasksAssignedList() {
        return bytesmartTasksAssignedList;
    }

    public void setBytesmartTasksAssignedList(List<BytesmartTasksAssigned> bytesmartTasksAssignedList) {
        this.bytesmartTasksAssignedList = bytesmartTasksAssignedList;
    }


    public Long getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(Long assignedId) {
        this.assignedId = assignedId;
    }

    public Long[] getAssignedIds() {
        return assignedIds;
    }

    public void setAssignedIds(Long[] assignedIds) {
        this.assignedIds = assignedIds;
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
