package com.bytesmart.webadmin.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.SysUser;

import java.util.List;

public interface IBytesmartEmployeeService {
    //查询所有
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee);

    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

    //按userName查询
    public BytesmartEmployee selectEmployeeByUsername(String userName);

    //新增
    public int insertEmployee(BytesmartEmployee employee);

    //修改
    public int updateEmployee(BytesmartEmployee employee);

    //删除

    public int deleteEmployeeById(Long employeeId);

    public int deleteEmployeeByIds(Long[] employeeIds);


    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
    public List<BytesmartEmployee> selectAllocatedList(BytesmartEmployee employee);

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
    public List<BytesmartEmployee> selectUnallocatedList(BytesmartEmployee employee);


    /**
     * 校验用户是否有数据权限
     *
     * @param emloyeeId 用户id
     */
    public void checkEmployeeDataScope(Long emloyeeId);

    /**
     * 注册用户信息
     *
     * @param employee 用户信息
     * @return 结果
     */
    public boolean registerEmployee(BytesmartEmployee employee);


    /**
     * 校验用户名称是否唯一
     *
     * @param employee 用户信息
     * @return 结果
     */
    public boolean checkUserNameUnique(BytesmartEmployee employee);


    /**
     * 校验手机号码是否唯一
     *
     * @param employee 用户信息
     * @return 结果
     */
    public boolean checkPhoneUnique(BytesmartEmployee employee);

    /**
     * 校验email是否唯一
     *
     * @param employee 用户信息
     * @return 结果
     */
    public boolean checkEmailUnique(BytesmartEmployee employee);

    /**
     * 校验用户是否允许操作
     *
     * @param employee 用户信息
     */
    public void checkEmployeeAllowed(BytesmartEmployee employee);

    /**
     * 导入用户数据
     *
     * @param employeeList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importEmployee(List<BytesmartEmployee> employeeList, Boolean isUpdateSupport, String operName);

    /**
     * 重置用户密码
     *
     * @param employee 用户信息
     * @return 结果
     */
    public int resetPwd(BytesmartEmployee employee);


    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetEmployeerPwd(String userName, String password);


    /**
     * 修改用户状态
     *
     * @param employee 用户信息
     * @return 结果
     */
    public int updateEmployeeStatus(BytesmartEmployee employee);

    /**
     * 用户授权角色
     *
     * @param employeeId 用户ID
     * @param roleIds 角色组
     */
    public void insertEmployeeAuth(Long employeeId, Long[] roleIds);

    /**
     * 修改用户基本信息
     *
     * @param employee 用户信息
     * @return 结果
     */
    public int updateEmployeeProfile(BytesmartEmployee employee);

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param employeeAvatar 头像地址
     * @return 结果
     */
    public boolean updateEmployeeAvatar(String userName, String employeeAvatar);

    /**
     * 根据用户ID查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectEmployeePostGroup(String userName);

    /**
     * 根据用户ID查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    public String selectEmployeeRoleGroup(String userName);






}
