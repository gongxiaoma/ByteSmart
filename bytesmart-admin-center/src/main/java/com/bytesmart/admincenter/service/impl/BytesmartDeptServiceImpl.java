package com.bytesmart.admincenter.service.impl;

import com.bytesmart.admincenter.mapper.BytesmartDeptMapper;
import com.bytesmart.admincenter.service.IBytesmartDeptService;
import com.bytesmart.apisystem.domain.BytesmartDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BytesmartDeptServiceImpl implements IBytesmartDeptService {

    @Autowired
    private BytesmartDeptMapper bytesmartDeptMapper;

    @Override
    public List<BytesmartDept> selectDeptList(BytesmartDept dept){
        return bytesmartDeptMapper.selectDeptList(dept);
    }

    @Override
    public BytesmartDept selectDeptById(Integer deptId){
        return bytesmartDeptMapper.selectDeptById(deptId);
    }

    @Override
    public int updateDept(BytesmartDept dept){
        return bytesmartDeptMapper.updateDept(dept);
    }

    @Override
    public int insertDept(BytesmartDept dept){
        return bytesmartDeptMapper.insertDept(dept);
    }

    @Override
    public int deleteDeptByIds(Integer[] deptIds){
        return bytesmartDeptMapper.deleteDeptByIds(deptIds);
    }



}
