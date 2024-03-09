import request from '@/utils/request'
import { parseStrEmpty } from "@/utils/ruoyi";

// 查询用户列表
export function listEmployee(query) {
  return request({
    url: '/webadmin/employee/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getEmployee(employeeId) {
  return request({
    url: '/webadmin/employee/' + parseStrEmpty(employeeId),
    method: 'get'
  })
}

// 新增用户
export function addEmployee(data) {
  return request({
    url: '/webadmin/employee',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateEmployee(data) {
  return request({
    url: '/webadmin/employee',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delEmployee(employeeId) {
  return request({
    url: '/webadmin/employee/' + employeeId,
    method: 'delete'
  })
}

// 用户密码重置
export function resetUserPwd(employeeId, password) {
  const data = {
    employeeId,
    password
  }
  return request({
    url: '/webadmin/employee/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeEmployeeStatus(employeeId, employeeStatus) {
  const data = {
    employeeId,
    employeeStatus
  }
  return request({
    url: '/webadmin/employee/changeStatus',
    method: 'put',
    data: data
  })
}

//查询用户个人信息
export function getEmployeeProfile() {
  return request({
    url: '/web/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateEmployeeProfile(data) {
  return request({
    url: '/web/user/profile',
    method: 'put',
    data: data
  })
}

// 用户个人中心密码重置
export function updateEmployeePwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/web/user/profile/updatePwd',
    method: 'put',

    
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/webadmin/employee/profile/avatar',
    method: 'post',
    data: data
  })
}

// 查询授权角色
export function getAuthRole(employeeId) {
  return request({
    url: '/webadmin/employee/authRole/' + employeeId,
    method: 'get'
  })
}

// 保存授权角色
export function updateAuthRole(data) {
  return request({
    url: '/webadmin/employee/authRole',
    method: 'put',
    params: data
  })
}

// 查询部门下拉树结构
export function deptTreeSelect() {
  return request({
    url: '/webadmin/employee/deptTree',
    method: 'get'
  })
}
