
/**
 * 下载文件
 * @param {*} resp     服务器端返回的文件内容
 * @param {*} fileName 文件名
 * @param {*} fileType 文件类型
 */
 export function download(resp, fileName, fileType) {
    let blob
    if(fileType) {
      blob = new Blob([resp], {type: fileType})
    } else {
      blob = new Blob([resp])
    }
     
    const url = window.URL.createObjectURL(blob)
    let dom = document.createElement("a")
    dom.style.display = "none"
    dom.href = url
    dom.setAttribute("download", fileName)
    document.body.appendChild(dom)
    dom.click()
    document.body.removeChild(dom)
  }
  