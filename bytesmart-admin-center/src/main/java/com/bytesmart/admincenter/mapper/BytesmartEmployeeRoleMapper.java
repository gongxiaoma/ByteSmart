package com.bytesmart.admincenter.mapper;

import com.bytesmart.admincenter.domain.BytesmartEmployeeRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartEmployeeRoleMapper {

    //通过用户ID删除用户和角色关联
    public int deleteEmployeeRoleByEmployeeId(Integer EmployeeId);


    //批量删除用户和角色关联
    public int deleteEmployeeRole(Integer[] ids);


    //通过角色ID查询角色使用数量
    public int countEmployeeRoleByRoleId(Integer roleId);


    //批量新增用户角色信息
    public int batchEmployeeRole(List<BytesmartEmployeeRole> EmployeeRoleList);


    //删除用户和角色关联信息
    public int deleteEmployeeRoleListInfo(BytesmartEmployeeRole EmployeeRole);

    /**
     * 批量取消授权用户角色
     *
     * @param roleId 角色ID
     * @param employeeIds 需要删除的用户数据ID
     * @return 结果
     */
    public int deleteEmployeeRoleInfos(@Param("roleId") Integer roleId, @Param("employeeIds") Integer[] employeeIds);


}
