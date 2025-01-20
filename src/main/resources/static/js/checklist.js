const insertVariResult = {
    'P' : '이상없음',
    'F' : '오류발견',
    'N' : '권한없음'
}

const restHandler = async (method, url, data = null, isFormData = false) => {
    try {
        const options = {
            method: method,
            headers: isFormData ? {} : { 'Content-Type': 'application/json' }
        };
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

const heightResize = () => {
    let contentsHeight = (window.innerHeight - 320);
    document.querySelector('#contents').style.height = contentsHeight + 'px';
    document.querySelector('#contents').style.width = window.innerWidth + 'px';
};
window.addEventListener('resize', heightResize);

const putChecklist = async (id, variResu, itemElement) => {
    let data = {
        id: id,
        variResu: variResu
    }
    try {
        const response = await restHandler('PUT', '/putChecklist', data);
    } catch (error) {
        alert(response.data);
        location.reload();
    }
    itemElement.querySelector('.resu-text').innerText = insertVariResult[data.variResu];
}

const timeFormat = (date) => {
    let time = new Date(date);
    return time.getFullYear() + '-' + (time.getMonth() + 1) + '-' + time.getDate()
        + ' ' + time.getHours() + ':' + time.getMinutes();
}

const loadChecklistItem = async () => {
    try {
        let response = await restHandler('GET', '/getChecklist', null);
        let checklistItemTemplate = document.querySelector('#checklist-item-template');
        let itemNumber = response.data.length;
        let userId = document.querySelector('.user-id');
        let userName = document.querySelector('.user-name');
    
        response = response.data;
        userId.innerText = '(' + response.userid + ')';
        userName.innerText = response.username;
        response.data.forEach(res => {
            let checklistItem = checklistItemTemplate.cloneNode(true);
            let tempResult = res.variResu
            checklistItem.id = 'item' + res.id;
            checklistItem.querySelector('.numb').innerText = itemNumber;
            checklistItem.querySelector('.chec-list-id').innerText = res.checListId;
            checklistItem.querySelector('.syst-name').innerText = res.systName;
            checklistItem.querySelector('.fron-vari-id').innerText = res.fronVariId;
            checklistItem.querySelector('.fron-vari-step').innerText = res.fronVariStep;
            
            const attachEventListener = (selector, status, text) => {
                checklistItem.querySelector(selector).addEventListener('click', () => {
                    putChecklist(res.id, status, checklistItem);
                    checklistItem.querySelector('.resu-text').innerText = text;
                });
            };
            attachEventListener('#noauthority', 'N', '권한없음');
            attachEventListener('#resu-fail', 'F', '오류발견');
            attachEventListener('#resu-pass', 'P', '이상없음');
            
            if (tempResult) {
                checklistItem.querySelector('.resu-text').innerText = insertVariResult[tempResult];
                checklistItem.querySelector('.crea-date').innerText = timeFormat(res.creaDate);
            } else checklistItem.querySelector('.resu-text').innerText = '미수행'
            itemNumber -= 1;
            checklistItemTemplate.after(checklistItem);
        });
        checklistItemTemplate.remove();
    } catch (error) {
        console.error('Error loading info items:', error);
    }
};

// Initialize the page
document.addEventListener('DOMContentLoaded', () => {
    heightResize();
    loadChecklistItem();
});