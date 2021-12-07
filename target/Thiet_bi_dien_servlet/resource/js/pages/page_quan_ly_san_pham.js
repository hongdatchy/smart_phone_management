let selectSearchDanhMuc, selectSearchSapXep, textSearchTen, numberSearchGia, numberSearchDaBan,numberSearchKhuyenMai, numberSearchBaoHanh, dateSearchNgayTao, selectSearchHetHang, btnTimKiem, tableDuLieu, textTen, selectDanhMuc, numberGia, numberDaBan, numberKhuyenMai,numberBaoHanh , fileAnh, dateNgayTao, textareaGioiThieu, textareaThongso, checkboxHetHang, btnLuuLai, btnXacNhanXoa, btnThem;
let indexProduct , elementProduct, checkAction;
let listProduct = [], listCategory = [];
let token;


$(function(){
    token = localStorage.getItem('token');
    checkTokenIsValidate(token)
    selectSearchDanhMuc = $("#select-search-danh-muc");
    selectSearchSapXep = $("#select-search-sap-xep");
    textSearchTen = $("#text-search-ten");
    numberSearchGia = $("#number-search-gia");
    numberSearchDaBan = $("#number-search-da-ban");
    numberSearchKhuyenMai = $("#number-search-khuyen-mai");
    numberSearchBaoHanh = $("#number-search-bao-hanh");
    dateSearchNgayTao = $("#date-search-ngay-tao");
    selectSearchHetHang = $("#select-search-het-hang");
    btnTimKiem = $("#btn-tim-kiem");
    tableDuLieu = $("#table-data");
    textTen = $("#text-ten");
    selectDanhMuc = $("#select-danh-muc");
    numberGia = $("#number-gia");
    numberDaBan = $("#number-da-ban");
    numberKhuyenMai = $("#number-khuyen-mai");
    fileAnh = $("#file-anh");
    dateNgayTao = $("#date-ngay-tao");
    textareaGioiThieu = $("#textarea-gioi-thieu");
    textareaThongso = $("#textarea-thong-so");
    checkboxHetHang = $("#checkbox-het-hang");
    btnLuuLai = $("#btn-luu-lai");
    btnXacNhanXoa = $("#btn-xac-nhan-xoa");
    numberBaoHanh = $("#number-bao-hanh");
    btnThem = $("#btn-them");
    getListProduct().then(function (){
        viewDanhSachSanPham();
    });
    $("#nav li:nth-child(2)").addClass("active");
    getCategory().then(function (){
        viewSelectDanhMuc();
    });
    viewDanhSachSanPham();
    xacNhanXoaSanPham();
    luuSanPham();
    themSanPham();
    timKiem();
    document.body.addEventListener('click', function (){
        checkTokenIsValidate(token)
    }, true);
})
async function getListProduct(){
    await productFindAll(token).then(function (resolve){
        if(resolve.message == "success"){
            listProduct = resolve.data;
        }else {
            listProduct = [];
        }
    }).catch(function (err){
        console.log(err);
    })
}
async function getCategory(){
    await fetchGet("category/find-all", token).then(function (resole){
        listCategory = resole.data;
    }).catch(function (err){
        console.log(err);
    })
}

function viewDanhSachSanPham(){
    let view ="<tr><td colspan='8' class='text-center'><strong>Không có dữ liệu</strong></td></tr>";
    if(listProduct && listProduct.length > 0){
        view =listProduct.map(function (data, index){
            return `<tr data-index ="${index}">
                        <th scope="row">${index+1}</th>
                        <td><img src="${data.image}"
                                 alt="" width="80px"></td>
                        <td>${viewField(data.name)}</td>
                        <td>${data.price}</td>
                        <td>${data.bought}</td>
                        <td>${data.introduction}</td>
                        <td>${data.specification}</td>
                        <td>${data.promotion}</td>
                        <td>${data.guarantee}</td>
                        <td>${data.createDate}</td>
                        <td class="text-center">${data.soldOut ? `<span class="badge badge-danger">Hết Hàng</span>`: `<span class="badge badge-success">Còn Hàng</span>`}</td>
                        <td>
                            <button type="button" class="btn btn-warning sua-san-pham"><i class="fas fa-pen"></i>
                                Sửa</button>
                            <button type="button" class="btn btn-danger xoa-san-pham" ><i class="fas fa-trash-alt"></i>
                                Xóa</button>
                        </td>
                    </tr>`
        }).join("");
    }
    tableDuLieu.html(view);
    xoaSanPham();
    suaSanPham();
    sapXepSanPham();
}

