package com.bytesmart.webadmin.mapper;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartEmployeeMapper {
    //查询所有
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee);

    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

    //按username查询
    public BytesmartEmployee selectEmployeeByUsername(String userName);

    //新增
    public int insertEmployee(BytesmartEmployee employee);

    //修改
    public int updateEmployee(BytesmartEmployee employee);

    //删除
    public int deleteEmployeeByIds(Long[] employeeIds);

    public int deleteEmployeeById(Long employeeId);

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
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    public BytesmartEmployee checkUserNameUnique(String userName);


    /**
     * 校验手机号码是否唯一
     *
     * @param employeeMobile 手机号码
     * @return 结果
     */
    public BytesmartEmployee checkPhoneUnique(String employeeMobile);


    /**
     * 校验email是否唯一
     *
     * @param employeeEmail 用户邮箱
     * @return 结果
     */
    public BytesmartEmployee checkEmailUnique(String employeeEmail);


    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    public int resetEmployeerPwd(@Param("userName") String userName, @Param("password") String password);


    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param employeeAvatar 头像地址
     * @return 结果
     */
    public int updateEmployeeAvatar(@Param("userName") String userName, @Param("employeeAvatar") String employeeAvatar);






}
