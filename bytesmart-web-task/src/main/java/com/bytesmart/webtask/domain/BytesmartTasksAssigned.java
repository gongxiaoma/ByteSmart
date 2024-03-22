package com.bytesmart.webtask.domain;

import com.bytesmart.common.core.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class BytesmartTasksAssigned{
    private Long taskId;
    private Long assignedId;
    private String assignedName;
    private String assigneDept;
    private String assignedGender;
    private String assignedPost;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date accessTime;



    public String getAssignedName() {
        return assignedName;
    }
    public void setAssignedName(String assignedName) {
        this.assignedName = assignedName;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getAssignedId() {
        return assignedId;
    }

    public void setAssignedId(Long assignedId) {
        this.assignedId = assignedId;
    }

    public String getAssigneDept() {
        return assigneDept;
    }

    public void setAssigneDept(String assigneDept) {
        this.assigneDept = assigneDept;
    }

    public String getAssignedGender() {
        return assignedGender;
    }

    public void setAssignedGender(String assignedGender) {
        this.assignedGender = assignedGender;
    }

    public String getAssignedPost() {
        return assignedPost;
    }

    public void setAssignedPost(String assignedPost) {
        this.assignedPost = assignedPost;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("taskId", getTaskId())
                .append("assignedId", getAssignedId())
                .append("assignedName", getAssignedName())
                .append("assigneDept", getAssigneDept())
                .append("assignedGender", getAssignedGender())
                .append("assignedPost", getAssignedPost())
                .append("accessTime", getAccessTime())
                .toString();
    }


}
