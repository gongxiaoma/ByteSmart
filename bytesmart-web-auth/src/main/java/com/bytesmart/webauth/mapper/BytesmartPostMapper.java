package com.bytesmart.webauth.mapper;

import com.bytesmart.webauth.domain.BytesmartPost;

import java.util.List;

public interface BytesmartPostMapper {


    //查询所有
    public List<BytesmartPost> selectPostList(BytesmartPost post);

    public List<BytesmartPost> selectPostAll();

    //根据id查询
    public BytesmartPost selectPostById(Long postId);


    //通过用户id获取岗位选择框列表
    public List<Long> selectPostListByEmployeeId(Long employeeId);

    //通过用户名查所属岗位
    public List<BytesmartPost> selectPostListByUserName(String username);



}
