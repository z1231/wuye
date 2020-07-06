<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <div>
          <el-button type="primary" class="task-btn" @click="add()">新增</el-button>
          <el-button type="primary" class="task-btn" @click="downloadTask()">下载</el-button>
        </div>
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
            label="操作">
            <template slot-scope="scope">
              <el-button
                size="small"
                type="success"
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
    <addSchedulingModal v-if="showAdd" :state="state" :row="row" @showAddModal="showAddModal"></addSchedulingModal>
  </div>
</template>

<script>
  import addSchedulingModal from "./addSchedulingModal";
  export default {
    name: "scheduling",
    data() {
      return{
        showAdd:false,
        showEdit:false,
        state: null,
        row: {},
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
          },
          {
            prop: 'workTime',
            label: '开始时间'
          },
          {
            prop: 'endTime',
            label: '结束时间'
          },
          {
            prop: 'address',
            label: '工作地点'
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
      addSchedulingModal
    },
    created () {
      this.getUser()
    },
    methods:{
      getUser () {
        this.$http.post('/user/cleanList', {
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
      handleEdit(index, row){
        this.state = 1
        this.row = row
        this.showAdd = true
        console.log('修改',row)
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getUser()
      },
      add(){
        console.log('add')
        this.state = 0
        this.showAdd = true
      },
      search () {
        this.getUser()
      },
      clear() {
        this.form.name=''
        this.form.org=''
        this.getUser()
      },
      handleClose(data){
        this.showAdd = data
        this.getUser()
      },
      showAddModal(data){
        this.showAdd=data
        this.getUser()
      },
      downloadTask(){
        this.$http({
          url: '/serve/download',
          method: 'post',
          responseType: 'blob', // 服务器返回的数据类型
          params: { // 其他参数
            excel: 'excel02'
          },
          data: {}
        }).then((res) => {
          // 关闭loading
          console.log(res)
          // 此处有个坑。这里用content保存文件流，最初是content=res，但下载的test.xls里的内容如下图1，
          // 检查了下才发现，后端对文件流做了一层封装，所以将content指向res.data即可
          // 另外，流的转储属于浅拷贝，所以此处的content转储仅仅是便于理解，并没有实际作用=_=
          const content = res.data
          const blob = new Blob([content]) // 构造一个blob对象来处理数据
          const fileName = '安保保洁排班.xlsx' // 导出文件名
          // 对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
          // IE10以上支持blob但是依然不支持download
          if ('download' in document.createElement('a')) { // 支持a标签download的浏览器
            const link = document.createElement('a') // 创建a标签
            link.download = fileName // a标签添加属性
            link.style.display = 'none'
            link.href = URL.createObjectURL(blob)
            document.body.appendChild(link)
            link.click() // 执行下载
            URL.revokeObjectURL(link.href) // 释放url
            document.body.removeChild(link) // 释放标签
          } else { // 其他浏览器
            navigator.msSaveBlob(blob, fileName)
          }
        }).catch((error) => {
          console.log(error)
        })
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
