let urlCategory = "category";
function categoryFindAll(token){
    return fetchGet(`${urlCategory}/find-all`, token);
}
function categoryDelete(id, token){
    return fetchDelete(`${urlCategory}?id=${id}`, token);
}
function categoryUpdate(category, token){
    return fetchPut(`${urlCategory}`, category, token);
}
function categoryCreate(category, token){
    return fetchPost(`${urlCategory}`, category, token);
}
