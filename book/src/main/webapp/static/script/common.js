function goHome(){
    window.location.href='book.do';
}
function quit(){
    if(confirm("是否确定退出登录")){
        window.location.href='user.do?operate=login';
    }
}
function goCartPage(){
    window.location.href='cart.do';
}

function goOrderPage(){
    window.location.href='order.do?operate=showOrder';
}

function goRegist(){
    window.location.href='page.do?operate=page&page=user/regist';
}

function gologin(){
    window.location.href='page.do?operate=page&page=user/login';
}