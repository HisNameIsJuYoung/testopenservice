const optnCstm = {};

optnCstm.donghae = ['동해세관', '원주지원센터']
optnCstm.seoul = ['직속', '통관국', '심사1국' ,'심사2국', '조사1국', '조사2국']
optnCstm.seoulEn = ['giksok', 'tongwan', 'simsa1' ,'simsa2', 'josa1', 'josa2']
optnCstm.seoul.giksok = ['세관운영과', '납세자보호담당관', '감사담당관', '수출입기업지원센터', '구로지원센터']
optnCstm.seoul.tongwan = ['수출입물류과', '이사화물과', '통관검사1과', '통관검사2과']
optnCstm.seoul.simsa1 = ['심사총괄1과', '1국심사1관', '1국심사2관', '1국심사3관', '심사정보과', '환급심사과', '체납관리과', '분석실']
optnCstm.seoul.simsa2 = ['심사총괄2과', '2국심사1관', '2국심사2관', '2국심사3관', '2국심사4관', '2국심사5관', '자유무역협정검증1과', '자유무역협정검증2과', '자유무역협정검증3과']
optnCstm.seoul.josa1 = ['조사총괄과', '조사1관', '조사2관', '특수조사과', '디지털무역범죄조사과', '조사정보과']
optnCstm.seoul.josa2 = ['외환조사총괄과', '외환조사1관', '외환조사2관', '외환조사3관', '외환검사과', '외환검사1관', '외환검사2관']
optnCstm.sokcho = ['통관지원과', '조사심사과', '고성지원센터']
optnCstm.anYang = ['통관지원과', '조사심사과']
optnCstm.cheonan = ['통관지원과', '조사심사과']
optnCstm.daejeon = ['통관지원과', '조사심사과']
optnCstm.cheongju = ['통관지원과', '조사심사과', '여행자통관과', '충주지원센터']
optnCstm.paju = ['파주세관', '도라산지원센터', '의정부지원센터']
const cstmDprtCode = {
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
    '서울세관1국심사1관' : '01089',
    '서울세관1국심사2관' : '01087',
    '서울세관1국심사3관' : '01088',
    '서울세관심사정보과' : '01075',
    '서울세관심사총괄1과' : '01061',
    '서울세관체납관리과' : '01063',
    '서울세관환급심사과' : '01065',
    '서울세관2국심사1관' : '010H1',
    '서울세관2국심사2관' : '010H2',
    '서울세관2국심사3관' : '010H3',
    '서울세관2국심사4관' : '010H4',
    '서울세관2국심사5관' : '010H5',
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

const contCstmDprt = {
    '서울세관세관운영과': { pc: 44, chckListAmnt: 18, emplRslt: 0 },
    '서울세관납세자보호담당관': { pc: 7, chckListAmnt: 26, emplRslt: 0 },
    '서울세관감사담당관': { pc: 8, chckListAmnt: 28, emplRslt: 0 },
    '서울세관수출입기업지원센터': { pc: 29, chckListAmnt: 44, emplRslt: 0 },
    '서울세관수출입물류과': { pc: 12, chckListAmnt: 48, emplRslt: 0 },
    '서울세관통관검사1과': { pc: 13, chckListAmnt: 49, emplRslt: 0 },
    '서울세관통관검사2과': { pc: 11, chckListAmnt: 31, emplRslt: 0 },
    '서울세관이사화물과': { pc: 15, chckListAmnt: 55, emplRslt: 0 },
    '서울세관심사총괄1과': { pc: 16, chckListAmnt: 76, emplRslt: 0 },
    '서울세관1국심사1관': { pc: 13, chckListAmnt: 53, emplRslt: 0 },
    '서울세관1국심사2관': { pc: 11, chckListAmnt: 69, emplRslt: 0 },
    '서울세관1국심사3관': { pc: 30, chckListAmnt: 35, emplRslt: 0 },
    '서울세관심사정보과': { pc: 12, chckListAmnt: 32, emplRslt: 0 },
    '서울세관환급심사과': { pc: 12, chckListAmnt: 7, emplRslt: 0 },
    '서울세관체납관리과': { pc: 12, chckListAmnt: 23, emplRslt: 0 },
    '서울세관분석실': { pc: 9, chckListAmnt: 18, emplRslt: 0 },
    '서울세관심사총괄2과': { pc: 21, chckListAmnt: 66, emplRslt: 0 },
    '서울세관2국심사1관': { pc: 16, chckListAmnt: 54, emplRslt: 0 },
    '서울세관2국심사2관': { pc: 15, chckListAmnt: 69, emplRslt: 0 },
    '서울세관2국심사3관': { pc: 13, chckListAmnt: 68, emplRslt: 0 },
    '서울세관2국심사4관': { pc: 11, chckListAmnt: 74, emplRslt: 0 },
    '서울세관2국심사5관': { pc: 16, chckListAmnt: 40, emplRslt: 0 },
    '서울세관자유무역협정검증1과': { pc: 19, chckListAmnt: 39, emplRslt: 0 },
    '서울세관자유무역협정검증2과': { pc: 21, chckListAmnt: 67, emplRslt: 0 },
    '서울세관자유무역협정검증3과': { pc: 21, chckListAmnt: 64, emplRslt: 0 },
    '서울세관조사총괄과': { pc: 16, chckListAmnt: 20, emplRslt: 0 },
    '서울세관조사1관': { pc: 11, chckListAmnt: 57, emplRslt: 0 },
    '서울세관조사2관': { pc: 12, chckListAmnt: 30, emplRslt: 0 },
    '서울세관특수조사과': { pc: 15, chckListAmnt: 71, emplRslt: 0 },
    '서울세관디지털무역범죄조사과': { pc: 20, chckListAmnt: 78, emplRslt: 0 },
    '서울세관조사정보과': { pc: 9, chckListAmnt: 77, emplRslt: 0 },
    '서울세관외환조사총괄과': { pc: 20, chckListAmnt: 49, emplRslt: 0 },
    '서울세관외환조사1관': { pc: 11, chckListAmnt: 67, emplRslt: 0 },
    '서울세관외환조사2관': { pc: 10, chckListAmnt: 22, emplRslt: 0 },
    '서울세관외환조사3관': { pc: 9, chckListAmnt: 71, emplRslt: 0 },
    '서울세관외환검사과': { pc: 17, chckListAmnt: 50, emplRslt: 0 },
    '서울세관외환검사1관': { pc: 16, chckListAmnt: 67, emplRslt: 0 },
    '서울세관외환검사2관': { pc: 15, chckListAmnt: 50, emplRslt: 0 },
    '서울세관구로지원센터': { pc: 14, chckListAmnt: 70, emplRslt: 0 },
    '안양세관통관지원과': { pc: 6, chckListAmnt: 47, emplRslt: 0 },
    '안양세관조사심사과': { pc: 14, chckListAmnt: 50, emplRslt: 0 },
    '천안세관통관지원과': { pc: 12, chckListAmnt: 39, emplRslt: 0 },
    '천안세관조사심사과': { pc: 12, chckListAmnt: 79, emplRslt: 0 },
    '청주세관통관지원과': { pc: 9, chckListAmnt: 31, emplRslt: 0 },
    '청주세관조사심사과': { pc: 11, chckListAmnt: 53, emplRslt: 0 },
    '청주세관여행자통관과': { pc: 24, chckListAmnt: 27, emplRslt: 0 },
    '청주세관충주지원센터': { pc: 5, chckListAmnt: 72, emplRslt: 0 },
    '대전세관통관지원과': { pc: 8, chckListAmnt: 61, emplRslt: 0 },
    '대전세관조사심사과': { pc: 6, chckListAmnt: 60, emplRslt: 0 },
    '속초세관통관지원과': { pc: 7, chckListAmnt: 31, emplRslt: 0 },
    '속초세관조사심사과': { pc: 20, chckListAmnt: 63, emplRslt: 0 },
    '속초세관고성지원센터': { pc: 2, chckListAmnt: 38, emplRslt: 0 },
    '동해세관동해세관': { pc: 23, chckListAmnt: 83, emplRslt: 0 },
    '동해세관원주지원센터': { pc: 5, chckListAmnt: 66, emplRslt: 0 },
    '성남세관성남세관': { pc: 23, chckListAmnt: 81, emplRslt: 0 },
    '파주세관파주세관': { pc: 15, chckListAmnt: 54, emplRslt: 0 },
    '파주세관의정부지원센터': { pc: 15, chckListAmnt: 57, emplRslt: 0 },
    '파주세관도라산지원센터': { pc: 5, chckListAmnt: 54, emplRslt: 0 } 
}
let userValue = {};
userValue.customsDepartment = null;  // 사용자가 선택한 세관 부서 코드, ajax로 넘겨주는 값

const resetUserValueSelectBefore = () => {
    userValue.selectBefore01 = '';      // 사용자가 선택한 세관 select 값
    userValue.selectBefore02 = '';      // 사용자가 선택한 부서1 select 값
    userValue.selectBefore03 = '';      // 사용자가 선택한 부서2 select 값
    userValue.number = 2;
}

const getKeyByValue = (obj, value) => {
    return Object.keys(obj).find(key => obj[key] === value);
}

const heightResize = () => {
    let contentsHeight = (window.innerHeight - 354);
    let widthCheck = window.innerWidth;
    document.querySelector('#contents').style.height = contentsHeight + 'px';
    document.querySelector('#contents').style.width = window.innerWidth + 'px';
    document.querySelector('.nav-bottom').style.width = widthCheck + 'px';
    document.querySelector('.thanks').style.width = widthCheck + 'px';
};
window.addEventListener('resize', heightResize);
const cstmDprt = document.querySelector('.cstm-dprt')

const deleteSelectElement = () => {
    if (document.querySelector('#dropdown2')) document.querySelector('#dropdown2').remove();
    if (document.querySelector('#dropdown3')) document.querySelector('#dropdown3').remove();
}

const userSelectResultProcprocess = (event) => {
    let userSelection = event.currentTarget.options[event.currentTarget.options.selectedIndex].value;
    if (optnCstm[userSelection]) makeDropDownElmn(optnCstm[userSelection], userSelection);
}

const userSelectSeoulProcess = (event) => {
    let userSelection = event.currentTarget.options[event.currentTarget.options.selectedIndex].value;
    if(document.querySelector('#dropdown3')) document.querySelector('#dropdown3').remove(); userValue.number = 3;
    let optionList = optnCstm.seoul[userSelection];
    console.log(optionList);
    makeDropDownElmn(optionList, userSelection);
}

const slctElmnRest = (cstmDprtElmn, slctDropDown, userSlct) => {
    cstmDprtElmn.id = 'dropdown' + userValue.number;
    userValue.number++;
    let stageName = (userSlct == 'seoul') ? '국(局)/직속을' : '부서를';
    slctDropDown.innerHTML = `<option value="" disabled selected>${stageName} 선택해 주세요.</option>`;
};

const makeSlctOptn = (optnList, userSlct, selectElement) => {
    optnList.forEach((option, index) => {
        let optionElement = document.createElement("option");
        optionElement.value = (userSlct != 'seoul') ? option : optnCstm.seoulEn[index];
        optionElement.text = option;
        selectElement.appendChild(optionElement);
    });
    selectElement.addEventListener('change', (event) => {
        userSelectResultProcprocess(event);
        userValue.selectBefore02 = event.currentTarget.options[event.currentTarget.options.selectedIndex].text;
        event.stopPropagation();
        if (userSlct == 'seoul') {
            userSelectSeoulProcess(event);
            userValue.selectBefore03 = event.currentTarget.options[event.currentTarget.options.selectedIndex].text;
        }
        userValue.selectBefore03 = (userValue.selectBefore01 != '서울세관') ? userValue.selectBefore03 : '';
        userValue.customsDepartment
            = userValue.selectBefore01 + userValue.selectBefore02 + userValue.selectBefore03;
    });
}

const makeDropDownElmn = (optnList, userSlct) => {
    let cstmDprtElmn = cstmDprt.cloneNode(true);
    let bttn = document.querySelector('.bttn');
    let slctDropDown = cstmDprtElmn.querySelector('select.drop-down');
    slctElmnRest(cstmDprtElmn, slctDropDown, userSlct)
    makeSlctOptn(optnList, userSlct, slctDropDown);
    bttn.before(cstmDprtElmn);
}

const rest = async (method, url, data = null, isFormData = false) => {
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

const setPage = async () => {
    const chckItemDetl = document.querySelector('.chck-item-detl');
    const DNSChecklistItem = document.querySelector('.DNSChecklist-item');
    try {
        let response = await rest('GET', '/auth/manager', null);
        if (response.status != 200) window.location = '/';
        else {
            if (response.data.role != 'ADMIN') document.querySelector('.srch-cstm-dprt').remove()
            document.querySelector('.user-name').innerText = response.data.username;
            document.querySelector('.user-id').innerText = '(' + response.data.userid + ')';
            var rspnChckList = response.data.data.checklist;
            var rspnDnsChckList = response.data.data.dnsChecklist;

            document.querySelector('.cstm-chck').innerText = getCstmDprtName(rspnChckList).substr(0, 4);
            document.querySelector('.dprt-chck').innerText = getCstmDprtName(rspnChckList).substr(4);

            let itemNumber = 1;
            rspnChckList.forEach(res => {
                let itemElement = chckItemDetl.cloneNode(true);
                let unchPrcn = ((res.unChck / res.chckAmnt * 100).toFixed(1) + '');
                let chckInner = {
                    '.empl-name': (res.userName + '(' + res.userId + ')'),
                    '.chck-amnt': res.chckAmnt,
                    '.chck-pass': res.chckPass,
                    '.chck-fail': res.chckFail,
                    '.chck-nthr': res.chckNthr,
                    '.un-chck': res.unChck,
                    '.chck-prcn': ((100 - unchPrcn).toFixed(1) + ''),
                    '.unch-prcn': unchPrcn
                };
                for (let key in chckInner) itemElement.querySelector(key).innerText = chckInner[key];
                itemElement.style.borderBottom = (itemNumber == rspnChckList.length) ? '0' : null;
                chckItemDetl.before(itemElement);
                itemNumber++;
            });
            chckItemDetl.remove();
            
            let allDnsChckList = rspnDnsChckList.length;
            let unchEmpl = rspnDnsChckList.filter(rspn => rspn.dnsChckRslt == null);
            let unChckDns = unchEmpl.length;
            let unchPrcnDns = ((unChckDns / allDnsChckList * 100).toFixed(1) + '');
            let DNSinner = {
                '.cstm-dns': (getCstmDprtName(rspnDnsChckList).substr(0, 4)),
                '.dprt-dns': (getCstmDprtName(rspnDnsChckList).substr(4)),
                '.empl-cont':  allDnsChckList,
                '.chck-rslt-dns': (allDnsChckList - unChckDns),
                '.un-chck-dns': unChckDns,
                '.chck-prcn-dns': ((100 - unchPrcnDns).toFixed(1) + ''),
                '.unch-prcn-dns': unchPrcnDns
            }
            DNSChecklistItem.style.borderBottom = '0';
            for (let key in DNSinner) DNSChecklistItem.querySelector(key).innerText = DNSinner[key];
            
            let unchEmplName = '';
            unchEmpl.forEach(rspn => { unchEmplName += rspn.userName + '(' + rspn.userId + ')<br>'; });
            DNSChecklistItem.querySelector('.unch-empl').innerHTML = unchEmplName;
        }
    } catch (error) { console.error('error in join.js : ', error);
        window.location = '/';
    }
}

const emplChckData = (allChckListRslt, objcElmn) => {
    let empl = {};
    allChckListRslt.forEach((chckListRslt) => {
        let value = chckListRslt[objcElmn];
        if (value in empl) { 
            empl[value].allList++;
            if (chckListRslt.chckRslt != '') empl[value].chck++;
            else empl[value].unch++;
        } else {
            empl[value] = {};
            empl[value].allList = 1;
            empl[value].chck = (chckListRslt.chckRslt != '') ? 1 : 0;
            empl[value].unch = (chckListRslt.chckRslt != '') ? 0 : 1;
        }
        empl[value].cstmDprtName = chckListRslt.cstmDprtName;
    });
    return empl;
}

const getCstmDprtName = (objectWithCstmDprtCode) => {
    let cstmDprt = objectWithCstmDprtCode[0].customs + objectWithCstmDprtCode[0].department;
    return Object.keys(cstmDprtCode).find(key => cstmDprtCode[key] === cstmDprt);
}

const download = async (workbook, fileName) => {
    const buffer = await workbook.xlsx.writeBuffer();
    const blob = new Blob([buffer], {
        type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
    const url = window.URL.createObjectURL(blob);
    const anchor = document.createElement('a');
    anchor.href = url;
    anchor.download = fileName + '.xlsx';
    anchor.click();
    window.URL.revokeObjectURL(url);
};

const timeFormatForManager = () => {
    let date = new Date();
    return ('' + date.getFullYear()).slice(-2) + ('0' + (date.getMonth() + 1)).slice(-2)
        + ('0' + date.getDate()).slice(-2)
}

const makeFile = async (e) => {
    let fileName = timeFormatForManager() + '시험운영결과보고(서울)';
    let workbook = new ExcelJS.Workbook();
    let sheet1 = workbook.addWorksheet('서울세관');
    let checklist = '', checklistResult = '', dnsChecklist =  '', rowNum = 2;
    const allChckListRslt = [];

    try { let response = await rest('GET', '/auth/allResult', null);
        if (response.status != 200) window.location = '/';
        else { checklist = response.data.data.checklist;
            checklistResult = response.data.data.checklistResult;
            dnsChecklist = response.data.data.dnsChecklist; }
    } catch (error) { console.error('error in join.js : ', error); }

    checklistResult.forEach((chckRslt) => {
        let temp = {};
        checklist.forEach((chck) => {
            if (chckRslt.checListId == chck.checListId) {
                for (key in chck) chckRslt[key] = chck[key];
                chckRslt.cstmDprtName = chckRslt.cstmName + chckRslt.dprtName;
                temp = chckRslt; } });
        allChckListRslt.push(temp);
    });
    
    const finlRslt = {};
    const unchRslt = {};
    let emplChckRslt = emplChckData(allChckListRslt, 'userId');
    Object.values(emplChckRslt).forEach((empl) => {
        let value = empl.cstmDprtName;
        if (value in finlRslt) {
            if ((empl.allList - empl.chck) == 0) finlRslt[value]++;
            else unchRslt[value]++;
        } else {
            finlRslt[value] = {};
            finlRslt[value] = ((empl.allList - empl.chck) == 0) ? 1 : 0
            unchRslt[value] = ((empl.allList - empl.chck) != 0) ? 1 : 0;
        }
    });

    sheet1.getCell('A1').value = '세관부서명';
    sheet1.getCell('B1').value = '전체PC';
    sheet1.getCell('C1').value = '완료PC';
    sheet1.getCell('D1').value = '미완료PC';
    for (key in contCstmDprt) {
        sheet1.getCell('A' + rowNum).value = key;
        sheet1.getCell('B' + rowNum).value = contCstmDprt[key].pc;
        sheet1.getCell('C' + rowNum).value = finlRslt[key];
        sheet1.getCell('D' + rowNum).value = unchRslt[key];
        rowNum++;
    }
    rowNum = 2;
    download(workbook, fileName).then(r => {});
}

document.addEventListener('DOMContentLoaded', async () => {
    resetUserValueSelectBefore(); heightResize(); setPage();
    let dropDownElmn = cstmDprt.querySelector('select.drop-down');
    let dprtChckList = document.querySelector('#dprt-chck-list');
    dprtChckList.addEventListener('click', makeFile);
    dropDownElmn.addEventListener('change', (event) => {
        resetUserValueSelectBefore(); deleteSelectElement();
        let userSelectedIndex = event.currentTarget.options[event.currentTarget.options.selectedIndex]
        userValue.customsDepartment = (userSelectedIndex.text == '성남세관')
            ? userSelectedIndex.text + userSelectedIndex.text : userSelectResultProcprocess(event, userValue.selectBefore01);
        userValue.selectBefore01 = userSelectedIndex.text; });
});
