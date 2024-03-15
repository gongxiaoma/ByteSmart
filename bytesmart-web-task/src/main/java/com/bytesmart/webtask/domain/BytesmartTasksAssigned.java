package com.bytesmart.webtask.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BytesmartTasksAssigned{
    private Long taskId;
    private Long assignedId;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("taskId", getTaskId())
                .append("assignedId", getAssignedId())
                .toString();
    }


}
