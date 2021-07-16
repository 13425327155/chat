<template>
    <div class="login_index">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login_container">
            <h3 class="login_title">后台登录</h3>
            <el-form-item prop="user_name">
                <el-input type="text" auto-complete="false" v-model="loginForm.user_name"
                          placeholder="请输入用户名"></el-input>
            </el-form-item>
                <el-form-item prop="user_password">
                <el-input type="password" auto-complete="false" v-model="loginForm.user_password"
                          placeholder="请输入密码"></el-input>
            </el-form-item>
<!--            <el-form-item prop="code">-->
<!--                <el-input type="false" auto-complete="false" v-model="loginForm.code" placeholder="点击图片更换验证码"-->
<!--                          style="width: 250px;margin-right: 50px"></el-input>-->
<!--                <img :src="captchaUrl" @click="updateCaptcha">-->
<!--            </el-form-item>-->
            <el-form-item>
                <el-checkbox v-model="checked" class="login_remember">记住我</el-checkbox>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" style="width: 100%" @click="submitLogin('loginForm')">登录</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>

    export default {
        name: "Login",
        data() {
            return {
                // captchaUrl: '',
                loginForm: {
                    user_name: 'ana',
                    user_password: '123456',
                    // code: '',
                },
                checked: true,
                loginRules: {
                    user_name: [
                        {required: true, message: '用户名不能为空!', trigger: 'blur'}
                    ],
                    user_password: [
                        {required: true, message: '密码不能为空!', trigger: 'blur'}
                    ],
                    // code: [
                    //     {required: true, message: '验证码不能为空!', trigger: 'blur'}
                    // ]
                }

            }
        },
        methods: {
            submitLogin(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _this.axios.post('/login',_this.loginForm).then(function (res) {
                            // console.log(res)
                            if (res.data == 'login success!'){
                                _this.$store.commit('login', _this.loginForm);

                                _this.$message({
                                    message: '登录成功!',
                                    type: 'success',
                                });
                                _this.$router.push({path:'/home'})
                            }else{
                                _this.$message.error('用户名或密码错误，请重新登录!');
                            }
                        }).catch(failResponse => {
                        })
                    } else {
                        this.$message.error('请输入所有字段!');
                        return false;
                    }
                });
            }
        }
    }
</script>

<style>
    body{
        margin: 0px;
    }

    .login_index{
        background: url("../assets/login_bg.jpg") no-repeat center 100%;
        width: 100%;
        height: 100%;
        background-size: cover;
        position: fixed;
    }

    .login_container {
        background-color: rgba(0, 0, 0, 0.5);
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background-color: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #b7e1e4;
    }

    .login_title {
        margin: 0px auto 40px auto;
        text-align: center;
    }

    .login_remember {
        text-align: left;
    }

    /*.el-form-item__content{*/
    /*    display: flex;*/
    /*    align-items: center;*/
    /*}*/
</style>