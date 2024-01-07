package com.bytesmart.webadmin.service;

import com.bytesmart.webadmin.domain.BytesmartPost;

import java.util.List;

public interface IBytesmartPostService {
    //查询所有
    public List<BytesmartPost> selectPostList(BytesmartPost post);

    //根据岗位id查询岗位
    public BytesmartPost selectPostById(Long postId);

    //修改
    public int updatePost(BytesmartPost post);

    //新增
    public int insertPost(BytesmartPost post);

    //删除

    public int deletePostById(Long postId);
    public int deletePostByIds(Long[] postIds);

    //查询所有岗位
    public List<BytesmartPost> selectPostAll();

    //通过用户id获取岗位选择框列表
    public List<Long> selectPostListByEmployeeId(Long employeeId);

    /**
     * 校验岗位名称
     *
     * @param post 岗位信息
     * @return 结果
     */
    public boolean checkPostNameUnique(BytesmartPost post);


    /**
     * 校验岗位类型
     *
     * @param post 岗位信息
     * @return 结果
     */
    public boolean checkPostCodeUnique(BytesmartPost post);

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    public int countEmployeePostById(Long postId);




}
