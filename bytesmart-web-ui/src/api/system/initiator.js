import request from '@/utils/request'

// 查询任务列表
// export function listTaskByInitiator(query) {
//   return request({
//     url: '/webtask/task/list',
//     method: 'get',
//     params: query
//   })
// }

// 查询岗位详细
export function getPost(postId) {
  return request({
    url: '/system/post/' + postId,
    method: 'get'
  })
}

// 查询任务详细
export function getTaskByInitiatorList(query) {
  return request({
    url: '/webtask/task/initiator',
    method: 'get',
    params: query
  })
}

// 新增任务
// export function addTask(data) {
//   return request({
//     url: '/webtask/task',
//     method: 'post',
//     data: data
//   })
// }

// // 修改任务
export function updateTask(data) {
  return request({
    url: '/webtask/task',
    method: 'put',
    data: data
  })
}

// // 角色数据权限
// export function dataScope(data) {
//   return request({
//     url: '/system/role/dataScope',
//     method: 'put',
//     data: data
//   })
// }

// // 角色状态修改
// export function changeRoleStatus(roleId, status) {
//   const data = {
//     roleId,
//     status
//   }
//   return request({
//     url: '/system/role/changeStatus',
//     method: 'put',
//     data: data
//   })
// }

// // 删除角色
// export function delRole(roleId) {
//   return request({
//     url: '/system/role/' + roleId,
//     method: 'delete'
//   })
// }

// // 查询角色已授权用户列表
// export function allocatedUserList(query) {
//   return request({
//     url: '/system/role/authUser/allocatedList',
//     method: 'get',
//     params: query
//   })
// }

// // 查询角色未授权用户列表
// export function unallocatedUserList(query) {
//   return request({
//     url: '/system/role/authUser/unallocatedList',
//     method: 'get',
//     params: query
//   })
// }

// // 取消用户授权角色
// export function authUserCancel(data) {
//   return request({
//     url: '/system/role/authUser/cancel',
//     method: 'put',
//     data: data
//   })
// }

// // 批量取消用户授权角色
// export function authUserCancelAll(data) {
//   return request({
//     url: '/system/role/authUser/cancelAll',
//     method: 'put',
//     params: data
//   })
// }

// // 授权用户选择
// export function authUserSelectAll(data) {
//   return request({
//     url: '/system/role/authUser/selectAll',
//     method: 'put',
//     params: data
//   })
// }

// // 根据角色ID查询部门树结构
// export function deptTreeSelect(roleId) {
//   return request({
//     url: '/system/role/deptTree/' + roleId,
//     method: 'get'
//   })
// }
