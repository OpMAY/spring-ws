/**
 * @dates 2022.03.23
 * @author kimwoosik
 * @description Modal Setting
 * Case : in page, '<div id="modal-container"></div>' must have exist
 */
/** Modal Lib Setting */
/** General Modal*/
const MODAL_PADDING = 17;
const MODAL_CONTAINER = $('#modal-container');
const ALERT_CONTAINER = $('#alert-container');
$(document).ready(function () {
    /**실행 코드*/
    /*viewModal({
        id: 'modal4', vCenter: true, btnCount: 2,
        title: 'Title', desc: 'Desc',
        confirm_text: 'Confirm Text', cancel_text: 'Cancel Text',
        onConfirm: function (e) {
            console.log('Confirm Button Click Callback', e.currentTarget);
        },
        onCancel: function (e) {
            console.log('Cancel Button Click Callback', e.currentTarget);
        },
        onShown: function (e) {
            console.log('Modal Show After Callback', e.currentTarget);
        },
        onHidden: function (e) {
            console.log('Modal Hide After Callback', e.currentTarget);
        },
        onShow: function (e) {
            console.log('Modal Show Before Callback', e.currentTarget);
        },
        onHide: function (e) {
            console.log('Modal Hide Before Callback', e.currentTarget);
        }
    });*/
    /*viewAlert({id: 'alert-1', type: 'failed', content: 'Login Failed'});*/
});
const viewAlert = ({
                       id = tokenGenerator(8),
                       type = 'success',
                       content,
                       fadeTimeOut = 400,
                       timeOut = 2000
                   }) => {
    let alert = document.createElement('div');
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

const viewModal = ({
                       id = tokenGenerator(8),
                       ariaHidden = true,
                       backDrop = false,
                       tabindex = -1,
                       zIndex = 1050,
                       vCenter = false,
                       btnCount = 1,
                       title = '알림',
                       desc = undefined,
                       confirm_text = '확인',
                       cancel_text = '취소',
                       onConfirm = undefined,
                       onCancel = undefined,
                       onShown = undefined,
                       onHidden = undefined,
                       onShow = undefined,
                       onHide = undefined
                   }) => {

    let modal = document.createElement('div');
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

    if (desc === undefined)
        modal.querySelector('.desc').remove();

    if (backDrop !== false)
        $modal.attr('data-backdrop', 'static');
    if (backDrop !== false)
        $modal.attr('data-keyboard', 'false');
    if (vCenter !== false)
        $modal.find('.modal-dialog').addClass('modal-dialog-centered');

    if (btnCount === 0) {
        /**Not Button*/
    } else if (btnCount === 1) {
        /**One Button*/
        let button = document.createElement('button');
        button.setAttribute('type', 'button');
        button.setAttribute('aria-label', 'agree');
        button.classList.add('btn', 'btn-lg', 'btn-red', 'modal-footer-radius', 'active');
        button.innerText = `${confirm_text}`;
        button.setAttribute('data-dismiss', 'modal');
        if (!isEmpty(onConfirm))
            button.addEventListener('click', onConfirm);
        $modal.find('.modal-footer').append(button);
    } else {
        /**Two Button*/
        let cancelBtn = document.createElement('button');
        cancelBtn.setAttribute('type', 'button');
        cancelBtn.setAttribute('aria-label', 'agree');
        cancelBtn.classList.add('btn', 'btn-lg', 'btn-gray-white', 'modal-footer-radius');
        cancelBtn.innerText = `${cancel_text}`;
        cancelBtn.setAttribute('data-dismiss', 'modal');
        if (!isEmpty(onCancel))
            cancelBtn.addEventListener('click', onCancel);
        $modal.find('.modal-footer').append(cancelBtn);

        let confirmBtn = document.createElement('button');
        confirmBtn.setAttribute('type', 'button');
        confirmBtn.setAttribute('aria-label', 'agree');
        confirmBtn.classList.add('btn', 'btn-lg', 'btn-red', 'modal-footer-radius', 'active');
        confirmBtn.innerText = `${confirm_text}`;
        confirmBtn.setAttribute('data-dismiss', 'modal');
        if (!isEmpty(onConfirm))
            confirmBtn.addEventListener('click', onConfirm);
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
}
const modalHiddenEvent = (event) => {
    if ($('#modal-container .general-modal.show').length === 0) {
        if ($('body').hasClass('general-modal-open')) {
            $('body').css('padding-right', 0);
            $('body').removeClass('general-modal-open');
        }
    } else {
        //$('body').css('padding-right', MODAL_PADDING);
    }
    event.currentTarget.remove();
}

const modalShownEvent = (event) => {
    // do something...
    if ($('body').hasClass('modal-open')) {
        /** padding-right in scroll width */
        //$('body').css('padding-right', MODAL_PADDING);
        $('body').addClass('general-modal-open');
    }
}
/** General Modal End*/
/** Modal Lib Setting End */