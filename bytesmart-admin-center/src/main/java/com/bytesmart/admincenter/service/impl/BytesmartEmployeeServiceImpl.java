package com.bytesmart.admincenter.service.impl;

import com.bytesmart.admincenter.domain.BytesmartEmployeePost;
import com.bytesmart.admincenter.domain.BytesmartEmployeeRole;
import com.bytesmart.admincenter.mapper.*;
import com.bytesmart.admincenter.service.IBytesmartEmployeeService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.datascope.annotation.DataScope;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class BytesmartEmployeeServiceImpl implements IBytesmartEmployeeService {
    private static final Logger log = LoggerFactory.getLogger(BytesmartEmployeeServiceImpl.class);

    @Autowired
    private BytesmartEmployeeMapper employeeMapper;

//    @Autowired
//    private BytesmartRoleMapper roleMapper;

//    @Autowired
//    private BytesmartPostMapper postMapper;

    @Autowired
    private BytesmartEmployeeRoleMapper employeeRoleMapper;

    @Autowired
    private BytesmartEmployeePostMapper employeePostMapper;


    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee)
    {
        return employeeMapper.selectEmployeeList(employee);
    }

    @Override
    public BytesmartEmployee selectEmployeeById(Integer emloyeeId ){
        return employeeMapper.selectEmployeeById(emloyeeId);
    }


   @Override
   @Transactional(rollbackFor = Exception.class)
   public int insertEmployee(BytesmartEmployee employee)
   {
       // 新增用户信息
       int rows = employeeMapper.insertEmployee(employee);
       // 新增用户岗位关联
       insertEmployeePost(employee);
       // 新增用户与角色管理
       insertEmployeeRole(employee);
       //返回类型为int,需要和方法体的返回类型一样
       return rows;
   }

    //新增用户岗位关联
    public void insertEmployeePost(BytesmartEmployee employee)
    {
        // 取前端传来PostIds值(调用employee对象的getPostIds方法，并将返回的结果赋值给posts数组)
        Integer[] posts = employee.getPostIds();
        if (StringUtils.isNotEmpty(posts))
        {
           // 初始化一个ArrayList集合,使用new关键字创建了一个新的ArrayList<BytesmartEmployeePost>对象，并将其分配给list变量,它的类型是List<BytesmartEmployeePost>即是包含BytesmartEmployeePost对象的列表
            List<BytesmartEmployeePost> list = new ArrayList<BytesmartEmployeePost>();
            //增强型for循环(一个for-each循环)来遍历posts集合,对于posts集合中的每一个元素（这里命名为postId），执行一次循环体内的代码。
            for (Integer postId : posts)
            {
                BytesmartEmployeePost bp = new BytesmartEmployeePost();

                Integer emloyeeId = employee.getEmployeeId();
                bp.setEmployeeId(employee.getEmployeeId());
                bp.setPostId(postId);
                list.add(bp);
            }
            employeePostMapper.batchEmployeePost(list);
        }
    }

    public void insertEmployeeRole(BytesmartEmployee employee){

        //取前端传来RoleIds值,调用employee对象的getRoleIds方法，并将返回的结果赋值给roles数组
        Integer[] roles = employee.getRoleIds();
        if (StringUtils.isNotEmpty(roles)){
            List<BytesmartEmployeeRole> list = new ArrayList<>();
            for (Integer roleId : roles){
                BytesmartEmployeeRole br = new BytesmartEmployeeRole();

                Integer emloyeeId = employee.getEmployeeId();
                br.setEmployeeId(emloyeeId);
                br.setRoleId(roleId);
                list.add(br);
            }
            employeeRoleMapper.batchEmployeeRole(list);
        }
    }

    //修改
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEmployee(BytesmartEmployee employee){
        //取个用户id值
        Integer emloyeeId = employee.getEmployeeId();

        //删除用户和岗位关联
        employeePostMapper.deleteEmployeePostByemployeeId(emloyeeId);
        //新增用户和岗位关联
        insertEmployeePost(employee);

        //删除用户和角色关联
        employeeRoleMapper.deleteEmployeeRoleByEmployeeId(emloyeeId);
        //新增用户和岗位关联
        insertEmployeeRole(employee);
        return employeeMapper.updateEmployee(employee);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmployeeByIds(Integer[] employeeIds){
        //删除用户和角色关联
        employeeRoleMapper.deleteEmployeeRole(employeeIds);
        //删除用户和角色关联
        employeePostMapper.deleteEmployeePost(employeeIds);
        return employeeMapper.deleteEmployeeByIds(employeeIds);

    }

















}
