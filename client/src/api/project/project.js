import request from '@/utils/request'

export const getProjectById = (id) =>
  request({
    url: '/project/project/' + id,
    method: 'get'
  })

export const listProjectPage = (search) =>
  request({
    url: '/project/project/list',
    method: 'post',
    data: search
  })

export const listProjectAll = (search) =>
  request({
    url: '/project/project/listAll',
    method: 'post',
    data: search
  })

export const saveProject = (project) =>
  request({
    url: '/project/project/save',
    method: 'post',
    data: project
  })

export const deleteProject = (project) =>
  request({
    url: '/project/project/delete',
    method: 'post',
    data: project
  })

export const bulkInsertProject = (projects) =>
  request({
    url: '/project/project/bulkInsert',
    method: 'post',
    data: projects
  })

export const bulkUpdateProject = (projects) =>
  request({
    url: '/project/project/bulkUpdate',
    method: 'post',
    data: projects
  })

export const bulkDeleteProject = (projects) =>
  request({
    url: '/project/project/bulkDelete',
    method: 'post',
    data: projects
  })

export const linkJdbcConnection = (project) =>
  request({
    url: '/project/project/linkJdbcConnection',
    method: 'post',
    data: project
  })

export const checkDataSource = (projectId) =>
  request({
    url: '/project/project/checkDataSource/' + projectId,
    method: 'get'
  })

export const listProjectTableColumn = (search) =>
  request({
    url: '/project/project/listProjectTableColumn',
    method: 'post',
    data: search
  })