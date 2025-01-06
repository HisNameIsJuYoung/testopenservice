// Function to sanitize HTML content using DOMPurify
function sanitizeHtml(html) {
    return DOMPurify.sanitize(html);
}

const restHandler = (method, url, data) => {
    let request = new XMLHttpRequest();
    request.open(method, url);
    request.setRequestHeader("content-type", "application/json");
    request.responseType = 'json';
    request.send(JSON.stringify(data));
    request.onload = () => {
        let response = request.response;
        console.log(response);
        if (response == null || response.status != 200) {
            alert(response.data);
            window.location = '/';
        } else {
            alert(response.data[0]);
            window.location = '/';
        }
    };
};

const heightResize = (addHeight) => {
    let menuContentsHeight = (window.innerHeight - 180);
    document.querySelector('.menu-contents').setAttribute('style', 'height: ' + menuContentsHeight + 'px;');
    document.querySelector('.menu').setAttribute('style', 'height: ' + menuContentsHeight + 'px;');
};
window.addEventListener('resize', heightResize);

async function fetchHtmlAsText(url) {
    const response = await fetch(url, {
        credentials: 'same-origin', // Include credentials for CSRF protection
        headers: {
            'X-CSRF-Token': csrfToken // Include CSRF token
        }
    });
    const text = await response.text();
    return sanitizeHtml(text); // Sanitize the fetched HTML content
}

async function importPage(target, contents) {
    const sanitizedHtml = await fetchHtmlAsText(contents + '.html');
    document.querySelector('#' + target).innerHTML = sanitizedHtml;
    restHandler('GET', '/infoList', null);
}

// Example usage
importPage('contents', 'contents');