/**
 * FindRegex,
 * regex 형태를 찾아주는 함수
 *
 * @param {string} type 찾을 regex 타입
 * */
function findRegex(type) {
    switch (type) {
        case 'email':
            return /^[0-9a-zA-Z_.-]{1,30}@[0-9a-zA-Z_-]{1,10}\.[0-9a-zA-Z_-]{0,10}$/; // 최대 50자, 이메일형식
        case 'name':
        case 'nickname':
            return /^[가-힣a-zA-Z\d\s]{2,20}$/; // 2~20자 영어, 한글, 숫자, 띄어쓰기
        case 'name1~20':
            return /^[가-힣a-zA-Z\d\s]{1,20}$/; // 1~20자 영어, 한글, 숫자, 띄어쓰기
        case 'title':
            return /^[가-힣a-zA-Z\d\s]{4,100}$/; // 4~100자 영어, 한글, 숫자, 띄어쓰기
        case 'password':
            return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&^])[A-Za-z\d@$!%*#?&^]{8,20}$/; // 8자 이상 20자 이하, 영문, 숫자, 특수문자(@$!%*#?&^) 모두 포함
        case 'phone':
            return /^([\d]{2,3})[\s|-]?([\d]{3,4})[\s|-]?([\d]{4})$/igm; // 3자리, 3~4자리, 4자리, 띄어쓰기나 - 입력가능
        case 'price':
            return /^(?!0+$)[,0-9]{1,13}$/; // 0 제외, 1~13자리, 최대 9,999,999,999
        case 'price0':
            return /^[,0-9]{1,13}$/; // 0 허용, 1~13자리, 최대 9,999,999,999
        case 'budget':
            return /^(?!0+$).{4,13}$/; // 0 제외, 1~13자리, 최대 9,999,999,999
        case 'date':
            return /^[\d]{4}.[\d]{2}.[\d]{2}$/; // yyyy.MM.dd 만 허용
        case 'datedash':
            return /^[\d]{4}-[\d]{2}-[\d]{2}$/; // yyyy.MM.dd 만 허용
        case '4~10':
            return /^.{4,10}$/gm;
        case '4~20':
            return /^.{4,20}$/gm;
        case '0~20':
            return /^.{0,20}$/gm;
        case 'address':
            return /^[가-힣a-zA-Z\d\s]{0,20}$/; // 0~20자 영어, 한글, 숫자, 띄어쓰기
        case '0~50':
            return /^.{0,50}$/gm;
        case '1~20':
            return /^.{1,20}$/gm;
        case '1~50':
            return /^.{1,50}$/gm;
        case '1~100':
            return /^.{1,100}$/gm;
        case '10~100':
            return /^.{10,100}$/gm;
        case '0~200':
            return /^.{0,200}$/gm;
        case '10~200':
            return /^.{10,200}$/gm;
        case '0~100':
            return /^.{0,100}$/gm;
        case '0~1000':
            return /^.{0,1000}$/gm;
        case '1~1000':
            return /^.{1,1000}$/gm;
        case '1~2000':
            return /^.{1,2000}$/gm;
        case '0~2000':
            return /^.{0,2000}$/gm;
        case 'verify_code':
            return /^.{10}$/gm; // 10자리 고정
        case 'verify_phone_code':
            return /^.{10}$/gm; // 10자리 고정
        case 'birthday':
            return /([\d]{2}(0[1-9]|1[0-2])(0[1-9]|[1,2][\d]|3[0,1]))/gm; // ex) 000000 6자리
        case 'account':
            return /^\d{1,15}/;
        case 'business': // 사업자 등록 번호
            return /^(\d{3})+[-]+(\d{2})+[-]+(\d{5})/gm;
        case 'company': // 법인 사업자 등록 번호
            return /^(\d{6})+[-]+(\d{7})/gm;
        default:
            throw new Error(`${type} is not define`);
    }
}

/**
 * Inspection,
 * validation을 해주는 함수,
 * selector or element or value 중 하나로 검사할 값을 찾아서 검사하는 함수
 * @requires [viewAlert, findRegex]
 * @param {string} selector - css selector
 * @param {HTMLElement | Element | Document} element - 지정 element 없으면 selector 로 element 지정
 * @param {string | number | Object} value - 지정 값이 없으면 selector 로 element 지정
 * @param {boolean} regex_check - 정규식 체크 여부, default = true
 * @param {string} regex_type - 정규식 타입
 * @param {string} empty_text - 빈값일때 노출 텍스트
 * @param {string} failed_text - 정규식 실패시 노출 텍스트
 * @param {boolean} isFocus - 체크 후 focus 여부, default = true
 * @param {boolean} empty_check - 빈값 체크 여부, default = true
 * @param {function} empty - 빈값일때 callback, default 정의되어있음
 * @param {function} failed - 정규식 통과 실패시 callback
 * @param {function} success - 모든 검사 통과 후 반환 직전 callback
 * @return {boolean} - 통과 여부
 */
function inspection({
                        selector,
                        element = document.querySelector(selector),
                        value = document.querySelector(selector) ? document.querySelector(selector).value : '', // 검사할 값, default = 해당 input의 value
                        regex_check = true,
                        regex_type,
                        empty_text = '입력값이 없습니다.',
                        failed_text = '잘못 된 입력입니다.',
                        isFocus = true,
                        empty_check = true,
                        empty = () => {
                            if (empty_text.length !== 0) {
                                viewAlert({
                                    content: empty_text,
                                });
                            }
                        },
                        failed = () => {
                            if (failed_text.length !== 0) {
                                viewAlert({
                                    content: failed_text,
                                });
                            }
                        },
                        success = () => {
                        },
                    }) {
    try {
        if (element) value = element.value;
        try {
            value = value.trim();
            value = value.replace(/\n/g, '');// 행바꿈제거
            value = value.replace(/\r/g, '');// 엔터제거
        } catch (e) {
            /** TextArea Value Trim Error Fix*/
            value = $.trim(value);
            value = value.replace(/\n/g, '');// 행바꿈제거
            value = value.replace(/\r/g, '');// 엔터제거
        }
        if (empty_check && isEmpty(value)) { // 빈 값
            if (element && isFocus) element.focus();
            empty(element);
            return false;
        }
        if (regex_check && !findRegex(regex_type).test(value)) { // 정규식 불합격 (길이, 특수문자 등)
            if (element && isFocus) element.focus();
            failed(element);
            return false;
        }
        success(element);
        return true;
    } catch (e) {
        throw new Error(e);
    }
}
