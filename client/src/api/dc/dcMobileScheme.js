import request from '@/utils/request'

export const getDcMobileSchemeById = (id) =>
  request({
    url: '/dc/dcMobileScheme/' + id,
    method: 'get'
  })

export const listDcMobileSchemePage = (search) =>
  request({
    url: '/dc/dcMobileScheme/list',
    method: 'post',
    data: search
  })

export const listDcMobileSchemeAll = (search) =>
  request({
    url: '/dc/dcMobileScheme/listAll',
    method: 'post',
    data: search
  })

export const saveDcMobileScheme = (dcMobileScheme) =>
  request({
    url: '/dc/dcMobileScheme/save',
    method: 'post',
    data: dcMobileScheme
  })

export const deleteDcMobileScheme = (dcMobileScheme) =>
  request({
    url: '/dc/dcMobileScheme/delete',
    method: 'post',
    data: dcMobileScheme
  })

export const bulkInsertDcMobileScheme = (dcMobileSchemes) =>
  request({
    url: '/dc/dcMobileScheme/bulkInsert',
    method: 'post',
    data: dcMobileSchemes
  })

export const bulkUpdateDcMobileScheme = (dcMobileSchemes) =>
  request({
    url: '/dc/dcMobileScheme/bulkUpdate',
    method: 'post',
    data: dcMobileSchemes
  })

export const bulkDeleteDcMobileScheme = (dcMobileSchemes) =>
  request({
    url: '/dc/dcMobileScheme/bulkDelete',
    method: 'post',
    data: dcMobileSchemes
  })
