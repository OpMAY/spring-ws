<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
          integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">

    <!--Chat CSS-->
    <link rel="stylesheet" href="../../resources/css/chat/chat.css">
    <title>Chat Module</title>

</head>
<body>
<%-- Container--%>
<div class="container-fluid">
    <%-- Row --%>
    <div class="row pt-3">
        <%-- Chat user list sidebar--%>
        <div class="col-xl-3 col-lg-4">
            <%-- Card --%>
            <div class="card">
                <%-- Card-body --%>
                <div class="card-body">
                    <%-- User Info--%>
                    <div class="d-flex align-items-start mb-3">
                        <img src="../../resources/assets/images/users/user-1.jpg" height="42" alt="Brandon Smith"
                             class="mr-2 rounded-circle">
                        <div class="w-100">
                            <h5 class="mt-0 mb-0 font-15">
                                <a href="#" class="text-reset">Lorem ipsum</a>
                            </h5>
                            <p class="mt-1 mb-0 text-muted font-14">
                                <small class="mdi mdi-circle text-success"></small>Online
                            </p>
                        </div>
                        <a href="javascript: void(0);" class="text-reset font-20">
                            <i class="mdi mdi-cog-outline"></i>
                        </a>
                    </div>
                    <%-- /User Info--%>

                    <%-- Search box --%>
                    <form class="search-bar mb-3">
                        <div class="position-relative">
                            <input type="text" class="form-control form-control-light"
                                   placeholder="People, groups &amp; messages...">
                            <span class="mdi mdi-magnify"></span>
                        </div>
                    </form>
                    <%-- /Search box --%>

                    <%-- Group Chat --%>
                    <h6 class="text-muted text-uppercase">Group Chat</h6>
                    <div class="p-2">
                        <a href="javascript: void(0);" class="text-reset mb-2 d-block">
                            <i class="mdi mdi-checkbox-blank-circle-outline mr-1 text-success"></i>
                            <span class="mb-0 mt-1">putate mauris, quis</span>
                        </a>

                        <a href="javascript: void(0);" class="text-reset mb-2 d-block">
                            <i class="mdi mdi-checkbox-blank-circle-outline mr-1 text-warning"></i>
                            <span class="mb-0 mt-1">n sagittis fa</span>
                        </a>
                    </div>
                    <%-- /Group Chat --%>

                    <h6 class="text-muted text-uppercase mb-2">Contacts</h6>

                    <%-- User List , Row --%>
                    <div class="row">
                        <%-- Col --%>
                        <div class="col">
                            <%-- Scroll, Chat-user-list --%>
                            <div class="chat-user-list">
                                <div class="chat-user-list-wrapper"
                                     style="max-height: 375px; height: auto; overflow:scroll;overflow-x: hidden;">
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-2.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    augue eu
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-1.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    laoreet orci fe
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-3.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    s pulvinar risus
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-4.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    ommodo mi
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-5.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    dictum velit lectus
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-6.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    sit amet
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                    <a href="javascript:void(0);" class="text-body">
                                        <div class="d-flex align-items-start p-2">
                                            <img src="../../resources/assets/images/users/user-7.jpg"
                                                 height="42"
                                                 alt="Brandon Smith"
                                                 class="mr-2 rounded-circle">
                                            <div class="w-100">
                                                <h6 class="mt-0 mb-0 font-weight-bold">
                                                    <span class="h6 text-muted font-weight-normal">4:30am</span>
                                                    ultrices
                                                </h6>
                                                <p class="h6 mt-1 mb-0 text-muted">
                                                                    <span class="w-25 text-end"><span
                                                                            class="badge badge-soft-danger">3</span></span>
                                                    <span class="small w-75">How are you today?</span>
                                                </p>
                                            </div>
                                        </div>
                                    </a>
                                </div>
                            </div>
                            <%-- /Scroll, /Chat-user-list --%>
                        </div>
                        <%-- /col --%>
                    </div>
                    <%-- /User List , /Row --%>
                </div>
                <%-- /Card-body--%>
            </div>
            <%-- /Card--%>
        </div>
        <%-- /Chat user list sidebar--%>

        <%-- Chat area --%>
        <div class="col-xl-9 col-lg-8">
            <%-- Card --%>
            <div class="card">
                <%-- Chat Content Header, Card-body--%>
                <div class="card-body py-2 px-3 border-bottom border-light">
                    <div class="row justify-content-between py-1">
                        <div class="col-sm-7">
                            <div class="d-flex align-items-start">
                                <img src="../../resources/assets/images/users/user-5.jpg" height="36"
                                     alt="Brandon Smith"
                                     class="mr-2 rounded-circle">
                                <div>
                                    <h5 class="mt-0 mb-0">
                                        <a href="#" class="text-reset">Lorem</a>
                                    </h5>
                                    <p class="mt-1 mb-0 text-muted small">
                                        <small class="mdi mdi-circle text-success"></small>Online
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%-- Chat Content Header--%>
                <%-- Chat Content --%>
                <div class="card-body">
                    <%-- Chat List --%>
                    <div class="chat-list">
                        <ul class="chat-list-wrapper"
                            style="padding: 0; padding-right: 15px; list-style: none; max-height: 542px; height: auto; overflow:scroll;overflow-x: hidden;">
                            <li class="chat-item clearfix pb-2 pt-2">
                                <div class="chat-item-wrapper d-inline-flex">
                                    <div class="chat-avatar">
                                        <img width="60" height="60" src="../../resources/assets/images/users/user-5.jpg"
                                             alt="James Z"
                                             class="rounded">
                                    </div>
                                    <div class="ml-2 chat-content">
                                        <div class="text-wrap position-relative">
                                            <i class="font-weight-bold">adipiscing elit <span class="text-primary">[Video Maker]</span></i>
                                            <p style="min-width: 500px;">Vivamus luctus mauris nulla, vel maximus quam
                                                pulvinar quis. Praesent a
                                                velit id diam molestie consequat at eget nulla. Nulla sed rhoncus
                                                libero, ac finibus erat. Nunc libero eros, eleifend non fringilla
                                                congue, pulvinar mollis odio. Sed porta diam mattis libero feugiat, eu
                                                finibus mauris tristique. Nunc vitae imperdiet urna. Proin tempor, quam
                                                at eleifend scelerisque, dolor ante condimentum velit, et ultrices leo
                                                orci quis nisi. Donec hendrerit ex non gravida vestibulum. Praesent
                                                laoreet orci felis, dignissim ultrices tellus porta posuere. Nullam quis
                                                fermentum lacus, quis cursus diam. Cras id vulputate mauris, quis
                                                pulvinar risus. Donec tortor augue, mattis porttitor elementum dapibus,
                                                porta sed ante.</p>
                                            <i class="position-absolute" style="top: 0; right: 0;">2022-02-25 11:45
                                                AM</i>
                                        </div>
                                    </div>
                                </div>
                                <div class="file-item card mt-2 mb-2 border text-left d-inline-block"
                                     style="margin-left: 68px;">
                                    <div class="file-item-wrapper p-2">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <div class="type">
                                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">ZIP</span>
                                                </div>
                                            </div>
                                            <div class="col ps-0">
                                                <a href="javascript:void(0);"
                                                   class="text-muted fw-bold">Lorem.zip</a>
                                                <p class="mb-0">2.3 MB</p>
                                            </div>
                                            <div class="col-auto">
                                                <%-- Button --%>
                                                <a href="javascript:void(0);"
                                                   class="btn btn-link btn-lg text-muted">
                                                    <i class="fa-solid fa-file-arrow-down"></i>
                                                </a>
                                                <%-- /Button --%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="chat-item clearfix pb-2 pt-2">
                                <div class="chat-item-wrapper d-flex">
                                    <div class="chat-avatar">
                                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                                             alt="James Z"
                                             class="rounded">
                                    </div>
                                    <div class="ml-2 chat-content">
                                        <div class="text-wrap position-relative">
                                            <i class="font-weight-bold">Lorem ipsum. <span class="text-warning">[Director]</span></i>
                                            <p style="min-width: 500px;">Donec tortor augue, mattis porttitor elementum
                                                dapibus, porta sed
                                                ante.</p>
                                            <i class="position-absolute" style="top: 0; right: 0;">2022-02-25 11:45
                                                AM</i>
                                        </div>
                                    </div>
                                </div>
                                <div class="file-item card mt-2 mb-2 border text-left d-inline-block"
                                     style="margin-left: 68px;">
                                    <div class="file-item-wrapper p-2">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <div class="type">
                                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">ZIP</span>
                                                </div>
                                            </div>
                                            <div class="col ps-0">
                                                <a href="javascript:void(0);"
                                                   class="text-muted fw-bold">Lorem Ipsum.zip</a>
                                                <p class="mb-0">2.3 MB</p>
                                            </div>
                                            <div class="col-auto">
                                                <%-- Button --%>
                                                <a href="javascript:void(0);"
                                                   class="btn btn-link btn-lg text-muted">
                                                    <i class="fa-solid fa-file-arrow-down"></i>
                                                </a>
                                                <%-- /Button --%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="chat-item clearfix my-4 d-flex">
                                <hr class="w-50">
                                <span class="mt-auto mb-auto ml-2 mr-2" style="white-space: nowrap;">2022-02-25</span>
                                <hr class="w-50">
                            </li>
                            <li class="chat-item odd clearfix pb-2 pt-2">
                                <div class="chat-item-wrapper d-flex">
                                    <div class="mr-2 chat-content">
                                        <div class="text-wrap position-relative">
                                            <i class="font-weight-bold">now use Lorem <span class="text-danger">[Video Planner]</span></i>
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
                                <div class="file-item card mt-2 mb-2 border text-left d-inline-block">
                                    <div class="file-item-wrapper p-2">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <div class="type">
                                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">ZIP</span>
                                                </div>
                                            </div>
                                            <div class="col ps-0">
                                                <a href="javascript:void(0);"
                                                   class="text-muted fw-bold">Lorem ipsum dolor.zip</a>
                                                <p class="mb-0">2.3 MB</p>
                                            </div>
                                            <div class="col-auto">
                                                <%-- Button --%>
                                                <a href="javascript:void(0);"
                                                   class="btn btn-link btn-lg text-muted">
                                                    <i class="fa-solid fa-file-arrow-down"></i>
                                                </a>
                                                <%-- /Button --%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="chat-item odd clearfix pb-2 pt-2">
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
                                <div class="file-item card mt-2 mb-2 border text-left d-inline-block">
                                    <div class="file-item-wrapper p-2">
                                        <div class="row align-items-center">
                                            <div class="col-auto">
                                                <div class="type">
                                                    <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">ZIP</span>
                                                </div>
                                            </div>
                                            <div class="col ps-0">
                                                <a href="javascript:void(0);"
                                                   class="text-muted fw-bold">Lorem Ipsum.zip</a>
                                                <p class="mb-0">2.3 MB</p>
                                            </div>
                                            <div class="col-auto">
                                                <%-- Button --%>
                                                <a href="javascript:void(0);"
                                                   class="btn btn-link btn-lg text-muted">
                                                    <i class="fa-solid fa-file-arrow-down"></i>
                                                </a>
                                                <%-- /Button --%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="chat-item odd clearfix pb-2 pt-2">
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
                                                <div class="player"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                            <li class="chat-item clearfix pb-2 pt-2">
                                <div class="chat-item-wrapper d-flex">
                                    <div class="chat-avatar">
                                        <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                                             alt="James Z"
                                             class="rounded">
                                    </div>
                                    <div class="ml-2 chat-content">
                                        <div class="text-wrap position-relative">
                                            <i class="font-weight-bold">Lorem ipsum. <span class="text-warning">[Director]</span></i>
                                            <p style="min-width: 500px;">Donec tortor augue, mattis porttitor elementum
                                                dapibus, porta sed
                                                ante.</p>
                                            <i class="position-absolute" style="top: 0; right: 0;">2022-02-25 11:45
                                                AM</i>
                                        </div>
                                    </div>
                                </div>
                                <div class="youtube-item card mt-2 mb-2 border-0 text-left d-inline-block w-100">
                                    <div class="file-item-wrapper p-0">
                                        <div class="row align-items-center">
                                            <div class="col-auto w-100">
                                                <%-- 1. The <iframe> (and video player) will replace this <div> tag. --%>
                                                <div class="player"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                        <%-- Textarea Container --%>
                        <div>
                            <div class="d-flex">
                                <div class="form-group mb-2 w-100">
                                    <label for="chat-keyboard" class="font-italic">Lorem ipsum dolor sit
                                        amet.</label>
                                    <textarea rows="3" id="chat-keyboard" class="form-control"></textarea>
                                </div>
                                <button type="submit" class="btn btn-primary my-auto ml-3">Submit</button>
                            </div>
                            <div class="mt-2">
                                <i data-type="general" class="icon-file fa-solid fa-file-arrow-up fa-2x mr-2 point"></i>
                                <i data-type="video" class="icon-file fa-solid fa-file-video fa-2x mr-2 point"></i>
                                <i data-type="audio"
                                   class="icon-file audio fa-solid fa-file-audio fa-2x mr-2 point"></i>
                                <i data-type="youtube"
                                   class="youtube fa-brands fa-youtube fa-2x mr-2 point position-relative">
                                    <div class="link-input-wrapper border p-2 rounded bg-white point-static"
                                         style="display:none; position:absolute; top: 50%; left: 140%; transform: translate(0,-50%);">
                                        <div class="form-group mb-0 d-flex">
                                            <label for="youtube-link-input" class="h6 text-nowrap mb-0 my-auto">Link
                                                : </label>
                                            <input type="url"
                                                   placeholder="https://www.youtube.com/watch?v=ETcVO6lBgkQ"
                                                   id="youtube-link-input" class="form-control border-0 pl-0 pr-0 ml-2"
                                                   style="min-width: 320px; max-height: 20px;">
                                        </div>
                                    </div>
                                </i>
                            </div>
                            <%-- Reference File Container--%>
                            <div class="ref-file-container d-none">
                            </div>
                            <%-- /Reference File Container--%>
                        </div>
                        <%-- /Textarea Container --%>
                    </div>
                    <%-- /Chat List --%>
                </div>
                <%--  Chat Content, /Card-body --%>
            </div>
            <%-- /Card --%>
        </div>
        <%-- /Chat area--%>

    </div>
    <%-- /row --%>
