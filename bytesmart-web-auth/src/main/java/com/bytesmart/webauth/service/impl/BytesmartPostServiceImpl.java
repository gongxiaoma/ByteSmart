package com.bytesmart.webauth.service.impl;

import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webauth.domain.BytesmartPost;
import com.bytesmart.webauth.mapper.BytesmartPostMapper;
import com.bytesmart.webauth.mapper.BytesmartRoleMapper;
import com.bytesmart.webauth.service.IBytesmartPostService;
import com.bytesmart.webauth.service.IBytesmartRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BytesmartPostServiceImpl implements IBytesmartPostService {

    @Autowired
    private BytesmartPostMapper postMapper;


    @Override
    public List<BytesmartPost> selectPostList(BytesmartPost post){
        return postMapper.selectPostList(post);
    }

    @Override
    public BytesmartPost selectPostById(Long postId){
        return postMapper.selectPostById(postId);
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







}
