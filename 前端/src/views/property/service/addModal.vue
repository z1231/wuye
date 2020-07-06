<template>
  <div class='setting-modal'>
    <div class='modal-content'>
      <div class='modal-header'>新增报修</div>
      <div class="modal-form">
        <div class="modal-upload">
          <el-form ref="form" :model="form" label-width="100px">
            <el-form-item label="业主姓名">
              <el-select style="width: 100%" v-model="form.name" @change="changeName" class='condition-item' placeholder="请选择">
                <el-option
                        v-for="item in userOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="业主电话">
              <el-input v-model="form.mobile" disabled></el-input>
            </el-form-item>
            <el-form-item label="报修位置">
              <el-input v-model="form.address" disabled></el-input>
            </el-form-item>
            <el-form-item label="维修人">
              <el-select style="width: 100%" v-model="form.serveMan" @change="changeRepair" class='condition-item' placeholder="请选择">
                <el-option
                        v-for="item in serveOptions"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="维修人电话">
              <el-input v-model="form.serveMobile" disabled></el-input>
            </el-form-item>
            <el-form-item label="配件">
              <el-select style="width: 100%" v-model="form.thing" class='condition-item' placeholder="请选择">
                <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="数量">
              <el-input v-model="form.num"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <div class="modal-btn">
          <el-button type="primary" @click="onSubmit">确定</el-button>
          <el-button @click="close">取消</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "addModal",
    data() {
      return {
        form:{
          name:'',
          mobile:'',
          address:'',
          serveMan:'',
          serveMobile:'',
          thing:'',
          num:'',
          money:'',
          code:'',
          upCode:'',
          serveStatus:'待维修',
          moneyStatus:'待支付',
        },
        reperdata:[],
        servedata:[],
        userdata:[],
        options: [{
          value: '50水龙头',
          label: '50水龙头'
        }, {
          value: '50水管',
          label: '50水管'
        }, {
          value: '120V空气开关',
          label: '120V空气开关'
        }, {
          value: '防烟报警阀',
          label: '防烟报警阀'
        }],
        serveOptions: [],
        userOptions: [],
      }
    },
    mounted() {
      this.$http.post('/serve/repairLoad' ,{})
        .then((res) => {
          if (res.data.status === 200) {
            console.log(res.data)
            this.reperdata = res.data.data.reperdata
            this.servedata = res.data.data.servedata
            this.userdata = res.data.data.userdata
            res.data.data.reperdata.map(item=>{
              this.reperdata.push(item)
            })
            this.servedata.map((item)=>{
              let serve = {
                value: '',
                label: ''
              }
              serve.value = item.name
              serve.label = item.name
              this.serveOptions.push(serve)
            })
            this.userdata.map((item)=>{
              let user = {
                value: '',
                label: ''
              }
              user.value = item.name
              user.label = item.name
              this.userOptions.push(user)
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })
    },
    methods: {
      onSubmit(){
        for (let i = 0; i < this.reperdata.length; i++) {
          if (this.reperdata[i].title === this.form.thing) {
            if(Number(this.reperdata[i].num) >=  Number(this.form.num)) {
              this.form.money = Number(this.form.num) * Number(this.reperdata[i].money)
              this.$http.post('/serve/repairAdd' ,this.form)
                .then((res) => {
                  if (res.data.status === 200) {
                    this.$message.success('提交成功')
                    this.close()
                  } else {
                    this.$message.error(res.data.msg)
                  }
                })
              break
            } else {
              this.$message.error('配件数量不够！')
            }
          }
        }
      },
      close(){
        console.log('关闭')
        this.$emit('showAddModal', false)
      },
      changeName () {
        this.userdata.map(item=> {
          if (item.name === this.form.name) {
            this.form.mobile = item.mobile
            this.form.address = item.address
            this.form.code = item.code
          }
        })
      },
      changeRepair () {
        this.servedata.map(item=> {
          if (item.name === this.form.serveMan) {
            this.form.serveMobile = item.mobile
            this.form.upCode = item.code
          }
        })
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
    z-index: 102;
    background-color:rgba(255,255,255,0.6);
    .modal-content {
      width: 500px;
      background: rgba(255, 255, 255, 1);
      box-shadow: 8px 8px 29px 0px rgba(7, 42, 90, 0.7);
      border-radius: 3px;
      z-index: 101;
      color: rgba(59, 86, 129, 1);
      position: relative;
      
      .modal-header {
        height: 65px;
        margin: 0;
        font-size: 24px;
        line-height: 65px;
        text-align: center;
        font-family: PingFang-SC;
        font-weight: 400;
        color: rgba(59, 86, 129, 1);
        border-bottom: 1px solid rgba(235, 236, 240, 0.95);
      }
      .modal-form{
        height: 100%;
        padding: 20px;
        .modal-btn{
          text-align: center;
        }
      }
    }
  }
</style>
