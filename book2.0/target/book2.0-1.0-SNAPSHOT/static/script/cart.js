window.onload=function (){
    var vue = new Vue({
        el:"#cart_div",
        data:{
            cartItem:{}
        },
        methods:{
          getCart(){
              axios.get(
                  "/book2_0_war_exploded/cart"
              ).then(response=>{
                  var cartItem = response.data;
                  vue.cartItem = cartItem;
              });
          },
          editCount(cartId,buyCount){
              axios.put(
                  "/book2_0_war_exploded/cart",
                  {cartId:cartId,buyCount:buyCount}
              ).then(response=>{
                  vue.getCart();
              });
          },
            deleteCart(cartId){
              axios.delete(
                  "/book2_0_war_exploded/cart",
                  {data:{cartId:cartId}}
              ).then(function (response){
                  vue.getCart();
              }).catch(function (reason){
                  console.log(reason);
              });
          }
        },
        mounted:function (){
            this.getCart();
        }
    });
}