// General Modal
// in page, '<div id="modal-container"></div>' must have exist

// MODAL_CONTAINER
const MODAL_CONTAINER = $('#modal-container');
// ALERT_CONTAINER
const ALERT_CONTAINER = $('#alert-container');
/**
 * ViewAlert,
 * Alert 메세지를 보여주는 함수
 * @version a.2
 * @requires [tokenGenerator]
 * @param {string} id Alert 메세지에서 설정될 ID 값, default = 8자리 랜덤토큰
 * @param {string} type Alert 메세지의 타입 ('success','failed','info','danger','warning'), default = 'success'
 * @param {string | InnerHTML} content Alert 메세지에서 보여줄 내용
 * @param {number} fadeTimeOut Alert 메세지가 사라지는데 걸리는 시간 (ms), default = 400
 * @param {number} timeOut Alert 메세지가 유지되는 시간 (ms), default = 2000
 * @example
 * viewAlert({id: 'alert-1', type: 'failed', content: 'Login Failed', fadeTimeOut: 400, timeOut: 2000});
 * */
const viewAlert = ({
  id = tokenGenerator(8),
  type = 'success',
  content,
  fadeTimeOut = 400,
  timeOut = 2000,
}) => {
  const alert = document.createElement('div');
  alert.setAttribute('id', id);
  alert.classList.add('alert');
  alert.style.display = 'none';
  if (type === 'success') {
    alert.classList.add('alert-success');
  } else if (type === 'failed') {
    alert.classList.add('alert-failed');
  } else if (type === 'info') {
    alert.classList.add('alert-info');
  }
  alert.innerHTML = `${content}`;
  ALERT_CONTAINER.append(alert);
  $('#' + id).fadeIn(fadeTimeOut);
  setTimeout(() => {
    $('#' + id).fadeOut(fadeTimeOut, () => {
      $('#' + id).remove();
    });
  }, timeOut);
};

/**
 * ViewModal,
 * Modal을 보여주는 함수
 * @version a.2
 * @requires [tokenGenerator, modalShownEvent, modalHiddenEvent, isEmpty]
 * @param {string} id 모달에서 사용되는 ID, default = 8자리 토큰
 * @param {boolean} ariaHidden Aria Control, default = true
 * @param {boolean} backDrop 모달 뒤쪽을 클릭했을 때 모달이 닫히는 여부, default = false
 * @param {number} tabindex Tab Index Control, default = -1
 * @param {number} zIndex Modal의 Z-Index 설정, default = 1050
 * @param {boolean} vCenter Modal의 수직 센터 정렬 설정 여부, default = false
 * @param {number} btnCount Modal의 버튼 개수 (최대2개), default = 1
 * @param {string | InnerHTML} title Modal의 제목 부분 설정, default = '알림'
 * @param {string | InnerHTML} desc Modal의 설명 부분 설정
 * @param {string} confirm_text Modal 버튼의 성공 텍스트, default = '확인'
 * @param {string} cancel_text Modal 버튼의 취소 텍스트, default = '취소'
 * @param {function} onConfirm Modal 성공 버튼을 눌렀을때의 콜백 함수
 * @param {function} onCancel Modal 취소 버튼을 눌렀을때의 콜백 함수
 * @param {function} onShown Modal 생성됬을 때의 콜백함수
 * @param {function} onHidden Modal 제거됬을 때의 콜백함수
 * @param {function} onShow Modal 생성되기 전 콜백함수
 * @param {function} onHide Modal 제거되기 전 콜백함수
 * @example
 * viewModal({
 *         id: 'modal4', vCenter: true, btnCount: 2,
 *         title: 'Title', desc: 'Desc',
 *         confirm_text: 'Confirm Text', cancel_text: 'Cancel Text',
 *         onConfirm: function (e) {
 *             console.log('Confirm Button Click Callback', e.currentTarget);
 *         },
 *         onCancel: function (e) {
 *             console.log('Cancel Button Click Callback', e.currentTarget);
 *         },
 *         onShown: function (e) {
 *             console.log('Modal Show After Callback', e.currentTarget);
 *         },
 *         onHidden: function (e) {
 *             console.log('Modal Hide After Callback', e.currentTarget);
 *         },
 *         onShow: function (e) {
 *             console.log('Modal Show Before Callback', e.currentTarget);
 *         },
 *         onHide: function (e) {
 *             console.log('Modal Hide Before Callback', e.currentTarget);
 *         }
 *     });
 * */
