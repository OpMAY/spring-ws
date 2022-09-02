/**
 * LoginSubmit,
 * 로그인폼 validation 함수
 * @requires [inspection]
 * @return {boolean}
 * */
const loginSubmit = () => {
  return inspection({
    selector: '[name="email"]',
    regex_type: 'email',
    empty_text: '이메일을 입력해주세요.',
    failed_text: '이메일을 정확히 입력해주세요.',
  }) && inspection({
    selector: '[name="password"]',
    regex_type: 'password',
    empty_text: '패스워드를 입력해주세요.',
    failed_text: '패스워드를 정확히 입력해주세요. (8자 이상 20자 이하, 영문, 숫자, 특수문자(@$!%*#?&^) 모두 포함)',
  });
};

/**
 * FindIdSubmit,
 * 아이디찾기 폼 validation 함수
 * @requires [inspection]
 * @return {boolean}
 * */
const findIdSubmit = () => {
  return inspection({
    selector: '[name="name"]',
    regex_type: 'name',
    empty_text: '이름을 입력해주세요.',
    failed_text: '이름을 정확히 입력해주세요. (2~20자 영어, 한글, 숫자, 띄어쓰기)',
  }) && inspection({
    selector: '[name="phone"]',
    regex_type: 'phone',
    empty_text: '휴대폰 번호를 입력해주세요.',
    failed_text: '휴대폰 번호를 정확히 입력해주세요. (3자리, 3~4자리, 4자리, 띄어쓰기나 \'-\' 허용)',
  });
};

/**
 * FindPsSubmit,
 * 패스워드찾기 폼 validation 함수
 * @requires [inspection]
 * @return {boolean}
 * */
const findPsSubmit = () => {
  return inspection({
    selector: '[name="email"]',
    regex_type: 'email',

    empty_text: '이메일을 입력해주세요.',
    failed_text: '이메일을 정확히 입력해주세요.',
  }) && inspection({
    selector: '[name="email_token"]',
    regex_type: 'verify_code',
    empty_text: '인증 코드를 입력해주세요.',
    failed_text: '인증 코드를 정확히 입력해주세요. (10자리)',
  });
};

/**
 * VerifyMailSubmit,
 * 이메일 인증 폼 validation 함수
 * @requires [inspection]
 * @return {boolean}
 * */
const verifyMailSubmit = () => {
  return inspection({
    selector: '[name="email"]',
    regex_type: 'email',
    empty_text: '이메일을 입력해주세요.',
    failed_text: '이메일을 정확히 입력해주세요.',
  });
};

/**
 * VerifyPhoneSubmit,
 * 휴대폰 인증 폼 validation 함수
 * @requires [inspection]
 * @return {boolean}
 * */
const verifyPhoneSubmit = () => {
  return inspection({
    selector: '[name="phone"]',
    regex_type: 'phone',
    empty_text: '휴대폰 번호를 입력해주세요.',
    failed_text: '휴대폰 번호를 정확히 입력해주세요. (3자리, 3~4자리, 4자리, 띄어쓰기나 \'-\' 허용)',
  });
};