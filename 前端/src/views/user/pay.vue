<template>
  <div>
    <div class="user">
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
                  prop="feeStatus"
                  label="缴费状态"
                  :formatter="convert"
          >
          </el-table-column>
          <el-table-column
                  prop="edit"
                  label="操作">
            <template slot-scope="scope">
              <el-button
                      size="small"
                      type="success"
                      icon="el-icon-circle-check"
                      plain
                      class="edit-btn"
                      :disabled="scope.row.feeStatus === 'Y'? true : false"
                      @click="handlePay(scope.$index, scope.row)">缴费</el-button>
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
    name: "pay",
    data() {
      return{
        form:{
          idCard:'',
          name:sessionStorage.getItem('name')
        },
        tableData:[],
        columns: [
          {
            prop: 'name',
            label: '姓名'
          },
          {
            prop: 'address',
            label: '位置'
          },
          {
            prop: 'mobile',
            label: '电话'
          },
          {
            prop: 'idCard',
            label: '身份证号'
          },
          {
            prop: 'endDate',
            label: '时间'
          },
          {
            prop: 'profee',
            label: '物业费'
          },
          {
            prop: 'waterfee',
            label: '水电费'
          },
          {
            prop: 'heatfee',
            label: '取暖费'
          },
          {
            prop: 'allNum',
            label: '总计'
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
    created() {
      this.getUser()
    },
    mounted() {
      setInterval(this.getUser,5000)
    },
    methods:{
      getUser () {
        this.$http.post('/serve/payList', {
          name: this.form.name,
          idCard: this.form.idCard,
          currentPage: this.currentPage,
          pageSize: this.pageSize
        })
                .then((res) => {
                  if (res.data.status === 200) {
                    this.tableData = res.data.data.records
                    this.pageTotal = res.data.data.total
                  }
                })
      },
      handlePay(index, row){
        this.$http.post('/money/pay', {
          out_trade_no: new Date().valueOf(),
          subject: `${row.id},物业费`,
          total_amount:row.allNum
        })
          .then((res) => {
            let routerData = this.$router.resolve({path:'/payModal',query:{htmls:res.data}})
            let htmls = res.data.result
            //打开新页面
            window.open(routerData.href,'_ blank')
            const div = document.createElement('div')
            div.innerHTML = htmls;
            document.body.appendChild(div);
            document.forms [0] .submit();
            this.payForm = res.data
          })
        setTimeout(()=>{
          this.$http.post('/serve/paymoney', {
            id: row.id,
            feeStatus: 'Y'
          })
            .then((res) => {
              if (res.data.status === 200) {
                this.getUser()
                this.$message.success('操作成功')
              }
            })
        },4000)
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
      convert(row) {
        return row.feeStatus === 'Y' ? '已缴费' : '未缴费'
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
