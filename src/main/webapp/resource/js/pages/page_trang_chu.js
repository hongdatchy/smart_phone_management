let token;
$(function(){
    token = localStorage.getItem('token');
    checkTokenIsValidate(token)
    document.body.addEventListener('click', function (){
        checkTokenIsValidate(token)
        console.log("dang click")
    }, true);
})