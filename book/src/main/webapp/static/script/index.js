function page(pageNo){
    window.location.href='book.do?pageNo='+pageNo;
}
function addCart(bookId){
    window.location.href='cart.do?bookId='+bookId+'&operate=add';
}
