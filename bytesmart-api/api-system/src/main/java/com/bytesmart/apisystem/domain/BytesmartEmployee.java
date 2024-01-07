package com.bytesmart.apisystem.domain;

import com.bytesmart.common.core.annotation.Excel;
import com.bytesmart.common.core.annotation.Excels;
import com.bytesmart.common.core.web.domain.BaseEntity;
import com.bytesmart.common.core.annotation.Excel.ColumnType;
import com.bytesmart.common.core.annotation.Excel.Type;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

public class BytesmartEmployee extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户序号", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long employeeId ;

    /** 部门ID */
    @Excel(name = "部门编号", type = Type.IMPORT)
    private Long deptId;

    /** 用户权重 */
    @Excel(name = "用户权重", type = Type.IMPORT)
    private Long employeeWeight;

    /** 用户工号 */
    @Excel(name = "用户工号", type = Type.IMPORT)
    private Long employeeNo;

    /** 用户年龄 */
    @Excel(name = "用户年龄", type = Type.IMPORT)
    private Long employeeAge;

    /** 用户账号 */
    @Excel(name = "登录名称")
    private String userName;

    /** 密码 */
    private String password;

    /** 用户名*/
    @Excel(name = "用户名")
    private String employeeName;

    /** 英文名*/
    @Excel(name = "用户英文名")
    private String employeeenglishName;

    /** 性别*/
    @Excel(name = "性别", readConverterExp = "0=男,1=女,2=未知")
    private String employeeGender;

    /** 是否已婚*/
    @Excel(name = "是否已婚")
    private String employeeIsmarital;

    /** 用户类型 */
    @Excel(name = "用户类型")
    private String employeeType;

    /** 用户邮箱 */
    @Excel(name = "用户邮箱")
    private String employeeEmail;

    /** 办公电话 */
    @Excel(name = "办公电话")
    private String officePhone;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String employeeMobile;

    /** 用户地址*/
    @Excel(name = "用户地址")
    private String employeeAddress;

    /** 用户头像 */
    private String employeeAvatar;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String employeeStatus;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 最后登录IP */
    @Excel(name = "最后登录IP", type = Type.EXPORT)
    private String loginIp;

    /** 最后登录时间 */
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date loginDate;

    /** 出生年月 */
    @Excel(name = "出生年月", width = 30, dateFormat = "yyyy-MM-dd", type = Type.EXPORT)
    private Date employeeBirthdate ;

    /** 入职时间 */
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd", type = Type.EXPORT)
    private Date employmentDate ;


    /** 部门对象 */
    @Excels({
            @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
            @Excel(name = "部门负责人", targetAttr = "deptLeader", type = Type.EXPORT)
    })
    private BytesmartDept dept;

    /** 角色对象 */
    private List<BytesmartRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    /** 角色ID */
    private Long roleId;
    /** 岗位 */
    private Long postId;


    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    public boolean isAdmin()
    {
        return isAdmin(this.employeeId);
    }

    public static boolean isAdmin(Long employeeId)
    {
        return employeeId != null && 1 == employeeId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getEmployeeWeight() {
        return employeeWeight;
    }

    public void setEmployeeWeight(Long employeeWeight) {
        this.employeeWeight = employeeWeight;
    }

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    public Long getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Long employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeenglishName() {
        return employeeenglishName;
    }

    public void setEmployeeenglishName(String employeeenglishName) {
        this.employeeenglishName = employeeenglishName;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public String getEmployeeIsmarital() {
        return employeeIsmarital;
    }

    public void setEmployeeIsmarital(String employeeIsmarital) {
        this.employeeIsmarital = employeeIsmarital;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getEmployeeAvatar() {
        return employeeAvatar;
    }

    public void setEmployeeAvatar(String employeeAvatar) {
        this.employeeAvatar = employeeAvatar;
    }

    public String getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getEmployeeBirthdate() {
        return employeeBirthdate;
    }

    public void setEmployeeBirthdate(Date employeeBirthdate) {
        this.employeeBirthdate = employeeBirthdate;
    }

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public BytesmartDept getDept() {
        return dept;
    }

    public void setDept(BytesmartDept dept) {
        this.dept = dept;
    }

    public List<BytesmartRole> getRoles() {
        return roles;
    }

    public void setRoles(List<BytesmartRole> roles) {
        this.roles = roles;
    }


    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long[] getPostIds() {
        return postIds;
    }

    public void setPostIds(Long[] postIds) {
        this.postIds = postIds;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("employeeId", getEmployeeId())
                .append("deptId", getDeptId())
                .append("userName", getUserName())
                .append("employeeName", getEmployeeName())
                .append("password",getPassword())
                .append("employeeEmail", getEmployeeEmail())
                .append("employeeMobile", getEmployeeMobile())
                .append("officePhone", getOfficePhone())
                .append("employeeGender", getEmployeeGender())
                .append("employeeWeight", getEmployeeWeight())
                .append("employeeNo", getEmployeeNo())
                .append("employeeAge", getEmployeeAge())
                .append("employeeType", getEmployeeType())
                .append("employeeAddress", getEmployeeAddress())
                .append("employeeType", getEmployeeType())
                .append("employeeIsmarital", getEmployeeIsmarital())
                .append("employeeenglishName", getEmployeeenglishName())
                .append("employmentDate", getEmploymentDate())
                .append("employeeBirthdate", getEmployeeBirthdate())
                .append("employeeStatus", getEmployeeStatus())
                .append("employeeAvatar", getEmployeeAvatar())
                .append("delFlag", getDelFlag())
                .append("loginIp", getLoginIp())
                .append("loginDate", getLoginDate())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("dept", getDept())
                .toString();
    }
}
