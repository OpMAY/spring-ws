// KAKAO Module

/**
 * Kakao Login JS
 * -> ### 표시 한 곳에 Element ID만 맞춰서 설정하시면 됩니다.
 * kakaoJSLoginFunction => 팝업 창으로 로그인 하여 프론트 단에 바로 로그인 정보 호출할 때
 * kakaoRestLoginFunction => oauth 로 Request 띄워서 call back url에 연동하여 로그인 정보 호출할 때
 * **/

const kakaoKey = $('#KAKAO_JAVASCRIPT').val();
$(document).ready(function() {
  if (typeof Kakao === 'undefined') {
    // CALL naver login script
    $.getScript('https://developers.kakao.com/sdk/js/kakao.js', function() {
      // Stuff to do after someScript has loaded
      kakaoInit();
      document.getElementById('kakao-popup').addEventListener('click', function() {
        kakaoJSLoginFunction();
      });
      $('#kakao').data('key', $('#KAKAO_KEY').val());
      document.getElementById('kakao').addEventListener('click', function() {
        kakaoRestLoginFunction($('#kakao').data().key);
      });
    });
  }
});

const kakaoInit = () => {
  // Require Javascript Key
  Kakao.init(kakaoKey);
};

const kakaoJSLoginFunction = () => {
  // Javascript 단에서만 동작
  Kakao.Auth.login({
    success: function(authObj) {
      console.log('success : ', authObj);
      // DO Something..
    },
    fail: function(err) {
      alert('failed to login: ' + JSON.stringify(err));
    },
  });
};

const kakaoRestLoginFunction = (key) => {
  // Back 단에서 동작
  /** Kakao*/
  /** href="https://kauth.kakao.com/oauth/authorize?client_id=###&redirect_uri=http://###/auth/login.do&response_type=code"*/
  const h = 'https://kauth.kakao.com/oauth/authorize';
  const c = key;
  const r = window.location.protocol + '//' + window.location.host + '/oauth/callback';
  const t = 'code';
  location.href = h + '?' + 'client_id=' + c + '&redirect_uri=' + r + '&response_type=' + t;
};
