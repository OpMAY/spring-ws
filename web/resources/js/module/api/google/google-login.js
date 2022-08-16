// GOOGLE Module

/**
 * Google Login js
 * -> 웹 페이지 내에서 구글 로그인 연동
 *
 * **/

const callBackURI2 = '/oauth/callback';

$(document).ready(function() {
  googleRestLoginFunction();
  if (typeof gapi === 'undefined') {
    // CALL google login script
    $.getScript('https://apis.google.com/js/platform.js?onload=init', function() {
      // Stuff to do after someScript has loaded
      gapi.load('auth2', function() {
        gapi.auth2.init();
        options = new gapi.auth2.SigninOptionsBuilder();
        options.setPrompt('select_account');
        // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
        options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
        // 인스턴스의 함수 호출 - element에 로그인 기능 추가
        // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
        gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
      });
    });
  }
});

function onSignIn(googleUser) {
  const access_token = googleUser.getAuthResponse().access_token;
  $.ajax({
    // people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
    url: 'https://people.googleapis.com/v1/people/me',
    // key에 자신의 API 키를 넣습니다.,
    data: {'personFields': 'birthdays', 'key': 'AIzaSyBOdmeC4SOSzXmPGLEM2vZueqiBSWKg3wk', 'access_token': access_token},
    method: 'GET',
  })
      .done(function(e) {
        // 프로필을 가져온다.
        const profile = googleUser.getBasicProfile();
        console.log(profile);
      })
      .fail(function(e) {
        console.log(e);
      });
}

function onSignInFailure(t) {
  console.log(t);
}

const googleRestLoginFunction = () => {
  const h = 'https://accounts.google.com/o/oauth2/v2/auth';
  const c = $('#GOOGLE_KEY').val();
  const r = window.location.protocol + '//' + window.location.host + callBackURI2;
  const t = 'code';
  const s = 'email,name,profile'; // 받아올 정보 목록
  console.log(h + '?' + 'client_id=' + c + '&redirect_uri=' + r + '&response_type=' + t + '&scope=' + s.replace(/,/g, '&20'));
};
