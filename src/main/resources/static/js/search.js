var city = "";
var limit;
var cookies;
var pages;

//設置Cookie
function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
//得到Cookie
function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}



//按下搜尋按鈕時觸發的行為
$("#search").click(function (){

    //
    setCookie("city" , $("#tourCity").val() , 365 );

    document.location.href = "http://localhost:8080/tour/search";

    datalist();

});

//全部+搜尋
var url = "http://localhost:8080/tour/tours?";
function datalist() {

    //從Cookie中 尋找city的Value值後進行搜尋
    cookies = getCookie("city");
    console.log(cookies);
    if (getCookie("city") != "") {
        url += "city=" + cookies;

    }


    $.ajax({
        type: "GET",
        url: url,
        dataType: 'json',
        contentType: "application/json ; charset=utf-8",
        success: function (data) {

            console.log(data);

            Info(data);
            setPage(Math.ceil(data.total / data.limit))
        }

    });

}


//畫面呈現出頁碼
function setPage(pageCount) {
    //var pageCount = data.pageCount;
    var pageHtml = '';
    var start, end;
    if (listPage < 6) {
        start = 1;
    } else {
        start = listPage - 5;
    }
    if (listPage > pageCount - 5) {
        end = pageCount;
    } else {
        end = listPage + 5;
    }

    if (listPage > 1) {
        pageHtml += '<span>上一頁</span>';
    }
    for (var i = start, page_cur = ''; i <= end; i++) {
        if (listPage == i) {
            page_cur = 'page_cur active';
        } else {
            page_cur = '';
        }
        pageHtml += '<span class="' + page_cur + '">' + i + '</span>';
    }
    if (listPage < pageCount) {
        pageHtml += '<span>下一頁</span>';
    }
    $('.page_show').empty().append(pageHtml);
}


//切換頁面
$('body').on('click', '.page_show span', function () {
    var $this = $(this);
    if ($this.hasClass('page_cur')) {
        return;
    }
    var page = $this.html();
    if (page == '上一頁') {
        listPage = listPage - 1;
        changePage(listPage);
    } else if (page == '下一頁') {
        listPage = listPage + 1;
        changePage(listPage);
    } else {
        listPage = parseInt(page);
        changePage(listPage);
    }



    //根據頁碼獲取當前頁列表數據
    setPage(pages)
});

var listPage=1;

function changePage(page) {
    var offset = limit * (page - 1);

    console.log(url);
    //limit已由後端設定，故在傳URL時不需要再設定limit
    $.ajax({
        type: "GET",
        url: url + "&offset=" + offset,
        success: function (data) {


            //依據offset可以得到該頁面的資料
            Info(data);
        }

    })
}


function Info(data){
    $("#dataList").empty();
    //商品資料
    var result = data.results;
    limit = data.limit;
    pages = Math.ceil(data.total / data.limit);

    console.log(result);


    // var html=`
    //         <div  class="col-lg-4 col-md-6 mb-4">
    //                 <div class="package-item bg-white mb-2">
    //                     <img class="img-fluid" src="
    //                         ${result.results.} alt="">
    //                     <div class="p-4">
    //                         <div class="d-flex justify-content-between mb-3">
    //                             <small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${item.city}</small>
    //                             <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${item.tourName}</small>
    //                             <small class="m-0"><i class="fa fa-user text-primary mr-2"></i>${item.address}</small>
    //                         </div>
    //
    //                         <div class="border-top mt-4 pt-4">
    //                             <div class="d-flex justify-content-between">
    //                                 <h6 class="m-0"><i class="fa fa-star text-primary mr-2"></i>4.5 <small>(250)</small></h6>
    //                                 <h5 class="m-0">$ ${item.address}</h5>
    //                             </div>
    //                         </div>
    //                     </div>
    //                 </div>
    //             </div>
    // `;



    $.each(result, function (idex , item) {

        html = `
        <div  class="col-lg-4 col-md-6 mb-4">
                    <div class="package-item bg-white mb-2">
                        <img id="image1" class="img-fluid" src="
                            ${item.imageUrl}" alt="" data-bs-target="#exampleModal${item.productId}">
                        <div class="p-4">
                            <div class="d-flex justify-content-between mb-3">
                                <small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${item.city}</small>
                                <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${item.tourName}</small>
                                <small class="m-0"><i class="fa fa-money text-primary mr-2"></i>NT$ ${item.tickets}</small>
                            </div>

                            <div class="border-top mt-4 pt-4">
                                <div class="d-flex justify-content-between">
                                    <h6 class="m-0"><i class="fa fa-star text-primary mr-2"></i>4.5 <small>(250)</small></h6>
                                    <h5 data-id=${item.productId} data-name=${item.tourName} data-price=${item.tickets}
                                    class="add-to-cart btn btn-primary ">加入購物車</h5>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


        `

        $("#dataList").append(html);
    })


    $('.add-to-cart').click(function (event){
        event.preventDefault();//阻止購物出彈出來
        var name = $(this).data('name');
        var price = Number($(this).data('price'))
        var id = $(this).data('id')

        shoppingCart.addItemToCart(name,price,1,id);
        displayCart();
    });
    displayCart();

}