const insertVariResult = {
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

const putDNSChecklist = async (id, dnsvariResu, itemElement) => {
    let cretDate = itemElement.querySelector('.cret-date')
    let dnsNchc = itemElement.querySelector('.nchc-rslt')
    let data = {
        id: id,
        dnsvariResu: dnsvariResu
    }
    try {
        let response = await rest('PUT', '/putDNSChecklist', data);
        cretDate.innerText = '완료, ' + timeFormat(response.data.cretDate);
        cretDate
        dnsNchc.innerHTML = `<button id="dns-nchc" onclick="deleteRslt()">취소</button>`;
    } catch (error) {
        alert(response.data);
        location.reload();
    }
}

const getDNSChecklist = async () => {
    try {
        let response = await rest('GET', '/getDNSChecklist', null);
        response = response.data;
        let DNSChecklistItemTemplate = document.querySelector('#DNSChecklist-item-template');
        let itemNumber = response.data.length;
        let userId = document.querySelector('.user-id');
        let userName = document.querySelector('.user-name');
        console.log(response);
        userId.innerText = '(' + response.userid + ')';
        userName.innerText = response.username;
        response.data.forEach(res => {
            let DNSChecklistItem = DNSChecklistItemTemplate.cloneNode(true);
            let cretDate = DNSChecklistItem.querySelector('.cret-date');
            DNSChecklistItem.id = 'item' + res.id;
            DNSChecklistItem.querySelector('.numb').innerText = itemNumber;
            DNSChecklistItem.querySelector('.user').innerText = res.userName;
            
            DNSChecklistItem.querySelector('#dns-chck-rslt').addEventListener('click', () => {
                putDNSChecklist(res.id, 'Y', DNSChecklistItem);
            });
            cretDate.innerText = (res.dnschckRslt) ? '완료, ' + timeFormat(res.cretDate) : '미수행';
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