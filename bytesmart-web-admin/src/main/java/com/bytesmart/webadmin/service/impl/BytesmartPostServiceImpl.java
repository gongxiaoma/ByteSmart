package com.bytesmart.webadmin.service.impl;


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
    public int deletePostByIds(Long[] postIds){
        return postMapper.deletePostByIds(postIds);
    }

    @Override
    public List<BytesmartPost> selectPostAll(){
        return postMapper.selectPostAll();
    }

    @Override
    public List<Long> selectPostListByEmployeeId(Long employeeId){
        return postMapper.selectPostListByEmployeeId(employeeId);
    }


//    @Override
//    public int countEmployeePostById(Long postId)
//    {
//        return employeePostMapper.countEmployeePostById(postId);
//    }





}
