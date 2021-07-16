<template>
    <div class="home_index">
        <el-container style="height: 100%; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: #d3ece7">
                <el-menu router style="background-color: #d3ece7">
                    <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" v-if="item.show">
                        <template slot="title"><i class="el-icon-user-solid"></i>{{item.name}}</template>
                        <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path"
                                      :class="$route.path==item2.path?'is-active':''">
                            {{item2.name}}
                        </el-menu-item>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header style="font-size: 20px">
                    <router-link to="/home" style="text-decoration: none;color: #5e6265">
                        <span style="font-family: 楷体
                              ;font-weight: bolder
                              ;font-size: 30px">
                            聊点社区后台管理</span>
                    </router-link>
                    <el-dropdown style="float: right;height: 60px">
                        <img class="root_img" src="../assets/anan.jpg"/>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item @click.native="logout" icon="icon iconfont icon-tuichu">
                                退出
                            </el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-header>

                <el-main>
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Home",
        methods: {
            logout() {
                // sessionStorage.setItem("token", 'false');
                this.$confirm('即将退出登录, 是否继续?', 'whether', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    window.localStorage.removeItem("root");
                    this.$message({
                        type: 'success',
                        message: '退出成功!'
                    });
                    this.$router.push("/login");
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消！'
                    });
                });
            }
        }
    }
</script>

<style>

    .home_index{
        width: 100%;
        height: 100%;
        display: block;
    }
    .home_index::after{
        content: "";
        background: url("../assets/bg.jpg");
        opacity: 0.5;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        position: absolute;
        z-index: -1;
    }

    .el-header {
        background-color: #f8aabb;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }

    .root_img {
        cursor: pointer;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        margin: 5px 0px 10px 10px;
    }
</style>
