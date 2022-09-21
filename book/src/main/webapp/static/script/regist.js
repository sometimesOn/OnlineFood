

function $(id){
    return document.getElementById(id);
}

function preRegist(){
    //用户名长度应为4到16个数字或字母组成
    var unameTxt = $("unameTxt");
    var unameReg = /[\da-zA-Z]{4,16}/;
    var uname = unameTxt.value;
    var unameSpan = $("unameSpan");
    if(!unameReg.test(uname)){
        unameSpan.style.visibility="visible";
        return false;
    }else {
        unameSpan.style.visibility = "hidden";
    }

    //密码至少为五位
    var pwdTxt = $("pwdTxt");
    var pwdReg = /\w{5,}/;
    var pwd = pwdTxt.value;
    var pwdSpan = $("pwdSpan");
    if(!pwdReg.test(pwd)){
        pwdSpan.style.visibility = "visible";
        return false;
    }else {
        unameSpan.style.visibility = "hidden";
    }

    //两个输入的密码要一致
    var cpwdTxt = $("cpwdTxt");
    if (cpwdTxt.value !== pwd){
        $("cpwdSpan").style.visibility = "visible";
        return false;
    }else {
        $("cpwdSpan").style.visibility = "hidden";
    }

    //邮箱格式有误
    var emailTxt = $("emailTxt");
    var emailReg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    var email = emailTxt.value;
    var emailSpan = $("emailSpan");
    if (!emailReg.test(email)){
        emailSpan.style.visibility = "visible";
        return false;
    }else {
        emailSpan.style.visibility = "hidden";
    }

    return true;

}

// var xmlHttpRequest;
//
// function createXmlHttpRequest(){
//     if(window.XMLHttpRequest){
//         xmlHttpRequest = new XMLHttpRequest();
//     }else if (window.ActiveXObject){
//         try {
//             xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
//         }catch (e) {
//             xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
//         }
//     }
// }
//
// function ckUname(uname) {
//
//     createXmlHttpRequest();
//
//     var url = 'user.do?operate=ckUname&uname='+uname;
//     xmlHttpRequest.open("GET", url, true);
//     //发送请求
//     xmlHttpRequest.send();
//     //回调函数
//     xmlHttpRequest.onreadystatechange = ckUnameCB;
// }
//
// function ckUnameCB(){
//     if (xmlHttpRequest.readyState===4 && xmlHttpRequest.status === 200 ){
//         alert(xmlHttpRequest.responseText);
//     }
// }
window.onload=function () {
    var vue = new Vue({
        el:"#regist_div",
        data:{},
        methods:{
            cUname:function (uname){
                axios({
                    method:"POST",
                    url:"user.do",
                    params:{
                        operate:'ckUname',
                        uname:uname
                    }
                })
                    .then(function (value){

                    })
                    .catch(function (reason){

                    })
            }
        }
    }) ;
}

