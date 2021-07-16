<template>
    <div>
        <el-table
                :data="backList"
                border
                style="width: 90%;margin-top: 10px"
                :default-sort="{prop: 'f_time', order: 'descending'}">
            <el-table-column
                    prop="f_time"
                    label="反馈时间"
                    sortable
                    width="210">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="f_content"
                    label="反馈内容"
                    width="400">
            </el-table-column>
            <el-table-column
                    prop="user.user_name"
                    label="用户"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作">
                <template slot-scope="scope" style="align-items: center">
                    <el-button style="padding:5px;"
                               icon="el-icon-edit-outline"
                               size="mini"
                               type="warning"
                               @click="toBack(scope.row)">回复
                        <!--                        scope.row:传入当前数据-->
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="display:flex;margin-top: 10px">
            <el-pagination
                    background
                    @current-change="currentChange"
                    @size-change="sizeChange"
                    :page-size="5"
                    layout="prev, pager, next, jumper, ->, slot , total"
                    :total="total">
            </el-pagination>
        </div>
        <el-dialog title="反馈"
                   :visible.sync="backFormVisible"
                   :close-on-click-modal="false">
            <el-form :model="backForm" :rules="backRules" ref="backForm">
                <el-form-item prop="f_answer">
                    <el-input type="textarea" v-model="backForm.f_answer"  auto-complete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="backFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="back('backForm')">提交回复</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "FeedBack",
        data() {
            return {
                backList: [],
                total: 0,       //页总数
                page: 0,
                size: '',
                backForm: {
                },
                backRules: {
                    f_answer: [
                        {required: true, message: '回复不能少于1个字！', trigger: 'blur'},
                    ],
                },
                backFormVisible: false
            }
        },

        methods: {
            sizeChange(pageSize) {
                //console.log('pageSize:'+pageSize)
                this.size = pageSize;
                this.created();
            },

            //当前页
            currentChange(currentPage) {
                //console.log('currentPage:'+currentPage)
                const _this = this;
                this.axios.get('/feedback/findByPage/' + currentPage + '/5').then(function (res) {
                    // console.log(res.data);
                    _this.backList = res.data.data_feedback;
                    console.log(_this.backList)
                })
            },

            //起始页
            created() {
                const _this = this;
                this.axios.get('/feedback/findByPage/1/5').then(function (res) {
                    // console.log(res.data);
                    _this.backList = res.data.data_feedback;
                    _this.total = res.data.total_feedback;
                    // console.log(_this.backList)
                    //通过user_id回显用户昵称于页面
                    // let backItem = _this.backList;
                    // const that = _this;
                    // backItem.forEach(e=>{
                    //     _this.axios.get('/user/findById/'+e.user_id).then(function (res){
                    //         // console.log(res.data);
                    //         if (res.data.user_name != null){
                    //             e.user_id = res.data.user_name;
                    //         }else e.user_id = '未知用户'
                    //     })
                    // })
                })

            },

            //修改
            toBack(row) {
                const _this = this;
                // console.log(row);
                this.axios.get('/feedback/findById/'+row.id).then(function (res) {
                    _this.backForm = res.data;
                    // console.log(_this.editForm);
                });
                this.backFormVisible = true;
            },

            back(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    //校验,$refs获取表单
                    if (valid) {
                        this.axios.put('/feedback/alt', this.backForm).then(function (res) {
                            console.log(res);
                            if (res.data == 'alt success!') {
                                _this.backFormVisible = false;
                                _this.$message({
                                    message: '回复成功！',
                                    type: 'success'
                                });
                                window.location.reload()
                            }else {
                                _this.$message({
                                    message: '回复失败！',
                                    type: 'warning'
                                });
                            }
                        })
                    }
                });
            }
        },

        mounted() {
            this.created();
        }
    }
</script>

<style scoped>

</style>