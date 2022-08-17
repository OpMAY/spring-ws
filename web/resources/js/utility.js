'use strict';
$(document).ready(function () {
    console.log('utility.js execute');
});

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
function addComma(num) {
    let len, point, str;
    num = num + "";
    point = num.length % 3;
    len = num.length;
    str = num.substring(0, point);
    while (point < len) {
        if (str !== "") str += ",";
        str += num.substring(point, point + 3);
        point += 3;
    }
    return str;
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

function replaceAll(str, search, replace) {
    if (search !== undefined && replace !== undefined && str !== undefined)
        return str.split(search).join(replace);
    else
        throw new Error(`parameter is undefined str : ${str}, search : ${search}, replace : ${replace}`);
}

/**
 * TokenGenerator,
 * 토큰을 생성해주는 함수
 * @param {number} length 만들려는 토큰의 자릿수
 * @return {String} 자릿수 만큼의 String을 리턴
 * */
const tokenGenerator = ((length = 11) => {
    return Math.random().toString(36).substr(2, length); // "twozs5xfni"
});

/**
 * GetURLBuilder,
 * base url 에 json object 를 get 요청 url 로 변환해주는 함수
 *
 * @param {string} baseUrl ex) url
 * @param {Object}  object ex) {key: 'value', k: 'v'}
 * @return {string} url?key=value&h&v
 */
function FetchGetURLBuilder(baseUrl, object) {
    let result = baseUrl + '?';
    Object.keys(object).forEach((key) => {
        result += key + '=' + object[key] + '&';
    });
    return result.substring(0, result.length - 1);
}

/**
 * $('#today').val(today());
 * */
class Time {

    static formatLocalDatetime(datetime) {
        if (datetime === undefined) return this.get_yyyy_mm_dd();
        try {
            const year = datetime.year;
            const month = datetime.monthValue > 9 ? datetime.monthValue : '0' + datetime.monthValue;
            const day = datetime.dayOfMonth > 9 ? datetime.dayOfMonth : '0' + datetime.dayOfMonth;
            const hour = datetime.hour > 9 ? datetime.hour : '0' + datetime.hour;
            const minute = datetime.minute > 9 ? datetime.minute : '0' + datetime.minute;
            const second = datetime.second > 9 ? datetime.second : '0' + datetime.second;

            return `${year}.${month}.${day} ${hour}:${minute}`;
        } catch (e) {
            console.error(e);
            return '';
        }
    }

    static formatLocalDate(datetime) {
        if (datetime === undefined) return this.get_yyyy_mm_dd();
        try {
            const year = datetime.year;
            const month = datetime.monthValue > 9 ? datetime.monthValue : '0' + datetime.monthValue;
            const day = datetime.dayOfMonth > 9 ? datetime.dayOfMonth : '0' + datetime.dayOfMonth;

            return `${year}.${month}.${day}`;
        } catch (e) {
            console.error(e);
            return '';
        }
    }

    static formatChatDateTime(datetime) {
        if (datetime === undefined || datetime === null) return '방금 전';
        try {
            const time_gap = new Date().getTime() - this.getLocalDateTime(datetime);
            if (time_gap < 1000 * 60) { // 1분 이내
                return '방금 전';
            } else if (time_gap < 1000 * 60 * 60) { // 60분 이내
                return Math.floor(time_gap / (1000 * 60)) + '분 전';
            } else if (time_gap < 1000 * 60 * 60 * 12) { // 12시간 이내
                return Math.floor(time_gap / (1000 * 60 * 60)) + '시간 전';
            } else {
                return this.formatLocalDatetime(datetime);
            }
        } catch (e) {
            console.error(e);
            return '';
        }
    }

    static getLocalDateTime(datetime) {
        const year = datetime.year;
        const month = datetime.monthValue - 1;
        const day = datetime.dayOfMonth;
        const hour = datetime.hour;
        const minute = datetime.minute;
        const second = datetime.second;
        return new Date(year, month, day, hour, minute, second).getTime();
    }

    static get_yyyy_mm_dd(target_date) {
        if (!target_date)
            target_date = new Date();
        const [year, month, date] = target_date.toLocaleDateString().replace(/\s/g, '').split('.');
        return `${year}.${month > 9 ? month : '0' + month}.${date > 9 ? date : '0' + date}`;
    }
}

/**
 * FocusInputLastCarret,
 * Input을 클릭시 생성되는 케럿을 맨 뒤로 이동시켜주는 함수
 *
 * @param {string} id 엘리먼트의 아이디, default = undefined
 * @param {string} selector 엘리먼트의 셀렉터, default = undefined
 * @param {HTMLElement} root 부모 엘리먼트, default = document.getElementsByTagName('body')[0]
 */
function focusInputLastCarret({id = undefined, selector = undefined, root = document.getElementsByTagName('body')[0]}) {
    const inputField = id !== undefined ? root.getElementById(id) : root.querySelector(selector);
    if (inputField != null && inputField.value.length != 0) {
        if (inputField.createTextRange) {
            const FieldRange = inputField.createTextRange();
            FieldRange.moveStart('character', inputField.value.length);
            FieldRange.collapse();
            FieldRange.select();
        } else if (inputField.selectionStart || inputField.selectionStart == '0') {
            const elemLen = inputField.value.length;
            inputField.selectionStart = elemLen;
            inputField.selectionEnd = elemLen;
            inputField.focus();
        }
    } else {
        inputField.focus();
    }
}

/**
 * DeleteChild,
 * 엘리먼트 안에 모든 하위 엘리먼트 제거하는 함수
 *
 * @param {HTMLElement | Node} element 하위 엘리먼트를 제거할 상위 엘리먼트
 * */
const deleteChild = (element) => {
    element.innerHTML = '';
};

function toFileSizeString(size) {
    let file_size = size / 1024 / 1024;
    if (file_size < 1) {
        file_size *= 1024;
        file_size = Math.ceil(file_size);
        file_size += 'KB';
    } else {
        file_size = Math.ceil(file_size);
        file_size += 'MB';
    }
    return file_size;
}

/**
 * @dates 2022.02.25
 * @author kimwoosik
 * @description UUID Creator
 * @return {uuid} 16자의 uuid create
 */
function getUUID() { // UUID v4 generator in JavaScript (RFC4122 compliant)
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
        let r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 3 | 8);
        return v.toString(16);
    });
}

