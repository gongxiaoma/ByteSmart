package com.bytesmart.admincenter.service.impl;

import com.bytesmart.admincenter.domain.vo.TreeSelect;
import com.bytesmart.admincenter.mapper.BytesmartDeptMapper;
import com.bytesmart.admincenter.mapper.BytesmartRoleMapper;
import com.bytesmart.admincenter.service.IBytesmartDeptService;
import com.bytesmart.apisystem.domain.BytesmartDept;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.common.core.utils.SpringUtils;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.datascope.annotation.DataScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BytesmartDeptServiceImpl implements IBytesmartDeptService {

    @Autowired
    private BytesmartDeptMapper bytesmartDeptMapper;

    @Autowired
    private BytesmartRoleMapper bytesmartRoleMapper;

    @Override
    public List<BytesmartDept> selectDeptList(BytesmartDept dept){
        return bytesmartDeptMapper.selectDeptList(dept);
    }

    @Override
    public BytesmartDept selectDeptById(Long deptId){
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
    public int deleteDeptByIds(Long[] deptIds){
        return bytesmartDeptMapper.deleteDeptByIds(deptIds);
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
     * 查询部门树结构信息
     *
     * @param dept 部门信息
     * @return 部门树信息集合
     */
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




}
