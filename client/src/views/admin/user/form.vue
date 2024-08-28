<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="30%"
      :fullscreen="false"
      :title="dialogProps.title"
      :visible.sync="dialogProps.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
    >
      <el-row>
        <el-form
          ref="editForm"
          :model="editFormData"
          label-width="100px"
          :disabled="action == 'view'"
          class="dc-el-form_ElEditForm"
        >
          <el-row>
            <el-form-item
              prop="name"
              label="名称"
              :rules="[{ required: true, message: '名称不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.name"
                :maxLength="128"
                placeholder="请输入名称"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="organ"
              label="组织机构"
              :rules="[{ required: true, message: '组织机构不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.organ"
                :maxLength="1024"
                placeholder="请输入组织机构"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="loginName"
              label="账号"
              :rules="[{ required: true, message: '账号不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.loginName"
                :maxLength="10"
                placeholder="请输入登录账号"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item prop="loginPasswordUpdate" label="修改密码" class="dc-el-form-item_Switch">
              <el-switch
                v-model="editFormData.loginPasswordUpdate"
                :active-value="true"
                :inactive-value="false"
                class="dc-el-switch_Switch"
              ></el-switch>
            </el-form-item>
            <el-form-item
              prop="loginPassword"
              label="密码"
              v-if="action != 'view' && editFormData.loginPasswordUpdate == true"
              :rules="[{ required: true, message: '密码不能为空', trigger: 'blur' }]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.loginPassword"
                :maxLength="-1"
                placeholder="请输入密码"
                clearable
                show-password
                autocomplete="new-password"
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="loginPasswordConfirm"
              label="确认密码"
              v-if="action != 'view' && editFormData.loginPasswordUpdate == true"
              :rules="[
                { required: true, message: '确认密码不能为空', trigger: 'blur' },
                { pattern: new RegExp(`^${editFormData.loginPassword}$`), message: '两次输入密码不一致', trigger: 'blur' }
              ]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.loginPasswordConfirm"
                :maxLength="-1"
                placeholder="请输入"
                clearable
                show-password
                autocomplete="new-password"
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item
              prop="isLocked"
              label="禁用"
              :rules="[{ required: true, message: '禁用不能为空', trigger: 'change' }]"
              class="dc-el-form-item_Switch"
            >
              <el-switch
                v-model="editFormData.isLocked"
                active-value="1"
                inactive-value="0"
                class="dc-el-switch_Switch"
              ></el-switch>
            </el-form-item>
            <el-form-item
              prop="email"
              label="邮箱地址"
              :rules="[
                { required: true, message: '邮箱地址不能为空', trigger: 'blur' },
                {
                  pattern: new RegExp(`^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$`),
                  message: '请输入正确的邮箱',
                  trigger: 'blur'
                }
              ]"
              class="dc-el-form-item_SingleInput"
            >
              <el-input
                v-model="editFormData.email"
                :maxLength="36"
                placeholder="请输入邮箱地址"
                clearable
                class="dc-el-input_SingleInput"
              ></el-input>
            </el-form-item>
            <el-form-item prop="remarks" label="备注信息" class="dc-el-form-item_MutilpleInput">
              <el-input
                v-model="editFormData.remarks"
                type="textarea"
                placeholder="请输入备注信息"
                rows="2"
                :maxLength="255"
                class="dc-el-input_MutilpleInput"
              ></el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button v-on:click="onSubmit" type="primary" v-show="action != 'view'">保存</el-button>
        <el-button v-on:click="onDialogClose" v-if="action != 'view'">取消</el-button>
        <el-button v-on:click="onDialogClose" v-if="action == 'view'">关闭</el-button>
      </span>
    </el-dialog>
  </el-row>
</template>
<script>
import { validatenull } from '@/utils/validate'
/** 根据用户界面配置import组件 结束 */

/** 根据用户界面配置import组件 结束 */
import { getUserById, saveUser } from '@/api/admin/user'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'user-form',
  components: {
    /** 根据用户界面配置组件 开始 */

    /** 根据用户界面配置组件 结束 */

    OperationIcon
  },
  data() {
    return {
      /** 根据用户界面配置生成data数据 开始 */
      editFormData: this.initEditData(),
      // 对话框属性变量
      dialogProps: {
        visible: false,
        title: '用户管理'
      },
      dialogTitle: '用户管理',
      // 选项变量

      /** 根据用户界面配置生成data数据 结束 */

      // 窗口操作类型 view/edit/add
      action: ''
    }
  },
  props: {
    // 权限
    permission: {
      type: Object
    }
  },
  computed: {},
  methods: {
    onSubmit() {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          this.doSave()
        } else {
          return false
        }
      })
    },
    doSave() {
      this.setLoad()

      saveUser(this.editFormData)
        .then((responseData) => {
          if (responseData.code == 100) {
            this.dialogProps.visible = false
            this.showMessage({
              type: 'success',
              msg: '保存成功'
            })
            this.$emit('save-finished')
          } else {
            this.showMessage(responseData)
          }
          this.resetLoad()
        })
        .catch((error) => {
          this.outputError(error)
        })
    },
    switchEdit() {
      this.action = 'edit'
      this.dialogProps.title = `修改${this.dialogTitle}`
      this.initOptions(this.editFormData)
    },

    getFormById(id) {
      this.setLoad()
      return new Promise((resolve) => {
        getUserById(id)
          .then((responseData) => {
            let form = {}
            if (responseData.code == 100) {
              form = responseData.data
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
            resolve(form)
          })
          .catch((error) => {
            this.outputError(error)
          })
      })
    },
    onDialogClose() {
      this.dialogProps.visible = false
    },
    onDialogOpen() {
      this.$nextTick(() => {
        this.$refs['editForm'].clearValidate()
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量
    },
    initEditData(This) {
      return {
        loginPasswordUpdate: this.dialogProps && this.action == 'add' ? true : false, // 是否修改密码
        name: '', // 名称
        organ: '', // 组织机构
        loginName: '', // 账号
        loginPassword: '', // 密码
        isLocked: '0', // 禁用
        email: '', // 邮箱地址
        remarks: '' // 备注信息
      }
    }
  },
  watch: {},
  mounted: function () {
    this.$nextTick(() => {
      this.$on('openViewDialog', async function (id) {
        this.action = 'view'
        this.dialogProps.title = `查看${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })

      this.$on('openEditDialog', async function (id) {
        this.action = 'edit'
        this.dialogProps.title = `修改${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function () {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData()
        this.initOptions(this.editFormData)
        this.dialogProps.visible = true
      })
      this.$on('openCopyDialog', async function (id) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...(await this.getFormById(id))
        }
        this.initOptions(this.editFormData)
        this.editFormData.id = null //把id设置为空，添加一个新的

        //TODO 修改加密字段
        this.editFormData.loginPasswordUpdate = true // 允许修改密码

        this.dialogProps.visible = true
      })
    })
  }
}
</script>
