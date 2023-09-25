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

    document.location.href = "http://localhost:8080/search";

    datalist();

});

//全部+搜尋
var url = "http://localhost:8080/tours/?";
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
            // setPage(Math.ceil(data.total / data.limit))
        }

    });

}

function Info(data){
    $("#dataList").empty();

    var result = data.results;

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
                        <img class="img-fluid" src="
                            ${item.imageUrl}" alt="">
                        <div class="p-4">
                            <div class="d-flex justify-content-between mb-3">
                                <small class="m-0"><i class="fa fa-map-marker-alt text-primary mr-2"></i>${item.city}</small>
                                <small class="m-0"><i class="fa fa-calendar-alt text-primary mr-2"></i>${item.tourName}</small>
                                <small class="m-0"><i class="fa fa-user text-primary mr-2"></i>${item.address}</small>
                            </div>

                            <div class="border-top mt-4 pt-4">
                                <div class="d-flex justify-content-between">
                                    <h6 class="m-0"><i class="fa fa-star text-primary mr-2"></i>4.5 <small>(250)</small></h6>
                                    <h5 class="m-0">$${item.tickets}</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


        `

        $("#dataList").append(html);
    })

}

