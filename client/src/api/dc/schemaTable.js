import request from '@/utils/request'

import axios from 'axios'

export const getSchemaTableById = (id) =>
    request({
        url: '/dc/schemaTable/' + id,
        method: 'get'
    })

export const listSchemaTablePage = (search) =>
    request({
        url: '/dc/schemaTable/list',
        method: 'post',
        data: search
    })

export const listSchemaTableAll = (search , projectId) =>
    request({
        url: '/dc/schemaTable/listAll',
        method: 'post',
        data: search,
        headers:{
          "project_id": projectId
        }
    })


export const saveSchemaTable = (schemaTable) =>
    request({
        url: '/dc/schemaTable/save',
        method: 'post',
        data: schemaTable
    })

export const deleteSchemaTable = (schemaTable) =>
    request({
        url: '/dc/schemaTable/delete',
        method: 'post',
        data: schemaTable
    })

