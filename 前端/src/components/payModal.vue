<template>
 <div id="divform" v-html="apply">
<!--  <form action="http://127.0.0.1:4000/money/pay" method="post">-->
<!--   商品单号-->
<!--   <input type="text" name="out_trade_no"/>-->
<!--   <br/>-->
<!--   商品名称-->
<!--   <input type="text" name="subject"/>-->
<!--   <br/>-->
<!--   付款金额-->
<!--   <input type="text" name="total_amount"/>-->
<!--   <br/>-->
<!--   商品描述（可空）-->
<!--   <input type="text" name="body"/>-->
<!--   <br/>-->
<!--   支付超时时间（秒可空）-->
<!--   <input type="text" name="timeout_express"/>-->
<!--   <br/>-->
<!--   销售产品码-->
<!--   <input type="text" name="product_code"/>-->
<!--   <br/>-->
<!--   <input type="submit" value="提交"/>-->
<!--  </form>-->
 </div>
</template>

<script>
  export default {
    name: "payModal",
    data() {
      return{
        apply:''
      }
    },
    mounted() {
      let form = this.$route.query.htmls
      this.apply = form;
      this.$nextTick(()=> {
        document.forms [0] .submit()
      })
      console.log('form', form)
    }
  }
</script>

<style scoped>

</style>