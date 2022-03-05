function checkImageType(file_name) {
    var result = false;
    var ext = file_name.substring(file_name.lastIndexOf('.') + 1);
    if (!ext) {
        return result;
    }
    var imgs = ['gif', 'jpg', 'jpeg', 'png', 'bmp', 'ico', 'apng'];
    ext = ext.toLocaleLowerCase();
    imgs.forEach(function (element) {
        if (ext == element) {
            result = true;
        }
    });
    return result;
}

/**
 * 1000 -> 1,000
 * */
function comma(num) {
    var len, point, str;
    num = num + "";
    point = num.length % 3;
    len = num.length;
    str = num.substring(0, point);
    while (point < len) {
        if (str != "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
    }
    return str;
}

function replaceAll(str, search, replace) {
    if (search !== undefined && replace !== undefined && str !== undefined)
        return str.split(search).join(replace);
    else
        throw new Error(`parameter is undefined str : ${str}, search : ${search}, replace : ${replace}`);
}

/**
 * moveToScroll({move_id(id or class), top, speed(scroll speed), isClass(id is class?)});
 * */
function moveToScroll({move_id, top = 0, speed = 400, isClass = false}) {
    if (move_id !== undefined && move_id !== null) {
        var offset;
        if (!isClass) {
            offset = $('#' + move_id).offset();
        } else {
            offset = $('.' + move_id).offset();
        }
        $('html, body').animate({scrollTop: offset.top + top}, speed);
    } else {
        throw new Error(`${move_id} is not exist`);
        console.log('move id is not exist');
    }
}

/**
 * $('#today').val(today());
 * */
class Time {
    today({maxEx, minEx}) {
        var today = new Date();
        var year = today.getFullYear(); // 년도
        var month = today.getMonth() + 1;  // 월
        var date = today.getDate();  // 날짜
        var day = today.getDay();  // 요일
        var hour = today.getHours(); // 시
        var minute = today.getMinutes();  // 분
        switch (maxEx) {
            case 'year':
                switch (minEx) {
                    case 'year':
                        return year;
                    case 'month':
                        return year + '-' + month;
                    case 'date':
                        return year + '-' + month + '-' + date;
                    case 'hour':
                        return year + '-' + month + '-' + date + ' ' + hour;
                    case 'minute':
                        return year + '-' + month + '-' + date + ' ' + hour + ':' + minute;
                    default:
                        return year + '-' + month + '-' + date + ' ' + hour + ':' + minute;
                }
            case 'month':
                switch (minEx) {
                    case 'month':
                        return month;
                    case 'date':
                        return month + '-' + date;
                    case 'hour':
                        return month + '-' + date + ' ' + hour;
                    case 'minute':
                        return month + '-' + date + ' ' + hour + ':' + minute;
                    default:
                        return month + '-' + date + ' ' + hour + ':' + minute;
                }
            case 'date':
                switch (minEx) {
                    case 'date':
                        return date;
                    case 'hour':
                        return date + ' ' + hour;
                    case 'minute':
                        return date + ' ' + hour + ':' + minute;
                    default:
                        return date + ' ' + hour + ':' + minute;
                }
            case 'hour':
                switch (minEx) {
                    case 'hour':
                        return hour;
                    case 'minute':
                        return hour + ':' + minute;
                    default:
                        return hour + ':' + minute;
                }
            case 'minute':
                switch (minEx) {
                    case 'minute':
                        return minute;
                    default:
                        return minute;
                }
            default:
                return year + '-' + month + '-' + date + ' ' + hour + ':' + minute;
        }
    }
}

const tokenGenerator = ((length = 11) => {
    return Math.random().toString(36).substr(2, length); // "twozs5xfni"
})