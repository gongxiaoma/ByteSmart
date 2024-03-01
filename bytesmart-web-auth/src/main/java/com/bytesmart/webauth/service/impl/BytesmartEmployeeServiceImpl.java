package com.bytesmart.webauth.service.impl;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.webauth.domain.BytesmartEmployeePost;
import com.bytesmart.webauth.domain.BytesmartEmployeeRole;
import com.bytesmart.webauth.domain.BytesmartPost;
import com.bytesmart.webauth.mapper.*;
import com.bytesmart.webauth.service.IBytesmartEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BytesmartEmployeeServiceImpl implements IBytesmartEmployeeService {
    private static final Logger log = LoggerFactory.getLogger(BytesmartEmployeeServiceImpl.class);

    @Autowired
    private BytesmartEmployeeMapper employeeMapper;

    @Autowired
    private BytesmartPostMapper postMapper;

    @Autowired
    private BytesmartRoleMapper roleMapper;

    @Autowired
    private BytesmartEmployeeRoleMapper employeeRoleMapper;

    @Autowired
    private BytesmartEmployeePostMapper employeePostMapper;




    @Override
    public BytesmartEmployee selectEmployeeById(Long emloyeeId){
        return employeeMapper.selectEmployeeById(emloyeeId);
    }

    @Override
    public BytesmartEmployee selectEmployeeByUsername(String userName){
        return employeeMapper.selectEmployeeByUsername(userName);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectEmployeeRoleGroup(String userName)
    {
        List<BytesmartRole> list = roleMapper.selectRolesByUserName(userName);
        if (CollectionUtils.isEmpty(list))
        {
            return StringUtils.EMPTY;
        }
        return list.stream().map(BytesmartRole::getRoleName).collect(Collectors.joining(","));
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectEmployeePostGroup(String userName)
    {
        List<BytesmartPost> list = postMapper.selectPostListByUserName(userName);
        if (CollectionUtils.isEmpty(list))
        {
            return StringUtils.EMPTY;
        }
        return list.stream().map(BytesmartPost::getPostName).collect(Collectors.joining(","));
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetEmployeerPwd(String userName, String password)
    {
        return employeeMapper.resetEmployeerPwd(userName, password);
    }

    /**
     * 修改用户基本信息
     *
     * @param employee 用户信息
     * @return 结果
     */
    @Override
    public int updateEmployeeProfile(BytesmartEmployee employee)
    {
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 校验手机号码是否唯一
     *
     * @param employee 用户信息
     * @return
     */
    @Override
    public boolean checkPhoneUnique(BytesmartEmployee employee)
    {
        Long employeeId = StringUtils.isNull(employee.getEmployeeId()) ? -1L : employee.getEmployeeId();
        BytesmartEmployee info = employeeMapper.checkPhoneUnique(employee.getEmployeeMobile());
        if (StringUtils.isNotNull(info) && info.getEmployeeId().longValue() != employeeId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param employee 用户信息
     * @return
     */
    @Override
    public boolean checkEmailUnique(BytesmartEmployee employee)
    {
        Long employeeId = StringUtils.isNull(employee.getEmployeeId()) ? -1L : employee.getEmployeeId();
        BytesmartEmployee info = employeeMapper.checkEmailUnique(employee.getEmployeeEmail());
        if (StringUtils.isNotNull(info) && info.getEmployeeId().longValue() != employeeId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    //修改用户信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEmployee(BytesmartEmployee employee){
        //取个用户id值
        Long emloyeeId = employee.getEmployeeId();

        //删除用户和岗位关联
        employeePostMapper.deleteEmployeePostByemployeeId(emloyeeId);
        //新增用户和岗位关联
        insertEmployeePost(employee);

        //删除用户和角色关联
        employeeRoleMapper.deleteEmployeeRoleByEmployeeId(emloyeeId);
        //新增用户和角色关联
        insertEmployeeRole(employee);
        return employeeMapper.updateEmployee(employee);
    }

    //新增用户岗位关联
    public void insertEmployeePost(BytesmartEmployee employee)
    {
        // 取前端传来PostIds值(调用employee对象的getPostIds方法，并将返回的结果赋值给posts数组)
        Long[] posts = employee.getPostIds();
        if (StringUtils.isNotEmpty(posts))
        {
            // 初始化一个ArrayList集合,使用new关键字创建了一个新的ArrayList<BytesmartEmployeePost>对象，并将其分配给list变量,它的类型是List<BytesmartEmployeePost>即是包含BytesmartEmployeePost对象的列表
            List<BytesmartEmployeePost> list = new ArrayList<BytesmartEmployeePost>();
            //增强型for循环(一个for-each循环)来遍历posts集合,对于posts集合中的每一个元素（这里命名为postId），执行一次循环体内的代码。
            for (Long postId : posts)
            {
                BytesmartEmployeePost bp = new BytesmartEmployeePost();
//                Long emloyeeId = employee.getEmployeeId();
                bp.setEmployeeId(employee.getEmployeeId());
                bp.setPostId(postId);
                list.add(bp);
            }
            employeePostMapper.batchEmployeePost(list);
        }

    }

    /**
     * 新增用户角色信息
     *
     * @param employee 用户对象
     */
    public void insertEmployeeRole(BytesmartEmployee employee)
    {
        this.insertEmployeeRole(employee.getEmployeeId(), employee.getRoleIds());
    }

    /**
     * 新增用户角色信息
     *
     * @param employeeId 用户ID
     * @param roleIds 角色组
     */
    public void insertEmployeeRole(Long employeeId, Long[] roleIds)
    {
        if (StringUtils.isNotEmpty(roleIds))
        {
            // 新增用户与角色管理
            List<BytesmartEmployeeRole> list = new ArrayList<BytesmartEmployeeRole>();
            for (Long roleId : roleIds)
            {
                BytesmartEmployeeRole ur = new BytesmartEmployeeRole();
                ur.setEmployeeId(employeeId);
                ur.setRoleId(roleId);
                list.add(ur);
            }
            employeeRoleMapper.batchEmployeeRole(list);
        }
    }



}
