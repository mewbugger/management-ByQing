import {createRouter, createWebHistory} from 'vue-router'
import {useUserStore} from '@/stores/user'
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',  
            name: 'Layout',
            component: () => import('../layout/Layout.vue'),
            redirect: 'home',
            children: [
                {
                    path: '/home',
                    name: 'Home',
                    component: () => import('../views/HomeView.vue')
                },
                {
                    path: '/charts',
                    name: 'Charts',
                    component: () => import('../views/Charts.vue')
                },
                {
                    path: '/category',
                    name: 'Category',
                    component: () => import('../views/Category.vue')
                },
                {
                    path: '/equipment',
                    name: 'Equipment',
                    component: () => import('../views/Equipment.vue')
                },
                {
                    path: '/inspection',
                    name: 'Inspection',
                    component: () => import('../views/Inspection.vue')
                },
                {
                    path: '/requirement',
                    name: 'Requirement',
                    component: () => import('../views/Requirement.vue')
                },
                {
                    path: '/welcome',
                    name: 'Welcome',
                    component: () => import('../components/TheWelcome.vue')
                }
            ],
        },
        {
            path: '/login',
            name: 'Login',
            component: () => import('../views/Login.vue')
        },
        {
            path: '/404',
            name: '404',
            component: () => import('../views/404.vue')
        },
        {
            path: '/register',
            name: 'Register',
            component: () => import('../views/Register.vue')
        },
        {
            path: '/：pathMatch(.*)',
            redirect:'/404'
        },
        
    ]
})

router.beforeEach((to, from, next) => {
    const store = useUserStore()
    const user = store.managerInfo.user
    console.log(user)

    const hasUser = user && user.id

    console.log(hasUser)
    const noPermissionPaths = ['/login','/register']
    if ((!hasUser && !noPermissionPaths.includes(to.path))) {
        //获取缓存的用户数据
        //如果to.path === '/login'的时候 !noPermissionPaths.includes(to.path)是返回false的，也就不会进next函数
        next("/login")
    } else{
        next()
    }
})

export default router
