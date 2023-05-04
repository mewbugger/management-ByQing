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
    form: {},
    options: [
        {
            value: '已修复',
            label: '已修复',
        },
        {
            value: '未能修复',
            label: '未能修复',
        },
    ]
})
const ruleFormRef = ref()
const multipleSelection = ref([])
//批量删除
const handleSelectionChange = (val) => {
    multipleSelection.value = val
}

const load = () => {
    request.get('/requirement/page', {
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



const rules = reactive({
    username: [
        { required: true, message: '请输入设备名', trigger: 'blur' },
    ],
})

const save = () => {
    ruleFormRef.value.validate(valid => {
        if (valid) {
            console.log('=======')
            console.log(state.form)
            request.request({
                url: '/requirement',
                method: state.form.requirement_id ? 'put' : 'post',
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
    console.log(row)
    console.log(row.equipment_category_id)
    state.form = JSON.parse(JSON.stringify(row))
    dialogFormVisible.value = true
    ruleFormRef.value.resetFields()
}

const del = (requirement_id) => {
    request.delete('/requirement/' + requirement_id).then(res => {
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
        </div>
        <div style="margin: 10px 0">
            <el-table :data="state.tableData" stripe border @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" />
                <el-table-column prop="requirement_id" label="维修编号"></el-table-column>
                <el-table-column prop="requirement_date" label="维修时间"></el-table-column>
                <el-table-column prop="requirement_description" label="维修描述"></el-table-column>
                <el-table-column prop="requirement_employee" label="维修人员"></el-table-column>
                <el-table-column prop="requirement_result" label="维修结果"></el-table-column>
                <el-table-column prop="inspection_id" label="巡检编号"></el-table-column>
                <el-table-column label="操作" width="180">
                    <template #default="scope">
                        <el-button type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-popconfirm title="您确定删除吗？" @confirm="del(scope.row.requirement_id)">
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

        <el-dialog v-model="dialogFormVisible" title="维修信息" width="40%">
            <el-form ref="ruleFormRef" :rules="rules" :model="state.form" label-width="80px" style="padding: 0 20px"
                status-icon>
                <el-form-item prop="requirement_date" label="维修时间">
                    <el-input v-model="state.form.requirement_date" autocomplete="off" :suffix-icon="Calendar" type="date" />
                </el-form-item>
                <el-form-item prop="requirement_employee" label="维修人员">

                    <el-input v-model="state.form.requirement_employee" autocomplete="off" disabled />
                </el-form-item>
                <el-form-item prop="requirement_description" label="维修描述">
                    <el-input v-model="state.form.requirement_description" autocomplete="off" />
                </el-form-item>
                <el-form-item prop="inspection_id" label="巡检编号">
                    <el-input v-model="state.form.inspection_id" autocomplete="off" disabled/>
                </el-form-item>
                <el-form-item prop="requirement_result" label="维修结果">
                    <el-select v-model="state.form.requirement_result" placeholder="请选择">
                        <el-option v-for="item in state.options" :key="item.value" :label="item.lable"
                            :value="item.value" />
                    </el-select>
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
