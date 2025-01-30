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

const cancelButton = (id, cretDate) => {
    return '완료, ' + timeFormat(cretDate)
                + `<button id="item${id}" onclick="putDnsChckRest()" style="margin: 0 0 0 25px;">취 소</button>`
}

const putDnsChecklist = async (id, dnsChckRslt, itemElement) => {
    let cretDate = itemElement.querySelector('.cret-date');
    let dnsChckRsltBttn = itemElement.querySelector('#dns-chck-rslt');
    let data = {
        id: id,
        dnsChckRslt: dnsChckRslt
    }
    try {
        let response = await rest('PUT', '/putDNSChecklist', data);
        cretDate.innerHTML = cancelButton(id, response.data.cretDate);
        dnsChckRsltBttn.disabled = true;
    } catch (error) {
        alert(response.data);
        location.reload();
    }
}

function putDnsChckRest() {
    let item = event.target.closest('.DNSChecklist-item');
    let id = item.id.replace('item', '');
    let cretDate = item.querySelector('.cret-date');
    let dnsChckRsltBttn = item.querySelector('#dns-chck-rslt');
    let data = {
        id: id,
        cretDate: null
    }
    rest('PUT', '/putDNSChecklist', data)
        .then(() => {
            cretDate.innerText = '미수행';
            dnsChckRsltBttn.disabled = false;
        })
        .catch((error) => {
            alert(response.data);
            location.reload();
        });
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
            let DNSChecklistItem = DNSChecklistItemTemplate.cloneNode(true);
            let cretDate = DNSChecklistItem.querySelector('.cret-date');
            let dnsChckRslt = DNSChecklistItem.querySelector('#dns-chck-rslt')
            DNSChecklistItem.id = 'item' + res.id;
            DNSChecklistItem.querySelector('.numb').innerText = itemNumber;
            DNSChecklistItem.querySelector('.user').innerText = res.userName + '(' + res.userId + ')';
            dnsChckRslt.disabled = (res.userId == response.userid && !res.dnsChckRslt) ? false : true;
            dnsChckRslt.addEventListener('click', () => { putDnsChecklist(res.id, 'Y', DNSChecklistItem); });
            cretDate.innerHTML = (res.dnsChckRslt) ? cancelButton(res.id, res.cretDate) : '미수행';
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