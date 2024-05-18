package com.bytesmart.webtask.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class BytesmartTasksAssigned{
    private Long tId;
    private Long assignedId;
    private String assignedName;
    private String assigneDept;
    private String assignedGender;
    private String assignedPost;



    public Long getTid() {
        return tId;
    }

    public void setTid(Long tId) {
        this.tId = tId;
    }

    public String getAssignedName() {
        return assignedName;
    }

    public void setAssignedName(String assignedName) {
        this.assignedName = assignedName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tId", getTid())
                .append("assignedId", getAssignedId())
                .append("assignedName", getAssignedName())
                .append("assigneDept", getAssigneDept())
                .append("assignedGender", getAssignedGender())
                .append("assignedPost", getAssignedPost())
                .toString();
    }


}
