package com.bytesmart.webauth.domain;

import com.bytesmart.common.core.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表 bytesmart_menu
 * 
 * @author hd
 */

//@TableName(value="bytesmart_menu")
public class BytesmartMenu extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 菜单ID */
    private Long menuId;

    /** 菜单名称 */
    private String menuName;

    /** 父菜单名称 */
    private String parentName;

    /** 父菜单ID */
    private Long parentId;

    /** 显示顺序权重 */
    private Integer menuWeight      ;

    /** 路由地址 */
    private String routeAddress;

    /** 组件路径 */
    private String componentPath   ;

    /** 路由参数 */
    private String routeParameter  ;

    /** 是否为外链（0是 1否） */
    private String isFrame;

    /** 是否缓存（0缓存 1不缓存） */
    private String isCache;

    /** 类型（M目录 C菜单 F按钮） */
    private String menuType;

    /** 显示状态（0显示 1隐藏） */
    private String menuDisplay;
    
    /** 菜单状态（0正常 1停用） */
    private String menuStatus;

    /** 权限字符串 */
    private String menuPerms;

    /** 菜单图标 */
    private String icon;

    /** 子菜单 */
    private List<BytesmartMenu> children = new ArrayList<BytesmartMenu>();

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getMenuWeight() {
        return menuWeight;
    }

    public void setMenuWeight(Integer menuWeight) {
        this.menuWeight = menuWeight;
    }

    public String getRouteAddress() {
        return routeAddress;
    }

    public void setRouteAddress(String routeAddress) {
        this.routeAddress = routeAddress;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    public String getRouteParameter() {
        return routeParameter;
    }

    public void setRouteParameter(String routeParameter) {
        this.routeParameter = routeParameter;
    }

    public String getIsFrame() {
        return isFrame;
    }

    public void setIsFrame(String isFrame) {
        this.isFrame = isFrame;
    }

    public String getIsCache() {
        return isCache;
    }

    public void setIsCache(String isCache) {
        this.isCache = isCache;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuDisplay() {
        return menuDisplay;
    }

    public void setMenuDisplay(String menuDisplay) {
        this.menuDisplay = menuDisplay;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
    }

    public String getMenuPerms() {
        return menuPerms;
    }

    public void setMenuPerms(String menuPerms) {
        this.menuPerms = menuPerms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<BytesmartMenu> getChildren() {
        return children;
    }

    public void setChildren(List<BytesmartMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("menuId", getMenuId())
            .append("menuName", getMenuName())
            .append("parentId", getParentId())
            .append("menuWeight", getMenuWeight())
            .append("routeAddress", getRouteAddress())
            .append("componentPath", getComponentPath())
            .append("isFrame", getIsFrame())
            .append("IsCache", getIsCache())
            .append("menuType", getMenuType())
            .append("menuDisplay", getMenuDisplay())
            .append("menuStatus ", getMenuStatus())
            .append("menuPerms", getMenuPerms())
            .append("icon", getIcon())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
