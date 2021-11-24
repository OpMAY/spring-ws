/**
 * Description : 스마트에디터 파일 업로더 Ajax
 * Prerequisite : Summernote Smarteditor가 존재 해야한다.
 * Parameter : deferred -> smarteditor 내무에 존재하는 변수
 * Return : Non
 * Date : 2021-07-12
 * Version : 1
 * */
function imageUploader(deferred) {
    var file = $('.note-image-input.form-control-file.note-form-control.note-input')[0].files[0]
    var formData = new FormData(); // HTML5
    formData.append("file", file);

    $.ajax({
        url: '/ajax/admin/editor/upload.do',
        processData: false,
        contentType: false,
        data: formData,
        type: 'POST',
        enctype: 'multipart/form-data',
        beforeSend: function (xhr) {
            // XHR Header를 포함해서 HTTP Request를 하기전에 호출됩니다.
            xhr.setRequestHeader('ajax', true);
            console.log('sample before');
        },
        success: function (data, status, xhr) {
            console.log('sample success', data, status, xhr);
            console.log(data.data);
            deferred.resolve(data.data.url);
            // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
            // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
        },
        error: function (xhr, status, error) {
            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에 error 콜백이 호출될 수 있습니다.
            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만, 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
            deferred.reject(data.data.url);
            throw new Error(`Error :: status : ${status}, error : ${error}`);
        },
        complete: function (xhr, status) {
            console.log('sample complete', xhr, status);
            // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
            // try - catch - finally의 finally 구문과 동일합니다.
        }
    });
}

function sample() {
    $.ajax({
        type: "GET", //요청 메소드 타입
        url: "/ajax.do", //요청 경로
        async: true, //비동기 여부
        data: {key: 'value'}, //요청 시 포함되어질 데이터
        processData: true, //데이터를 컨텐트 타입에 맞게 변환 여부
        cache: false, //캐시 여부
        contentType: "application/json", //요청 컨텐트 타입 "application/x-www-form-urlencoded; charset=UTF-8"
        dataType: "json", //응답 데이터 형식 명시하지 않을 경우 자동으로 추측
        beforeSend: function (xhr) {
            // XHR Header를 포함해서 HTTP Request를 하기전에 호출됩니다.
            xhr.setRequestHeader('ajax', true);
            console.log('sample before');
        },
        success: function (data, status, xhr) {
            console.log('sample success', data, status, xhr);
            console.log(data.data);
            // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
            // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
        },
        error: function (xhr, status, error) {
            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에 error 콜백이 호출될 수 있습니다.
            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만, 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
            throw new Error(`Error :: status : ${status}, error : ${error}`);
        },
        complete: function (xhr, status) {
            console.log('sample complete', xhr, status);
            // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
            // try - catch - finally의 finally 구문과 동일합니다.
        }
    });
};

function errorSample() {
    $.ajax({
        type: "GET", //요청 메소드 타입
        url: "/ajaxtest.do", //요청 경로
        async: true, //비동기 여부
        data: {key: 'value'}, //요청 시 포함되어질 데이터
        processData: true, //데이터를 컨텐트 타입에 맞게 변환 여부
        cache: false, //캐시 여부
        contentType: "application/json", //요청 컨텐트 타입 "application/x-www-form-urlencoded; charset=UTF-8"
        dataType: "json", //응답 데이터 형식 명시하지 않을 경우 자동으로 추측
        beforeSend: function (xhr) {
            // XHR Header를 포함해서 HTTP Request를 하기전에 호출됩니다.
            xhr.setRequestHeader('ajax', true);
            console.log('sample before');
        },
        success: function (data, status, xhr) {
            console.log('sample success', data, status, xhr);
            console.log(data.data);
            // 정상적으로 응답 받았을 경우에는 success 콜백이 호출되게 됩니다.
            // 이 콜백 함수의 파라미터에서는 응답 바디, 응답 코드 그리고 XHR 헤더를 확인할 수 있습니다.
        },
        error: function (xhr, status, error) {
            // 응답을 받지 못하였다거나 정상적인 응답이지만 데이터 형식을 확인할 수 없기 때문에 error 콜백이 호출될 수 있습니다.
            // 예를 들어, dataType을 지정해서 응답 받을 데이터 형식을 지정하였지만, 서버에서는 다른 데이터형식으로 응답하면  error 콜백이 호출되게 됩니다.
            throw new Error(`Error :: status : ${status}, error : ${error}`);
        },
        complete: function (xhr, status) {
            console.log('sample complete', xhr, status);
            // success와 error 콜백이 호출된 후에 반드시 호출됩니다.
            // try - catch - finally의 finally 구문과 동일합니다.
        }
    });
};