package com.bytesmart.webauth.service;

import com.bytesmart.webauth.domain.BytesmartMenu;
import com.bytesmart.webauth.domain.BytesmartPost;
import com.bytesmart.webauth.domain.vo.RouterVo;

import java.util.List;
import java.util.Set;

public interface IBytesmartPostService {

    //查询所有
    public List<BytesmartPost> selectPostList(BytesmartPost post);

    //根据岗位id查询岗位
    public BytesmartPost selectPostById(Long postId);

    //查询所有岗位
    public List<BytesmartPost> selectPostAll();

    //通过用户id获取岗位选择框列表
    public List<Long> selectPostListByEmployeeId(Long employeeId);


}
