<template>
    <div>
        <div>
            <el-autocomplete
                    v-model="searchTitle"
                    :fetch-suggestions="querySearch"
                    placeholder="输入标题进行搜索..."
                    prefix-icon="el-icon-search"
                    value-key="n_title"
                    @select="handleSelect">
            </el-autocomplete>
            <el-button type="info" icon="el-icon-close" @click="clearSelect"
                       placeholder="close selected..."></el-button>
        </div>

        <el-table
                :data="newsList"
                border
                style="width: 90%;margin-top: 10px"
                :default-sort="{prop: 'n_time', order: 'descending'}">
            <el-table-column
                    prop="n_time"
                    label="发表时间"
                    sortable
                    width="210">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="n_title"
                    label="文章标题"
                    width="400">
            </el-table-column>
            <el-table-column
                    prop="n_views"
                    label="访问量"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
            >
                <template slot-scope="scope" style="align-items: center">
                    <el-button style="padding:5px;"
                               icon="el-icon-edit-outline"
                               size="mini"
                               type="warning"
                               @click="toEdit(scope.row)">修改
                        <!--                        scope.row:传入当前数据-->
                    </el-button>
                    <el-button style="padding:5px;"
                               icon="el-icon-delete"
                               size="mini"
                               type="danger"
                               @click="deleteNews(scope.row)">删除
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
        <el-dialog title="新闻编辑"
                   :visible.sync="editFormVisible"
                   :close-on-click-modal="false">
            <el-form :model="editForm" :rules="editRules" label-width="80px" ref="editForm">
                <el-form-item label="新闻标题" prop="n_title">
                    <el-input v-model="editForm.n_title"  auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="发表时间" prop="n_time">
                    <el-date-picker
                            v-model="editForm.n_time"
                            type="datetime"
                            placeholder="选择日期时间"
                            default-value="editForm.n_time">
                    </el-date-picker>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="edit('editForm')">修改</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                newsList: [],
                total: 0,       //页总数
                page: 0,
                size: '',
                searchTitle: '',
                timeout: null,
                editForm: {
                    n_title: '',
                    n_time: ''
                },
                editRules: {
                    n_title: [
                        {required: true, message: '新闻标题不能为空！', trigger: 'blur'},
                    ],
                    n_time: [
                        {required: true, message: '发表时间不能为空！', trigger: 'change'}
                    ]
                },
                editFormVisible: false
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
                this.axios.get('/news/findByPage/' + currentPage + '/5').then(function (res) {
                    //需要把地址前面部分删除
                    // console.log(res.data);
                    _this.newsList = res.data.data_news;
                })
            },

            //起始页
            created() {
                const _this = this;
                this.axios.get('/news/findByPage/1/5').then(function (res) {
                    //axios.get写法
                    // console.log(res.data);
                    _this.newsList = res.data.data_news;
                    _this.total = res.data.total_news;
                })
            },

            //修改
            toEdit(row) {
                const _this = this;
                // console.log(row);
                this.axios.get('/news/findById/'+row.id).then(function (res) {
                    _this.editForm = res.data;
                    // console.log(_this.editForm);
                });
                this.editFormVisible = true;
            },

            edit(formName) {
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    //校验,$refs获取表单
                    if (valid) {
                        this.axios.put('/news/alt', this.editForm).then(function (res) {
                            console.log(res);
                            if (res.data == 'alt success!') {
                                _this.editFormVisible = false;
                                _this.$message({
                                    message: '修改成功！',
                                    type: 'success'
                                });
                                window.location.reload()
                            }else {
                                _this.$message({
                                    message: '修改失败！',
                                    type: 'warning'
                                });
                            }
                        })
                    }
                });
            },

            //删除当前行
            deleteNews(row) {
                // console.log(row);
                const _this = this;
                this.$confirm('即将删除的是标题为 “' + row.n_title + '” 的新闻', '删除确认', {
                    distinguishCancelAndClose: true,
                    confirmButtonText: '确认删除',
                    cancelButtonText: '取消'
                }).then(() => {
                    this.axios.get('/news/delete/' + row.id).then(function (res) {
                        // console.log(res);
                        if (res.data == 'delete success!') {
                            _this.$message({
                                message: '新闻：' + row.n_title +
                                    '                   删除成功！',
                                type: 'success'
                            });
                            _this.created()
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
                this.axios.get('/news/findAll').then(function (res) {
                    // console.log(res.data);
                    var searchTitle = res.data;
                    // console.log(selectName);
                    var results = queryString ? searchTitle.filter(_this.createSearchFilter(queryString)) : searchTitle;
                    // 调用 callback 返回建议列表的数据
                    clearTimeout(_this.timeout);
                    _this.timeout = setTimeout(() => {
                        cb(results);
                    }, 1000 * Math.random());
                })
            },
            //搜索框输入建议值
            createSearchFilter(queryString) {
                return (searchTitle) => {
                    return (searchTitle.n_title.toLowerCase().indexOf(queryString.toLowerCase()) !== -1);
                };
            },
            //搜索框选择事件
            handleSelect(item) {
                // console.log(item);
                const _this = this;
                this.axios.get('/news/findByName/' + item.n_title).then(function (res) {
                    // console.log(res);
                    _this.newsList = res.data;
                })
            },
            //清除当前搜索
            clearSelect() {
                this.searchTitle = '';
                this.created();
            }
        },

        mounted() {
            this.created();
        }

    }
</script>