import request from '@/utils/request'

export const getSysDbTypeById = (id) =>
  request({
    url: '/dbtype/sysDbType/' + id,
    method: 'get'
  })

export const listSysDbTypePage = (search) =>
  request({
    url: '/dbtype/sysDbType/list',
    method: 'post',
    data: search
  })

export const listSysDbTypeAll = (search) =>
  request({
    url: '/dbtype/sysDbType/listAll',
    method: 'post',
    data: search
  })

export const saveSysDbType = (sysDbType) =>
  request({
    url: '/dbtype/sysDbType/save',
    method: 'post',
    data: sysDbType
  })

export const deleteSysDbType = (sysDbType) =>
  request({
    url: '/dbtype/sysDbType/delete',
    method: 'post',
    data: sysDbType
  })

export const bulkInsertSysDbType = (sysDbTypes) =>
  request({
    url: '/dbtype/sysDbType/bulkInsert',
    method: 'post',
    data: sysDbTypes
  })

export const bulkUpdateSysDbType = (sysDbTypes) =>
  request({
    url: '/dbtype/sysDbType/bulkUpdate',
    method: 'post',
    data: sysDbTypes
  })

export const bulkDeleteSysDbType = (sysDbTypes) =>
  request({
    url: '/dbtype/sysDbType/bulkDelete',
    method: 'post',
    data: sysDbTypes
  })
