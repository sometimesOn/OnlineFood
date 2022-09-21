function del(cartItemId){
    if(confirm("是否删除该个购物项")){
        window.location.href='cart.do?operate=del&cartItemId='+cartItemId;
    }
}

function clearCart(){
    if(confirm("是否清空购物车")){
        window.location.href='cart.do?operate=clear';
    }
}

function count(flag,cartItemId){
    window.location.href='cart.do?operate=count&flag='+flag+'&cartItemId='+cartItemId;
}

function pay(){
    window.location.href='order.do?operate=checkout';
}