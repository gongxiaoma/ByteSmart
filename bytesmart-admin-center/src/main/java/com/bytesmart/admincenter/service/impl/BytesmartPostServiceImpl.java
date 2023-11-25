package com.bytesmart.admincenter.service.impl;

import com.bytesmart.admincenter.domain.BytesmartEmployeePost;
import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.admincenter.mapper.BytesmartPostMapper;
import com.bytesmart.admincenter.service.IBytesmartPostService;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BytesmartPostServiceImpl implements IBytesmartPostService {

    @Autowired
    private BytesmartPostMapper postMapper;

//    @Autowired
//    private BytesmartEmployeePost bytesmartEmployeePost;

    @Override
    public List<BytesmartPost> selectPostList(BytesmartPost post){
        return postMapper.selectPostList(post);
    }

    @Override
    public BytesmartPost selectPostById(Integer postId){
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
    public int deletePostByIds(Long[] postIds){
        return postMapper.deletePostByIds(postIds);
    }

    @Override
    public List<BytesmartPost> selectPostAll(){
        return postMapper.selectPostAll();
    }


    @Override
    public List<Integer> selectPostListByEmployeeId(Integer employeeId)
    {
        return postMapper.selectPostListByEmployeeId(employeeId);
    }




}
