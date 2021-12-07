let urlProduct = "product";
function productFindAll(token){
    return fetchGet(`${urlProduct}/find-all`, token);
}
function productSortByField(field, token){
    return fetchGet(`${urlProduct}/sort-by-field?field=${field}`, token);
}
function productDelete(id, token){
    return fetchDelete(`${urlProduct}?id=${id}`, token);
}
function productUpdate(product, token){
    return fetchPut(`${urlProduct}`, product, token);
}
function productCreate(product, token){
    return fetchPost(`${urlProduct}`, product, token);
}
