<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <el-col :span="4">
          <el-input
            v-model="form.name"
            class='condition-item'
            placeholder="姓名"
          >
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-input
            v-model="form.phone"
            class='condition-item'
            placeholder="电话"
          >
          </el-input>
        </el-col>
        
        <el-col>
          <el-button type="primary" class="task-btn" @click="search()">查询</el-button>
          <el-button type="warning" class="task-btn" @click="clear()">重置</el-button>
        </el-col>
      </el-row>
      <el-row class='task-list'>
        <el-table :data="tableData" >
          <el-table-column
            v-for='(item, index) in columns'
            :key='index'
            :prop="item.prop"
            :label="item.label"
            style="float: contour"
          >
          </el-table-column>
          <el-table-column
            prop="edit"
            label="操作"
            width="300">
            <template slot-scope="scope">
              <el-button
                size="small"
                type="primary"
                icon="iconfont icon-edit"
                plain
                class="edit-btn"
                @click="handleEdit(scope.$index, scope.row)">修改</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>
      <el-row class="task-pagination">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page.sync="currentPage"
          background
          :page="page"
          :page-size="pageSize"
          layout="prev, pager, next, jumper"
          :total="pageTotal">
        </el-pagination>
      </el-row>
    </div>
    <edit-modal v-if="showEdit" :classify="classify" :row="row"  @handleModal="handleModal"></edit-modal>
  </div>
</template>

<script>
  import editModal from "./editModal";
  export default {
    name: "information",
    data() {
      return{
        form:{
          name:'',
          mobile:''
        },
        showEdit: false,
        classify: 1,
        tableData:[],
        columns: [
          {
            prop: 'code',
            label: '账号'
          },
          {
            prop: 'password',
            label: '密码'
          },
          {
            prop: 'name',
            label: '姓名'
          },
          {
            prop: 'idCard',
            label: '身份证号'
          },
          {
            prop: 'mobile',
            label: '电话'
          },
          {
            prop: 'address',
            label: '位置'
          }
        ],
        page: 1,
        pageSize: 10,
        pageTotal: 0,
        currentPage: 1,
        row: {}
      }
    },
    components:{ editModal },
    created () {
      this.getInformation()
    },
    methods:{
      getInformation () {
        if (sessionStorage.getItem('code')) {
          this.$http.post('/user/ownerUser', {
            name: this.form.name,
            mobile: this.form.mobile,
            currentPage: this.currentPage,
            pageSize: this.pageSize
          })
            .then((res) => {
              console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
              console.log('res:', res)
              console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
              if (res.data.status === 200) {
                this.tableData = res.data.data.records
                this.pageTotal = res.data.data.total
              }
            })
        } else {
          this.$message.error('还没登录，请登录!')
          // this.$router.push('/')
        }
      },
      handleEdit(index, row){
        this.showEdit = true
        console.log('修改',row)
        this.row = row
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getInformation()
      },
      search () {
        this.getInformation()
      },
      clear() {
        this.form.code=''
        this.form.password=''
        this.form.name=''
        this.form.idCard=''
        this.form.mobile=''
        this.form.address=''
        this.getInformation()
      },
      handleModal () {
        this.showEdit = false
        this.getInformation()
      }
    }
  }
</script>

<style lang="scss" scoped>
  .user{
    width: 100%;
    height: 100%;
    background: white;
    .task-condition {
      width: 100%;
      padding: 20px 20px 10px 20px;
      box-sizing: border-box;
      padding-bottom: 10px;
      display: flex;
      align-items: center;
      .task-btn{
        margin: 0 5px;
      }
      .condition-item {
        width: 100%;
      }
      .el-col {
        margin-right: 2%;
        &:last-child {
          margin-right: 0;
        }
      }
    }
    .task-list {
      padding: 0 20px;
      overflow: hidden;
      width: 100%;
    }
    .edit-btn {
      margin-right: 5px;
      &:last-child {
        margin-right: 0;
      }
    }
    .task-pagination {
      margin: 0 20px;
      padding: 10px 40px 10px 0;
      background: #fff;
      display: flex;
      flex-direction: row-reverse;
    }
  }
</style>
