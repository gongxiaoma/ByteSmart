package com.bytesmart.webadmin.service.impl;


import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webadmin.domain.BytesmartPost;
import com.bytesmart.webadmin.mapper.BytesmartEmployeePostMapper;
import com.bytesmart.webadmin.mapper.BytesmartPostMapper;
import com.bytesmart.webadmin.service.IBytesmartPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BytesmartPostServiceImpl implements IBytesmartPostService {

    @Autowired
    private BytesmartPostMapper postMapper;

    @Autowired
    private BytesmartEmployeePostMapper employeePostMapper;

    @Override
    public List<BytesmartPost> selectPostList(BytesmartPost post){
        return postMapper.selectPostList(post);
    }

    @Override
    public BytesmartPost selectPostById(Long postId){
        return postMapper.selectPostById(postId);
    }


    @Override
    public int updatePost(BytesmartPost post)
    {
        return postMapper.updatePost(post);
    }


    @Override
    public int insertPost(BytesmartPost post){
        return postMapper.insertPost(post);
    }

    @Override
    public int deletePostById(Long postId)
    {
        return postMapper.deletePostById(postId);
    }


    @Override
    public List<BytesmartPost> selectPostAll(){
        return postMapper.selectPostAll();
    }

    //根据用户ID获取岗位选择框列表
    @Override
    public List<Long> selectPostListByEmployeeId(Long employeeId){
        return postMapper.selectPostListByEmployeeId(employeeId);
    }


    //通过岗位ID查询岗位使用数量
    @Override
    public int countEmployeePostById(Long postId)
    {
        return employeePostMapper.countEmployeePostById(postId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     */
    @Override
    public int deletePostByIds(Long[] postIds)
    {
        for (Long postId : postIds)
        {
            BytesmartPost post = selectPostById(postId);
            if (countEmployeePostById(postId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        return postMapper.deletePostByIds(postIds);
    }



    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostNameUnique(BytesmartPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        BytesmartPost info = postMapper.checkPostNameUnique(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验岗位类型是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public boolean checkPostCodeUnique(BytesmartPost post)
    {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        BytesmartPost info = postMapper.checkPostCodeUnique(post.getPostType());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }




}
