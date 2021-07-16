<template>
    <div>
        <el-tabs v-model="activeName" type="card">
            <el-tab-pane label="寻物启事" name="find">
                <el-table
                        :data="findList"
                        border
                        style="width: 90%;margin-top: 10px">
                    <el-table-column
                            prop="g_time"
                            label="发布时间"
                            sortable
                            width="200">
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
                            prop="g_content"
                            label="内容"
                            width="500">
                    </el-table-column>
                    <el-table-column
                            label="物品图片"
                            width="150" >
                        <template slot-scope="scope">
                            <el-image
                                    style="width: 80px; height: 80px"
                                    :src="'data:image/jpg;base64,' + scope.row.g_img">
                            </el-image>
                        </template>
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作">
                        <template slot-scope="scope" style="align-items: center">
                            <el-button style="padding:5px;"
                                       icon="el-icon-delete"
                                       size="mini"
                                       type="danger"
                                       @click="delGoods(scope.row)">删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="display:flex;margin-top: 10px">
                    <el-pagination
                            background
                            @current-change="currentChange"
                            @size-change="sizeChange"
                            :page-size="3"
                            layout="prev, pager, next, jumper, ->, slot , total"
                            :total="findTotal">
                    </el-pagination>
                </div>
            </el-tab-pane>
            <el-tab-pane label="失物招领" name="lost">
                <el-table
                        :data="lostList"
                        border
                        style="width: 90%;margin-top: 10px">
                    <el-table-column
                            prop="g_time"
                            label="发布时间"
                            sortable
                            width="200">
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
                            prop="g_content"
                            label="内容"
                            width="500">
                    </el-table-column>
                    <el-table-column
                            label="物品图片"
                            width="100" >
                        <template slot-scope="scope">
                            <el-image
                                    style="width: 80px; height: 80px"
                                    :src="'data:image/jpg;base64,' + scope.row.g_img">
                            </el-image>
                        </template>
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作">
                        <template slot-scope="scope" style="align-items: center">
                            <el-button style="padding:5px;"
                                       icon="el-icon-delete"
                                       size="mini"
                                       type="danger"
                                       @click="delGoods(scope.row)">删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="display:flex;margin-top: 10px">
                    <el-pagination
                            background
                            @current-change="currentChange"
                            @size-change="sizeChange"
                            :page-size="3"
                            layout="prev, pager, next, jumper, ->, slot , total"
                            :total="lostTotal">
                    </el-pagination>
                </div>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        name: "GoodsManager",
        data() {
            return {
                activeName: 'find',
                findList: [],
                lostList: [],
                findTotal: 0,       //页总数
                lostTotal: 0,       //页总数
                page: 0,
                size: ''
            }
        },
        methods: {
            //选项卡
            // handleClick(tab, event) {
            //     console.log(tab, event);
            // },
            sizeChange(pageSize) {
                //console.log('pageSize:'+pageSize)
                this.size = pageSize;
                this.created();
            },
            //当前页
            currentChange(currentPage) {
                //console.log('currentPage:'+currentPage)
                const _this = this;
                this.axios.get('/goods/findFindGoods/' + currentPage + '/3').then(function (res) {
                    _this.findList = res.data.data_find;
                });
                this.axios.get('/goods/findLostGoods/' + currentPage + '/3').then(function (res) {
                    _this.lostList = res.data.data_lost;
                });
            },
            //起始页
            created() {
                const _this = this;
                //获取寻物数据
                this.axios.get('/goods/findFindGoods/1/3').then(function (res) {
                    // console.log(resp.data);
                    _this.findList = res.data.data_find;
                    _this.findTotal = res.data.total_find;
                });
                //获取失物数据
                this.axios.get('/goods/findLostGoods/1/3').then(function (res) {
                    // console.log(resp.data);
                    _this.lostList = res.data.data_lost;
                    _this.lostTotal = res.data.total_lost;
                })
            },
            //删除当前行
            delGoods(row) {
                // console.log(row);
                const _this = this;
                this.$confirm('即将删除用户 “' + row.user.user_name + '” 的物品信息', '删除确认', {
                    distinguishCancelAndClose: true,
                    confirmButtonText: '确认删除',
                    cancelButtonText: '取消'
                }).then(() => {
                    this.axios.get('/goods/delete/' + row.id).then(function (res) {
                        // console.log(res);
                        if (res.data == 'delete success!') {
                            _this.$message({
                                message: '     删除成功！',
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
            }
        },
        mounted() {
            this.created();
        }
    }
</script>

<style scoped>

</style>