document.querySelector('.login-process').setAttribute('style', 'height: ' + (window.innerHeight - 180) +'px;')

let loginObject = {
    init: function() {
        document.querySelector(".button-login").onclick = () => {
            this.login();
        };
    },

    login : function() {
        // alert("로그인 요청됨");
        let data = {
            userid : document.getElementById("userid").value,
            password : document.getElementById("password").value,
        }
        let request = new XMLHttpRequest()
        request.open('POST', '/auth/login')
        request.setRequestHeader("content-type", "application/json")
        request.responseType = 'json'
        request.send(JSON.stringify(data))
        request.onload = () => {
            let response = request.response
            if (response.status == 200) {
                alert(response.data)
                window.location = '/'
            } else {
                alert(response.data)
                window.location = '/auth/login'
            }
        }
    }
}

loginObject.init()