const viewModal = ({
  id = tokenGenerator(8),
  ariaHidden = true,
  backDrop = false,
  tabindex = -1,
  zIndex = 1050,
  vCenter = false,
  btnCount = 1,
  title = '알림',
  desc,
  confirm_text = '확인',
  cancel_text = '취소',
  onConfirm,
  onCancel,
  onShown,
  onHidden,
  onShow,
  onHide,
}) => {
  const modal = document.createElement('div');
  const $modal = $(modal);

  modal.classList.add('modal', 'fade', 'general-modal');
  modal.style.zIndex = zIndex;
  modal.setAttribute('id', id);
  modal.setAttribute('tabindex', tabindex);
  modal.setAttribute('aria-hidden', ariaHidden);

  modal.innerHTML = `<div class="modal-dialog">
                          <div class="modal-content radius-12">
                              <div class="modal-body">
                                  <div class="body-content my-auto mx-auto">
                                      <span class="title">${title}</span>
                                      <span class="desc">${desc}</span>
                                  </div>
                              </div>
                              <div class="modal-footer"
                                   count="${btnCount}">
                              </div>
                          </div>
                      </div>`;

  if (desc === undefined) {
    modal.querySelector('.desc').remove();
  }

  if (backDrop !== false) {
    $modal.attr('data-backdrop', 'static');
  }
  if (backDrop !== false) {
    $modal.attr('data-keyboard', 'false');
  }
  if (vCenter !== false) {
    $modal.find('.modal-dialog').addClass('modal-dialog-centered');
  }

  if (btnCount === 0) {
    /** Not Button*/
  } else if (btnCount === 1) {
    /** One Button*/
    const button = document.createElement('button');
    button.setAttribute('type', 'button');
    button.setAttribute('aria-label', 'agree');
    button.classList.add('btn', 'btn-lg', 'btn-red', 'modal-footer-radius', 'active');
    button.innerText = `${confirm_text}`;
    button.setAttribute('data-dismiss', 'modal');
    if (!isEmpty(onConfirm)) {
      button.addEventListener('click', onConfirm);
    }
    $modal.find('.modal-footer').append(button);
  } else {
    /** Two Button*/
    const cancelBtn = document.createElement('button');
    cancelBtn.setAttribute('type', 'button');
    cancelBtn.setAttribute('aria-label', 'agree');
    cancelBtn.classList.add('btn', 'btn-lg', 'btn-gray-white', 'modal-footer-radius');
    cancelBtn.innerText = `${cancel_text}`;
    cancelBtn.setAttribute('data-dismiss', 'modal');
    if (!isEmpty(onCancel)) {
      cancelBtn.addEventListener('click', onCancel);
    }
    $modal.find('.modal-footer').append(cancelBtn);

    const confirmBtn = document.createElement('button');
    confirmBtn.setAttribute('type', 'button');
    confirmBtn.setAttribute('aria-label', 'agree');
    confirmBtn.classList.add('btn', 'btn-lg', 'btn-red', 'modal-footer-radius', 'active');
    confirmBtn.innerText = `${confirm_text}`;
    confirmBtn.setAttribute('data-dismiss', 'modal');
    if (!isEmpty(onConfirm)) {
      confirmBtn.addEventListener('click', onConfirm);
    }
    $modal.find('.modal-footer').append(confirmBtn);
  }

  MODAL_CONTAINER.append(modal);
  if (!isEmpty(onShown)) {
    MODAL_CONTAINER.find('#' + id + '.general-modal').on('shown.bs.modal', onShown);
  }
  MODAL_CONTAINER.find('#' + id + '.general-modal').on('shown.bs.modal', modalShownEvent);
  if (!isEmpty(onHidden)) {
    MODAL_CONTAINER.find('#' + id + '.general-modal').on('hidden.bs.modal', onHidden);
  }
  MODAL_CONTAINER.find('#' + id + '.general-modal').on('hidden.bs.modal', modalHiddenEvent);
  if (!isEmpty(onShow)) {
    MODAL_CONTAINER.find('#' + id + '.general-modal').on('show.bs.modal', onShow);
  }
  if (!isEmpty(onHide)) {
    MODAL_CONTAINER.find('#' + id + '.general-modal').on('hide.bs.modal', onHide);
  }
  $('#' + id).modal('show');
};

/**
 * ModalHiddenEvent,
 * 모달이 제거됬을때의 이벤트
 * @param {Event} event
 * */
const modalHiddenEvent = (event) => {
  if ($('#modal-container .general-modal.show').length === 0) {
    if ($('body').hasClass('general-modal-open')) {
      $('body').removeClass('general-modal-open');
    }
  }
  event.currentTarget.remove();
};

/**
 * ModalShownEvent,
 * 모달이 생성됬을때의 이벤트
 * @param {Event} event
 * */
const modalShownEvent = (event) => {
  if ($('body').hasClass('modal-open')) {
    $('body').addClass('general-modal-open');
  }
};
