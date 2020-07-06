<template>
  <div>
    <div class="user">
      <el-row class='task-condition' type="flex" justify="space-between">
        <div style="display: flex">
          <el-button size="small" type="primary" style="margin-right: 20px" @click="downLoad">下载模板</el-button>
          <el-upload action :show-file-list="false" :http-request="devUpload">
            <el-button
                    class="reset-btn"
                    type="primary"
                    icon="iconfont icon-import"
                    size="small"
                    plain
            >导入</el-button>
          </el-upload>
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
    <editAttendance v-if="showAttendanceModal" :row="row" @handleClose="handleClose"></editAttendance>
  </div>
</template>

<script>
  import editAttendance from "./editAttendance";
  export default {
    name: "attendance",
    data() {
      return{
        fileList: [],
        bool: true,
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
            prop: 'lateNum',
            label: '累计迟到次数'
          },
          {
            prop: 'earlyNum',
            label: '累计早退次数'
          },
          {
            prop: 'absentNum',
            label: '累计旷工次数'
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
        currentPage: 1,
        showAttendanceModal: false,
        row: {}
      }
    },
    components:{
      editAttendance
    },
    created () {
      this.getAttendance()
    },
    methods:{
      getAttendance () {
        this.$http.post('/user/checkList', {
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
        this.showAttendanceModal = true
        this.row = row
        console.log('修改',row)
      },
      handleSizeChange (val) {
        console.log(`每页 ${val} 条`)
      },
      handleCurrentChange (val) {
        console.log(`当前页: ${val}`)
        this.getAttendance()
      },
      search () {
        this.getAttendance()
      },
      clear() {
        this.form.name=''
        this.form.org=''
        this.getAttendance()
      },
      handleClose(){
        this.showAttendanceModal = false
        this.getAttendance()
      },
      downLoad () {
        this.$http({
          url: '/serve/downExp',
          method: 'post',
          responseType: 'blob', // 服务器返回的数据类型
          params: { // 其他参数
            excel: 'excel02'
          },
          data: {}
        }).then((res) => {
          const content = res.data
          const blob = new Blob([content]) // 构造一个blob对象来处理数据
          const fileName = '考勤.xlsx' // 导出文件名
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
      },
      devUpload (fileObj) {
        let param = new window.FormData();
        param.append("file", fileObj.file);
        this.$http({
          url: '/serve/doExpt',
          method: 'post',
          data: param,
          processData: false, //因为data值是FormData对象，不需要对数据做处理。
          contentType: false,
          headers:{"Content-Type":"multipart/form-data"},
        })
          .then((res) => {
            if (res.data.status === 200) {
              this.$message.success('提交成功')
              this.getAttendance()
            }
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
      color: #50668B;
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
