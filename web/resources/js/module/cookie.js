/**
 * SetCookie,
 * 쿠키를 설정하는 함수
 * @version a.2
 * @param {string} key 쿠키에서의 키 설정
 * @param {string | JSON} value 쿠키에서의 해당 키에대한 값 설정
 * @param {number} day 해당 쿠키를 얼마나 유지할건지에 대한 숫자 (DAY 기준)
 *
 * @example
 * setCookie({key: key,value: value,day: 10});
 * */
const setCookie = function({key, value, day = 1}) {
  if ((key !== undefined && key != null) && (value !== undefined && value !== null)) {
    const date = new Date();
    date.setTime(date.getTime() + day * 60 * 60 * 24 * 1000);
    document.cookie = `${key}=${value};expires=${date.toUTCString()};path=/`;
  } else {
    throw new Error(`${key} or ${value} cookie is empty data`);
  }
};

/**
 * GetCookie,
 * 설정한 쿠키를 가져오는 함수
 * @version a.2
 * @param {string} key 가져올 쿠키의 키값
 *
 * @example
 * getCookie(key);
 * */
const getCookie = function(key) {
  if (key !== undefined && key != null) {
    const value = document.cookie.match('(^|;) ?' + key + '=([^;]*)(;|$)');
    return value ? value[2] : null;
  } else {
    throw new Error(`cookie key is not exist key(${key}`);
  }
};

/**
 * DeleteCookie,
 * 설정한 쿠키를 제거하는 함수
 * @version a.2
 * @param {string} key 제거할 쿠키의 키값
 *
 * @example
 * deleteCookie(key);
 * */
const deleteCookie = function(key) {
  if (key !== undefined && key != null) {
    const date = new Date();
    document.cookie = `${key}= ;expires=${date.toUTCString()};path=/`;
  } else {
    throw new Error(`deleteCookie key is not exist key ${key}`);
  }
};
