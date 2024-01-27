package com.bytesmart.webadmin.service.impl;

import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.apisystem.domain.SysRole;
import com.bytesmart.apisystem.domain.SysUser;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.core.utils.bean.BeanValidators;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.webadmin.domain.BytesmartEmployeePost;
import com.bytesmart.webadmin.domain.BytesmartEmployeeRole;
import com.bytesmart.webadmin.domain.BytesmartPost;
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
import org.springframework.util.CollectionUtils;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    protected Validator validator;


    /**
     * 根据条件分页查询用户列表
     *
     * @param employee 用户信息
     * @return 用户信息集合信息
     */
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
//                Long emloyeeId = employee.getEmployeeId();
                bp.setEmployeeId(employee.getEmployeeId());
                bp.setPostId(postId);
                list.add(bp);
            }
            employeePostMapper.batchEmployeePost(list);
        }
    }

    //新增用户角色信息,为什么不能用这个，因为后面还需要些授权角色，类型参数要对应
//    public void insertEmployeeRole(BytesmartEmployee employee){
//
//        //取前端传来RoleIds值,调用employee对象的getRoleIds方法，并将返回的结果赋值给roles数组
//        Long[] roles = employee.getRoleIds();
//        if (StringUtils.isNotEmpty(roles)){
//            List<BytesmartEmployeeRole> list = new ArrayList<>();
//            for (Long roleId : roles){
//                BytesmartEmployeeRole br = new BytesmartEmployeeRole();
//
//                Long emloyeeId = employee.getEmployeeId();
//                br.setEmployeeId(emloyeeId);
//                br.setRoleId(roleId);
//                list.add(br);
//            }
//            employeeRoleMapper.batchEmployeeRole(list);
//        }
//    }

    /**
     * 用户授权角色
     *
     * @param employeeId 用户ID
     * @param roleIds 角色组
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertEmployeeAuth(Long employeeId, Long[] roleIds)
    {
        employeeRoleMapper.deleteEmployeeRoleByEmployeeId(employeeId);
        insertEmployeeRole(employeeId, roleIds);
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
        //新增用户和岗位关联
        insertEmployeeRole(employee);
        return employeeMapper.updateEmployee(employee);
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
     * 修改用户头像
     *
     * @param userName 用户名
     * @param employeeAvatar 头像地址
     * @return 结果
     */
    @Override
    public boolean updateEmployeeAvatar(String userName, String employeeAvatar)
    {
        return employeeMapper.updateEmployeeAvatar(userName, employeeAvatar) > 0;
    }

    /**
     * 批量删除用户信息
     *
     * @param employeeIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmployeeByIds(Long[] employeeIds)
    {
        for (Long employeeId : employeeIds)
        {
            checkEmployeeAllowed((new BytesmartEmployee()));
            checkEmployeeDataScope(employeeId);
        }
        // 删除用户与角色关联
        employeeRoleMapper.deleteEmployeeRole(employeeIds);
        // 删除用户与岗位关联
        employeePostMapper.deleteEmployeePost(employeeIds);
        return employeeMapper.deleteEmployeeByIds(employeeIds);
    }

//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int deleteEmployeeByIds(Long[] employeeIds){
//        //删除用户和角色关联
//        employeeRoleMapper.deleteEmployeeRole(employeeIds);
//        //删除用户和角色关联
//        employeePostMapper.deleteEmployeePost(employeeIds);
//        return employeeMapper.deleteEmployeeByIds(employeeIds);
//    }

    /**
     * 通过用户ID删除用户
     *
     * @param employeeId 用户ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmployeeById(Long employeeId)
    {
        // 删除用户与角色关联
        employeeRoleMapper.deleteEmployeeRoleByEmployeeId(employeeId);
        // 删除用户与岗位表
        employeePostMapper.deleteEmployeePostByemployeeId(employeeId);
        return employeeMapper.deleteEmployeeById(employeeId);
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
        if (!BytesmartEmployee.isAdmin(SecurityUtils.getUserId()))
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

    /**
     * 校验用户是否允许操作
     *
     * @param employee 用户信息
     */
    @Override
    public void checkEmployeeAllowed(BytesmartEmployee employee)
    {
        if (StringUtils.isNotNull(employee.getEmployeeId()) && employee.isAdmin())
        {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }


    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importEmployee(List<BytesmartEmployee> employeeList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(employeeList) || employeeList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = bytesmartConfigService.selectConfigByKey("webadmin.employee.initPassword");
        for (BytesmartEmployee employee : employeeList)
        {
            try
            {
                // 验证是否存在这个用户
                BytesmartEmployee u = employeeMapper.selectEmployeeByUsername(employee.getUserName());
                if (StringUtils.isNull(u))
                {
                    BeanValidators.validateWithException(validator, employee);
                    employee.setPassword(SecurityUtils.encryptPassword(password));
                    employee.setCreateBy(operName);
                    employeeMapper.insertEmployee(employee);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + employee.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, employee);
                    checkEmployeeAllowed(u);
                    checkEmployeeDataScope(u.getEmployeeId());
                    employee.setEmployeeId(u.getEmployeeId());
                    employee.setUpdateBy(operName);
                    employeeMapper.updateEmployee(employee);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + employee.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + employee.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + employee.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 重置用户密码
     *
     * @param employee 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(BytesmartEmployee employee)
    {
        return employeeMapper.updateEmployee(employee);
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
     * 修改用户状态
     *
     * @param employee 用户信息
     * @return 结果
     */
    @Override
    public int updateEmployeeStatus(BytesmartEmployee employee)
    {
        return employeeMapper.updateEmployee(employee);
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




}
