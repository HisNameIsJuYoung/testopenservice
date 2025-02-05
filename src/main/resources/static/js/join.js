const optnCstm = {};

optnCstm.donghae = ['동해세관', '원주지원센터']
optnCstm.seoul = ['직속', '통관국', '심사1국' ,'심사2국', '조사1국', '조사2국']
optnCstm.seoulEn = ['giksok', 'tongwan', 'simsa1' ,'simsa2', 'josa1', 'josa2']
optnCstm.seoul.giksok = ['세관운영과', '납세자보호담당관', '감사담당관', '수출입기업지원센터', '구로지원센터']
optnCstm.seoul.tongwan = ['수출입물류과', '이사화물과', '통관검사1과', '통관검사2과']
optnCstm.seoul.simsa1 = ['심사총괄1과', '심사1관', '심사2관', '심사3관', '심사정보과', '환급심사과', '체납관리과', '분석실']
optnCstm.seoul.simsa2 = ['심사총괄2과', '심사1관', '심사2관', '심사3관', '심사4관', '심사5관', '자유무역협정검증1과', '자유무역협정검증2과', '자유무역협정검증3과']
optnCstm.seoul.josa1 = ['조사총괄과', '조사1관', '조사2관', '특수조사과', '디지털무역범죄조사과', '조사정보과']
optnCstm.seoul.josa2 = ['외환조사총괄과', '외환조사1관', '외환조사2관', '외환조사3관', '외환검사과', '외환검사1관', '외환검사2관']
optnCstm.sokcho = ['통관지원과', '조사심사과', '고성지원센터']
optnCstm.anYang = ['통관지원과', '조사심사과']
optnCstm.cheonan = ['통관지원과', '조사심사과']
optnCstm.daejeon = ['통관지원과', '조사심사과']
optnCstm.cheongju = ['통관지원과', '조사심사과', '여행자통관과', '충주지원센터']
optnCstm.paju = ['파주세관', '도라산지원센터', '의정부지원센터']
const cstmDptmCode = {
    '대전세관조사심사과' : '15064',
    '대전세관통관지원과' : '150D9',
    '동해세관동해세관' : '100D9',
    '동해세관원주지원센터' : '102D9',
    '서울세관감사담당관' : '010CA',
    '서울세관구로지원센터' : '130D9',
    '서울세관납세자보호담당관' : '010D2',
    '서울세관세관운영과' : '01071',
    '서울세관수출입기업지원센터' : '010DG',
    '서울세관분석실' : '01091',
    '서울세관심사1관' : '01089',
    '서울세관심사2관' : '01087',
    '서울세관심사3관' : '01088',
    '서울세관심사정보과' : '01075',
    '서울세관심사총괄1과' : '01061',
    '서울세관체납관리과' : '01063',
    '서울세관환급심사과' : '01065',
    '서울세관심사1관' : '010H1',
    '서울세관심사2관' : '010H2',
    '서울세관심사3관' : '010H3',
    '서울세관심사4관' : '010H4',
    '서울세관심사5관' : '010H5',
    '서울세관심사총괄2과' : '010HH',
    '서울세관자유무역협정검증1과' : '01066',
    '서울세관자유무역협정검증2과' : '01067',
    '서울세관자유무역협정검증3과' : '01068',
    '서울세관디지털무역범죄조사과' : '01056',
    '서울세관조사1관' : '01043',
    '서울세관조사2관' : '01048',
    '서울세관조사정보과' : '01076',
    '서울세관조사총괄과' : '01041',
    '서울세관특수조사과' : '01030',
    '서울세관외환검사1관' : '010B8',
    '서울세관외환검사2관' : '010B9',
    '서울세관외환검사과' : '010B7',
    '서울세관외환조사1관' : '01096',
    '서울세관외환조사2관' : '01097',
    '서울세관외환조사3관' : '01078',
    '서울세관외환조사총괄과' : '01042',
    '서울세관수출입물류과' : '010D8',
    '서울세관이사화물과' : '01019',
    '서울세관통관검사1과' : '010C1',
    '서울세관통관검사2과' : '010C2',
    '성남세관성남세관' : '012D9',
    '속초세관고성지원센터' : '103D9',
    '속초세관조사심사과' : '10146',
    '속초세관통관지원과' : '101D9',
    '안양세관조사심사과' : '13164',
    '안양세관통관지원과' : '131D9',
    '천안세관조사심사과' : '15264',
    '천안세관통관지원과' : '152D9',
    '청주세관여행자통관과' : '15121',
    '청주세관조사심사과' : '15164',
    '청주세관충주지원센터' : '153D9',
    '청주세관통관지원과' : '151D9',
    '파주세관파주세관' : '017D9',
    '파주세관도라산지원센터' : '017D9',
    '파주세관의정부지원센터' : '011D9'
}
let userValue = {};
userValue.customsDepartment = null;  // 사용자가 선택한 세관 부서 코드, ajax로 넘겨주는 값

