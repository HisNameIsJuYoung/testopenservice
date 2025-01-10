let folder = 'info';
let file = 'infoList';
let contents = '/' + folder + '/' + file;
let summernoteHeight = 510;
const message = {
    infoProgressing: '공지사항을 등록합니다.',
    infoRequestError: '공지사항을 요청할 수 없습니다.',
    infoNetworkError: '네트워크 상태를 확인해 주세요.',
    infoPostError: '공지사항을 등록할 수 없습니다.',
    infoDeleteConfirm: '공지사항을 삭제하시겠습니까?',
    infoDeleteComplete: '삭제되었습니다.',
    infoDeleteError: '공지사항을 삭제할 수 없습니다.',
    infoUpdateProgressing: '공지사항을 수정합니까?',
    infoUpdateComplete: '번 공지사항을 수정합니다.'
};

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
            throw new Error(errorMessage || message.infoRequestError);
        }
        return await response.json();
    } catch (error) {
        alert(error.message);
        throw new Error(message.infoNetworkError);
    }
};

const heightResize = () => {
    let contentsHeight = (window.innerHeight - 320);
    document.querySelector('#contents').style.height = contentsHeight + 'px';
    document.querySelector('#contents').style.width = window.innerWidth + 'px';
};
window.addEventListener('resize', heightResize);

let openedItem = {};

const contentOpen = (infoItem) => {
    infoItem.style.backgroundColor = '#d1f1ee';
    infoItem.style.borderRadius = '10px';
    infoItem.querySelector('.modify-delete').classList.remove('dspnon');
    infoItem.querySelector('.content').classList.remove('dspnon');
};

const contentClose = (infoItem) => {
    infoItem.removeAttribute('style');
    infoItem.querySelector('.modify-delete').classList.add('dspnon');
    infoItem.querySelector('.content').classList.add('dspnon');
};

const loadInfoItems = async () => {
    try {
        const response = await restHandler('GET', '/getInfo', null);
        const infoTemplate = document.querySelector('#info-template');
        let itemNumber = 1;
        response.data.forEach(res => {
            let infoItem = infoTemplate.cloneNode(true);
            infoItem.id = 'item' + res.id;
            infoItem.querySelector('.number').innerText = itemNumber;
            infoItem.querySelector('.date').innerText = res.createDate.substr(0, 10);
            infoItem.querySelector('.title').innerText = res.title;
            infoItem.querySelector('.content').innerHTML = res.content;

            infoItem.querySelector('.info-item-summary').addEventListener('click', () => {
                if (openedItem.element === infoItem) {
                    contentClose(infoItem);
                    openedItem = {};
                } else {
                    if (openedItem.element) contentClose(openedItem.element);
                    contentOpen(infoItem);
                    openedItem.element = infoItem;
                    openedItem.number = itemNumber - 1;
                }
            });

            infoItem.querySelector('#delete').addEventListener('click', async () => {
                if (confirm(message.infoDeleteConfirm) == true) {
                    try {
                        await restHandler('DELETE', '/deleteInfo', { id: res.id });
                        alert((openedItem.number) + '번 ' + message.infoDeleteComplete);
                        location.reload();
                    } catch (error) {
                        alert(message.infoDeleteError);
                    }
                } else {
                    return false;
                }
            });

            infoItem.querySelector('#modify').addEventListener('click', (event) => {
                writeInfo(openedItem.element, event);
            });

            itemNumber += 1;
            infoTemplate.after(infoItem);
        });
        infoTemplate.remove();
    } catch (error) {
        console.error('Error loading info items:', error);
    }
};

const insertDataInSummernote = (infoItem) => {
    document.querySelector('#title-info').value = infoItem.querySelector('.title').innerHTML;
    document.querySelector('.note-editable').innerHTML = infoItem.querySelector('.content').innerHTML;
};

const writeInfo = async (event) => {
    document.querySelector('#contents').innerHTML = '';
    file = 'postInfo';
    contents = '/' + folder + '/' + file;

    const fetchHtmlAsText = async (url) => {
        const response = await fetch(url);
        return await response.text();
    };

    const importPage = async (target, contents) => {
        document.querySelector('#' + target).innerHTML = await fetchHtmlAsText(contents + '.html');
        $('#summernote').summernote({
            height: summernoteHeight,
            lang: "ko-KR",
            toolbar: [
                ['fontname', ['fontname']],
                ['fontsize', ['fontsize']],
                ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
                ['color', ['color']],
                ['table', ['table']],
                ['para', ['ul', 'ol', 'paragraph']],
                ['height', ['height']],
                ['insert', ['picture']]
            ],
            focus: true,
            fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋음체', '바탕체'],
            fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72', '96'],
            callbacks: {
                onImageUpload: function(files) {
                    for (let i = 0; i < files.length; i++) {
                        uploadImage(files[i], this);
                    }
                }
            }
        });

        if (openedItem.element && event) {
            insertDataInSummernote(openedItem.element);
            let buttonPostInfo = document.querySelector('button#post-info');
            buttonPostInfo.setAttribute('onclick', 'putInfo()');
            buttonPostInfo.innerText = '수정완료';
        }
    };

    await importPage('contents', contents);
};

const uploadImage = async (file, editor) => {
    let formData = new FormData();
    formData.append('file', file);

    try {
        const data = await restHandler('POST', '/imageUpload', formData, true);
        const imageUrl = `/Users/ysh/workspace/testopenservice/src/main/resources/static/image/${data.fileName}`;
        $(editor).summernote('insertImage', imageUrl, function($image) {
            $image.attr('data-filename', data.fileName);
            $image.css('width', "100%");
        });
    } catch (error) {
        console.error('Error uploading image:', error);
    }
};

const postInfo = async () => {
    let data = {
        title: document.querySelector('#title-info').value,
        content: document.querySelector('#summernote').value
    };
    alert(message.infoProgressing);
    try {
        const response = await restHandler('POST', '/postInfo', data);
        if (response.status === 200) location.reload();
        else alert(message.infoPostError);
    } catch (error) {
        alert(message.infoPostError);
    }
};

const putInfo = async () => {
    let data = {
        id: parseInt(openedItem.element.id.substr(4)),
        title: document.querySelector('#title-info').value,
        content: document.querySelector('#summernote').value
    };
    if (confirm(message.infoUpdateProgressing) == true) {
        try {
            const response = await restHandler('PUT', '/putInfo', data);
            alert(openedItem.number + infoUpdateComplete);
            location.reload();
        } catch (error) {
            alert(response.data);
            location.reload();
        }
    } else {
        return false;
    }
};

// Initialize the page
document.addEventListener('DOMContentLoaded', () => {
    heightResize();
    loadInfoItems();
});


LOAD DATA local INFILE '/Users/ysh/Downloads/testing.csv'
INTO TABLE checklist
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'