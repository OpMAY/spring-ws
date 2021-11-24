function findRegex(type) {
    var regex;
    switch (type) {
        case 'email' :
            regex = /^[0-9a-zA-Z_\.-]+@[0-9a-zA-Z_-]+\.[0-9a-zA-Z_-]+$/;
            break;
        default:
            throw new Error(`${type} is not define`);
    }
    return regex;
}

function isEmpty(value) {
    if (value.replace(/\s/g, '').length !== 0) {
        return false;
    } else {
        return true;
    }
}

function inspection
({
     id_type, id, value, type,
     isFocus = true,
     empty = () => {
     },
     success = () => {
     },
     failed = () => {
     }
 }) {
    if (id_type !== undefined && id !== undefined && value !== undefined && type !== undefined) {
        var focus;
        if (id_type === 'id') {
            focus = $('#' + id);
        } else if (id_type === 'class') {
            focus = $('.' + id);
        }
        value = value.trim();
        if (isEmpty(value)) {
            focus.focus();
            empty(focus);
            return false;
        }
        if (!findRegex(type).test(value)) {
            focus.focus();
            failed(focus);
            return false;
        }
        success(focus);
        return true;
    } else {
        throw new Error(`inspection(${id_type}, ${id}, ${value} ,${type}) not define parameter`);
    }
}