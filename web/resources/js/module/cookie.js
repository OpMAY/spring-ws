/**
 * setCookie(name,value,day)
 * */
var setCookie = function ({name, value, day = 1}) {
    if ((name !== undefined && name !== null) && (value !== undefined && value !== null)) {
        var date = new Date();
        date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
        document.cookie = `${name}=${value};expires=${date.toUTCString()};path=/`;
    } else {
        throw new Error(`${name} or ${value} cookie is empty data`);
    }
};

/**
 *  var object = getCookie(name);
 * */
var getCookie = function (name) {
    if (name !== undefined && name !== null) {
        var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
        return value ? value[2] : null;
    } else {
        throw new Error(`cookie key is not exist key(${name}`);
    }
};

/**
 *  deleteCookie(name);
 * */
var deleteCookie = function (name) {
    if (name !== undefined && name !== null) {
        var date = new Date();
        document.cookie = `${name}= ;expires=${date.toUTCString()};path=/`;
    } else {
        throw new Error(`deleteCookie key is not exist key ${name}`);
    }
};