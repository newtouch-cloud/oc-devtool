<template>
  <el-collapse v-model="activeNames" v-loading="loading">
    <el-collapse-item name="3">
      <template slot="title">
        <span class="collapse-title">{{title}}<span style="color: #ccc">（展开查看更多）</span></span>
      </template>
      <el-upload
        :disabled='action == "view"'
        class="upload-demo"
        multiple
        list-type="picture-card"
        action=""
        :auto-upload="false"
        accept=".jpg,.jpeg,.png"
        :file-list="fileList"
        :headers="uploadHeaders"
        :limit="limit"
        :on-preview="onPreviewFile"
        :on-remove="onRemoveFileList"
        :before-remove="beforeRemoveFile"
        :on-success="fileSuccess"
        :on-exceed="onExceedFile"
        :on-change="onChangeFileList">
        <i slot="default" class="el-icon-plus"></i>
        <div slot="file" slot-scope="{file}">
          <img
            class="el-upload-list__item-thumbnail"
            :src="file.url" alt=""
          >
          <span class="el-upload-list__item-actions">
            <span
              class="el-upload-list__item-preview"
              @click="onViewFileList(file)"
            >
              <i class="el-icon-zoom-in"></i>
            </span>
            <span
              v-if='action != "view"'
              class="el-upload-list__item-delete"
              @click="onUploadViewFile(file)"
            >
              <i class="el-icon-download"></i>
            </span>
            <span
              v-if='action != "view"'
              class="el-upload-list__item-delete"
              @click="onRemoveViewFile(file)"
            >
              <i class="el-icon-delete"></i>
            </span>
          </span>
        </div>
      </el-upload>
      <el-dialog :visible.sync="dialogVisible" append-to-body="true">
        <img width="100%" :src="dialogImageUrl" alt="">
      </el-dialog>
    </el-collapse-item>
  </el-collapse>
</template>

<script>
  import { validatenull } from '@/utils/validate'
  import { fileUploadById, deleteSysFile, listSysFileAll } from '@/api/sys/sysFile'
  import OperationIcon from '@/components/OperationIcon'
  import BaseUI from '@/views/components/baseUI'
  import { getLocalToken } from '@/utils/auth'

  let Base64 = require('js-base64').Base64
  export default {
    extends: BaseUI,
    name: "uploadFile",
    props: {
      value: {
        type: Object
      },
      action: {
        type: String
      },
      objectId: {
        type: String | Number
      },
      title: {
        type: String,
        default: () => {
          return ''
        }
      },
      limit:{
        type : String,
        default: () => {
          return '1'
        }
      },
      fileSizes:{
        type: String | Number,
        default: () => {
          return 1
        }
      },
      fileFormats:{
        type: Array,
        default: () => {
          return ['jpg', 'png', 'jpeg']
        }
      },
    },
    data() {
      return {
        uploadBtnId: 'uploadBtn' + Math.random(),  // 上传文件动态id，解决一个页面多个文件上传组件存在问题

        activeNames: [], // 折叠面板
        uploadHeaders: {'X-Token': getLocalToken()},
        baseApi: window.config(process.env.NODE_ENV).BASE_API,
        fileList:  [],
        loading: false,

        dialogImageUrl: '',
        dialogVisible: false,
      }
    },
    components: {
      OperationIcon
    },
    methods: {
      onRemoveFileList: function (file, fileList) { // 移除文件
        this.value.uploads = fileList
      },
      onUploadViewFile(row) { // 下载
        let dom = document.createElement("a");
        dom.style.display = "none";
        dom.href = row.url;
        dom.setAttribute("download", row.name);
        document.body.appendChild(dom);
        dom.click();
      },
      onPreviewFile(file) {
        console.log(file);
      },
      onExceedFile(file, fileList) {
        this.$message.warning(`当前限制上传`+ this.limit +`个文件`);
      },
      beforeRemoveFile(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      fileSuccess(res, file){                 //上传文件成功
        console.log(file);
      },
      onChangeFileList(file, fileList) {
        //获取后缀名
        const suffix = file.name.substring(file.name.lastIndexOf('.') + 1)
        //判断是否符合文件大小的限制需求
        let isLt2M = true;
        if (this.fileSizes) {
          isLt2M = file.size / 1024 / 1024 < this.fileSizes
        }
        //判断是否符合文件类型需求
        // let accept ='jpg/png/gif/jpeg'
        let isLtType = true;
        if (this.fileFormats) {
          isLtType = this.fileFormats.indexOf(suffix) >= 0
        }
        if (!isLtType) {
          this.$message.error(`上传文件格式必须为${this.fileFormats}`);
        }
        if (!isLt2M) {
          this.$message.error(`上传文件大小不能超过${this.fileSizes}M!`);
        }
        if (isLtType && isLt2M) {
          this.fileList = fileList
          this.value.uploads = fileList
        }else{
          this.fileList.splice(fileList.length - 1, 1)
          return false;
        }
      },
      onViewFileList(row) { // 预览
        this.dialogImageUrl = row.url;
        this.dialogVisible = true;
      },
      onRemoveViewFile(row) { // 从数据库上传已存在文件
        this.$confirm(`确定移除 ${ row.name }吗？`, '确认', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(() => {
          if (!validatenull(row.id)) {
            let index = this.fileList.findIndex(item => {
              return item.id == row.id
            })
            this.fileList.splice(index, 1)
            this.value.deletes.push(row.id)
          } else {
            let index = this.fileList.findIndex(item => {
              return item.uid == row.uid
            })
            this.fileList.splice(index, 1)
          }
        })
      },
      // 根据objectId，获取附件信息
      getSysFileAll() {
        this.setLoad()
        let file_search = {
          params: [{'columnName':'object_id', 'queryType': '=', 'value': this.objectId}]
        }
        listSysFileAll(file_search).then(responseData => {
          if(responseData.code == 100) {
            this.fileList = responseData.data
            if (!validatenull(this.fileList)) {
              this.activeNames.push("3")
              for(let i = 0; i < this.fileList.length; i++) {
                fileUploadById(this.fileList[i].id).then(responseData => {
                  const content = responseData;
                  const blob = new Blob([content]);
                  const url = window.URL.createObjectURL(blob);
                  this.$set(this.fileList[i], 'url', url)
                }).catch(error => {
                  this.outputError(error)
                });
              }
            }
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        }).catch(error => {
          this.outputError(error)
        })
      },
      initOption() { // 初始化数据
        this.fileList = []  // 上传附件列表
        this.activeNames = [] // 折叠面板初始化
        if (!validatenull(this.objectId)) {
          this.getSysFileAll()
        }
      }
    },
    watch: {
      'objectId': function(newVal, oldVal) {
        this.initOption()
      }
    },
    mounted: function() {
      this.initOption()
    }
  }
</script>

<style lang="scss" scoped>
  .collapse-title {
    font-size: 14px;
    color: #606266;
  }
</style>
