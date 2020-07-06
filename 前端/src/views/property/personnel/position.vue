<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <el-col :span="24">
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
        </el-col>
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
                type="primary"
                icon="el-icon-edit-outline"
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
    <editPosition v-if="showEdit" :row="row" @handleClose="handleClose"></editPosition>
  </div>
</template>

<script>
  import editPosition from "./editPosition";
  export default {
    name: "position",
    data() {
      return{
        row: {},
        showEdit:false,
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
    components:{
      editPosition
    },
    created () {
      this.getPosition()
    },
    methods:{
      getPosition () {
        this.$http.post('/user/workList',{
          name: this.form.name,
          org: this.form.org,
          currentPage: this.currentPage,
          pageSize: this.pageSize
        })
          .then((res) => {
            if (res.data.status === 200) {
              this.tableData = res.data.data.records
              this.pageTotal = res.data.data.total
            } else {
              this.$message.error(res.data.msg)
            }
          })
      },
      handleEdit(index, row){
        this.showEdit = true
        this.row = row
        console.log('修改',row)
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getPosition()
      },
      search () {
        this.getPosition()
      },
      clear() {
        this.form.name=''
        this.form.org=''
        this.getPosition()
      },
      handleClose(){
        this.showEdit = false
        this.getPosition()
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
        width: 20%;
        margin-right: 10px;
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
