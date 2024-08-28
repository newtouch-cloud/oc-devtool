<template>
  <el-row class="dc-container">
    <el-dialog
      v-on:open="onDialogOpen"
      v-on:close="onDialogClose"
      v-loading="loading"
      width="70%"
      :fullscreen="false"
      :title="dialogProps.title"
      :visible.sync="dialogProps.visible"
      :close-on-click-modal="false"
      class="dc-el-dialog_ElDialog"
    >
      <el-row>
        <el-tabs v-model="tabName" type="border-card" tab-position="top" class="dc-el-tabs_ElTabs">
          <el-tab-pane label="项目信息" name="editForm" class="dc-el-tab-pane_ElTabPane">
            <el-form
              ref="editForm"
              :model="editFormData"
              label-width="100px"
              :disabled="action == 'view'"
              class="dc-el-form_ElEditForm"
            >
              <el-row>
                <el-col :span="16">
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
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="status" label="归档" class="dc-el-form-item_Switch">
                    <el-switch
                      v-model="editFormData.status"
                      active-value="1"
                      inactive-value="0"
                      class="dc-el-switch_Switch"
                    ></el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="dbIp" label="数据库IP" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.dbIp"
                      :maxLength="64"
                      placeholder="请输入数据库IP"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
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
                      <el-option
                        v-for="(item, index) in dbTypeOptions"
                        :key="index"
                        :label="item.name"
                        :value="item"
                      ></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="dbName" label="数据库名" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.dbName"
                      :maxLength="32"
                      placeholder="请输入数据库名"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="dbUsername" label="数据库用户" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.dbUsername"
                      :maxLength="64"
                      placeholder="请输入数据库用户"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="dbPassword" label="数据库密码" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.dbPassword"
                      :maxLength="64"
                      placeholder="请输入数据库密码"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item prop="dbPort"
                                label="数据库端口"
                                :rules="[{ pattern: new RegExp(`^[1-9]\\d*$`), message: '请输入正确的数据库端口', trigger: 'blur' }]"
                                class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.dbPort"
                      :maxLength="10"
                      placeholder="请输入数据库端口"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item prop="dbUrl" label="数据库地址" class="dc-el-form-item_SingleInput">
                    <el-input
                      v-model="editFormData.dbUrl"
                      :maxLength="128"
                      placeholder="请输入数据库地址"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item
                    prop="cloudName"
                    label="服务名"
                    :rules="[{ required: true, message: '服务名不能为空', trigger: 'blur' }]"
                    class="dc-el-form-item_SingleInput"
                  >
                    <el-input
                      v-model="editFormData.cloudName"
                      :maxLength="64"
                      placeholder="请输入服务名"
                      clearable
                      class="dc-el-input_SingleInput"
                    ></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="24">
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
                </el-col>
              </el-row>
            </el-form>
            <el-tooltip disabled class="dc-el-tooltip_Button">
              <el-button
                type="primary"
                icon="el-icon-refresh-right"
                v-on:click="linkJdbcConnection"
                class="dc-el-button_Button"
              >
                测试连接
              </el-button>
            </el-tooltip>
          </el-tab-pane>
          <el-tab-pane label="项目成员" name="memberTable" class="dc-el-tab-pane_ElTabPane">
            <el-row v-if="action != 'view'" gutter="0" type="flex" justify="end" align="top" class="dc-el-row_ElRow">
              <el-tooltip disabled class="dc-el-tooltip_Button">
                <el-button
                  type="primary"
                  icon="el-icon-plus"
                  v-on:click="onAddMemberList"
                  class="dc-el-button_Button"
                ></el-button>
              </el-tooltip>
            </el-row>
            <el-form
              ref="memberForm"
              :model="editFormData"
              label-width="100px"
              :disabled="action == 'view'"
              class="dc-el-form_ElEditForm"
            >
              <el-row>
                <ux-grid
                  :data="editFormData.memberList"
                  ref="memberTable"
                  :currentRow="table118CurrentRow"
                  height="200px"
                  border
                  :edit-config="{ trigger: 'click', mode: 'cell' }"
                  class="dc-ux-grid_EditTable"
                >
                  <ux-table-column
                    field="user"
                    title="用户"
                    tree-node
                    resizable
                    align="left"
                    header-align="center"
                    show-overflow
                    :edit-render="{ autofocus: '.el-input__inner' }"
                    class="dc-ux-table-column_SelectInput"
                  >
                    <template v-slot:header="{ column }">
                      <span style="color: #f56c6c">*</span>
                      {{ column.title }}
                    </template>
                    <template v-slot:edit="{ row, rowIndex }">
                      <el-form-item
                        :prop="'memberList.' + rowIndex + '.user'"
                        label-width="0px"
                        :rules="[{ required: true, message: '用户不能为空', trigger: 'change' }]"
                        class="dc-el-form-item_SelectInput"
                      >
                        <el-select
                          v-model="row.user"
                          :style="{ width: '100%' }"
                          placeholder="请选择用户"
                          clearable
                          value-key="id"
                          filterable
                          class="dc-el-select_SelectInput"
                        >
                          <el-option
                            v-for="(item, index) in userOptions"
                            :key="index"
                            :label="item.name"
                            :value="item"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </template>
                    <template v-slot="{ row, rowIndex }">
                      <span class="my-input-sc">
                        <el-form-item
                          :prop="'memberList.' + rowIndex + '.user'"
                          label-width="0px"
                          :rules="[{ required: true, message: '用户不能为空', trigger: 'change' }]"
                          class="dc-el-form-item_SelectInput"
                        >
                          {{ row.user.name }}
                        </el-form-item>
                      </span>
                    </template>
                  </ux-table-column>
                  <ux-table-column
                    field="remarks"
                    title="备注信息"
                    tree-node
                    resizable
                    min-width="120px"
                    align="left"
                    header-align="center"
                    show-overflow
                    :edit-render="{ autofocus: '.el-input__inner' }"
                    class="dc-ux-table-column_MutilpleInput"
                  >
                    <template v-slot:header="{ column }">
                      {{ column.title }}
                    </template>
                    <template v-slot:edit="{ row, rowIndex }">
                      <el-form-item
                        :prop="'memberList.' + rowIndex + '.remarks'"
                        label-width="0px"
                        class="dc-el-form-item_MutilpleInput"
                      >
                        <el-input
                          v-model="row.remarks"
                          type="textarea"
                          placeholder="请输入备注信息"
                          rows="2"
                          :maxLength="255"
                          class="dc-el-input_MutilpleInput"
                        ></el-input>
                      </el-form-item>
                    </template>
                    <template v-slot="{ row, rowIndex }">
                      <span class="my-input-sc">
                        <el-form-item
                          :prop="'memberList.' + rowIndex + '.remarks'"
                          label-width="0px"
                          class="dc-el-form-item_MutilpleInput"
                        >
                          {{ row.remarks }}
                        </el-form-item>
                      </span>
                    </template>
                  </ux-table-column>
                  <ux-table-column
                    field="user"
                    title="角色"
                    tree-node
                    resizable
                    align="left"
                    header-align="center"
                    show-overflow
                    class="dc-ux-table-column_ElTableColumn"
                  >
                    <template slot-scope="{ row, rowIndex }">
                      <div
                        v-html="
                          ((cellValue, row, index) => {
                            if (cellValue.id == editFormData.user.id) {
                              return '负责人'
                            }
                            return '成员'
                          })(row.user, row, rowIndex)
                        "
                      ></div>
                    </template>
                  </ux-table-column>
                  <ux-table-column
                    title="操作"
                    tree-node
                    resizable
                    width="160px"
                    min-width="80px"
                    fixed="right"
                    align="left"
                    header-align="center"
                    v-if="action != 'view'"
                    class="dc-ux-table-column_ElTableOptColumn"
                  >
                    <template v-slot:header="scope">
                      <span>操作</span>
                    </template>
                    <template slot-scope="scope">
                      <OperationIcon
                        v-on:click="onDeleteChild(editFormData.memberList, scope.rowIndex)"
                        type="danger"
                        v-if="scope.row.user.id != editFormData.user.id"
                        content="删除"
                        placement="top"
                        icon-name="el-icon-delete"
                        class="dc-OperationIcon_IconButton"
                      ></OperationIcon>
                    </template>
                  </ux-table-column>
                </ux-grid>
              </el-row>
            </el-form>
          </el-tab-pane>
        </el-tabs>
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
import { listSysDbTypeAll } from '@/api/dbtype/sysDbType.js'
import { listUserAll } from '@/api/admin/user.js'
/** 根据用户界面配置import组件 结束 */
import { getProjectById, saveProject, linkJdbcConnection } from '@/api/project/project'
import BaseUI from '@/views/components/baseUI'
import OperationIcon from '@/components/OperationIcon'
const artTemplate = require('art-template/lib/index')
export default {
  extends: BaseUI,
  name: 'project-form',
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
        title: '项目'
      },
      dialogTitle: '项目',
      // 选项变量
      // 数据库类型选项
      dbTypeOptions: [],
      table118CurrentRow: {}, // 当前行
      // null选项
      userOptions: [],
      tabName: 'editForm', // tab标签页
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
  computed: {
    //计算from表单数据中的三个属性
    computeUrl() {
      const { dbIp, dbPort, dbName, dbType } = this.editFormData
      return {
        dbIp,
        dbPort,
        dbName,
        dbType
      }
    }
  },
  methods: {
    onSubmit() {
      let validFlag = true

      this.$refs['memberForm'].validate((valid) => {
        if (valid) {
        } else {
          this.tabName = 'memberTable'
          validFlag = false
          return false
        }
      })

      this.$refs['editForm'].validate((valid) => {
        if (valid) {
        } else {
          this.tabName = 'editForm'
          validFlag = false
          return false
        }
      })
      if (validFlag) {
        this.doSave()
      }
    },
    doSave() {
      this.setLoad()

      saveProject(this.editFormData)
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
        getProjectById(id)
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

    listUserOptions() {
      let search_List = {
        params: []
      }
      // filter条件
      search_List.params.push.apply(search_List.params, [])

      listUserAll(search_List).then((responseData) => {
        if (responseData.code == 100) {
          this.userOptions = responseData.data
        } else {
          this.showMessage(responseData)
        }
      })
    },

    initOptions(This) {
      // 初始化自定义类型选择框选项变量

      this.listDbTypeOptions()

      this.listUserOptions()
    },
    onDeleteChild(tableData, index) {
      // 根据下标删除子表数据
      tableData.splice(index, 1)
    },
    initEditData(This) {
      let newPrj = {
        user: currentUser, // 当前用户作为项目负责人
        name: '', // 名称
        status: '0', // 归档
        dbIp: '', // 数据库IP
        dbType: {
          id: null,
          name: null,
          driver: null,
          rule: null
        },
        dbName: '', // 数据库名
        dbUsername: '', // 数据库用户
        dbPassword: '', // 数据库密码
        dbPort: '', // 数据库端口
        dbUrl: '', // 数据库地址
        cloudName: '', // 服务名
        remarks: '', // 备注信息
        memberList: []
      }
      newPrj.memberList = [
        {
          user: currentUser,
          remarks: ''
        }
      ] // 当前用户作为项目成员
      return newPrj
    },

    onAddMemberList() {
      this.editFormData.memberList.push({
        user: {
          id: null,

          name: null
        },

        remarks: '' // 备注信息
      })
    },

    linkJdbcConnection() {
      this.$refs['editForm'].validate((valid) => {
        if (valid) {
          //发送请求到后端 看是否能连接到数据库
          this.setLoad()
          linkJdbcConnection(this.editFormData).then((responseData) => {
            if (responseData.code == 100) {
              this.showMessage(responseData)
            } else {
              this.showMessage(responseData)
            }
            this.resetLoad()
          })
        } else {
          return false
        }
      })
    }
  },
  watch: {
    tabName(val, oldVal) {
      this.$nextTick(() => {
        if (this.$refs[val] && this.$refs[val].doLayout) {
          this.$refs[val].doLayout()
        }
      })
    },

    computeUrl() {
      const { dbIp, dbPort, dbName, dbType } = this.computeUrl
      if (!validatenull(dbType.id)) {
        let rule = dbType.rule
        this.editFormData.dbUrl = artTemplate.render(rule, this.computeUrl)
      }
    }
  },
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
        this.tabName = 'editForm'
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
        this.tabName = 'editForm'
        this.dialogProps.visible = true
      })
      this.$on('openAddDialog', function () {
        this.action = 'add'
        this.dialogProps.title = `添加${this.dialogTitle}`
        this.editFormData = this.initEditData()
        this.initOptions(this.editFormData)
        this.tabName = 'editForm'
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
        this.tabName = 'editForm'
        this.editFormData.id = null //把id设置为空，添加一个新的

        for (var i = 0; i <= this.editFormData.memberList.length - 1; i++) {
          this.editFormData.memberList[i].id = null
        }

        this.dialogProps.visible = true
      })
    })
  }
}
</script>
<style>
.my-input-sc {
  display: inline-block;
  height: 30px;
  line-height: 30px;
  -webkit-appearance: none;
  background-color: #ffffff;
  background-image: none;
  border-radius: 4px;
  border: 1px solid #dcdfe6;
  box-sizing: border-box;
  color: #606266;
  font-size: inherit;
  outline: none;
  padding: 0 15px;
  transition: border-color 0.2s cubic-bezier(0.645, 0.045, 0.355, 1);
  width: 100%;
}
</style>
