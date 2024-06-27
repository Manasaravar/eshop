"use strict"
const addButtonHandler = (productId)=> {
    console.log("Нажата " + productId);
    $.ajax({
        url:'/cart/add',
        method:"POST",
        dataType:"HTML",
        data:{
            id:productId,
            quantity:1
        }
    })
}

const addPositionButtonHandler = (productId)=> {
    let quantity = document.querySelector("#position")
    console.log("Нажата " + productId);
    $.ajax({
        url:'/cart/add',
        method:"POST",
        dataType:"HTML",
        data:{
            id:productId,
            quantity:quantity.value
        }
    })
}