</div>
<%--/Container--%>

<%----%>
<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
        crossorigin="anonymous"></script>

<!-- custom chat-->
<script src="../../resources/js/chat/chat.js"></script>
<%--<script src="/webjars/sockjs-client/sockjs.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.2/sockjs.min.js"></script>--%>
<script src="../../resources/js/stomp.js"></script>
<script src="../../resources/js/sockjs.js"></script>

<!-- font-awesome -->
<script src="https://kit.fontawesome.com/d529fb2138.js" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2" crossorigin="anonymous"></script>
-->
<script>
    let client = null;

    /** Document Ready*/
    $(document).ready(function () {
        /** Textarea Keyboard Shortcut Save */
        let textarea = document.getElementsByTagName('textarea')[0];
        textarea.addEventListener('keydown', e => {
            if (e.ctrlKey && e.key === 's') {
                // Prevent the Save dialog to open
                e.preventDefault();
                // Place your code here
                console.log('CTRL + S');
                /*TODO Send to message by keyboard short cut*/
            }
        });
        /** Textarea Keyboard Shortcut Save End*/

        /** Add File Upload Listener*/
        let icon_files = document.querySelectorAll('.icon-file');
        icon_files.forEach((e) => {
            e.addEventListener('click', fileUpload);
        });
        let youtube = document.querySelector('.youtube');
        let youtube_link = document.querySelector('.link-input-wrapper');
        youtube_link.addEventListener('click', (e) => {
            e.stopPropagation();
        });
        let youtube_link_input = document.querySelector('#youtube-link-input');
        $(youtube_link_input).bind('change keyup input', (e) => {
            if (e.keyCode == 13) {
                console.log('input youtube link');
                /**chat youtube item create*/
                let container = $('.chat-list-wrapper');
                let chat = {
                    username: 'Kimwoosik',
                    message: 'Donec tortor augue, mattis porttitor elementum dapibus, porta sed ante.',
                    timestamp: '2022-02-25 11:45 AM',
                    role: 'Developer',
                    video: {
                        id: 'w3nSaIP5it0'
                    },
                }
                container.append(appendChatYoutubeLeftItem(chat));
                container.append(appendChatYoutubeRightItem(chat));
                console.log(e);
                $(e.currentTarget).val('');
                youtubeAppendInit();
                youtube.click();
            }
        });
        youtube.addEventListener('click', (e) => {
            $('.link-input-wrapper').toggle(100, 'linear', (e) => {
                console.log('toggle after');
            });
        });
        /** Add File Upload Listener End*/
        connect();
    });

    function connect() {
        // 웹소켓 주소
        let wsUri = "/stomp";
        console.log('connecting to ', wsUri);
        client = Stomp.over(new SockJS(wsUri));

        client.connect({}, () => {
            console.log('connect stomp');
            client.subscribe('/topic/message', (event) => {
                console.log(event);
                console.log('message: ', JSON.parse(event.body).message);
            });
        });
    }

    function disconnect() {
        if (client !== null) {
            client.disconnect();
            client = null;
        }
        console.log("Disconnected");
    }

    function sendMessage() {
        if (client === null) {
            alert('연결되지 않음');
            return;
        }
        const message = document.querySelector('#message').value;
        const data = {message};
        client.send('/test', {}, JSON.stringify(data));
    }

    function fileUpload() {
        let container = $('.ref-file-container');
        let uuid = getUUID();
        container.append(`<input data-uuid="\${uuid}" data-type="\${this.dataset.type}" type="file" name="file">`);
        let input = document.querySelector(`[data-uuid="\${uuid}"]`);
        input.addEventListener("change", e => {
            readImage(e.target);
        });
        input.click();
    }

    function readImage(input) {
        let container = $('.chat-list-wrapper');
        // 인풋 태그에 파일이 있는 경우
        if (input.files && input.files[0]) {
            // 이미지 파일인지 검사 (생략)
            // FileReader 인스턴스 생성
            const reader = new FileReader()
            // 이미지가 로드가 된 경우
            reader.onload = e => {
                console.log('readImage onload');
            }
            reader.onloadend = e => {
                console.log(e);
                /**chat file item create*/
                let chat = {
                    username: 'Kimwoosik',
                    message: 'Donec tortor augue, mattis porttitor elementum dapibus, porta sed ante.',
                    timestamp: '2022-02-25 11:45 AM',
                    role: 'Developer',
                    file: {
                        type: 'ZIP',
                        name: 'file.zip',
                        size: '2.5 MB'
                    }
                }
                container.append(appendChatRightItem(chat));
                container.append(appendChatLeftItem(chat));
            }
            // reader가 이미지 읽도록 하기
            reader.readAsDataURL(input.files[0])
        }
    }

    /**
     * @dates 2022.02.25
     * @author kimwoosik
     * @description UUID Creator
     * @return {uuid} 16자의 uuid create
     */
    function getUUID() { // UUID v4 generator in JavaScript (RFC4122 compliant)
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
            let r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 3 | 8);
            return v.toString(16);
        });
    }

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
                                <i class="font-weight-bold">\${chat.username} <span class="text-primary">[\${chat.role}]</span></i>
                                <p>\${chat.message}</p>
                                <i class="position-absolute" style="top: 0; right: 0;">\${chat.timestamp}</i>
                            </div>
                        </div>
                        <div class="chat-avatar">
                            <img width="60" height="60" src="../../resources/assets/images/users/user-2.jpg"
                                 alt="\${chat.username}"
                                 class="rounded">
                        </div>
                    </div>
                    <div class="file-item card mt-2 mb-2 border text-left d-inline-block">
                        <div class="file-item-wrapper p-2">
                            <div class="row align-items-center">
                                <div class="col-auto">
                                    <div class="type">
                                        <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">\${chat.file.type}</span>
                                    </div>
                                </div>
                                <div class="col ps-0">
                                    <a href="javascript:void(0);"
                                       class="text-muted fw-bold">\${chat.file.name}</a>
                                    <p class="mb-0">\${chat.file.size}</p>
                                </div>
                                <div class="col-auto">
                                    <%-- Button --%>
                                    <a href="javascript:void(0);"
                                       class="btn btn-link btn-lg text-muted">
                                        <i class="fa-solid fa-file-arrow-down"></i>
                                    </a>
                                    <%-- /Button --%>
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
                                 alt="\${chat.username}"
                                 class="rounded">
                        </div>
                        <div class="chat-content ml-2">
                            <div class="text-wrap position-relative">
                                <i class="font-weight-bold">\${chat.username} <span class="text-primary">[\${chat.role}]</span></i>
                                <p>\${chat.message}</p>
                                <i class="position-absolute" style="top: 0; right: 0;">\${chat.timestamp}</i>
                            </div>
                        </div>
                    </div>
                    <div class="file-item card mt-2 mb-2 border text-left d-inline-block"
                         style="margin-left: 68px;">
                        <div class="file-item-wrapper p-2">
                            <div class="row align-items-center">
                                <div class="col-auto">
                                    <div class="type">
                                        <span class="avatar-title bg-secondary rounded text-white p-1 text-uppercase">\${chat.file.type}</span>
                                    </div>
                                </div>
                                <div class="col ps-0">
                                    <a href="javascript:void(0);"
                                       class="text-muted fw-bold">\${chat.file.name}</a>
                                    <p class="mb-0">\${chat.file.size}</p>
                                </div>
                                <div class="col-auto">
                                    <%-- Button --%>
                                    <a href="javascript:void(0);"
                                       class="btn btn-link btn-lg text-muted">
                                        <i class="fa-solid fa-file-arrow-down"></i>
                                    </a>
                                    <%-- /Button --%>
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
                                 alt="\${chat.username}"
                                 class="rounded">
                        </div>
                        <div class="chat-content ml-2">
                            <div class="text-wrap position-relative">
                                <i class="font-weight-bold">\${chat.username} <span class="text-primary">[\${chat.role}]</span></i>
                                <p>\${chat.message}</p>
                                <i class="position-absolute" style="top: 0; right: 0;">\${chat.timestamp}</i>
                            </div>
                        </div>
                    </div>
                    <div class="youtube-item card mt-2 mb-2 border-0 text-left d-inline-block w-100">
                        <div class="file-item-wrapper p-0">
                            <div class="row align-items-center">
                                <div class="col-auto w-100">
                                    <%-- 1. The <iframe> (and video player) will replace this <div> tag. --%>
                                    <div class="player" data-id="\${chat.video.id}"></div>
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
                                    <div class="player" data-id="\${chat.video.id}"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>`;
    }

    /**
     * @dates 2022.02.25
     * @author kimwoosik
     * @description if your chat video item append then init this function
     */
    function youtubeAppendInit() {
        let players = $('.player').not('.player[id]');
        players.each((i, e) => {
            $(e).attr('id', 'player-' + getUUID());
            let avatar_width = e.parentElement.parentElement.parentElement.parentElement.parentElement
                .querySelector('.chat-item-wrapper .chat-avatar').offsetWidth;
            let content_width = e.parentElement.parentElement.parentElement.parentElement.parentElement
                .querySelector('.chat-item-wrapper .chat-content').offsetWidth;
            let player = e;
            player = new YT.Player(player.id, {
                height: '360',
                width: avatar_width + content_width + 8,
                videoId: 'w3nSaIP5it0',
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
            });
            player_elems.push(player);
        });
    }
</script>
<script>
    /** Youtube Video Frame API*/
    // 2. This code loads the IFrame Player API code asynchronously.
    let tag = document.createElement('script');

    tag.src = "https://www.youtube.com/iframe_api";
    let firstScriptTag = document.getElementsByTagName('script')[0];
    firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

    // 3. This function creates an <iframe> (and YouTube player)
    //    after the API code downloads.
    let players = $('.player');
    let player_elems = new Array();

    function onYouTubeIframeAPIReady() {
        players.each((i, e) => {
            $(e).attr('id', 'player-' + getUUID());
            let avatar_width = e.parentElement.parentElement.parentElement.parentElement.parentElement
                .querySelector('.chat-item-wrapper .chat-avatar').offsetWidth;
            let content_width = e.parentElement.parentElement.parentElement.parentElement.parentElement
                .querySelector('.chat-item-wrapper .chat-content').offsetWidth;
            //console.log(avatar_width + content_width + 8);
            let player = e;
            player = new YT.Player(player.id, {
                height: '360',
                width: avatar_width + content_width + 8,
                videoId: 'w3nSaIP5it0',
                events: {
                    'onReady': onPlayerReady,
                    'onStateChange': onPlayerStateChange
                }
            });
            player_elems.push(player);
        });
    }

    // 4. The API will call this function when the video player is ready.
    function onPlayerReady(event) {
        //event.target.playVideo();
    }

    // 5. The API calls this function when the player's state changes.
    //    The function indicates that when playing a video (state=1),
    //    the player should play for six seconds and then stop.
    let done = false;

    function onPlayerStateChange(event) {
        let frame = event.target.h;
        if (event.data == YT.PlayerState.PLAYING && !done) {
            setTimeout(function () {
                frame.stopVideo();
            }, 6000);
            done = true;
        }
    }
</script>
</body>
</html>
