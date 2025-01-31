const chckRsltValu = {
    'P' : '이상없음',
    'F' : '오류발견',
    'N' : '권한없음'
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
            let errorMessage = await response.text();
            throw new Error(errorMessage || '요청할 수 없습니다.');
        }
        return await response.json();
    } catch (error) {
        alert('네트워크 상태를 확인해 주세요.');
        window.location = '/'
    }
};

const heightResize = () => {
    let contentsHeight = (window.innerHeight - 270);
    document.querySelector('#contents').style.height = contentsHeight + 'px';
    document.querySelector('#contents').style.width = window.innerWidth + 'px';
};
window.addEventListener('resize', heightResize);

const timeFormat = (date) => {
    let time = new Date(date);
    return time.getFullYear() + '-' + ('0' + (time.getMonth() + 1)).slice(-2)
        + '-' + ('0' + time.getDate()).slice(-2) + ' ' 
        + ('0' + time.getHours()).slice(-2) + ':' + ('0' + time.getMinutes()).slice(-2);
}

const putChecklist = async (id, chckRslt, itemElement) => {
    let data = {
        id: id,
        chckRslt: chckRslt
    }
    try {
        let response = await rest('PUT', '/putChecklist', data);
        itemElement.querySelector('.crea-date').innerText = timeFormat(response.data.createDate);
    } catch (error) {
        alert(response.data);
        location.reload();
    }
}

const getChecklistItem = async () => {
    try {
        let response = await rest('GET', '/getChecklist', null);
        response = response.data;
        let checklistItemTemplate = document.querySelector('#checklist-item-template');
        let itemNumber = response.data.length;
        let userId = document.querySelector('.user-id');
        let userName = document.querySelector('.user-name');
    
        userId.innerText = '(' + response.userid + ')';
        userName.innerText = response.username;
        response.data.forEach(res => {
            let checklistItem = checklistItemTemplate.cloneNode(true);
            let tempResult = res.chckRslt
            checklistItem.id = 'item' + res.id;
            checklistItem.querySelector('.numb').innerText = itemNumber;
            checklistItem.querySelector('.chec-list-id').innerText = res.checListId;
            checklistItem.querySelector('.syst-name').innerText = res.systName;
            checklistItem.querySelector('.fron-vari-id').innerText = res.fronVariId;
            checklistItem.querySelector('.fron-vari-step').innerText = res.fronVariStep;
            
            const attachEventListener = (selector, checkResult, text) => {
                checklistItem.querySelector(selector).addEventListener('click', () => {
                    putChecklist(res.id, checkResult, checklistItem);
                    checklistItem.querySelector('.resu-text').innerText = text;
                });
            };
            attachEventListener('#noauthority', 'N', '권한없음');
            attachEventListener('#resu-fail', 'F', '오류발견');
            attachEventListener('#resu-pass', 'P', '이상없음');
            
            if (tempResult) {
                checklistItem.querySelector('.resu-text').innerText = chckRsltValu[tempResult];
                checklistItem.querySelector('.crea-date').innerText = timeFormat(res.createDate);
            } else checklistItem.querySelector('.resu-text').innerText = '미수행'
            itemNumber -= 1;
            checklistItemTemplate.after(checklistItem);
        });
        checklistItemTemplate.remove();
    } catch (error) {
        console.error('Error loading info items:', error);
        window.location = '/'
    }
};

// Initialize the page
document.addEventListener('DOMContentLoaded', () => {
    heightResize();
    getChecklistItem();
});