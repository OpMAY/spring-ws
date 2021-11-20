/**
 * setCookie(name,value,day)
 * */
var setCookie = function ({name, value, day = 1 * 60 * 60 * 24 * 1000}) {
    if ((name !== undefined && name !== null) && (value !== undefined && value !== null)) {
        var date = new Date();
        date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
        document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
    } else {
        console.log('setCookie is valid data');
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
        console.log('getCookie key is not exist key(' + name + ')');
    }
};

/**
 *  deleteCookie(name);
 * */
var deleteCookie = function (name) {
    if (name !== undefined && name !== null) {
        var date = new Date();
        document.cookie = name + "= " + "; expires=" + date.toUTCString() + "; path=/";
    } else {
        console.log('deleteCookie key is not exist key(' + name + ')');
    }
};