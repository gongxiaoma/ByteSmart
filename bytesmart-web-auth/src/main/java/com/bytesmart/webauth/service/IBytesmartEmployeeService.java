package com.bytesmart.webauth.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;

public interface IBytesmartEmployeeService {

    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

}
