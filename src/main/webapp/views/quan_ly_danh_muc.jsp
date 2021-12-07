<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="resource/js/model/fetch_model_category.js"></script>
<script src="resource/js/pages/page_quan_ly_danh_muc.js"></script>
<main>

    <!-- Modal -->
    <div class="modal fade" id="add-or-update-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Chi tiết danh mục sản phẩm</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                <div class="form-group">
                                    <label>Tên danh mục</label>
                                    <input type="text" class="form-control" id="text-ten"  placeholder="Nhập tên danh mục...">
                                    <div class="invalid-feedback">
                                        Please choose a username.
                                    </div>
                                </div>
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
    <div class="modal fade" id="delete-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Xác nhận thao tác</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                Bạn có chắc chắn xóa danh mục này không?
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
    <!-- End Modal   -->

    <div class="title-page mt-4">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h1>Quản Lý Danh Mục Sản Phẩm</h1>
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
                <div class="col-md-10">
                    <button type="button" class="btn btn-primary" id="btn-them"><i class="fas fa-plus"></i> Thêm danh mục</button>
                </div>
                <div class="col-md-2">
                    <select class="form-control" id="select-sap-xep">
                        <option value="default">Sắp xếp</option>
                        <option value="AZ">A->Z</option>
                        <option value="ZA">Z->A</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="table-data">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="table-responsive">
                        <table class="table table-bordered table-hover" id="table-data">
                            <thead>
                            <tr>
                                <th scope="col">STT</th>
                                <th scope="col">Tên Danh Mục</th>
                                <th scope="col">Hành Động</th>
                            </tr>
                            <tr>
                                <th scope="row"></th>
                                <td><input type="text"  id="text-search-ten" class="form-control"></td>
                                <td class="text-center">
                                    <button type="button" class="btn btn-primary" id="btn-tim-kiem"><i class="fas fa-search"></i> Tìm kiếm</button>
                                </td>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- site dynamic data  -->
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
