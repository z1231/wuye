<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <div class="staff-head">
          <el-input
            v-model="form.name"
            class='condition-item'
            placeholder="姓名"
          >
          </el-input>
          <el-button type="primary" class="task-btn" @click="search()">查询</el-button>
          <el-button type="warning" class="task-btn" @click="clear()">重置</el-button>
        </div>
      </el-row>
      <el-row class='task-list'>
        <el-table :data="tableData">
          <el-table-column
            v-for='(item, index) in columns'
            :key='index'
            :prop="item.prop"
            :label="item.label"
            style="float: contour"
          >
          </el-table-column>
          <el-table-column
            prop="content"
            label="内容"
            width="700">
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
  </div>
</template>

<script>
  export default {
    name: "personal",
    data() {
      return{
        form:{
          name:''
        },
        tableData:[],
        columns: [
          {
            prop: 'name',
            label: '姓名'
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
        showAdd: false
      }
    },
    components:{
    },
    created () {
      this.getUser()
    },
    methods:{
      getUser () {
        if (sessionStorage.getItem('code')) {
          let code
          if(sessionStorage.getItem('status') === '1') {
            code = ''
          } else {
            code = sessionStorage.getItem('code')
          }
          this.$http.post('/user/adviceList', {
            code: code,
            name: this.form.name,
            currentPage: this.currentPage,
            pageSize: this.pageSize
          })
            .then((res) => {
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
      handleSend(){
        window.open("https://mail.qq.com")
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getUser()
      },
      search () {
        this.getUser()
      },
      clear() {
        this.form.name=''
        this.form.department=''
        this.getUser()
      },
      add () {
        this.showAdd = true
      },
      handleClose () {
        this.getUser()
        this.showAdd = false
      }
    }
  }
</script>

<style lang="scss" scoped>
  .user{
    width: 100%;
    height: 100%;
    min-width: 1200px;
    min-height: 500px;
    background: white;
    .task-condition {
      width: 100%;
      padding: 20px 20px 10px 20px;
      box-sizing: border-box;
      padding-bottom: 10px;
      display: flex;
      align-items: flex-end;
      .staff-head{
        width: 40%;
        .task-btn{
          margin: 0 5px;
        }
        .condition-item {
          width: 30%;
          margin-right: 10px;
        }
        .el-col {
          margin-right: 2%;
          &:last-child {
            margin-right: 0;
          }
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
