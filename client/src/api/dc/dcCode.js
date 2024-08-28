import request from '@/utils/request'

export const downProject = (projectId) => 
    request({
        url: '/dc/download/project/' + projectId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 120000
    })

export const downBack = (projectId) => 
    request({
        url: '/dc/download/back/' + projectId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 120000
    })

export const downClient = (projectId) => 
    request({
        url: '/dc/download/client/' + projectId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 120000
    })

export const downBackScheme = (schemeId) => 
    request({
        url: '/dc/download/backScheme/' + schemeId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 30000
    })

export const downClientScheme = (schemeId) => 
    request({
        url: '/dc/download/clientScheme/' + schemeId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 30000
    })

export const downMobileScheme = (mobileId) => 
    request({
        url: '/dc/download/mobileScheme/' + mobileId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 30000
    })

export const downMobile = (projectId) => 
    request({
        url: '/dc/download/mobile/' + projectId,
        method: 'get',
        type:'blob',
        responseType: 'arraybuffer',
        timeout: 120000
    })