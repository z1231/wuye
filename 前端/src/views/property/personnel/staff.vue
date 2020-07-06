<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
<!--        <el-button type="primary" class="task-btn" @click="add()">入职</el-button>-->
        <div class="staff-head">
          <el-select v-model="form.org" class='condition-item' placeholder="请选择部门">
            <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
          </el-select>
          
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
            prop="edit"
            label="操作"
            width="300">
            <template slot-scope="scope">
              <el-button
                size="small"
                type="danger"
                icon="el-icon-delete-solid"
                plain
                class="edit-btn"
                @click="handleDelete(scope.$index, scope.row)">离职</el-button>
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
<!--    <addStaffModal v-if="showAdd" @handleClose="handleClose"></addStaffModal>-->
  </div>
</template>

<script>
  // import addStaffModal from "./addStaffModal";
  export default {
    name: "staff",
    data() {
      return{
        showAdd:false,
        form:{
          org:'',
          name:''
        },
        tableData:[],
        columns: [
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
            prop: 'org',
            label: '部门'
          },
          {
            prop: 'work',
            label: '岗位'
          }
        ],
        options: [{
          value: '财务部',
          label: '财务部'
        }, {
          value: '商务部',
          label: '商务部'
        }, {
          value: '后勤部',
          label: '后勤部'
        }, {
          value: '人力资源部',
          label: '人力资源部'
        }],
        page: 1,
        pageSize: 10,
        pageTotal: 0,
        currentPage: 1
      }
    },
    // components:{
    //   addStaffModal
    // },
    created () {
      this.getStaff()
    },
    methods:{
      getStaff () {
        this.$http.post('/user/personList', {
          name: this.form.name,
          org: this.form.org,
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
            } else {
              this.$message.error(res.data.msg)
            }
          })
      },
      handleDelete(index, row){
        this.$http.post('/user/personGo', {
          id: row.id
        })
          .then((res) => {
            console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
            console.log('res:', res)
            console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
            if (res.data.status === 200) {
              this.$message.success('离职成功')
              this.getStaff()
            } else {
              this.$message.error(res.data.msg)
            }
          })
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getStaff()
      },
      // add(){
      //   console.log('add')
      //   this.showAdd = true
      // },
      search () {
        this.getStaff()
      },
      clear() {
        this.form.name=''
        this.form.org=''
        this.getStaff()
      }
      // handleClose(data){
      //   this.showAdd = data
      //   this.getStaff()
      // }
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
