/**
 * @Description 环境变量配置
 * @param env 环境变量
 * @return 环境配置对象
 */
const conFun = (env) => {
  if (env === 'development') {
    return {
      NODE_ENV: "development",
      BASE_API: "/api",	//proxy table启用时，只需要指定访问的路径
      WEB_SOCKET_URL: "ws://127.0.0.1:9998/websocket/",

      // 报表地址配置
      REPORT_CLIENT_URL: "http://localhost:9528/",
      REPORT_SERVER_URL: "http://localhost:8081/",

      // 文件在线预览地址
      FILE_PREVIEW_URL: "http://377328t1x9.zicp.vip:52265/",
      // kkFile在线预览地址
      KK_FILE_URL: "http://192.168.0.4:8012/onlinePreview?url=",
    }
  } else if (env === 'test') {
    return {
      NODE_ENV: "test",
      BASE_API: "/devtool-back-test",
      WEB_SOCKET_URL: "/devtool-back-test/websocket/",
    }
  } else {
    return {
      NODE_ENV: "production",
      BASE_API: "http://59.80.34.149/lease-backend-test",
      WEB_SOCKET_URL: "ws://59.80.34.149/lease-backend-test/websocket/",

      // 报表地址配置
      REPORT_CLIENT_URL: "http://59.80.34.149/dataease/frontend/",
      REPORT_SERVER_URL: "http://59.80.34.149/dataease/backend/",

      // 文件在线预览地址
      FILE_PREVIEW_URL: "http://59.80.34.149/lease-backend-test/",
      // kkFile在线预览地址
      KK_FILE_URL: "http://59.80.34.149/ffview/onlinePreview?url=",
    }
  }
}
window.config = conFun
