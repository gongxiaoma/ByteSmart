package com.bytesmart.apisystem.domain;

import com.bytesmart.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class BytesmartDept extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Long deptWeight;

    /** 负责人 */
    private String deptLeader;

    /** 联系电话 */
    private String deptMobile;

    /** 邮箱 */
    private String deptEmail;

    /** 部门状态:0正常,1停用 */
    private String deptStatus;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


    /** 父部门名称 */
    private String parentName;

    /** 子部门 */
    private List<BytesmartDept> children = new ArrayList<BytesmartDept>();

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getDeptWeight() {
        return deptWeight;
    }

    public void setDeptWeight(Long deptWeight) {
        this.deptWeight = deptWeight;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    public String getDeptMobile() {
        return deptMobile;
    }

    public void setDeptMobile(String deptMobile) {
        this.deptMobile = deptMobile;
    }

    public String getDeptEmail() {
        return deptEmail;
    }

    public void setDeptEmail(String deptEmail) {
        this.deptEmail = deptEmail;
    }

    public String getDeptStatus() {
        return deptStatus;
    }

    public void setDeptStatus(String deptStatus) {
        this.deptStatus = deptStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public List<BytesmartDept> getChildren() {
        return children;
    }

    public void setChildren(List<BytesmartDept> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("parentId", getParentId())
                .append("ancestors", getAncestors())
                .append("deptName", getDeptName())
                .append("deptWeight", getDeptWeight())
                .append("deptLeader", getDeptLeader())
                .append("deptMobile", getDeptMobile())
                .append("deptEmail", getDeptEmail())
                .append("deptStatus", getDeptStatus())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }


}
