<template>
    <div class="user-info">
        <el-dropdown trigger="click">
            <span class="el-dropdown-link" style="color: white; font-weight: bold">  
                迪丽热巴  
            <i class="el-icon-arrow-right el-icon--right"></i>  
            </span>

            <el-dropdown-menu class="userinfo-dropdown" slot="dropdown">

            <router-link to="/user/profile">
            <el-dropdown-item>个人中心</el-dropdown-item>
            </router-link>

            <el-dropdown-item @click.native="setting = true">
                <span>布局设置</span>
            </el-dropdown-item>

            <el-dropdown-item divided @click.native="logout">
                <span>退出登录</span>
            </el-dropdown-item>

            </el-dropdown-menu>
        </el-dropdown>

    </div>
</template>

<script>
export default {
    name: 'Userinfo',
    computed: {
        setting: {
            get() {
                return this.$store.state.settings.showSettings
            },
            set(val) {
                this.$store.dispatch('settings/changeSetting', {
                key: 'showSettings',
                value: val
                })
            }
        }
    },
    methods: {
        async logout() {
        this.$confirm('确定注销并退出系统吗？', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            this.$store.dispatch('LogOut').then(() => {
            location.href = '/index';
            })
        }).catch(() => {});
        }
  }
}
</script>

<style lang="scss">
.user-info {
    font-size: 14px;
    font-weight: 500;
    text-align: center;
    margin-top: 10px;
    margin-bottom: 16px;
    color: blue;
}
.userinfo-dropdown {
    margin-left: 105px;
    
}

</style>