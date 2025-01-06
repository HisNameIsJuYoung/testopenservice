let loginObject = {
    init: function() {
        document.querySelector(".button-join").onclick = () => {
            this.login();
        };
    },

    login : function() {
        // alert("로그인 요청됨");
        let data = {
            userid : document.getElementById("userid").value,
            password : document.getElementById("password").value,
            email : document.getElementById("email").value,
        }
        let request = new XMLHttpRequest()
        request.open('POST', '/auth/join')
        request.setRequestHeader("content-type", "application/json")
        request.responseType = 'json'
        request.send(JSON.stringify(data))
        request.onload = () => {
            let response = request.response
            if (response.status == 400) {
                alert(response.data)
                window.location = '/auth/join'
            } else {
                alert(response.data)
                window.location = '/'
            }
        }
    }
}

loginObject.init()