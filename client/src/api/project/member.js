import request from '@/utils/request'

export const getMemberById = (id) =>
  request({
    url: '/project/member/' + id,
    method: 'get'
  })

export const listMemberPage = (search) =>
  request({
    url: '/project/member/list',
    method: 'post',
    data: search
  })

export const listMemberAll = (search) =>
  request({
    url: '/project/member/listAll',
    method: 'post',
    data: search
  })

export const saveMember = (member) =>
  request({
    url: '/project/member/save',
    method: 'post',
    data: member
  })

export const deleteMember = (member) =>
  request({
    url: '/project/member/delete',
    method: 'post',
    data: member
  })

export const bulkInsertMember = (members) =>
  request({
    url: '/project/member/bulkInsert',
    method: 'post',
    data: members
  })

export const bulkUpdateMember = (members) =>
  request({
    url: '/project/member/bulkUpdate',
    method: 'post',
    data: members
  })

export const bulkDeleteMember = (members) =>
  request({
    url: '/project/member/bulkDelete',
    method: 'post',
    data: members
  })
