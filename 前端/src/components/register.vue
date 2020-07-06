<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>注册新用户</div>
      <div class="modal-form">
        <el-scrollbar style="height: 100%">
          <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="账号">
              <el-input v-model="form.code"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input show-password v-model="form.password"></el-input>
            </el-form-item>
            <el-form-item label="角色">
              <el-radio-group v-model="form.role">
                <el-radio label="业主" disabled="disabled"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="姓名">
              <el-input v-model="form.name"></el-input>
            </el-form-item>
            <el-form-item label="电话">
              <el-input v-model="form.mobile"></el-input>
            </el-form-item>
            <el-form-item label="身份证号">
              <el-input v-model="form.iDCard" maxlength></el-input>
            </el-form-item>
            <el-form-item label="邮箱">
              <el-input v-model="form.email" maxlength></el-input>
            </el-form-item>
            <el-form-item label="楼层位置">
              <el-tree
                :data="data"
                :props="defaultProps"
                ref="tree"
                node-key="id"
                accordion
                highlight-current
                @node-click="setCheckedNodes">
              </el-tree>
            </el-form-item>
          </el-form>
        </el-scrollbar>
        <div class="modal-btn">
          <el-button type="primary" @click="onSubmit">注册</el-button>
          <el-button @click="close">取消</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "register",
    data() {
      return {
        form: {
          code :'',
          password:'',
          role:'2',
          name: '',
          iDCard: '',
          mobile:'',
          address:'',
          email:''
        },
        position:[],
        data: [{
          id: 1,
          label: '1#',
          children: [{
            id: 2,
            label: '1单元',
            children: [{
              id: 3,
              label: '101'
            },{
              id: 4,
              label: '102'
            },{
              id: 5,
              label: '103'
            }]
          },{
            id: 6,
            label: '2单元',
            children: [{
              id: 7,
              label: '101'
            },{
              id: 8,
              label: '102'
            },{
              id: 9,
              label: '103'
            }]
          },{
            id: 10,
            label: '3单元',
            children: [{
              id: 11,
              label: '101'
            },{
              id: 12,
              label: '102'
            },{
              id: 13,
              label: '103'
            }]
          }]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      }
    },
    methods:{
      onSubmit() {
        if(this.objectKeyIsEmpty (this.form)){
          this.$message.error('请填写完整信息')
        }else{
          //请求接口在成功回调方法里调用下一行
          this.$http.post('/user/register',this.form)
            .then((res) => {
              console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
              console.log('res:', res)
              if (res.data.status === 200) {
                this.$message.success('恭喜你，注册成功')
                this.close()
              } else {
                this.$message.error(res.data.msg)
                this.form.code =''
                this.form.password=''
                this.form.role='2'
                this.form.name= ''
                this.form.iDCard= ''
                this.form.mobile=''
                this.form.address=''
                this.form.email=''
              }
              console.log('■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■')
            })
          console.log('submit!', this.form);
        }
      },
      // 检查未填满数据
      objectKeyIsEmpty (obj) {
        // debugger
        let empty = null
        for(const index in obj){
          if(obj[index] === ''){
            empty = true
            break
          }else {
            empty = false
          }
        }
        return empty
      },
      // 将三级地址存入数组中
      setCheckedNodes(data){
        if(this.$refs.tree.getNode(data.id).parent.data.label){
          if(this.$refs.tree.getNode(this.$refs.tree.getNode(data.id).parent.data.id).parent.data.label) {
            this.position[2] = data.label
            this.position[1] = this.$refs.tree.getNode(data.id).parent.data.label
            this.position[0] = this.$refs.tree.getNode(this.$refs.tree.getNode(data.id).parent.data.id).parent.data.label
            this.form.address = this.position.toString()
          }
        }
      },
      close() {
        this.$emit('handleClose',false)
        this.form.code =''
        this.form.password=''
        this.form.role='2'
        this.form.name= ''
        this.form.iDCard= ''
        this.form.mobile=''
        this.form.address=''
        this.form.email=''
      }
    }
  }
</script>

<style lang="scss" scoped>
  .setting-modal {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 101;
    background-color:rgba(255,255,255,0.6);
  }
  .modal-content {
    width:600px;
    height:650px;
    background:rgba(255,255,255,1);
    box-shadow:8px 8px 29px 0px rgba(7,42,90,0.7);
    border-radius:3px;
    z-index: 101;
    color:rgba(59,86,129,1);
    address: relative;
    .modal-header {
      height: 65px;
      margin: 0;
      font-size:34px;
      line-height: 65px;
      text-align: center;
      font-family:PingFang-SC;
      font-weight:400;
      color:rgba(59,86,129,1);
      border-bottom: 1px solid rgba(235,236,240,0.95);
    }
    .modal-form{
      width: 80%;
      height: 500px;
      margin: 0 auto;
      padding: 10px 0;
      ::v-deep.el-scrollbar__wrap {
        overflow-x: hidden;
      }
      .modal-btn{
        text-align: center;
      }
    }
  }
</style>
