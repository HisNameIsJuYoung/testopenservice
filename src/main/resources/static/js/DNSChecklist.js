const insertVariResult = {
    'P' : '이상없음',
    'F' : '오류발견',
    'N' : '권한없음'
}

const rest = async (method, url, data = null, isFormData = false) => {
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

const putDNSChecklist = async (id, variResu, itemElement) => {
    let data = {
        id: id,
        variResu: variResu
    }
    try {
        const response = await restHandler('PUT', '/putDNSChecklist', data);
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

const getDNSChecklist = async () => {
    try {
        let response = await rest('GET', '/getDNSChecklist', null);
        response = response.data;
        let DNSChecklistItemTemplate = document.querySelector('#DNSChecklist-item-template');
        let itemNumber = response.data.length;
        let userId = document.querySelector('.user-id');
        let userName = document.querySelector('.user-name');
        
        userId.innerText = '(' + response.userid + ')';
        userName.innerText = response.username;
        response.data.forEach(res => {
            console.log(res);
            console.log(res.dnsvariResu);
            let DNSChecklistItem = DNSChecklistItemTemplate.cloneNode(true);
            DNSChecklistItem.id = 'item' + res.id;
            DNSChecklistItem.querySelector('.numb').innerText = itemNumber;
            DNSChecklistItem.querySelector('.user').innerText = res.userName;
            
            DNSChecklistItem.querySelector('#dns-chck-rslt').addEventListener('click', () => {
                putDNSChecklist(res.id);
            });
            DNSChecklistItem.querySelector('.crea-date').innerText
                = (res.dnsvariResu) ? timeFormat(res.creaDate) : '미수행';
            DNSChecklistItemTemplate.after(DNSChecklistItem);
            itemNumber -= 1;
        });
        DNSChecklistItemTemplate.remove();
    } catch (error) {
        console.error('Error loading info items:', error);
    }
};

// Initialize the page
document.addEventListener('DOMContentLoaded', () => {
    heightResize();
    getDNSChecklist();
});