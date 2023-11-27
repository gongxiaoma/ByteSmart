package com.bytesmart.admincenter.mapper;

import com.bytesmart.admincenter.domain.BytesmartMenu;

import java.util.List;

public interface BytesmartMenuMapper {
    public List<BytesmartMenu> selectMenuList(BytesmartMenu menu);

}
