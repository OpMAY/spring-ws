/**
 * @dates 2022.02.25
 * @author kimwoosik
 * @description right chat item create
 * @param {object} chat
 * @return {element} Chat Item
 */
function appendChatRightItem(chat) {
    return `<li class="chat-item odd clearfix pb-2 pt-2">
                <div class="chat-item-wrapper d-flex">
                    <div class="mr-2 chat-content">
                        <div class="text-wrap position-relative">
                            <i class="font-weight-bold">${chat.username} <span class="text-primary">[${chat.role}]</span></i>
                            <p>${chat.message}</p>
                            <i class="position-absolute" style="top: 0; right: 0;">${chat.timestamp}</i>
                        </div>
                    </div>
                    <div class="chat-avatar">
                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                             alt="${chat.username}"
                             class="rounded">
                    </div>
                </div>
                <div class="file-item card mt-2 mb-2 border text-left d-inline-block">
                    <div class="file-item-wrapper p-2">
                        <div class="row align-items-center">
                            <div class="col-auto">
                                <div class="type">
                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">${chat.file.type}</span>
                                </div>
                            </div>
                            <div class="col ps-0">
                                <a href="javascript:void(0);"
                                   class="text-muted fw-bold">${chat.file.name}</a>
                                <p class="mb-0">${chat.file.size}</p>
                            </div>
                            <div class="col-auto">
                                <a href="javascript:void(0);"
                                   class="btn btn-link btn-lg text-muted">
                                    <i class="fa-solid fa-file-arrow-down"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
}

/**
 * @dates 2022.02.25
 * @author kimwoosik
 * @description left chat item create
 * @param {object} chat
 * @return {element} Chat Item
 */
function appendChatLeftItem(chat) {
    return `<li class="chat-item clearfix pb-2 pt-2">
                <div class="chat-item-wrapper d-flex">
                    <div class="chat-avatar">
                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                             alt="${chat.username}"
                             class="rounded">
                    </div>
                    <div class="chat-content ml-2">
                        <div class="text-wrap position-relative">
                            <i class="font-weight-bold">${chat.username} <span class="text-primary">[${chat.role}]</span></i>
                            <p>${chat.message}</p>
                            <i class="position-absolute" style="top: 0; right: 0;">${chat.timestamp}</i>
                        </div>
                    </div>
                </div>
                <div class="file-item card mt-2 mb-2 border text-left d-inline-block"
                     style="margin-left: 68px;">
                    <div class="file-item-wrapper p-2">
                        <div class="row align-items-center">
                            <div class="col-auto">
                                <div class="type">
                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">${chat.file.type}</span>
                                </div>
                            </div>
                            <div class="col ps-0">
                                <a href="javascript:void(0);"
                                   class="text-muted fw-bold">${chat.file.name}</a>
                                <p class="mb-0">${chat.file.size}</p>
                            </div>
                            <div class="col-auto">
                                <a href="javascript:void(0);"
                                   class="btn btn-link btn-lg text-muted">
                                    <i class="fa-solid fa-file-arrow-down"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
}

/**
 * @dates 2022.02.25
 * @author kimwoosik
 * @description left chat video item create
 * @param {object} chat
 * @return {element} Chat Item
 */
