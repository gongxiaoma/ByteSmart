package com.bytesmart.webauth.service.impl;

import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.webauth.mapper.*;
import com.bytesmart.webauth.service.IBytesmartEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BytesmartEmployeeServiceImpl implements IBytesmartEmployeeService {
    private static final Logger log = LoggerFactory.getLogger(BytesmartEmployeeServiceImpl.class);

    @Autowired
    private BytesmartEmployeeMapper employeeMapper;


    @Override
    public BytesmartEmployee selectEmployeeById(Long emloyeeId){
        return employeeMapper.selectEmployeeById(emloyeeId);
    }


}
