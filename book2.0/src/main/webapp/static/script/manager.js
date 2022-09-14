window.onload=function (){
    var vue = new Vue({
        el:"#manager_div",
        data:{
            bookList:{},
            bookId:"",
            text:"",
            option:""
        },
        methods:{
            getBook(){
                axios.get(
                    "/book2_0_war_exploded/book"
                ).then(response=>{
                    var bookList = response.data;
                    vue.bookList = bookList;
                })
            },
            updateBook(){
                axios.put(
                    "/book2_0_war_exploded/book",
                    {bookId:vue.bookId,option:vue.option,text:vue.text}
                ).then(response=>{
                    vue.getBook();
                })
            }
        },
        mounted:function (){
            this.getBook();
        }
    });
}