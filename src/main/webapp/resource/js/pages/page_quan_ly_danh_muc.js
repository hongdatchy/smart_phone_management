let tableDuLieu, btnXacNhanXoa, textTen, btnLuuLai, btnThem, selectSapXep, textSearchTen, btnTimKiem;
let indexCategory , elementCategory, checkAction;
let listCategory = [];
let token;

$(function(){
    token = localStorage.getItem('token');
    // checkTokenIsValidate(token)
    tableDuLieu = $("tbody");
    btnXacNhanXoa = $("#btn-xac-nhan-xoa");
    textTen = $("#text-ten");
    btnLuuLai = $("#btn-luu-lai");
    btnThem = $("#btn-them");
    selectSapXep = $("#select-sap-xep");
    textSearchTen = $("#text-search-ten");
    btnTimKiem = $("#btn-tim-kiem");
    xacNhanXoaDanhMuc();
    themDanhMuc();
    luuDanhMuc();
    timKiem();
    $("#nav li:nth-child(3)").addClass("active");
    getListCategory().then(function (){
        render();
    })
    document.body.addEventListener('click', function (){
        checkTokenIsValidate(token)
    }, true);
})

async function getListCategory(){
    await categoryFindAll(token).then(rs =>{
        if(rs.message === "success"){
            listCategory = rs.data;
        }else{
            listCategory = [];
        }
    }).catch((err => {
        console.log(err);
        redirectToLoginPage();
    }))
}

async function myDel(idCategory){
    return await categoryDelete(idCategory, token);
};

function render(){
    let view ="<tr><td colspan='3'><strong>Không có dữ liệu</strong></td></tr>";
    if(listCategory && listCategory.length > 0){
        view =listCategory.map(function (data, index){
            return `<tr data-index ="${index}">
                        <th scope="row">${index+1}</th>
                        <td class="text-center">${data.name}</td>
                        <td class="text-center">
                            <button type="button" class="btn btn-warning sua-danh-muc"><i class="fas fa-pencil-alt"></i> Sửa</button>
                            <button type="button" class="btn btn-danger xoa-danh-muc"><i class="fas fa-trash-alt"></i> Xóa</button>
                        </td>
                    </tr>`
        }).join("");
    }
    tableDuLieu.html(view);
    xoaDanhMuc();
    suaDanhMuc();
    sapXepDanhMuc();
}
function xoaDanhMuc(){
    $(".xoa-danh-muc").click(function (){
        indexCategory = $(this).parents("tr").attr("data-index");
        $("#delete-category-modal").modal("show");
    })
}
function xacNhanXoaDanhMuc(){
    btnXacNhanXoa.click(function (){
        let idCategory = listCategory[indexCategory].id;
        myDel(idCategory).then();
        listCategory = listCategory.filter(function (data, index){
            return index != indexCategory;
        })
        render();
        $("#delete-category-modal").modal("hide");
    })
}
function suaDanhMuc(){
    $(".sua-danh-muc").click(function (){
        checkAction ="sua";
        indexCategory = $(this).parents("tr").attr("data-index");
        elementCategory = listCategory[indexCategory];
        textTen.val(elementCategory.name);
        $("#add-or-update-category-modal").modal("show");
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
function luuDanhMuc(){
    btnLuuLai.click(function (){
        //    kiểm tra dữ liệu đầu vào của người dùng
        let {val:valTen, check:checkTen} = checkData(textTen, "Định dạng tên chưa đúng");
        if(checkTen){
            elementCategory.name = valTen;
            if(checkAction== "sua"){
                listCategory[indexCategory] = elementCategory;
                async function myUpdate(obj){
                    return await categoryUpdate(obj, token);
                };
                myUpdate(elementCategory).then(function (rs){
                    console.log(rs.message =="success" ? "sua thanh cong": "sua that bai");
                }).catch(function (err){
                    console.log(err);
                    redirectToLoginPage();
                })
            }else{
                listCategory.push(elementCategory);
                async function myCreate(obj){
                    return await categoryCreate(obj, token);
                }
                myCreate({name: elementCategory.name}).then(function (rs){
                    console.log(rs.message =="success" ? "them thanh cong": "them that bai");
                    getListCategory().then();
                }).catch(function (err){
                    console.log(err);
                    redirectToLoginPage();
                })

            }
            render();
            $("#add-or-update-category-modal").modal("hide");
        }
    })
}
function themDanhMuc(){
    btnThem.click(function (){
        elementCategory = [];
        checkAction = "them";
        $("#add-or-update-category-modal").modal("show");
    })
}

function sapXepDanhMuc(){
    selectSapXep.change(function (){
        if($(this).val() == "default"){
            listCategory = listCategory.sort(function (a,b){
                return a.id- b.id;
            });
        }
        if($(this).val() == "AZ"){
            listCategory = listCategory.sort(function (a,b){
                return a.name.charCodeAt(0)- b.name.charCodeAt(0);
            });
        }
        if($(this).val() == "ZA"){
            listCategory = listCategory.sort(function (a,b){
                return b.name.charCodeAt(0)- a.name.charCodeAt(0);
            });
        }
        render();
    })
}
function timKiem(){
    btnTimKiem.click(function (){
        let varSearchTen = textSearchTen.val();
        getListCategory().then(function (){
            if(varSearchTen.length != 0){
                listCategory = listCategory.filter(function (item){
                    return item.name.toUpperCase().indexOf(varSearchTen.toUpperCase()) >=0 ;
                })
            }
            render();
        });
    })

    textSearchTen.bind('keypress', function(e) {
        if (e.which==13) {
            btnTimkiem.click();
        }
    });
}


