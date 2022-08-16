// NAVER Module

// NAVER KEY (CLIENT ID)
const key = document.getElementById('NAVER_KEY').value;
let naverLogin;
const callBackURI = '/oauth/callback';

/**
 * Naver Login JS (미완성)
 * ->  웹 페이지 내에서 바로 로그인을 적용시키기 위한 JS
 * -> 네이버 로그인 기본 div는 naverIdLogin으로 필수로 설정해주셔야합니다.
 * TODO 확인
 * **/

$(document).ready(function() {
  if (typeof naver === 'undefined' && document.getElementById('naverIdLogin').length !== 0) {
    // CALL naver login script
    $.getScript('https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js', function() {
      // Stuff to do after someScript has loaded
      naverLogin = new naver.LoginWithNaverId({
        clientId: key,
        // Java Oauth Call back 와는 다름 (오로지 javascript 단에서만 해결)
        callbackUrl: window.location.protocol + '//' + window.location.host + callBackURI,
        isPopup: false,
        loginButton: {color: 'green', type: 2, height: 40},
      });
      naverLogin.init();

      /**
             * TODO 강제로 해당 URL로 호출하여 CALLBACK URL에서 LoginApi 내의 함수를 이용하고 싶었지만,
             * http://localhost:8080/oauth/callback#access_token=AAAAOdtLPDQDsUtMPS1eoO0DOaHb-Ww49E2Br_MX60lbiQ7zTX6b0djvVOgLCZE29O9MV1f32mDAkHBHZDt10fM7vnI&state=cf00e39d-86cb-4aeb-95b2-82da4ced8b1c&token_type=bearer&expires_in=3600
             * 위와 같이 URL의 형태로 Redirect를 걸어버려서 서버에서 정보를 받아올 수 없음
             * **/
      console.log('use This url for custom button : ', naverLogin.generateAuthorizeUrl());
      // AFTER CALLBACK

      naverLogin.getLoginStatus(function(status) {
        if (status) {
          console.log('it is logined');
          $('#naver-logout').removeClass('d-none');
          $('#naver-logout').on('click', function() {
            naverLogin.logout();
            location.replace('/test/login');
          });
        } else {
          console.log('not logined');
          $('#naver-logout').addClass('d-none');
          $('#naver-logout').on('click', function(e) {
            e.preventDefault();
          });
        }
      });
    });
  }
});

$('#naver-rest').on('click', function() {
  naverRestLoginFunction();
});

const naverRestLoginFunction = (key) => {
  // naverLogin.generateAuthorizeUrl() = https://nid.naver.com/oauth2.0/authorize?response_type=token&client_id=oIQIgOq3cYee9n8UAYph&state=cf00e39d-86cb-4aeb-95b2-82da4ced8b1c&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Foauth%2Fcallback&version=js-2.0.1&svctype=1;
  location.href = 'https://nid.naver.com/oauth2.0/authorize?response_type=token&client_id=oIQIgOq3cYee9n8UAYph&state=cf00e39d-86cb-4aeb-95b2-82da4ced8b1c&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Foauth%2Fcallback&version=js-2.0.1&svctype=1';
};


// 네이버 로그인 버튼은 무조건 ID = naverIdLogin


