<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <div class="staff-head">
          <el-input
                  v-model="form.mobile"
                  class='condition-item'
                  placeholder="电话"
          >
          </el-input>

          <el-input
                  v-model="form.name"
                  class='condition-item'
                  placeholder="业主姓名"
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
                  prop="edit"
                  width="300"
                  label="操作">
            <template slot-scope="scope">
              <el-button
                      size="small"
                      type="success"
                      icon="el-icon-circle-check"
                      plain
                      class="edit-btn"
                      :disabled="scope.row.serveStatus === '待维修'? false : true"
                      @click="handleChangeRepair(scope.$index, scope.row, '正在维修中')">正在维修中</el-button>
              <el-button
                      size="small"
                      type="success"
                      icon="el-icon-circle-check"
                      plain
                      class="edit-btn"
                      :disabled="scope.row.serveStatus === '正在维修中'? false : true"
                      @click="handleChangeRepair(scope.$index, scope.row, '完成报修')">完成报修</el-button>
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
  </div>
</template>

<script>
  export default {
    name: "repair",
    data() {
      return{
        showModal:false,
        form:{
          mobile:'',
          name:''
        },
        tableData:[],
        columns: [
          {
            prop: 'name',
            label: '业主姓名'
          },
          {
            prop: 'mobile',
            label: '业主电话'
          },
          {
            prop: 'address',
            label: '报修位置'
          },
          {
            prop: 'serveMan',
            label: '维修人'
          },
          {
            prop: 'serveMobile',
            label: '维修人电话'
          },
          {
            prop: 'thing',
            label: '配件'
          },
          {
            prop: 'num',
            label: '数量'
          },
          {
            prop: 'money',
            label: '金额'
          },
          {
            prop: 'serveStatus',
            label: '报修状态'
          },
          {
            prop: 'moneyStatus',
            label: '缴费状态'
          }
        ],
        page: 1,
        pageSize: 10,
        pageTotal: 0,
        currentPage: 1
      }
    },
    components:{
    },
    mounted() {
      this.getRepair()
    },
    methods:{
      getRepair () {
        this.$http.post('/serve/repairList', {
          name: this.form.name,
          mobile: this.form.mobile,
          code: sessionStorage.getItem('code'),
          role: sessionStorage.getItem('status'),
          currentPage: this.currentPage,
          pageSize: this.pageSize
        })
          .then((res) => {
            if (res.status === 200) {
              this.tableData = []
              this.tableData = res.data.data.records
              this.pageTotal = res.data.data.total
            }
          })
      },
      handleChangeRepair (index, row, status) {
        this.serveStatus = status
        this.$http.post('/serve/serveUpdate', {
          id: row.id,
          serveStatus: this.serveStatus
        })
          .then((res) => {
            if (res.status === 200) {
              this.getRepair()
              this.$message.success('操作成功')
            }
          })
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getRepair()
      },
      search () {
        this.getRepair()
      },
      clear() {
        this.form.name=''
        this.form.department=''
        this.getRepair()
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
