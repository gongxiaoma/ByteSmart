package com.bytesmart.admincenter.service;

import com.bytesmart.admincenter.domain.BytesmartPost;

import java.util.List;

public interface IBytesmartPostService {
    //查询所有
    public List<BytesmartPost> selectPostList(BytesmartPost post);

    //根据id查询
    public BytesmartPost selectPostById(Integer postId);

    //修改
    public int updatePost(BytesmartPost post);

    //新增
    public int insertPost(BytesmartPost post);

    //删除
    public int deletePostByIds(Long[] postIds);

    public List<BytesmartPost> selectPostAll();

    //通过用户id获取岗位选择框列表
    public List<Integer> selectPostListByEmployeeId(Integer employeeId);


}
