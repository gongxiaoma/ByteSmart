package com.bytesmart.webauth.controller;

import com.bytesmart.apisystem.RemoteFileService;
import com.bytesmart.apisystem.domain.BytesmartEmployee;
import com.bytesmart.apisystem.domain.SysFile;
import com.bytesmart.apisystem.domain.SysUser;
import com.bytesmart.apisystem.model.LoginUser;
import com.bytesmart.common.core.domain.R;
import com.bytesmart.common.core.utils.StringUtils;
import com.bytesmart.common.core.utils.file.FileTypeUtils;
import com.bytesmart.common.core.utils.file.MimeTypeUtils;
import com.bytesmart.common.core.web.controller.BaseController;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.bytesmart.springsecurity.domain.WebLoginUser;
import com.bytesmart.springsecurity.service.WebTokenService;
import com.bytesmart.springsecurity.utils.WebSecurityUtils;

import com.bytesmart.webauth.service.IBytesmartEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.util.Arrays;

/**
 * 个人信息 业务处理
 *
 * @author hd
 */
@RestController
@RequestMapping("/user/profile")
public class BytesmartProfileController extends BaseController
{
    @Autowired
    private IBytesmartEmployeeService employeeService;

    @Autowired
    private WebTokenService webtokenService;



    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        String username = WebSecurityUtils.getUsername();
        System.out.println(username);
        BytesmartEmployee employee = employeeService.selectEmployeeByUsername(username);
        System.out.println(employee);
        AjaxResult ajax = AjaxResult.success(employee);
        ajax.put("roleGroup", employeeService.selectEmployeeRoleGroup(username));
        ajax.put("postGroup", employeeService.selectEmployeePostGroup(username));
        System.out.println(employee);
        return ajax;
    }

    /**
     * 修改用户
     */

    @PutMapping
    public AjaxResult updateProfile(@RequestBody BytesmartEmployee bytesmartEmployee)
    {
        WebLoginUser webLoginUser = WebSecurityUtils.getWebLoginUser();
        BytesmartEmployee currentEmployee = webLoginUser.getEmployee();
        currentEmployee.setEmployeeName(bytesmartEmployee.getEmployeeName());
        currentEmployee.setEmployeeEmail(bytesmartEmployee.getEmployeeEmail());
        currentEmployee.setEmployeeMobile(bytesmartEmployee.getEmployeeMobile());
        currentEmployee.setEmployeeGender(bytesmartEmployee.getEmployeeGender());

        if (StringUtils.isNotEmpty(bytesmartEmployee.getEmployeeMobile()) && !employeeService.checkPhoneUnique(currentEmployee))
        {
            return error("修改用户'" + bytesmartEmployee.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(bytesmartEmployee.getEmployeeEmail()) && !employeeService.checkEmailUnique(currentEmployee))
        {
            return error("修改用户'" + bytesmartEmployee.getUserName() + "'失败，邮箱账号已存在");
        }
        if (employeeService.updateEmployeeProfile(currentEmployee) > 0)
        {
            // 更新缓存用户信息
            webtokenService.setWebLoginUser(webLoginUser);
            System.out.println(bytesmartEmployee.getEmployeeGender());
            return success();
        }
        return error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */

    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        String username = WebSecurityUtils.getUsername();
        BytesmartEmployee employee = employeeService.selectEmployeeByUsername(username);
        String password = employee.getPassword();
        if (!WebSecurityUtils.matchesPassword(oldPassword, password))
        {
            return error("修改密码失败，旧密码错误");
        }
        if (WebSecurityUtils.matchesPassword(newPassword, password))
        {
            return error("新密码不能与旧密码相同");
        }
        if (employeeService.resetEmployeerPwd(username, WebSecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            WebLoginUser webLoginUser = WebSecurityUtils.getWebLoginUser();
            webLoginUser.getEmployee().setPassword(WebSecurityUtils.encryptPassword(newPassword));
            webtokenService.setWebLoginUser(webLoginUser);
            return success();
        }
        return error("修改密码异常，请联系管理员");
    }
//
//    /**
//     * 头像上传
//     */
//    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
//    @PostMapping("/avatar")
//    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file)
//    {
//        if (!file.isEmpty())
//        {
//            LoginUser loginUser = SecurityUtils.getLoginUser();
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
//            if (userService.updateUserAvatar(loginUser.getUsername(), url))
//            {
//                AjaxResult ajax = AjaxResult.success();
//                ajax.put("imgUrl", url);
//                // 更新缓存用户头像
//                loginUser.getSysUser().setAvatar(url);
//                tokenService.setLoginUser(loginUser);
//                return ajax;
//            }
//        }
//        return error("上传图片异常，请联系管理员");
//    }
}
