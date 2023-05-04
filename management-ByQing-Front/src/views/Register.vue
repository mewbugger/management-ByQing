<script setup>
import { User, Lock } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue';
import request from '@/utils/request'
import { ElMessage } from 'element-plus';
import router from '../router';
const loginData = reactive({})
const ruleFormRef = ref()

const confirmPassword=(rule,value,callback)=>{
    if(value===''){
        callback(new Error('请确认密码'))
    }
    if(loginData.password !==value){
        callback(new Error('两次密码不一致'))
    }
    callback()
}
const rules = reactive({
    username: [
        { required: true, message: '请输入账号', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 3, max: 20, messgae: '密码长度在3-20位之间', trigger: 'blur' }
    ],
    confirm: [
        {validator: confirmPassword,trigger:'blur'}
    ],
})
const register = () => {
    ruleFormRef.value.validate(valid => {
        if (valid) {
            //发送表单数据给后台
            request.post('/register', loginData).then(res => {
                if (res.code === '200') {
                    ElMessage.success('注册成功')
                    router.push('/login')
                } else {
                    ElMessage.error('注册失败')
                }
            })
        }
    })
}
</script>
<template>
    <div style="height: 100vh; overflow: hidden;">
        <div style="width: 400px; margin: 150px auto; border: 1px solid #ddd; padding: 30px; border-radius: 10px;">
            <el-form ref="ruleFormRef" :model="loginData" :rules="rules" size="large" status-icon>
                <div
                    style="text-align: center; color: dodgerblue; font-size: 30px; font-weight: bold; margin-bottom: 30px">
                    注 册</div>
                <el-form-item prop="username">
                    <el-input v-model="loginData.username" placeholder="请输入用户名" :prefix-icon="User" />
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="loginData.password" placeholder="请输入密码" :prefix-icon="Lock" />
                </el-form-item>
                <el-form-item prop="confirm">
                    <el-input v-model="loginData.confirm" placeholder="请确认密码" :prefix-icon="Lock" />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" style="width: 100%;" @click="register">注 册</el-button>
                </el-form-item>
                <div style="text-align: right">
                    <el-button type="text" @click="router.push('/login')">
                        已有账号？请登录
                    </el-button>
                </div>
            </el-form>
        </div>
    </div>
</template>