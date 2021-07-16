<template>
    <div>
        <el-button @click="clearFilter">清除类型过滤器</el-button>
        <el-table
                :data="commentList"
                ref="commentTable"
                style="width: 90%;margin-top: 10px">
            <el-table-column
                    fixed
                    label="类型"
                    prop="c_type"
                    width="80"
                    :filters="[{text: '学习', value: '学习'}, {text: '就业', value: '就业'}, {text: '交友', value: '交友'}, {text: '打听', value: '打听'}]"
                    :filter-method="filterTag"
                    filter-placement="bottom-end">
                <template slot-scope="scope">
                    <el-tag
                            :type="scope.row.news !== null
                            ? 'success' : 'primary'"
                            disable-transitions>{{scope.row.c_type}}</el-tag>
                </template>
            </el-table-column>
            <el-table-column
                label="用户"
                width="80">
            <template slot-scope="scope">
                <el-popover trigger="hover" placement="top">
                    <p>用户名: {{ scope.row.user.user_name }}</p>
                    <p>联系方式: {{ scope.row.user.user_phone }}</p>
                    <p>用户类型: {{ scope.row.user.user_type }}</p>
                    <div slot="reference">
                        <el-image
                                class="user_img"
                                :src="'data:image/jpg;base64,' + scope.row.user.user_img">
                        </el-image>
                    </div>
                </el-popover>
            </template>
        </el-table-column>
            <el-table-column
                    label="评论时间"
                    width="130"
                    prop="c_time"
                    sortable>
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.c_time }}</span>
                </template>
            </el-table-column>
            <el-table-column
                    prop="c_content"
                    label="评论内容"
                    width="400">
            </el-table-column>
            <el-table-column
                    prop="news.n_title"
                    label="新闻标题"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作">
                <template slot-scope="scope" style="align-items: center">
                    <el-button style="padding:5px;"
                               icon="el-icon-delete"
                               size="mini"
                               type="danger"
                               @click="delComment(scope.row)">删除
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

    </div>

</template>

<script>
    export default {
        data() {
            return {
                commentList: [],
                total: 0,       //页总数
                page: 0,
                size: '',
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
                this.axios.get('/comment/findByPage/' + currentPage + '/5').then(function (res) {
                    //需要把地址前面部分删除
                    // console.log(resp.data);
                    _this.commentList = res.data.data_comment;
                })
            },
            //起始页
            created() {
                const _this = this;
                this.axios.get('/comment/findByPage/1/5').then(function (res) {
                    // console.log(res.data);
                    _this.commentList = res.data.data_comment;
                    _this.total = res.data.total_comment;
                    // console.log(_this.commentList)
                })
            },
            //删除当前评论
            delComment(row) {
                // console.log(row);
                const _this = this;
                this.$confirm('即将删除用户 “' + row.user.user_name + '” 的评论', '删除确认', {
                    distinguishCancelAndClose: true,
                    confirmButtonText: '确认删除',
                    cancelButtonText: '取消'
                }).then(() => {
                    this.axios.get('/comment/alt/' + row.id).then(function (res) {
                        // console.log(res);
                        if (res.data == 'alt success!') {
                            _this.$message({
                                message: '       删除成功！',
                                type: 'success'
                            });
                            window.location.reload();
                        }
                    })
                }).catch(action => {
                    this.$message({
                        type: 'info',
                        message: action === 'cancel'
                            ? '取消删除'
                            : '停留在当前页面'
                    })
                });
            },
            //问答类型过滤器
            clearFilter() {
                this.$refs.commentTable.clearFilter();
            },
            filterTag(value, row) {
                return row.c_type === value;
            }
        },

        mounted() {
            this.created();
        }

    }
</script>

<style>
    .user_img {
        cursor: pointer;
        width: 50px;
        height: 50px;
        border-radius: 50%;
        margin-left: 10px;
    }
</style>