const resetUserValueSelectBefore = () => {
    userValue.selectBefore01 = '';      // 사용자가 선택한 세관 select 값
    userValue.selectBefore02 = '';      // 사용자가 선택한 부서1 select 값
    userValue.selectBefore03 = '';      // 사용자가 선택한 부서2 select 값
    userValue.number = 2;
}

const heightResize = () => {
    let contentsHeight = (window.innerHeight - 200);
    let widthCheck = window.innerWidth;
    document.querySelector('.container').style.height = contentsHeight + 'px';
    document.querySelector('.container').style.width = widthCheck + 'px';
    document.querySelector('.nav-bottom').style.width = widthCheck + 'px';
    document.querySelector('.thanks').style.width = widthCheck + 'px';
};
window.addEventListener('resize', heightResize);
window.setTimeout('window.location.reload()', 1700000);
const customsDepartment = document.querySelector('.customsDepartment')

const deleteSelectElement = () => {
    if (document.querySelector('#dropdown2')) document.querySelector('#dropdown2').remove();
    if (document.querySelector('#dropdown3')) document.querySelector('#dropdown3').remove();
}

const keyboardFunction = (element, executeFunction) => {
    if (element) { element.addEventListener('keydown', (event) => {
            if (event.key === 'Enter') executeFunction;
        });
    }
}

const userSelectResultProcprocess = (event) => {
    let userSelection = event.currentTarget.options[event.currentTarget.options.selectedIndex].value;
    if (optnCstm[userSelection]) makeDropdownElement(optnCstm[userSelection], userSelection);
}

const userSelectSeoulProcess = (event) => {
    let userSelection = event.currentTarget.options[event.currentTarget.options.selectedIndex].value;
    if(document.querySelector('#dropdown3')) document.querySelector('#dropdown3').remove(); userValue.number = 3;
    let optionList = optnCstm.seoul[userSelection];
    makeDropdownElement(optionList, userSelection);
}

const selectElementReset = (selectCustomsDepartment, selectElement, userSelection) => {
    selectCustomsDepartment.querySelector('.textbox-name').innerText = '';
    selectCustomsDepartment.id = 'dropdown' + userValue.number;
    userValue.number++;
    let stageName = (userSelection == 'seoul') ? '국(局)/직속을' : '부서를';
    selectElement.innerHTML = `<option value="" disabled selected>${stageName} 선택해 주세요.</option>`;
};

