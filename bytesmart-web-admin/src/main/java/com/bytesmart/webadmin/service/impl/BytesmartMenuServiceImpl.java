package com.bytesmart.webadmin.service.impl;


import com.bytesmart.webadmin.domain.BytesmartMenu;
import com.bytesmart.webadmin.domain.vo.MetaVo;
import com.bytesmart.webadmin.domain.vo.RouterVo;
import com.bytesmart.webadmin.domain.vo.TreeSelect;
import com.bytesmart.webadmin.mapper.BytesmartMenuMapper;
import com.bytesmart.webadmin.mapper.BytesmartRoleMapper;
import com.bytesmart.webadmin.mapper.BytesmartRoleMenuMapper;
import com.bytesmart.webadmin.service.IBytesmartMenuService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.BytesmartRole;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.constant.UserConstants;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.security.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class BytesmartMenuServiceImpl implements IBytesmartMenuService {
    public static final String PREMISSION_STRING = "perms[\"{0}\"]1";

    @Autowired
    private BytesmartMenuMapper bytesmartMenuMapper;

    @Autowired
    private BytesmartRoleMapper bytesmartRoleMapper;

    @Autowired
    private BytesmartRoleMenuMapper bytesmartRoleMenuMapper;

    /**
     * 根据用户查询系统菜单列表
     *
     * @param employeeId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<BytesmartMenu> selectMenuList(Long employeeId)
    {
        return selectMenuList(new BytesmartMenu(), employeeId);
    }


    @Override
    public List<BytesmartMenu> selectMenuList(BytesmartMenu menu, Long employeeId)
    {
        List<BytesmartMenu> menuList = null;
        // 管理员显示所有菜单信息
        if (BytesmartEmployee.isAdmin(employeeId))
        {
            menuList = bytesmartMenuMapper.selectMenuList(menu);
        }
        else
        {
            menu.getParams().put("employeeId", employeeId);
            menuList = bytesmartMenuMapper.selectMenuListByEmployeeId(menu);
        }
        return menuList;
    }

    /**
     * 根据用户ID查询权限
     *
     * @param employeeId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByEmployeeId(Long employeeId)
    {
        List<String> perms = bytesmartMenuMapper.selectMenuPermsByEmployeeId(employeeId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId)
    {
        List<String> perms = bytesmartMenuMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param employeeId 用户名称
     * @return 菜单列表
     */
    @Override
    public List<BytesmartMenu> selectMenuTreeByEmployeeId(Long employeeId)
    {
        List<BytesmartMenu> menus = null;
        if (SecurityUtils.isAdmin(employeeId))
        {
            menus = bytesmartMenuMapper.selectMenuTreeAll();
        }
        else
        {
            menus = bytesmartMenuMapper.selectMenuTreeByEmployeeId(employeeId);
        }
        return getChildPerms(menus, 0);
    }


    @Override
    public List<Long> selectMenuListByRoleId(Long roleId)
    {
        BytesmartRole role = bytesmartRoleMapper.selectRoleById(roleId);
        return bytesmartMenuMapper.selectMenuListByRoleId(roleId,role.isMenuCheckStrictly());
    }

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    @Override
    public List<RouterVo> buildMenus(List<BytesmartMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (BytesmartMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setHidden("1".equals(menu.getMenuDisplay()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getRouteParameter());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getMenuIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getRouteAddress()));
            List<BytesmartMenu> cMenus = menu.getChildren();
            if (!cMenus.isEmpty() && cMenus.size() > 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType()))
            {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            }
            else if (isMenuFrame(menu))
            {
                router.setMeta(null);
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                children.setPath(menu.getRouteAddress());
                children.setComponent(menu.getComponentPath());
                children.setName(StringUtils.capitalize(menu.getRouteAddress()));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getMenuIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getRouteAddress()));
                children.setQuery(menu.getRouteParameter());
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            else if (menu.getParentId().intValue() == 0 && isInnerLink(menu))
            {
                router.setMeta(new MetaVo(menu.getMenuName(), menu.getMenuIcon()));
                router.setPath("/");
                List<RouterVo> childrenList = new ArrayList<RouterVo>();
                RouterVo children = new RouterVo();
                String routerPath = innerLinkReplaceEach(menu.getRouteAddress());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaVo(menu.getMenuName(), menu.getMenuIcon(), menu.getRouteAddress()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    @Override
    public List<BytesmartMenu> buildMenuTree(List<BytesmartMenu> menus)
    {
        List<BytesmartMenu> returnList = new ArrayList<BytesmartMenu>();
        List<Long> tempList = menus.stream().map(BytesmartMenu::getMenuId).collect(Collectors.toList());
        for (Iterator<BytesmartMenu> iterator = menus.iterator(); iterator.hasNext();)
        {
            BytesmartMenu menu = (BytesmartMenu) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId()))
            {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param menus 菜单列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildMenuTreeSelect(List<BytesmartMenu> menus)
    {
        List<BytesmartMenu> menuTrees = buildMenuTree(menus);
        return menuTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 根据菜单ID查询信息
     *
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public BytesmartMenu selectMenuById(Long menuId)
    {
        return bytesmartMenuMapper.selectMenuById(menuId);
    }

    /**
     * 是否存在菜单子节点
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean hasChildByMenuId(Long menuId)
    {
        int result = bytesmartMenuMapper.hasChildByMenuId(menuId);
        return result > 0;
    }

    /**
     * 查询菜单使用数量
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public boolean checkMenuExistRole(Long menuId)
    {
        int result = bytesmartRoleMenuMapper.checkMenuExistRole(menuId);
        return result > 0;
    }

    /**
     * 新增保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(BytesmartMenu menu)
    {
        return bytesmartMenuMapper.insertMenu(menu);
    }

    /**
     * 修改保存菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int updateMenu(BytesmartMenu menu)
    {
        return bytesmartMenuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单管理信息
     *
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId)
    {
        return bytesmartMenuMapper.deleteMenuById(menuId);
    }

    /**
     * 校验菜单名称是否唯一
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public boolean checkMenuNameUnique(BytesmartMenu menu)
    {
        Long menuId = StringUtils.isNull(menu.getMenuId()) ? -1L : menu.getMenuId();
        BytesmartMenu info = bytesmartMenuMapper.checkMenuNameUnique(menu.getMenuName(),menu.getParentId());
        if (StringUtils.isNotNull(info) && info.getMenuId().longValue() != menuId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }


    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(BytesmartMenu menu)
    {
        String routerName = StringUtils.capitalize(menu.getComponentPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu))
        {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(BytesmartMenu menu)
    {
        String routerPath = menu.getRouteAddress();
        // 内链打开外网方式
        if (menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParentId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame()))
        {
            routerPath = "/" + menu.getComponentPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu))
        {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(BytesmartMenu menu)
    {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponentPath()) && !isMenuFrame(menu))
        {
            component = menu.getComponentPath();
        }
        else if (StringUtils.isEmpty(menu.getComponentPath()) && menu.getParentId().intValue() != 0 && isInnerLink(menu))
        {
            component = UserConstants.INNER_LINK;
        }
        else if (StringUtils.isEmpty(menu.getComponentPath()) && isParentView(menu))
        {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(BytesmartMenu menu)
    {
        return menu.getParentId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(BytesmartMenu menu)
    {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && StringUtils.ishttp(menu.getRouteAddress());
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(BytesmartMenu menu)
    {
        return menu.getParentId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    public List<BytesmartMenu> getChildPerms(List<BytesmartMenu> list, int parentId)
    {
        List<BytesmartMenu> returnList = new ArrayList<BytesmartMenu>();
        for (Iterator<BytesmartMenu> iterator = list.iterator(); iterator.hasNext();)
        {
            BytesmartMenu t = (BytesmartMenu) iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId)
            {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<BytesmartMenu> list, BytesmartMenu t)
    {
        // 得到子节点列表
        List<BytesmartMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (BytesmartMenu tChild : childList)
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
    private List<BytesmartMenu> getChildList(List<BytesmartMenu> list, BytesmartMenu t)
    {
        List<BytesmartMenu> tlist = new ArrayList<BytesmartMenu>();
        Iterator<BytesmartMenu> it = list.iterator();
        while (it.hasNext())
        {
            BytesmartMenu n = (BytesmartMenu) it.next();
            if (n.getParentId().longValue() == t.getMenuId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<BytesmartMenu> list, BytesmartMenu t)
    {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return
     */
    public String innerLinkReplaceEach(String path)
    {
        return StringUtils.replaceEach(path, new String[] { Constants.HTTP, Constants.HTTPS, Constants.WWW, "." },
                new String[] { "", "", "", "/" });
    }

}
