<template>
    <div>
        <div>
            <el-autocomplete
                    v-model="searchName"
                    :fetch-suggestions="querySearch"
                    placeholder="输入用户名进行搜索..."
                    prefix-icon="el-icon-search"
                    value-key="user_name"
                    @select="handleSelect">
            </el-autocomplete>
            <el-button type="info" icon="el-icon-close" @click="clearSelect"
                       placeholder="close selected..."></el-button>
        </div>

        <el-table
                :data="userList"
                border
                style="width: 90%;margin-top: 10px">
            <el-table-column
                    fixed
                    prop="user_name"
                    label="姓名"
                    width="200">
            </el-table-column>
            <el-table-column
                    label="用户头像"
                    width="80">
                <template slot-scope="scope">
                    <el-image
                            class="user_img"
                            :src="'data:image/jpg;base64,' + scope.row.user_img">
                    </el-image>
                </template>
            </el-table-column>
            <el-table-column
                    prop="user_number"
                    label="用户账号"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="user_phone"
                    label="联系方式"
                    width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作">
                <template slot-scope="scope" style="align-items: center">
                    <el-button style="padding:5px;"
                               icon="el-icon-delete"
                               size="mini"
                               type="danger"
                               @click="delUser(scope.row)">删除
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
                userList: [],
                total: 0,       //页总数
                page: 0,
                size: '',
                searchName: '',
                timeout: null
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
                this.axios.get('/user/findByPage/' + currentPage + '/5').then(function (res) {
                    //需要把地址前面部分删除
                    // console.log(resp.data);
                    _this.userList = res.data.data_user;
                })
            },
            //起始页
            created() {
                const _this = this;
                this.axios.get('/user/findByPage/1/5').then(function (res) {
                    //axios.get写法
                    // console.log(resp.data);
                    _this.userList = res.data.data_user;
                    _this.total = res.data.total_user;
                })
            },
            //删除当前行
            delUser(row) {
                // console.log(row);
                const _this = this;
                this.$confirm('即将删除的是姓名为 “' + row.user_name + '” 的用户', '删除确认', {
                    distinguishCancelAndClose: true,
                    confirmButtonText: '确认删除',
                    cancelButtonText: '取消'
                }).then(() => {
                    this.axios.get('/user/delete/' + row.id).then(function (res) {
                        // console.log(res);
                        if (res.data == 'delete success!') {
                            _this.$message({
                                message: '用户：' + row.user_name +
                                    '                   删除成功！',
                                type: 'success'
                            });
                            window.location.reload();
                        }
                        ;
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
            //搜索框的输入值
            querySearch(queryString, cb) {
                const _this = this;
                this.axios.get('/user/findAll').then(function (res) {
                    // console.log(resp.data);
                    var selectName = res.data;
                    // console.log(selectName);
                    var results = queryString ? selectName.filter(_this.createSearchFilter(queryString)) : selectName;
                    // 调用 callback 返回建议列表的数据
                    clearTimeout(_this.timeout);
                    _this.timeout = setTimeout(() => {
                        cb(results);
                    }, 1000 * Math.random());
                })
            },
            //搜索框输入建议值
            createSearchFilter(queryString) {
                return (searchName) => {
                    return (searchName.user_name.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
                };
            },
            //搜索框选择事件
            handleSelect(item) {
                // console.log(item);
                const _this = this;
                this.axios.get('/user/findByName/' + item.user_name).then(function (res) {
                    // console.log(resp);
                    _this.userList = res.data;
                })
            },
            //清除当前搜索
            clearSelect() {
                this.created();
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