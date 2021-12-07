let urlAdmin = "admin";
function adminFindAll(){
    return fetchGet(`${urlAdmin}/find-all`);
}

function adminLogin(loginForm){
    return fetchPost(`${urlAdmin}/login`, loginForm);
}
