package com.bytesmart.webadmin.service.impl;

import com.bytesmart.webadmin.domain.BytesmartEmployeeRole;
import com.bytesmart.webadmin.domain.BytesmartRoleDept;
import com.bytesmart.webadmin.domain.BytesmartRoleMenu;
import com.bytesmart.webadmin.mapper.BytesmartEmployeeRoleMapper;
import com.bytesmart.webadmin.mapper.BytesmartRoleDeptMapper;
import com.bytesmart.webadmin.mapper.BytesmartRoleMapper;
import com.bytesmart.webadmin.mapper.BytesmartRoleMenuMapper;
import com.bytesmart.webadmin.service.IBytesmartRoleService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BytesmartRoleServiceImpl implements IBytesmartRoleService {

    @Autowired
    private BytesmartRoleMapper bytesmartRoleMapper;

    @Autowired
    private BytesmartEmployeeRoleMapper bytesmartEmployeeRoleMapper;

    @Autowired
    private BytesmartRoleMenuMapper bytesmartRoleMenuMapper;

    @Autowired
    private BytesmartRoleDeptMapper bytesmartRoleDeptMapper;



    //根据用户ID查询权限
    @Override
    public Set<String> selectRolePermissionByEmployeeId(Long employeeId)
    {
        List<BytesmartRole> perms = bytesmartRoleMapper.selectRolePermissionByEmployeeId(employeeId);


        Set<String> permsSet = new HashSet<>();
        for (BytesmartRole perm : perms)
        {
            if (StringUtils.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    @DataScope(deptAlias = "d")
    public List<BytesmartRole> selectRoleList(BytesmartRole role){
        return bytesmartRoleMapper.selectRoleList(role);
    }


    @Override
    public BytesmartRole selectRoleById(Long roleId){
        return bytesmartRoleMapper.selectRoleById(roleId);
    }


    /**
     * 校验角色名称是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean checkRoleNameUnique(BytesmartRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        BytesmartRole info = bytesmartRoleMapper.checkRoleNameUnique(role.getRoleName());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验角色权限是否唯一
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public boolean checkRoleKeyUnique(BytesmartRole role)
    {
        Long roleId = StringUtils.isNull(role.getRoleId()) ? -1L : role.getRoleId();
        BytesmartRole info = bytesmartRoleMapper.checkRoleKeyUnique(role.getRoleKey());
        if (StringUtils.isNotNull(info) && info.getRoleId().longValue() != roleId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    //修改角色信息
    @Override
    public int updateRole(BytesmartRole role){
        //修改角色信息
        bytesmartRoleMapper.updateRole(role);
        //删除角色和菜单关联
        bytesmartRoleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
    }

    /**
     * 修改角色状态
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    public int updateRoleStatus(BytesmartRole role)
    {
        return bytesmartRoleMapper.updateRole(role);
    }

    /**
     * 修改数据权限信息
     *
     * @param role 角色信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int authDataScope(BytesmartRole role)
    {
        // 修改角色信息
        bytesmartRoleMapper.updateRole(role);
        // 删除角色与部门关联
        bytesmartRoleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
    }

    /**
     * 通过角色ID删除角色
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleById(Long roleId)
    {
        // 删除角色与菜单关联
        bytesmartRoleMenuMapper.deleteRoleMenuByRoleId(roleId);
        // 删除角色与部门关联
        bytesmartRoleDeptMapper.deleteRoleDeptByRoleId(roleId);
        return bytesmartRoleMapper.deleteRoleById(roleId);
    }

    /**
     * 根据用户ID查询角色
     *
     * @param employeeId 用户ID
     * @return 角色列表
     */
    @Override
    public List<BytesmartRole> selectRolesByEmployeeId(Long employeeId)
    {
        List<BytesmartRole> userRoles = bytesmartRoleMapper.selectRolePermissionByEmployeeId(employeeId);
        List<BytesmartRole> roles = selectRoleAll();
        for (BytesmartRole role : roles)
        {
            for (BytesmartRole bytesmartRole : userRoles)
            {
                if (role.getRoleId().longValue() == bytesmartRole.getRoleId().longValue())
                {
                    role.setFlag(true);
                    break;
                }
            }
        }
        return roles;
    }



    /**
     * 校验角色是否允许操作
     *
     * @param role 角色信息
     */
    @Override
    public void checkRoleAllowed(BytesmartRole role)
    {
        if (StringUtils.isNotNull(role.getRoleId()) && role.isAdmin())
        {
            throw new ServiceException("不允许操作超级管理员角色");
        }
    }

    /**
     * 通过角色ID查询角色使用数量
     *
     * @param roleId 角色ID
     * @return 结果
     */
    @Override
    public int countemployeeRoleByRoleId(Long roleId)
    {
        return bytesmartEmployeeRoleMapper.countEmployeeRoleByRoleId(roleId);
    }

    /**
     * 校验角色是否有数据权限
     *
     * @param roleId 角色id
     */
    @Override
    public void checkRoleDataScope(Long roleId)
    {
//        if (!BytesmartEmployee.isAdmin(SecurityUtils.getUserId()))
        if (!BytesmartEmployee.isAdmin(1L))
        {
            BytesmartRole role = new BytesmartRole();
            role.setRoleId(roleId);
            List<BytesmartRole> roles = SpringUtils.getAopProxy(this).selectRoleList(role);
            if (StringUtils.isEmpty(roles))
            {
                throw new ServiceException("没有权限访问角色数据！");
            }
        }
    }



    /**
     * 批量删除角色信息
     *
     * @param roleIds 需要删除的角色ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleByIds(Long[] roleIds)
    {
        for (Long roleId : roleIds)
        {
            checkRoleAllowed(new BytesmartRole(roleId));
            checkRoleDataScope(roleId);
            BytesmartRole role = selectRoleById(roleId);
            if (countemployeeRoleByRoleId(roleId) > 0)
            {
                throw new ServiceException(String.format("%1$s已分配,不能删除", role.getRoleName()));
            }
        }
        // 删除角色与菜单关联
        bytesmartRoleMenuMapper.deleteRoleMenu(roleIds);
        // 删除角色与部门关联
        bytesmartRoleDeptMapper.deleteRoleDept(roleIds);
        return bytesmartRoleMapper.deleteRoleByIds(roleIds);
    }

    /**
     * 取消授权用户角色
     *
     * @param employeeRole 用户和角色关联信息
     * @return 结果
     */
    @Override
    public int deleteAuthEmployee(BytesmartEmployeeRole employeeRole)
    {
        return bytesmartEmployeeRoleMapper.deleteEmployeeRoleListInfo(employeeRole);
    }


    @Override
    public int insertRole(BytesmartRole role){
        //新增角色信息
        bytesmartRoleMapper.insertRole(role);
        return insertRoleMenu(role);
    }

    /**
     * 新增角色菜单信息
     *
     * @param role 角色对象
     */
    public int insertRoleMenu(BytesmartRole role) {
        int rows = 1;
        // 新增用户与角色管理
        List<BytesmartRoleMenu> list = new ArrayList<BytesmartRoleMenu>();
        for (Long menuId : role.getMenuIds()) {
            BytesmartRoleMenu rm = new BytesmartRoleMenu();
            rm.setRoleId(role.getRoleId());
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (list.size() > 0) {
            rows = bytesmartRoleMenuMapper.batchRoleMenu(list);
        }
        return rows;
    }

    /**
     * 新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
    public int insertRoleDept(BytesmartRole role)
    {
        int rows = 1;
        // 新增角色与部门（数据权限）管理
        List<BytesmartRoleDept> list = new ArrayList<BytesmartRoleDept>();
        for (Long deptId : role.getDeptIds())
        {
            BytesmartRoleDept rd = new BytesmartRoleDept();
            rd.setRoleId(role.getRoleId());
            rd.setDeptId(deptId);
            list.add(rd);
        }
        if (list.size() > 0)
        {
            rows = bytesmartRoleDeptMapper.batchRoleDept(list);
        }
        return rows;
    }

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    @Override
    public List<BytesmartRole> selectRoleAll(){
        return SpringUtils.getAopProxy(this).selectRoleList(new BytesmartRole());

    }


    /**
     * 批量取消授权用户角色
     *
     * @param roleId 角色ID
     * @param employeeIds 需要取消授权的用户数据ID
     * @return 结果
     */
    @Override
    public int deleteAuthEmployees(Long roleId, Long[] employeeIds)
    {
        return bytesmartEmployeeRoleMapper.deleteEmployeeRoleInfos(roleId,employeeIds);
    }

    /**
     * 批量选择授权用户角色
     *
     * @param roleId 角色ID
     * @param employeeIds 需要授权的用户数据ID
     * @return 结果
     */
    @Override
    public int insertAuthEmployees(Long roleId, Long[] employeeIds)
    {
        // 新增用户与角色管理
        List<BytesmartEmployeeRole> list = new ArrayList<BytesmartEmployeeRole>();
        for (Long employeeId : employeeIds)
        {
            BytesmartEmployeeRole ur = new BytesmartEmployeeRole();
            ur.setEmployeeId(employeeId);
            ur.setRoleId(roleId);
            list.add(ur);
        }
        return bytesmartEmployeeRoleMapper.batchEmployeeRole(list);
    }



}
