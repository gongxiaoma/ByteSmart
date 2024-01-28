package com.bytesmart.webadmin.controller;

import com.bytesmart.apisystem.RemoteFileService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
//import com.bytesmart.apisystem.model.aLoginEmployee;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.common.log.annotation.Log;
import com.bytesmart.common.log.enums.BusinessType;
import com.bytesmart.common.security.service.TokenService;
import com.bytesmart.common.security.utils.SecurityUtils;
import com.bytesmart.webadmin.service.IBytesmartEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 个人信息 业务处理
 *
 * @author hd
 */
@RestController
@RequestMapping("/employee/profile")
public class BytesmartProfileController extends BaseController {

    @Autowired
    private IBytesmartEmployeeService employeeService;

    @Autowired
    private TokenService tokenService;

//    @Autowired
//    private RemoteFileService remoteFileService;


    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        String username = SecurityUtils.getUsername();
        BytesmartEmployee employee = employeeService.selectEmployeeByUsername(username);
        AjaxResult ajax = AjaxResult.success(employee);
        ajax.put("roleGroup", employeeService.selectEmployeeRoleGroup(username));
        ajax.put("postGroup", employeeService.selectEmployeePostGroup(username));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody BytesmartEmployee employee)
    {
        //        Long employeeId = 1L;
        Long employeeId = SecurityUtils.getUserId();
        BytesmartEmployee bytesmartEmployee = new BytesmartEmployee();
        bytesmartEmployee.setEmployeeId(employeeId);
        bytesmartEmployee.setEmployeeName(employee.getEmployeeName());
        bytesmartEmployee.setEmployeeEmail(employee.getEmployeeEmail());
        bytesmartEmployee.setEmployeeMobile(employee.getEmployeeMobile());
        bytesmartEmployee.setEmployeeGender(employee.getEmployeeGender());
        employeeService.updateEmployeeProfile(bytesmartEmployee);
        return success();

//        BytesmartEmployee currentEmployee = loginEmployee.getBytesmartEmployee();

//        System.out.println(employee.getEmployeeName());
//        currentEmployee.setEmployeeName(employee.getEmployeeName());
//        currentEmployee.setEmployeeEmail(employee.getEmployeeEmail());
//        currentEmployee.setEmployeeMobile(employee.getEmployeeMobile());
//        currentEmployee.setEmployeeGender(employee.getEmployeeGender());
//        if (StringUtils.isNotEmpty(employee.getEmployeeMobile()) && !employeeService.checkPhoneUnique(currentEmployee))
//        {
//            return error("修改用户'" + employee.getUserName() + "'失败，手机号码已存在");
//        }
//        if (StringUtils.isNotEmpty(employee.getEmployeeEmail()) && !employeeService.checkEmailUnique(currentEmployee))
//        {
//            return error("修改用户'" + employee.getUserName() + "'失败，邮箱账号已存在");
//        }
//        if (employeeService.updateEmployeeProfile(currentEmployee) > 0)
//        {
//            // 更新缓存用户信息
//            tokenService.setLoginEmployee(loginEmployee);
//            return success();
//        }
//        return error("修改个人信息异常，请联系管理员");
    }
//
//    /**
//     * 重置密码
//     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
//        方法1：
//        Long employeeId = SecurityUtils.getUserId();
//        BytesmartEmployee bytesmartEmployee = new BytesmartEmployee();
//        bytesmartEmployee.setEmployeeId(employeeId);
//        employeeService.selectEmployeeById(employeeId);
//        bytesmartEmployee.getPassword();
//        employeeService.resetPwd(bytesmartEmployee);
//        return success();

//        方法2：
        String username = SecurityUtils.getUsername();
        BytesmartEmployee employee = employeeService.selectEmployeeByUsername(username);
        String password = employee.getPassword();
//        System.out.println(password);
//        System.out.println(oldPassword);
//        System.out.println(newPassword);
//        String username = SecurityUtils.getUsername();
//        BytesmartEmployee employee = employeeService.selectEmployeeByUsername(username);
//        String password = employee.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return error("新密码不能与旧密码相同");
        }
        if (employeeService.resetEmployeerPwd(username, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
//            aLoginEmployee loginEmployee = SecurityUtils.getLoginEmployee();
//            loginEmployee.getBytesmartEmployee().setPassword(SecurityUtils.encryptPassword(newPassword));
//            tokenService.setLoginEmployee(loginEmployee);
            employeeService.resetPwd(employee);
            return success();
        }
        return error("修改密码异常，请联系管理员");

    }

    /**
     * 头像上传
     */
//    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
//    @PostMapping("/avatar")
//    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file)
//    {
//        if (!file.isEmpty())
//        {
//            aLoginEmployee loginEmployee = SecurityUtils.getLoginEmployee();
//            String extension = FileTypeUtils.getExtension(file);
//            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION))
//            {
//                return error("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
//            }
//            R<SysFile> fileResult = remoteFileService.upload(file);
//            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData()))
//            {
//                return error("文件服务异常，请联系管理员");
//            }
//            String url = fileResult.getData().getUrl();
//            if (employeeService.updateEmployeeAvatar(loginEmployee.getUsername(), url))
//            {
//                AjaxResult ajax = AjaxResult.success();
//                ajax.put("imgUrl", url);
//                // 更新缓存用户头像
//                loginEmployee.getBytesmartEmployee().setEmployeeAvatar(url);
//                tokenService.setLoginEmployee(loginEmployee);
//                return ajax;
//            }
//        }
//        return error("上传图片异常，请联系管理员");
//    }


}
