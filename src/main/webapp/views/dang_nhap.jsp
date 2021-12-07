<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="resource/css/login.css">
<script src="resource/js/model/fetch_model_admin.js"></script>
<script src="resource/js/pages/page_dang_nhap.js"></script>

<div class="alert alert-warning alert-dismissible fade show d-none" role="alert">
    bạn chưa đăng nhập hoặc phiên làm việc đã hết hạn
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="main-content page-login">
    <div class="container">
        <div class="row justify-content-center align-items-center">
            <div class="col-6 col-md-4">
                <div class="card">
                    <div class="card-body">
                        <div class="card-title text-center">
                            <img src="resource/file/icon-login.png"
                                 width="60" class="d-inline-block align-top" alt="">
                            <h3>Đăng nhập</h3>
                        </div>
                        <div class="form-login">
                            <div class="col-12">
                                <div class="form-group">
                                    <label>Tài khoản</label>
                                    <input type="text" class="form-control" id="username" placeholder="Nhập tài khoản">
                                    <div class="invalid-feedback">
                                        Error!
                                    </div>
                                </div>
                            </div>
                            <div class="col-12">
                                <div class="form-group">
                                    <label>Mật khẩu</label>
                                    <input type="password" class="form-control" id="password" placeholder="Nhập mật khẩu">
                                    <div class="invalid-feedback">
                                        Error!
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 text-center">
                                <button type="button" class="btn btn-secondary" id="btn-login">Đăng nhập</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