function xoaSanPham(){
    $(".xoa-san-pham").click(function (){
        indexProduct = $(this).parents("tr").attr("data-index");
        $("#exampleModal1").modal("show");

    })
}
function xacNhanXoaSanPham(){
    btnXacNhanXoa.click(function (){
        let idProduct = listProduct[indexProduct - 0].id;
        async function myDelete(id){
            return await productDelete(id, token);
        }
        myDelete(idProduct).then(function (resole){
            console.log(resole);
        }).catch(function (err){
            console.log(err);
        });
        listProduct = listProduct.filter(function (data, index){
            return index != indexProduct;
        });
        viewDanhSachSanPham();
        $("#exampleModal1").modal("hide");
    })
}
function getFormattedDate(newDate) {
    let todayTime = newDate;
    let month = todayTime.getMonth() + 1;
    let day = todayTime.getDate();
    let year = todayTime.getFullYear();
    return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
}
function suaSanPham(){
    $(".sua-san-pham").click(function (){
        indexProduct = $(this).parents("tr").attr("data-index") - 0;
        elementProduct = listProduct[indexProduct];
        textTen.val(elementProduct.name);
        let innerSelect = listCategory.map(function (item){
            return item.id == elementProduct.categoryId ? `<option selected value="${item.id}">${item.name}</option>` : `<option value="${item.id}">${item.name}</option>`;
        })
        selectDanhMuc.html(innerSelect.join(""));
        numberGia.val(elementProduct.price);
        numberDaBan.val(elementProduct.bought);
        numberBaoHanh.val(elementProduct.guarantee);
        numberKhuyenMai.val(elementProduct.promotion);
        dateNgayTao.val(getFormattedDate(new Date(elementProduct.createDate)));
        textareaGioiThieu.val(elementProduct.introduction);
        textareaThongso.val(elementProduct.specification);
        if(elementProduct.soldOut) checkboxHetHang.prop("checked", true);
        checkAction = "sua";
        $("#exampleModal").modal("show");
    })
}
function checkData(selector, textError){
    let val = $(selector).val();
    let check = false;
    if(val.length > 0){
        check = true;
        hiddenError(selector);
    }else{
        viewError(selector, textError);
    }
    return {val, check};
}
function luuSanPham(){
    let valDanhMuc;
    selectDanhMuc.change(function (){
        valDanhMuc = $(this).val();
    })
    btnLuuLai.click(function (){
        if(valDanhMuc == undefined){
            if(checkAction == "sua"){
                valDanhMuc = elementProduct.categoryId;
            }else {
                valDanhMuc = listCategory[0].id;
            }
        }
        let {val:valTen, check:checkTen} = checkData(textTen, "Định dạng tên chưa đúng");
        let {val:valGia, check:checkGia}= checkData(numberGia, "Giá bán phải là số");
        let {val:valDaBan, check:checkDaBan}= checkData(numberDaBan, "Nhập số lượng đã bán");
        let {val:valBaoHanh, check:checkBaoHanh}= checkData(numberBaoHanh, "Nhập thời gian bảo hành");
        let {val:valKhuyenMai, check:checkKhuyenMai}= checkData(numberKhuyenMai, "Nhập phần trăm khuyến mãi");
        let valGioiThieu = textareaGioiThieu.val();
        let valThongSo = textareaThongso.val();
        let valHetHang = checkboxHetHang.is(":checked");
        if(checkBaoHanh && checkKhuyenMai && checkGia && checkTen && checkDaBan){
            elementProduct.name = valTen;
            elementProduct.categoryId = valDanhMuc;
            elementProduct.price = valGia;
            elementProduct.bought = valDaBan;
            elementProduct.guarantee = valBaoHanh;
            elementProduct.promotion = valKhuyenMai;
            elementProduct.introduction = valGioiThieu;
            elementProduct.specification = valThongSo;
            elementProduct.soldOut = valHetHang;
            async function myUpdate(obj){
                return await productUpdate(obj, token);
            }
            async function myCreate(obj){
                return await productCreate(obj, token);
            }
            if(checkAction === "sua"){
                let tempCreateDate = elementProduct.createDate;
                delete elementProduct.createDate;
                listProduct[indexProduct] = elementProduct;
                myUpdate(elementProduct).then(function (resole){
                    console.log(resole);
                    elementProduct.createDate = tempCreateDate;
                }).then(function (){
                    viewDanhSachSanPham();
                })
            }else if(checkAction ==="them"){
                listProduct.push(elementProduct);
                delete elementProduct.createDate;
                myCreate(elementProduct).then(function (resole){
                    console.log(resole);
                    elementProduct.createDate = resole.data.createDate;
                    elementProduct.image = resole.data.image;
                    getListProduct().then();
                }).then(function (){
                    viewDanhSachSanPham();
                })
            }
            $("#exampleModal").modal("hide");
        }
    })
}
function themSanPham(){
    btnThem.click(function (){
        elementProduct = {};
        checkAction = "them";
        dateNgayTao.val(getFormattedDate(new Date()));
        let innerSelect = listCategory.map(function (item){
            return `<option value="${item.id}">${item.name}</option>`;
        })
        selectDanhMuc.html(innerSelect);
        $("#exampleModal").modal("show");
    })
}
function viewSelectDanhMuc(){
    let innerSelect = listCategory.map(function (item){
        return `<option value="${item.id}">${item.name}</option>`;
    })
    innerSelect.unshift(`<option value="all">Loại sản Phẩm</option>`);
    selectSearchDanhMuc.html(innerSelect.join(""));
    selectSearchDanhMuc.change(function (){
        let thisOption = $(this).val();
        if(thisOption == "all"){
            getListProduct().then(function (){
                viewDanhSachSanPham();
            })
        }else{
            getListProduct().then(function (){
                listProduct = listProduct.filter(function (item){
                    return item.categoryId == thisOption;
                })
                viewDanhSachSanPham();
            })
        }
    })
}
function sapXepSanPham(){
    selectSearchSapXep.change(function (){
        let thisVal = $(this).val();
        async function mySort(){
            await productSortByField(thisVal, token).then(function (resole){
                if(resole.message == "success"){
                    listProduct = resole.data;
                }
            }).catch(function (error){
                console.log(error);
            })
        }
        mySort().then(function (){
            viewDanhSachSanPham();
        })
    })
}
function timKiem(){
    let varThisOption;
    selectSearchHetHang.change(function (){
        varThisOption = $(this).val();
    })
    btnTimKiem.click(function (){
        let varSearchTen = textSearchTen.val();
        let varSearchGia = numberSearchGia.val();
        let varSearchDaBan = numberSearchDaBan.val();
        let varSearchKhuyenMai = numberSearchKhuyenMai.val();
        let varSearchBaoHanh = numberSearchBaoHanh.val();
        let varSearchNgayTao = dateSearchNgayTao.val();
        getListProduct().then(function (){
            if(varSearchTen.length !== 0){
                listProduct = listProduct.filter(function (item){
                    return item.name.toUpperCase().indexOf(varSearchTen.toUpperCase()) == 0 ;
                })
            }
            if(varSearchGia.length !== 0){
                listProduct = listProduct.filter(function (item){
                    return item.price == varSearchGia ;
                })
            }
            if(varSearchDaBan.length !== 0){
                listProduct = listProduct.filter(function (item){
                    return item.bought == varSearchDaBan ;
                })
            }
            if(varSearchBaoHanh.length !== 0){
                listProduct = listProduct.filter(function (item){
                    return item.guarantee == varSearchBaoHanh ;
                })
            }
            console.log(varSearchKhuyenMai)
            if(varSearchKhuyenMai.length !== 0){
                listProduct = listProduct.filter(function (item){
                    return item.promotion == varSearchKhuyenMai ;
                })
            }
            if(varSearchNgayTao.length !== 0){
                listProduct = listProduct.filter(function (item){
                    return getFormattedDate(new Date(item.createDate)) === getFormattedDate(new Date(varSearchNgayTao)) ;
                })
            }
            if(varThisOption != undefined){
                if(varThisOption == "true"){
                    listProduct = listProduct.filter(function (item){
                        return item.soldOut == true;
                    })
                }
                if(varThisOption == "false"){
                    listProduct = listProduct.filter(function (item){
                        return item.soldOut == false;
                    })
                }
            }
            viewDanhSachSanPham();
        });
    })

    textSearchTen.bind('keypress', function(e) {
        if (e.which==13) {
            btnTimKiem.click();
        }
    });
}