function appendChatYoutubeLeftItem(chat) {
    return `<li class="chat-item clearfix pb-2 pt-2">
                <div class="chat-item-wrapper d-flex">
                    <div class="chat-avatar">
                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                             alt="${chat.username}"
                             class="rounded">
                    </div>
                    <div class="chat-content ml-2">
                        <div class="text-wrap position-relative">
                            <i class="font-weight-bold">${chat.username} <span class="text-primary">[${chat.role}]</span></i>
                            <p>${chat.message}</p>
                            <i class="position-absolute" style="top: 0; right: 0;">${chat.timestamp}</i>
                        </div>
                    </div>
                </div>
                <div class="youtube-item card mt-2 mb-2 border-0 text-left d-inline-block w-100">
                    <div class="file-item-wrapper p-0">
                        <div class="row align-items-center">
                            <div class="col-auto w-100">
                                <%-- 1. The <iframe> (and video player) will replace this <div> tag. --%>
                                <div class="player" data-id="${chat.video.id}"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
}

/**
 * @dates 2022.02.25
 * @author kimwoosik
 * @description right chat video item create
 * @param {object} chat
 * @return {element} Chat Item
 */
function appendChatYoutubeRightItem(chat) {
    return `<li class="chat-item odd clearfix pb-2 pt-2">
                <div class="chat-item-wrapper d-flex">
                    <div class="mr-2 chat-content">
                        <div class="text-wrap position-relative">
                            <i class="font-weight-bold">readable content <span class="text-secondary">[Video Editor]</span></i>
                            <p style="min-width: 500px;">Donec tortor augue, mattis porttitor elementum
                                dapibus, porta sed
                                ante.</p>
                            <i class="position-absolute" style="top: 0; right: 0;">2022-02-25 11:45
                                AM</i>
                        </div>
                    </div>
                    <div class="chat-avatar">
                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                             alt="James Z"
                             class="rounded">
                    </div>
                </div>
                <div class="youtube-item card mt-2 mb-2 border-0 text-left d-inline-block w-100">
                    <div class="file-item-wrapper p-0">
                        <div class="row align-items-center">
                            <div class="col-auto w-100">
                                <%-- 1. The <iframe> (and video player) will replace this <div> tag. --%>
                                <div class="player" data-id="${chat.video.id}"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
}

/**
 * @dates 2022.02.25
 * @author sangwoo
 * @description text message html
 * @param username
 * @param role
 * @param message
 * @param timestamp
 * @param self true: 자신의 매세지, false: 타인의 메세지
 * @returns {string} html
 */
function getTextMessage({username, role, message, timestamp, self}) {
    const text_position = self ? 'odd' : '';
    return `<li class="chat-item ${text_position} clearfix pb-2 pt-2">
                <div class="chat-item-wrapper d-flex">
                    <div class="mr-2 chat-content">
                        <div class="text-wrap position-relative">
                            <i class="font-weight-bold">${username} <span class="text-primary">[${role}]</span></i>
                            <p>${message}</p>
                            <i class="position-absolute" style="top: 0; right: 0;">${timestamp}</i>
                        </div>
                    </div>
                    <div class="chat-avatar">
                         <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg" alt="${username}" class="rounded">  <!-- todo: user profile image -->
                    </div>
                </div>
            </li>`;
}

/**
 * @dates 2022.02.25
 * @author sangwoo
 * @description file message html
 * @param username
 * @param role
 * @param message
 * @param timestamp
 * @param file : object {type, name, size}
 * @param self true: 자신의 매세지, false: 타인의 메세지
 * @param temp 파일 업로드 대기를 위한 임시 파일인지 아닌지
 * @returns {string} html
 */
function getFileMessage({username, role, message, timestamp, file, self, temp= false}) {
    const text_position = self ? 'odd' : '';
    const temp_id = temp ? 'id="temp"' : '';
    return `<li class="chat-item ${text_position} clearfix pb-2 pt-2" ${temp_id}>
                <div class="chat-item-wrapper d-flex">
                    <div class="mr-2 chat-content">
                        <div class="text-wrap position-relative">
                            <i class="font-weight-bold">${username} <span class="text-primary">[${role}]</span></i>
                            <i class="position-absolute" style="top: 0; right: 0;">${timestamp}</i>
                        </div>
                    </div>
                    <div class="chat-avatar">
                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg" alt="${username}" class="rounded">  <!-- todo: user profile image -->
                    </div>
                </div>
                <div class="file-item card mt-2 mb-2 border text-left d-inline-block">
                    <div class="file-item-wrapper p-2">
                        <div class="row align-items-center">
                            <div class="col-auto">
                                <div class="type">
                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">${file.type}</span>
                                </div>
                            </div>
                            <div class="col ps-0">
                                <a href="${file.url}" target="_blank"
                                   class="text-muted fw-bold">${file.name}</a>
                                <p class="mb-0">${parseFileSize(file.size)}</p>
                            </div>
                            <div class="col-auto">
                                <a href="javascript:void(0);"
                                   class="btn btn-link btn-lg text-muted">
                                    <i class="fa-solid fa-file-arrow-down"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>`;
}