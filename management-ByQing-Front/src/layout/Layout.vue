<script setup>
import router from '@/router';
import {
    Document,
    Menu as IconMenu,
    UserFilled,
} from '@element-plus/icons-vue'
import { reactive, ref } from 'vue';
import { RouterView } from 'vue-router'
import {useUserStore} from '@/stores/user'
import request from '../utils/request';
import { ElMessage } from 'element-plus';
const userStore=useUserStore()
const user=userStore.getUser
const menuVisible = ref(false)
const logout = ()=>{
    request.get('/logout/'+user.id).then(res =>{
        if(res.code==='200'){
            userStore.logout()
        }else{
            ElMessage.error(res.msg)
        }
    })
}
const load = () =>{
    if(user.username == 'Leader'){
        menuVisible.value = true;
    }
}
load()
</script>
<template>

    <div>
        <div style="height: 60px; line-height:60px; border-bottom:1px solid #ccc; background-color: aliceblue">
            <div style="display: flex">
                <div style="width:200px ;color :dodgerblue; font-weight: bold;text-align: center;font-size:20px">
                    后台管理
                </div>
                <div style="flex: 1;display: flex">
                    <div style="flex: 1">

                    </div>
                    <div style="width:200px;text-align: center; padding-right:20px">
                        <el-dropdown>
                            <el-avatar :icon="UserFilled" style="margin-top: 10px;" />
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item><div @click="router.push('/person')">个人信息</div></el-dropdown-item>
                                    <el-dropdown-item><div @click="logout()">退出登录</div></el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                </div>
            </div>
        </div>

        <div style="display: flex;">
            <div style="width: 200px;min-height: calc(100vh - 60px); border-right: 1px solid #ccc;">
                <el-menu default-active="1" class="el-menu-vertical-demo" router>
                    <el-menu-item index="/welcome">
                        <el-icon><icon-menu /></el-icon>
                        <span>首页</span>
                    </el-menu-item>
                    <el-menu-item index="/home" v-if="menuVisible == true">
                        <el-icon><icon-menu /></el-icon>
                        <span>人员管理</span>
                    </el-menu-item>
                    <el-menu-item index="/category">
                        <el-icon><icon-menu /></el-icon>
                        <span>设备类别管理</span>
                    </el-menu-item>
                    <el-menu-item index="/equipment" >
                        <el-icon><icon-menu /></el-icon>
                        <span>设备管理</span>
                    </el-menu-item>
                    <el-menu-item index="/inspection">
                        <el-icon><icon-menu /></el-icon>
                        <span>巡检管理</span>
                    </el-menu-item>
                    <el-menu-item index="/requirement">
                        <el-icon><icon-menu /></el-icon>
                        <span>维修管理</span>
                    </el-menu-item>
                </el-menu>
            </div>
            <div style="flex: 1; padding: 10px;">
                <RouterView />
            </div>
        </div>

    </div>






</template>