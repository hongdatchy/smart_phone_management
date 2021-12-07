let usernameTextField, passwordTextField, btnLogin

$(function(){
    usernameTextField = $("#username")
    passwordTextField = $("#password")
    btnLogin = $("#btn-login")
    btnLogin.click(function (){
        let loginForm = {
            username: usernameTextField.val(),
            password: passwordTextField.val()
        }
        login(loginForm)
    })
    let reloading = sessionStorage.getItem("reloading");
    if(reloading === "true") {
        sessionStorage.removeItem("reloading");
        $('.alert').removeClass("d-none")
    }
})

async function login(loginForm){
    await adminLogin(loginForm).then(function (resolve){
        if(resolve.message === "success"){
            localStorage.setItem("token", resolve.data);
            redirectToHomePage();
        }else {
            alert(resolve.data)
        }
    }).catch(function (err){
        console.log(err);
    })
}


