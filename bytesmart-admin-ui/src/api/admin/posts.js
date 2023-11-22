import request from '@/utils/request'

// 查询岗位列表
export function listPosts(query) {
  return request({
    url: '/admin/posts/list',
    method: 'get',
    params: query
  })
}

// 查询岗位详细
export function getPosts(postId) {
  return request({
    url: '/admin/posts/' + postId,
    method: 'get'
  })
}

// 新增岗位
export function addPosts(data) {
  return request({
    url: '/admin/posts',
    method: 'post',
    data: data
  })
}

// 修改岗位
export function updatePosts(data) {
  return request({
    url: '/admin/posts',
    method: 'put',
    data: data
  })
}

// 删除岗位
export function delPosts(postId) {
  return request({
    url: '/admin/posts/' + postId,
    method: 'delete'
  })
}
