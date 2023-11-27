package com.bytesmart.admincenter.mapper;

import com.bytesmart.admincenter.domain.BytesmartEmployeePost;

import java.util.List;

public interface BytesmartEmployeePostMapper {


     //通过用户ID删除用户和岗位关联
    public int deleteEmployeePostByemployeeId(Integer employeeId);


    // 通过岗位ID查询岗位使用数量
    public int countEmployeePostByemployeeId(Integer postId);


    //批量删除用户和岗位关联
    public int deleteEmployeePost(Integer[] employeeIds);


    //批量新增用户岗位信息
    public int batchEmployeePost(List<BytesmartEmployeePost> EmployeePostList);
}
