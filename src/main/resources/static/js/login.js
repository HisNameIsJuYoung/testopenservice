const heightResize = () => {
    let heightCheck = window.innerHeight - 83.5;
    let widthCheck = window.innerWidth;
    document.querySelector('.container').style.height = heightCheck + 'px';
    document.querySelector('.nav-bottom').style.width = widthCheck + 'px';
    document.querySelector('.thanks').style.width = widthCheck + 'px';
};
window.addEventListener('resize', heightResize);
window.setTimeout('window.location.reload()', 1800000);

const restHandler = async (method, url, data = null, isFormData = false) => {
    try {
        const options = { method: method,
            headers: isFormData ? {} : { 'Content-Type': 'application/json' } };
        if (data) options.body = isFormData ? data : JSON.stringify(data);
        const response = await fetch(url, options);
        if (!response.ok) {
            const errorMessage = await response.text();
            throw new Error(errorMessage || '요청할 수 없습니다.');
        }
        return await response.json();
    } catch (error) {
        alert(error.message);
        throw new Error('네트워크 상태를 확인해 주세요.');
    }
};

let loginObject = {
    init : function() {
        document.querySelector("#button-login").onclick = () => this.login();
    },
    
    login : async function() {
        let rsa = new RSAKey();  // RSA 암호화 키 생성
        let password = document.getElementById("password").value
        
        rsa.setPublic(document.querySelector('#RSAModulus').value,
            document.querySelector('#RSAExponent').value);
        password = rsa.encrypt(password);  //사용자 계정정보 암호화처리
        
        if (!userid) {
            alert("아이디를 입력해주세요.")
            window.location = '/auth/login'
        } else if (!password) {
            alert("비밀번호를 입력해주세요.")
            window.location = '/auth/login'
        } else {
            let data = { userId : document.getElementById("userid").value,
                password : password }
            try {
                const response = await restHandler('POST', '/auth/login', data);
                alert(response.data);
                if (response.status == 200) {
                    window.location = '/';
                } else if (response.status == data.userId) {
                    window.location = '/auth/join';
                } else {
                    window.location = '/auth/login';
                }
            } catch (error) { console.error('error in login.js : ', error); }
        }
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    try {
        let response = await restHandler('GET', '/auth/getRSA', null);
        document.querySelector('#RSAModulus').value = response.data.module;
        document.querySelector('#RSAExponent').value = response.data.exponent;
    } catch (error) {
        console.error('Error loading info items:', error);
    }
    heightResize(); loginObject.init()
    document.querySelector('#password').addEventListener('keydown', (event) => {
        if (event.key === 'Enter') loginObject.login();
    })
});