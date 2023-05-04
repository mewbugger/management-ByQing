<script setup>
import {
  Search,
} from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus';
import { reactive, ref } from 'vue';
import request from '../utils/request';

const dialogFormVisible = ref(false)
const username = ref('')
const pageNum = ref(1)
const pageSize = ref(5)
const total = ref(0)
const state = reactive({
  tableData: [],
  form: {}
})
const ruleFormRef = ref()
const multipleSelection = ref([])
//批量删除
const handleSelectionChange = (val) => {
  multipleSelection.value = val
}

const load = () => {
  request.get('/category/page', {
    params: {
      pageNum: pageNum.value,
      pageSize: pageSize.value
    }
  }).then(res => {
    console.log(res)
    state.tableData = res.data.list
    total.value = res.data.total
  })
}
load()//调用load方法拿到后台数据

const reset = () => {
  username.value = ''
  load()
}

const currentChange = (num) => {
  pageNum.value = num
  load()
}

const handleSizeChange = (size) => {
  pageSize.value = size
  load()
}

const add = () => {
  dialogFormVisible.value = true
  ruleFormRef.value.resetFields()
  state.form = {}
}

const rules = reactive({
  username: [
    { required: true, message: '请输入设备名', trigger: 'blur' },
  ],
})

const save = () => {
  ruleFormRef.value.validate(valid => {
    if (valid) {
      request.request({
        url: '/category',
        method: state.form.equipment_category_id ? 'put' : 'post',
        data: state.form
      }).then(res => {
        if (res.code === '200') {
          ElMessage.success('操作成功')
          dialogFormVisible.value = false
          load()
        } else {
          ElMessage.error(res.msg)
        }
      })
    }
  })
}

const handleEdit = (row) => {
  state.form = JSON.parse(JSON.stringify(row))
  dialogFormVisible.value = true
  ruleFormRef.value.resetFields()
}

const del = (equipment_category_id) => {
  request.delete('/category/' + equipment_category_id).then(res => {
    if (res.code === '200') {
      ElMessage.success('操作成功')
      load()
    } else {
      ElMessage.error(res.msg)
    }
  })
}

</script>

<template>
  <div>
    <div>
      <!-- <el-input v-model="username" placeholder="Please input username" class="w300" /> -->
      <!-- <el-button type="primary" class="ml5" @click="load">
        <el-icon style="vertical-align:middle">
          <Search />
        </el-icon><span style="vertival-align: middle"> 搜 索 </span>
      </el-button> -->
      <el-button type="warning" class="ml5" @click="reset">
        <el-icon style="vertical-align:middle">
          <Search />
        </el-icon><span style="vertival-align: middle"> 重 置 </span>
      </el-button>
      <el-button type="success" class="ml5" @click="add">
        <el-icon style="vertical-align:middle">
          <Search />
        </el-icon><span style="vertival-align: middle"> 新 增 </span>
      </el-button>
    </div>
    <div style="margin: 10px 0">
      <el-table :data="state.tableData" stripe border @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column prop="equipment_category_id" label="设备类别编号"></el-table-column>
        <el-table-column prop="equipment_category_name" label="设备类别"></el-table-column>
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
            <el-popconfirm title="您确定删除吗？" @confirm="del(scope.row.equipment_category_id)">
              <template #reference>
                <el-button type="danger">删除</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div>
      <el-pagination @current-change="currentChange" @size-change="handleSizeChange" v-model:current-page="pageNum"
        v-model:page-size="pageSize" background :page-sizes="[2, 5, 10, 20]"
        layout="total, sizes, prev, pager, next, jumper" :total="total" class="mt-4" />
    </div>

    <el-dialog v-model="dialogFormVisible" title="设备信息" width="40%">
      <el-form ref="ruleFormRef" :rules="rules" :model="state.form" label-width="80px" style="padding: 0 20px"
        status-icon>
        <el-form-item prop="equipment_category_name" label="设备类别">
          <el-input v-model="state.form.equipment_category_name" autocomplete="off" />
        </el-form-item>

      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取消</el-button>
          <el-button type="primary" @click="save">
            保存
          </el-button>
        </span>
      </template>
    </el-dialog>

  </div>
</template>
