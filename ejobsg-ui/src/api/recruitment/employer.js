import request from '@/utils/request'

// 查询雇主列表
export function listEmployer(query) {
  return request({
    url: '/recruitment/employer/list',
    method: 'get',
    params: query
  })
}

// 查询雇主详细
export function getEmployer(id) {
  return request({
    url: '/recruitment/employer/' + id,
    method: 'get'
  })
}

// 新增雇主
export function addEmployer(data) {
  return request({
    url: '/recruitment/employer',
    method: 'post',
    data: data
  })
}

// 修改雇主
export function updateEmployer(data) {
  return request({
    url: '/recruitment/employer',
    method: 'put',
    data: data
  })
}

// 删除雇主
export function delEmployer(id) {
  return request({
    url: '/recruitment/employer/' + id,
    method: 'delete'
  })
}
