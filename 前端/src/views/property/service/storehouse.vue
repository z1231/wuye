<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <div>
          <el-button type="primary" class="task-btn" @click="add()">新增</el-button>
        </div>
        <div>
          <el-input
            v-model="name"
            class='condition-item'
            placeholder="名称"
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
    <addStoreModal v-if="showAddModal" @handleClose="handleClose"></addStoreModal>
  </div>
</template>

<script>
  import addStoreModal from "./addStoreModal";
  export default {
    name: "user",
    data() {
      return{
        showAddModal:false,
        name:'',
        tableData:[],
        columns: [
          {
            prop: 'title',
            label: '名称'
          },
          {
            prop: 'num',
            label: '数量'
          },
          {
            prop: 'money',
            label: '金额'
          }
        ],
        page: 1,
        pageSize: 10,
        pageTotal: 0,
        currentPage: 1
      }
    },
    components:{ addStoreModal },
    created () {
      this.getReper()
    },
    methods:{
      getReper () {
        this.$http.post('/serve/reperList', {
          name: this.name,
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
      handleEdit(row){
        console.log('修改',row)
      },
      handleDelete(row){
        console.log('删除',row)
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getReper()
      },
      add(){
        console.log('add')
        this.showAddModal = true
      },
      search () {
        this.getReper()
      },
      clear() {
        this.name=''
        this.getReper()
      },
      handleClose(){
        this.showAddModal = false
        this.getReper()
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
