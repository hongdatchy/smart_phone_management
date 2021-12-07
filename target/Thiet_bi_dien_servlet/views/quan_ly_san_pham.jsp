<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resource/js/model/fetch_model_product.js"></script>
<script src="resource/js/pages/page_quan_ly_san_pham.js"></script>
<main>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Chi tiết sản phẩm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body form-custom">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Tên</label>
                                <input type="text" class="form-control" id="text-ten" placeholder="Nhập tên">
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Loại sản phẩm</label>
                                <select id="select-danh-muc" class="form-control">
                                    <option>Điện thoại</option>
                                    <option value="2">Cáp sạc</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Giá</label>
                                <input type="number" class="form-control" id="number-gia"placeholder="Nhập giá" min="0">
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Đã bán</label>
                                <input type="number" class="form-control" id="number-da-ban"placeholder="Nhập số lượng" min="0">
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Bảo hành</label>
                                <input type="number" class="form-control" id="number-bao-hanh"placeholder="Nhập thời lượng" min="0">
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Khuyến mãi</label>
                                <input type="number" class="form-control" id="number-khuyen-mai"placeholder="Nhập khuyến mãi" min="0">
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Ảnh</label>
                                <input type="file" class="form-control-file" id="file-anh">
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Ngày tạo</label>
                                <input type="date" class="form-control" id="date-ngay-tao" disabled>
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Giới thiệu</label>
                                <textarea class="form-control" id="textarea-gioi-thieu"
                                          rows="3"></textarea>
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>Thông số</label>
                                <textarea class="form-control" id="textarea-thong-so"
                                          rows="3"></textarea>
                                <div class="invalid-feedback">
                                    Error!
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="checkbox-het-hang">
                                <label class="form-check-label" for="checkbox-het-hang">
                                    Hết hàng
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-success" id="btn-luu-lai">Lưu</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade" id="exampleModal1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" >Xác nhận thao tác</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                Bạn có chắc chắn xóa sản phẩm này không?
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                    <button type="button" class="btn btn-danger" id="btn-xac-nhan-xoa">Xóa</button>
                </div>
            </div>
        </div>
    </div>
    <!-- End Modal-->

    <div class="title-page mt-4">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h1>Quản Lý Sản Phẩm</h1>
                </div>
                <div class="col-12">
                    <hr>
                </div>
            </div>
        </div>
    </div>

    <div class="tool-page">
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <button type="button" class="btn btn-primary" id="btn-them"><i class="fas fa-plus"></i> Thêm sản phẩm</button>
                </div>
                <div class="col-md-2 mt-1">
                    <select class="form-control" id="select-search-danh-muc">
                        <option>Loại sản phẩm</option>
                        <option value="2">Bút</option>
                        <option value="3">Dây điện</option>
                    </select>
                </div>
                <div class="col-md-2 mt-1">
                    <select class="form-control" id="select-search-sap-xep">
                        <option value="id">Sắp xếp</option>
                        <option value="name">Theo tên</option>
                        <option value="price">Theo giá</option>
                        <option value="create_date">Ngày tạo</option>
                        <option value="bought">Số lượng đã bán</option>
                        <option value="guarantee">Theo bảo hành</option>
                        <option value="promotion">khuyến mại</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="table-data">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <div class="table-reponsive">
                        <table class="table table-hover table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col" style="width: 100px;">Ảnh</th>
                                <th scope="col">Tên</th>
                                <th scope="col">Giá</th>
                                <th scope="col">Đã Bán</th>
                                <th scope="col">Giới thiệu</th>
                                <th scope="col">Thông số kĩ thuật</th>
                                <th scope="col">Khuyễn mại</th>
                                <th scope="col">Thời gian bảo hành</th>
                                <th scope="col">Ngày Tạo</th>
                                <th scope="col" style="width: 150px;">Còn Hàng</th>
                                <th scope="col" style="width: 175px;">Hành Động</th>
                            </tr>
                            <tr>
                                <th scope="row"></th>
                                <td></td>
                                <td><input type="text" class="form-control" id="text-search-ten"></td>
                                <td><input type="number" class="form-control" id="number-search-gia" min="0" ></td>
                                <td><input type="number" class="form-control" id="number-search-da-ban" min="0" ></td>
                                <td></td>
                                <td></td>
                                <td><input type="number" class="form-control" id="number-search-khuyen-mai" min="0" ></td>
                                <td><input type="number" class="form-control" id="number-search-bao-hanh" min="0" ></td>
                                <td>
                                    <input type="date" class="form-control" id="date-search-ngay-tao">
                                </td>
                                <td>
                                    <select id="select-search-het-hang" class="form-control">
                                        <option value="all">Tất cả</option>
                                        <option value="false">Còn hàng</option>
                                        <option value="true">Hết hàng</option>
                                    </select>
                                </td>
                                <td>
                                    <button type="button" class="btn btn-primary" id="btn-tim-kiem"><i class="fas fa-search"></i> Tìm kiếm</button>
                                </td>
                            </tr>
                            </thead>
                            <tbody id="table-data">
                            <tr>
                                <th scope="row">1</th>
                                <td><img src="https://cdn.cellphones.com.vn/media/catalog/product/cache/7/thumbnail/300x/9df78eab33525d08d6e5fb8d27136e95/i/p/iphone11-purple-select-2019.png"
                                         alt="" width="80px"></td>
                                <td>Otto</td>
                                <td>Otto</td>
                                <td>Otto</td>
                                <td>Otto</td>
                                <td class="text-center"><span class="badge badge-success">Còn hàng</span></td>
                                <td>
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModal"><i class="fas fa-pen"></i>
                                        Sửa</button>
                                    <button type="button" class="btn btn-danger" data-toggle="modal"
                                            data-target="#exampleModal1"><i class="fas fa-trash-alt"></i>
                                        Xóa</button>
                                </td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td><img src="https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-green-600x600.jpg"
                                         alt="" width="80px"></td>
                                <td>Thornton</td>
                                <td>Thornton</td>
                                <td>Thornton</td>
                                <td>Thornton</td>
                                <td class="text-center"><span class="badge badge-danger">Hết hàng</span></td>
                                <td>
                                    <button type="button" class="btn btn-warning"><i class="fas fa-pen"></i>
                                        Sửa</button>
                                    <button type="button" class="btn btn-danger"><i class="fas fa-trash-alt"></i>
                                        Xóa</button>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
