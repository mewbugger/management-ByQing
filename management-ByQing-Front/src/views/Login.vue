<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue';
import request from '@/utils/request'
import { ElMessage } from 'element-plus';
import { useUserStore } from '@/stores/user';
import router from '@/router/index.js';
const loginData = reactive({})
const rules = reactive({
    username: [
        { required: true, message: '请输入账号', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 3, max: 20, messgae: '密码长度在3-20位之间', trigger: 'blur' }
    ],
})
const ruleFormRef = ref()
const radio1 = ref("1");
const login = () => {
    ruleFormRef.value.validate(valid => {
        if (valid) {
            //发送表单数据给后台
            if (radio1.value == 1) {
                request.post('/login', loginData).then(res => {
                    if (res.code === '200') {
                        ElMessage.success('登录成功')
                        const userStore = useUserStore()
                        console.log(res.data)
                        userStore.setManagetInfo(res.data)
                        router.push('/welcome')
                        console.log(111)
                    } else {
                        ElMessage.error(res.msg)
                    }
                })
            } else {
                request.post('/login', loginData).then(res => {
                    if (res.code === '200') {
                        ElMessage.success('登录成功')
                        const userStore = useUserStore()
                        console.log(res.data)
                        userStore.setManagetInfo(res.data)
                        router.push('/welcome')
                        console.log(111)
                    } else {
                        ElMessage.error(res.msg)
                    }
                })
            }

        }
    })
}
</script>
<template>
    <div style="height: 100vh; overflow: hidden;">
        <div style="width: 400px; margin: 150px auto; border: 1px solid #ddd; padding: 30px; border-radius: 10px;">
            <el-form ref="ruleFormRef" :model="loginData" :rules="rules" size="large" status-icon>
                <div style="text-align: center; color: dodgerblue; font-size: 30px; font-weight: bold; margin-bottom: 30px">
                    登录</div>
                <el-form-item prop="username">
                    <el-input v-model="loginData.username" placeholder="请输入用户名" :prefix-icon="User" />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginData.password" placeholder="请输入密码" :prefix-icon="Lock" />
                </el-form-item>
                <el-radio-group v-model="radio1" class="ml-4">
                    <el-radio label="1" size="large">普通用户</el-radio>
                    <el-radio label="2" size="large">管理员</el-radio>
                </el-radio-group>

                <el-form-item>
                    <el-button type="primary" style="width: 100%;" @click="login">登 录</el-button>
                </el-form-item>
                <div style="text-align: right">
                    <el-button type="text" @click="router.push('/register')">
                        没有账号？请注册
                    </el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>