/**
 * element 의 input 값을 숫자로 변경, 세자리마다 comma formatting, max 값 세팅시 최대값 제한
 * @param elem - input element
 */
function inspectNumberInput(elem) {
    elem.value = elem.value.replace(/\D/g, ''); // replace non digit
    elem.value = isNaN(Number(elem.value)) ? 0 : comma(Number(elem.value));
    if (elem.value.length === 0) elem.value = 0;

    const max = elem.dataset.max ? Number(elem.dataset.max) : 9_999_999_999;
    if (max < Number(elem.value.replace(/\D/g, ''))) // max 값보다 value 가 크면
        elem.value = comma(max); // 최대값 설정
}

function inspectPureNumberInput(elem) {
    elem.value = elem.value.replace(/\D/g, ''); // replace non digit
    elem.value = isNaN(Number(elem.value)) ? 0 : Number(elem.value);
    if (elem.value.length === 0) elem.value = 0;

    const max = elem.dataset.max ? Number(elem.dataset.max) : 9_999_999_999;
    if (max < Number(elem.value.replace(/\D/g, ''))) // max 값보다 value 가 크면
        elem.value = max; // 최대값 설정
}

function toNumberToMoneyString(number) {
    var inputNumber = number < 0 ? false : number;
    var unitWords = ["", "만", "억", "조", "경"];
    var splitUnit = 10000;
    var splitCount = unitWords.length;
    var resultArray = [];
    var resultString = "";

    for (var i = 0; i < splitCount; i++) {
        var unitResult =
            (inputNumber % Math.pow(splitUnit, i + 1)) / Math.pow(splitUnit, i);
        unitResult = Math.floor(unitResult);
        if (unitResult > 0) {
            resultArray[i] = unitResult;
        }
    }
    for (var i = 0; i < resultArray.length; i++) {
        if (!resultArray[i]) continue;
        resultString = String(numberFormat(resultArray[i])) + unitWords[i] + resultString;
    }
    return resultString.length === 0 ? '0원' : resultString + '원';
}

/**
 * 핸드폰 번호 입력할 때 자동대시
 * 11자리 : 000-0000-0000
 * 10자리 : 000-000-0000
 */
function autoDashPhoneNumber(value) {
    value = value.replace(/[\D]/g, '');
    let tmp = '';
    if (value.length < 4) {
        return value;
    } else if (value.length < 7) {
        tmp += value.substring(0, 3);
        tmp += '-';
        tmp += value.substring(3);
        return tmp;
    } else if (value.length < 11) {
        tmp += value.substring(0, 3);
        tmp += '-';
        tmp += value.substring(3, 3);
        tmp += '-';
        tmp += value.substring(6);
        return tmp;
    } else {
        tmp += value.substring(0, 3);
        tmp += '-';
        tmp += value.substring(3, 4);
        tmp += '-';
        tmp += value.substring(7);
        return tmp;
    }
    return value;
}

const getURLParameter = (name) => {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    let regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function isMobileCheck() {
    return /(android|bb\d+|meego).+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|mobile.+firefox|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|series(4|6)0|symbian|treo|up\.(browser|link)|vodafone|wap|windows ce|xda|xiino|android|ipad|playbook|silk/i.test(navigator.userAgent.substr(0, 4)) || /1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|yas\-|your|zeto|zte\-/i.test(navigator.userAgent.substr(0, 4));
}

/** Compatible RT
 * @Author kimwoosik
 * @Date 2022-07-03
 * */
function mobilePlaceHolderChanger(name) {
    if (isMobileCheck()) {
        let placeholders = $(`[${name}]`);
        placeholders.each(function (i, e) {
            $(this).attr('placeholder', this.dataset.name);
            //$('[data-mobile-holder-text]').attr('placeholder', '');
        });
    }
}

const isResponseSize = (size) => {
    let s = (size !== undefined && size !== null) ? size : 1200;
    let w = screen.width;
    if (w <= s) {
        return true;
    }
    return false;
}