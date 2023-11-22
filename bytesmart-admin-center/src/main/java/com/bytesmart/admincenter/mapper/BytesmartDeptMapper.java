package com.bytesmart.admincenter.mapper;

import com.bytesmart.admincenter.domain.BytesmartPost;
import com.bytesmart.apisystem.domain.BytesmartDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BytesmartDeptMapper {
    //查询所有
    public List<BytesmartDept> selectDeptList(BytesmartDept dept);

    //根据id查询
    public BytesmartDept selectDeptById(Integer deptId);

    //修改
    public int updateDept(BytesmartDept dept);

    //增加
    public int insertDept(BytesmartDept dept);

    //删除
    public int deleteDeptByIds(Integer[] deptIds);




}
