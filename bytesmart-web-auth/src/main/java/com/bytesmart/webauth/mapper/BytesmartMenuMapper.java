package com.bytesmart.webauth.mapper;

import com.bytesmart.webauth.domain.BytesmartMenu;
import java.util.List;
import java.util.Set;

public interface BytesmartMenuMapper {

    public List<String> selectMenuPermsByRoleId(Long roleId);

    public Set<String> selectMenuPermsByEmployeeId(Long employeeId);

    public List<BytesmartMenu> selectMenuTreeByEmployeeId(Long employeeId);

    public List<BytesmartMenu> selectMenuTreeAll();

}
