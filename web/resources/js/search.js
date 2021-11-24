'use strict'

class Search {
    constructor({search_id = undefined, result_id = undefined, click}) {
        if (search_id !== undefined && result_id !== undefined) {
            this.search_id = search_id;
            this.result_id = result_id;
            this.search = document.getElementById(this.search_id);
            this.container = document.getElementById(this.result_id);
            if (click !== undefined) {
                this.click = click;
            }
        } else {
            throw new Error(`parameter is not defined (search_id : ${search_id} result_id : ${result_id})`)
        }
    }

    init({value = '', placeholder = '검색어를 입력해주세요.'}) {
        this.search.value = value;
        this.search.placeholder = placeholder;
        this.list = this.container.querySelector('.suggest-list');
        this.list.innerHTML = '';
        this.container.style.display = 'none';
    }

    click(event) {
    }

    clickAfter(event) {
        search.closeList();
    }

    updateWidth(width) {
        this.container.style.width = width + 'px';
    }

    setData(data) {
        var search = this;
        this.data = data;
        this.data.forEach(function (item) {
            search.append(item);
        });
    }

    getData() {
        return this.data;
    }

    append({href, title, date, desc, sub_desc = undefined}) {
        if (href !== undefined &&
            title !== undefined &&
            date !== undefined &&
            desc !== undefined)
            this.list.appendChild(this.createElement({href, title, date, desc, sub_desc}));
        else
            throw new Error(`parameter is not defined (href : ${href} title : ${title} date : ${date} desc : ${desc})`);
    }

    openList() {
        this.container.style.display = 'block';
    }

    closeList() {
        this.container.style.display = 'none';
    }

    createElement(object) {
        var root = document.createElement('a');
        root.classList.add('list-group-item');
        root.classList.add('list-group-item-action');
        root.classList.add('suggest-item');
        root.setAttribute('href', object.href);
        if (object.sub_desc !== undefined)
            root.innerHTML = `<div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1 title">${object.title}</h5>
                                <small class="date">${object.date}</small>
                            </div>
                            <p class="mb-1 desc">${object.desc}</p>
                            <small class="sub-desc">${object.sub_desc}</small>`;
        else
            root.innerHTML`<div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1 title">${object.title}</h5>
                                <small class="date">${object.date}</small>
                            </div>
                            <p class="mb-1 desc">${object.desc}</p>`;
        root.addEventListener('click', this.click);
        root.addEventListener('click', this.clickAfter);
        return root;
    }
}