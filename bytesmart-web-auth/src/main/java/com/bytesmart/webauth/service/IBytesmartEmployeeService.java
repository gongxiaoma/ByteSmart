package com.bytesmart.webauth.service;

import com.bytesmart.apisystem.domain.BytesmartEmployee;

import java.util.List;

public interface IBytesmartEmployeeService {

    //按id查询
    public BytesmartEmployee selectEmployeeById(Long emloyeeId);

}
