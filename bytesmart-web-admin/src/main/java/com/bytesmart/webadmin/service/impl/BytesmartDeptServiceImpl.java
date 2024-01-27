package com.bytesmart.webadmin.service.impl;

import com.bytesmart.apisystem.domain.*;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.exception.ServiceException;
import com.bytesmart.common.core.text.Convert;
import com.bytesmart.common.datascope.annotation.DataScope;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.webadmin.domain.vo.TreeSelect;
import com.bytesmart.webadmin.mapper.BytesmartDeptMapper;
import com.bytesmart.webadmin.mapper.BytesmartRoleMapper;
import com.bytesmart.webadmin.service.IBytesmartDeptService;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.SystemColor.info;

@Service
public class BytesmartDeptServiceImpl implements IBytesmartDeptService {

    @Autowired
    private BytesmartDeptMapper bytesmartDeptMapper;

    @Autowired
    private BytesmartRoleMapper bytesmartRoleMapper;

    //全部查询
    @Override
    @DataScope(deptAlias = "d")
    public List<BytesmartDept> selectDeptList(BytesmartDept dept){
        return bytesmartDeptMapper.selectDeptList(dept);
    }

    //根据id查询
    @Override
    public BytesmartDept selectDeptById(Long deptId){
        return bytesmartDeptMapper.selectDeptById(deptId);
    }

//    @Override
//    public int updateDept(BytesmartDept dept){
//        return bytesmartDeptMapper.updateDept(dept);
//    }

    @Override
    public int insertDept(BytesmartDept dept){
//        return bytesmartDeptMapper.insertDept(dept);
        BytesmartDept info = bytesmartDeptMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getDeptStatus()))
        {
            throw new ServiceException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return bytesmartDeptMapper.insertDept(dept);
    }



    //查询部门树结构信息
    @Override
    public List<TreeSelect> selectDeptTreeList(BytesmartDept dept)
    {
        List<BytesmartDept> depts = SpringUtils.getAopProxy(this).selectDeptList(dept);
        return buildDeptTreeSelect(depts);

    }

    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<BytesmartDept> buildDeptTree(List<BytesmartDept> depts)
    {
        List<BytesmartDept> returnList = new ArrayList<BytesmartDept>();
        List<Long> tempList = depts.stream().map(BytesmartDept::getDeptId).collect(Collectors.toList());
        for (BytesmartDept dept : depts)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<BytesmartDept> depts)
    {
        List<BytesmartDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }


    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Override
    public List<Long> selectDeptListByRoleId(Long roleId)
    {
        BytesmartRole role = bytesmartRoleMapper.selectRoleById(roleId);
        return bytesmartDeptMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(Long deptId)
    {
        return bytesmartDeptMapper.selectNormalChildrenDeptById(deptId);
    }

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public boolean hasChildByDeptId(Long deptId)
    {
        int result = bytesmartDeptMapper.hasChildByDeptId(deptId);
        return result > 0;
    }


    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistEmployee(Long deptId)
    {
        int result = bytesmartDeptMapper.checkDeptExistEmployee(deptId);
        return result > 0;
    }



    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(BytesmartDept dept)
    {
        BytesmartDept newParentDept = bytesmartDeptMapper.selectDeptById(dept.getParentId());
        BytesmartDept oldDept = bytesmartDeptMapper.selectDeptById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = bytesmartDeptMapper.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getDeptStatus()) && StringUtils.isNotEmpty(dept.getAncestors())
                && !StringUtils.equals("0", dept.getAncestors()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(dept);
        }
        return result;
    }

    @Override
    public int deleteDeptById(Long deptId)
    {
        return bytesmartDeptMapper.deleteDeptById(deptId);
    }


    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(BytesmartDept dept)
    {
        String ancestors = dept.getAncestors();
        Long[] deptIds = Convert.toLongArray(ancestors);
        bytesmartDeptMapper.updateDeptStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<BytesmartDept> children = bytesmartDeptMapper.selectChildrenDeptById(deptId);
        for (BytesmartDept child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            bytesmartDeptMapper.updateDeptChildren(children);
        }
    }


    /**
     * 递归列表
     */
    private void recursionFn(List<BytesmartDept> list, BytesmartDept t)
    {
        // 得到子节点列表
        List<BytesmartDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (BytesmartDept tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<BytesmartDept> getChildList(List<BytesmartDept> list, BytesmartDept t)
    {
        List<BytesmartDept> tlist = new ArrayList<BytesmartDept>();
        Iterator<BytesmartDept> it = list.iterator();
        while (it.hasNext())
        {
            BytesmartDept n = (BytesmartDept) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<BytesmartDept> list, BytesmartDept t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }



    /**
     * 校验部门是否有数据权限
     *
     * @param deptId 部门id
     */
    @Override
    public void checkDeptDataScope(Long deptId)
    {
        if (!BytesmartEmployee.isAdmin(SecurityUtils.getUserId()))
        {
            BytesmartDept dept = new BytesmartDept();
            dept.setDeptId(deptId);
            List<BytesmartDept> depts = SpringUtils.getAopProxy(this).selectDeptList(dept);
            if (StringUtils.isEmpty(depts))
            {
                throw new ServiceException("没有权限访问部门数据！");
            }
        }
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public boolean checkDeptNameUnique(BytesmartDept dept)
    {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        BytesmartDept info = bytesmartDeptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }








    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
//    @Override
//    public List<Long> selectDeptListByRoleId(Long roleId)
//    {
//        BytesmartRole role = bytesmartRoleMapper.selectRoleById(roleId);
//        return bytesmartDeptMapper.selectDeptListByRoleId(roleId, role.isDeptCheckStrictly());
//    }



}
