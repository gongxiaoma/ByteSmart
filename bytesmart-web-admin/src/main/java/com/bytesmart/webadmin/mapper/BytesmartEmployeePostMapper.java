package com.bytesmart.webadmin.mapper;

import com.bytesmart.webadmin.domain.BytesmartEmployeePost;

import java.util.List;

public interface BytesmartEmployeePostMapper {


     //通过用户ID删除用户和岗位关联
    public int deleteEmployeePostByemployeeId(Long employeeId);


    // 通过岗位ID查询岗位使用数量
    public int countEmployeePostById(Long postId);


    //批量删除用户和岗位关联
    public int deleteEmployeePost(Long[] employeeIds);


    //批量新增用户岗位信息
    public int batchEmployeePost(List<BytesmartEmployeePost> EmployeePostList);
}
