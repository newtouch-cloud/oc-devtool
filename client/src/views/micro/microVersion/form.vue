<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="50%"
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
            <el-col :span="12">
              <el-form-item
                prop="type.id"
                label="版本类型"
                :rules="[{ required: true, message: '版本类型不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.type"
                  :style="{ width: '100%' }"
                  placeholder="请选择版本类型"
                  clearable
                  value-key="id"
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option v-for="(item, index) in typeOptions" :key="index" :label="item.name" :value="item"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                prop="name"
                label="版本号"
                :rules="[{ required: true, message: '版本号不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.name"
                  :maxLength="10"
                  placeholder="请输入版本号"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item
                prop="url"
                label="应用入口"
                :rules="[{ required: true, message: '应用入口不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.url"
                  :maxLength="1024"
                  placeholder="请输入应用入口"
                  clearable
                  autocomplete="new-password"
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item
                prop="mobilUrl"
                label="	 移动端入口"
                :rules="[{ required: true, message: '	 移动端入口不能为空', trigger: 'blur' }]"
                class="dc-el-form-item_SingleInput"
              >
                <el-input
                  v-model="editFormData.mobilUrl"
                  :maxLength="1024"
                  placeholder="请输入	 移动端入口"
                  clearable
                  class="dc-el-input_SingleInput"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item
                prop="dbType.id"
                label="数据库类型"
                :rules="[{ required: true, message: '数据库类型不能为空', trigger: 'change' }]"
                class="dc-el-form-item_SelectInput"
              >
                <el-select
                  v-model="editFormData.dbType"
                  :style="{ width: '100%' }"
                  placeholder="请选择数据库类型"
                  clearable
                  value-key="id"
                  filterable
                  class="dc-el-select_SelectInput"
                >
                  <el-option v-for="(item, index) in dbTypeOptions" :key="index" :label="item.name" :value="item"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="isLocked" label="停用" class="dc-el-form-item_Switch">
                <el-switch
                  v-model="editFormData.isLocked"
                  active-value="1"
                  inactive-value="0"
                  class="dc-el-switch_Switch"
                ></el-switch>
              </el-form-item>
            </el-col>
            <el-col :span="24">
              <el-form-item prop="remarks" label="版本描述" class="dc-el-form-item_MutilpleInput">
                <el-input
                  v-model="editFormData.remarks"
                  type="textarea"
                  placeholder="请输入版本描述"
                  rows="2"
                  :maxLength="255"
                  class="dc-el-input_MutilpleInput"
                ></el-input>
              </el-form-item>
            </el-col>
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
import { listDictItemAll } from '@/api/sys/dictItem.js'
import { listSysDbTypeAll } from '@/api/dbtype/sysDbType.js'
/** 根据用户界面配置import组件 结束 */
import { getMicroVersionById, saveMicroVersion } from '@/api/micro/microVersion'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
export default {
  extends: BaseUI,
  name: 'microVersion-form',
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
        title: '版本'
      },
      dialogTitle: '版本',
      // 选项变量
      // 版本类型选项
      typeOptions: [],
      // 数据库类型选项
      dbTypeOptions: [],
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

      saveMicroVersion(this.editFormData)
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
        getMicroVersionById(id)
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

    listTypeOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [
        {
          columnName: 'dict_type_id',
          queryType: '=',
          value: '1368150871391518806'
        }
      ])

      // 数据权限: 字典项sys_dict_item
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, '1290867985774919713')

      listDictItemAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.typeOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    listDbTypeOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      // 数据权限: 数据库类型sys_db_type
      this.pushDataPermissions(search_List.params, this.$route.meta.routerId, '1371119793305198596')

      listSysDbTypeAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.dbTypeOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

      this.listTypeOptions()

      this.listDbTypeOptions()
    },
    initEditData(This) {
      return {
        type: {
          id: null,
          name: null
        },
        name: '', // 版本号
        url: '', // 应用入口
        mobilUrl: '', // 	 移动端入口
        dbType: {
          id: null,
          name: null,
          driver: null,
          rule: null
        },
        isLocked: '0', // 停用
        remarks: '' // 版本描述
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
      this.$on('openAddDialog', function (parent) {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = {
          ...this.initEditData(),
          ...parent
        }
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

        this.dialogProps.visible = true
      })
    })
  }
}
</script>
