<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <div>
          <el-button type="primary" class="task-btn" @click="add">新增</el-button>
        </div>
        <div>
          <el-input
            v-model="form.name"
            class='condition-item'
            placeholder="姓名"
          >
          </el-input>
          <el-input
            v-model="form.duty"
            class='condition-item'
            placeholder="岗位"
          >
          </el-input>
          <el-button type="primary" class="task-btn" @click="search()">查询</el-button>
          <el-button type="warning" class="task-btn" @click="clear()">重置</el-button>
        </div>
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
            label="操作">
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
    <edit-modal v-if="showEdit" :classify="classify" :row="row" @handleModal="handleModal"></edit-modal>
    <add-modal v-if="showAdd" @handleAddModal="handleAddModal"></add-modal>
  </div>
</template>

<script>
  import editModal from "./editModal";
  import AddModal from "./addModal";
  export default {
    name: "user",
    data() {
      return{
        showEdit: false,
        showAdd: false,
        classify: 0,
        form:{
          name:'',
          duty:''
        },
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
            prop: 'org',
            label: '部门'
          },
          {
            prop: 'work',
            label: '岗位'
          }
        ],
        page: 1,
        pageSize: 10,
        pageTotal: 0,
        currentPage: 1,
        row: {}
      }
    },
    components:{AddModal, editModal },
    created () {
      this.getUser()
    },
    methods:{
      getUser () {
        if (sessionStorage.getItem('code')) {
          this.$http.post('/user/adminUser', {
            name: this.form.name,
            duty: this.form.duty,
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
        console.log('修改',row)
        this.showEdit = true
        this.row = row
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getUser()
      },
      add(){
        this.showAdd = true
      },
      search () {
        this.getUser()
      },
      clear() {
        this.form.name=''
        this.form.duty=''
        this.getUser()
      },
      handleAddModal (){
        this.showAdd = false
        this.getUser()
      },
      handleModal () {
        this.showEdit = false
        this.getUser()
      }
    }
  }
</script>

<style lang="scss" scoped>
.user{
  width: 100%;
  height: 100%;
  min-width: 1000px;
  min-height: 500px;
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
      width: 130px;
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