const makeSelectOptions = (optionList, userSelection, selectElement) => {
    optionList.forEach((option, index) => {
        let optionElement = document.createElement("option");
        optionElement.value = (userSelection != 'seoul') ? option : optnCstm.seoulEn[index];
        optionElement.text = option;
        selectElement.appendChild(optionElement);
    });
    selectElement.addEventListener('change', (event) => {
        userSelectResultProcprocess(event);
        userValue.selectBefore02 = event.currentTarget.options[event.currentTarget.options.selectedIndex].text;
        event.stopPropagation();
        if (userSelection == 'seoul') {
            userSelectSeoulProcess(event);
            userValue.selectBefore03 = event.currentTarget.options[event.currentTarget.options.selectedIndex].text;
        }
        userValue.selectBefore03 = (userValue.selectBefore01 != '서울세관') ? userValue.selectBefore03 : '';
        userValue.customsDepartment
            = userValue.selectBefore01 + userValue.selectBefore02 + userValue.selectBefore03;
    });
}

const makeDropdownElement = (optionList, userSelection) => {
    let selectCustomsDepartment = customsDepartment.cloneNode(true);
    let attachSelectElement = document.querySelector('.attachSelectElement');
    let selectElement = selectCustomsDepartment.querySelector('select.drop-down');
    selectElementReset(selectCustomsDepartment, selectElement, userSelection)
    makeSelectOptions(optionList, userSelection, selectElement);
    attachSelectElement.before(selectCustomsDepartment);
}

const restHandler = async (method, url, data = null, isFormData = false) => {
    try {
        let options = {
            method: method,
            headers: isFormData ? {} : { 'Content-Type': 'application/json' }
        };
        if (data) options.body = isFormData ? data : JSON.stringify(data);
        let response = await fetch(url, options);
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
        document.querySelector("#button-join").onclick = () => {
            this.login();
        };
    },

    login : async function() {
        let rsa = new RSAKey();  // RSA 암호화 키 생성
        rsa.setPublic(document.querySelector('#RSAModulus').value,
            document.querySelector('#RSAExponent').value);

        let password = document.getElementById("password").value;
        let passwordConfirm = document.getElementById("passwordConfirm").value;
        let cstmCode = cstmDptmCode[userValue.customsDepartment].substr(0, 3);
        let dptmCode = cstmDptmCode[userValue.customsDepartment].substr(3);
        
        if (!userid) {
            alert("아이디를 입력해주세요.")
        } else if (password != passwordConfirm) {
            alert("비밀번호가 일치하지 않습니다.")
        } else if (!cstmCode) {
            alert("세관을 선택해주세요.")
        } else if (!dptmCode) {
            alert("부서를 선택해주세요.")
        } else {
            password = rsa.encrypt(password)  //사용자 계정정보 암호화처리
            let data = {
                userId : document.getElementById("userid").value,
                password : password,
                customs : cstmCode,
                department : dptmCode
            }
            try {
                let response = await restHandler('PUT', '/auth/join', data);
                alert(response.data);
                if (response.status != 200) {
                    window.location = '/auth/join';
                } else {
                    window.location = '/';
                }
            } catch (error) {
                console.error('error in join.js : ', error);
            }
        }
    }
}

document.addEventListener('DOMContentLoaded', async () => {
    resetUserValueSelectBefore(); heightResize(); loginObject.init()
    try { let response = await restHandler('GET', '/auth/getRSA', null);
        document.querySelector('#RSAModulus').value = response.data.module;
        document.querySelector('#RSAExponent').value = response.data.exponent;
    } catch (error) { console.error('Error loading info items:', error); }
    let passwordConfirm = document.getElementById("passwordConfirm");
    keyboardFunction(passwordConfirm, loginObject.login);
    let dropdownElement = customsDepartment.querySelector('select.drop-down')
    dropdownElement.addEventListener('change', (event) => {
        resetUserValueSelectBefore(); deleteSelectElement();
        let userSelectedIndex = event.currentTarget.options[event.currentTarget.options.selectedIndex]
        userValue.customsDepartment = (userSelectedIndex.text == '성남세관')
            ? userSelectedIndex.text + userSelectedIndex.text : userSelectResultProcprocess(event, userValue.selectBefore01);
        userValue.selectBefore01 = userSelectedIndex.text;
    });
});
