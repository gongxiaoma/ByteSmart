package com.bytesmart.webadmin.service.impl;

import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.apisystem.domain.SysUser;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.common.security.utils.WebSecurityUtils;
import com.bytesmart.webadmin.domain.BytesmartEmployeePost;
import com.bytesmart.webadmin.domain.BytesmartEmployeeRole;
import com.bytesmart.webadmin.service.IBytesmartConfigService;
import com.bytesmart.webadmin.service.IBytesmartEmployeeService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.datascope.annotation.DataScope;
import com.bytesmart.webadmin.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class BytesmartEmployeeServiceImpl implements IBytesmartEmployeeService {
    private static final Logger log = LoggerFactory.getLogger(BytesmartEmployeeServiceImpl.class);

    @Autowired
    private BytesmartEmployeeMapper employeeMapper;

    @Autowired
    private BytesmartRoleMapper roleMapper;

    @Autowired
    private BytesmartPostMapper postMapper;

    @Autowired
    private BytesmartEmployeeRoleMapper employeeRoleMapper;

    @Autowired
    private BytesmartEmployeePostMapper employeePostMapper;

    @Autowired
    private IBytesmartConfigService bytesmartConfigService;


    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<BytesmartEmployee> selectEmployeeList(BytesmartEmployee employee)
    {
        return employeeMapper.selectEmployeeList(employee);
    }

    @Override
    public BytesmartEmployee selectEmployeeById(Long emloyeeId){
        return employeeMapper.selectEmployeeById(emloyeeId);
    }

    @Override
    public BytesmartEmployee selectEmployeeByUsername(String userName){
        return employeeMapper.selectEmployeeByUsername(userName);
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
        Long[] posts = employee.getPostIds();
        if (StringUtils.isNotEmpty(posts))
        {
           // 初始化一个ArrayList集合,使用new关键字创建了一个新的ArrayList<BytesmartEmployeePost>对象，并将其分配给list变量,它的类型是List<BytesmartEmployeePost>即是包含BytesmartEmployeePost对象的列表
            List<BytesmartEmployeePost> list = new ArrayList<BytesmartEmployeePost>();
            //增强型for循环(一个for-each循环)来遍历posts集合,对于posts集合中的每一个元素（这里命名为postId），执行一次循环体内的代码。
            for (Long postId : posts)
            {
                BytesmartEmployeePost bp = new BytesmartEmployeePost();

                Long emloyeeId = employee.getEmployeeId();
                bp.setEmployeeId(employee.getEmployeeId());
                bp.setPostId(postId);
                list.add(bp);
            }
            employeePostMapper.batchEmployeePost(list);
        }
    }

    public void insertEmployeeRole(BytesmartEmployee employee){

        //取前端传来RoleIds值,调用employee对象的getRoleIds方法，并将返回的结果赋值给roles数组
        Long[] roles = employee.getRoleIds();
        if (StringUtils.isNotEmpty(roles)){
            List<BytesmartEmployeeRole> list = new ArrayList<>();
            for (Long roleId : roles){
                BytesmartEmployeeRole br = new BytesmartEmployeeRole();

                Long emloyeeId = employee.getEmployeeId();
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
        Long emloyeeId = employee.getEmployeeId();

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
    public int deleteEmployeeByIds(Long[] employeeIds){
        //删除用户和角色关联
        employeeRoleMapper.deleteEmployeeRole(employeeIds);
        //删除用户和角色关联
        employeePostMapper.deleteEmployeePost(employeeIds);
        return employeeMapper.deleteEmployeeByIds(employeeIds);
    }

    /**
     * 根据条件分页查询已分配用户角色列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<BytesmartEmployee> selectAllocatedList(BytesmartEmployee employee)
    {
        return employeeMapper.selectAllocatedList(employee);
    }

    /**
     * 根据条件分页查询未分配用户角色列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
    public List<BytesmartEmployee> selectUnallocatedList(BytesmartEmployee employee){
        return employeeMapper.selectUnallocatedList(employee);
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
//    @Override
//    public List<BytesmartRole> selectRoleAll()
//    {
//        return SpringUtils.getAopProxy(this).selectRoleList(new BytesmartRole());
//    }

    /**
     * 根据条件分页查询角色数据
     *
     * @param role 角色信息
     * @return 角色数据集合信息
     */
//    @Override
//    @DataScope(deptAlias = "d")
//    public List<BytesmartRole> selectRoleList(BytesmartRole role)
//    {
//        return roleMapper.selectRoleList(role);
//    }

    //校验用户ID查询是否有权限
    @Override
    public void checkEmployeeDataScope(Long employeeId)
    {
        if (!BytesmartEmployee.isAdmin(WebSecurityUtils.getEmployeeId()))
        {
            BytesmartEmployee bytesmartEmployee = new BytesmartEmployee();
            bytesmartEmployee.setEmployeeId(employeeId);
            List<BytesmartEmployee> employees = SpringUtils.getAopProxy(this).selectEmployeeList(bytesmartEmployee);
            if (StringUtils.isEmpty(employees))
            {
                throw new ServiceException("没有权限访问用户数据！");
            }
        }
    }

    /**
     * 注册用户信息
     *
     * @param bytesmartEmployee 用户信息
     * @return 结果
     */
    @Override
    public boolean registerEmployee(BytesmartEmployee bytesmartEmployee)
    {
        return employeeMapper.insertEmployee(bytesmartEmployee) > 0;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param bytesmartEmployee 用户信息
     * @return 结果
     */
    @Override
    public boolean checkUserNameUnique(BytesmartEmployee bytesmartEmployee)
    {
        Long employeeId = StringUtils.isNull(bytesmartEmployee.getEmployeeId()) ? -1L : bytesmartEmployee.getEmployeeId();
        BytesmartEmployee info = employeeMapper.checkUserNameUnique(bytesmartEmployee.getUserName());
        if (StringUtils.isNotNull(info) && info.getEmployeeId().longValue() != employeeId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }




}
