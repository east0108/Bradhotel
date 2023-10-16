$(document).ready(function () {
    checkuser();
})


function checkuser() {
    $.ajax({

        url: "http://localhost:8080/tour/home/checklogin", success: function (backstagedataUser) {
            console.log(backstagedataUser)//取得使用者ID
            orderhistory(backstagedataUser);
        }, error: function () {

            document.location.href = "http://localhost:8080/tour/login";
        }


    });

}

//取得訂單資料
function orderhistory(backstagedataUser) {

    console.log(backstagedataUser);//取得使用者ID

    var id = backstagedataUser.userId.toString();
    var url = "http://localhost:8080/tour/users/" + id + "/orders"

    //使用ID去跟後端要訂單資料

    $.ajax({
        url: url, type: "GET", dataType: 'json', contentType: "application/json ; charset=utf-8",

        success: function (backstagedata) {
            console.log(backstagedata)//訂單要出現在console內

            orderdatatable(backstagedata);

        }, error: function () {

        }


    })

}

//呈現訂單表單
function orderdatatable(backstagedata) {

    $("#selectOrderItem").empty();

    var html = "";


    var result = backstagedata.results

    console.log(backstagedata)
    console.log(result[0].orderId);

    for (i = 0; i <= backstagedata.results.length; i++) {
        html = `
<div id="content" class="section-padding">
    <div class="container">
        <div class="row mx-0 justify-content-center">
            <div class="col-sm-12 col-md-8 col-lg-9  ">
                <div class="page-content">
                    <div class="inner-box">
                        <div class="dashboard-wrapper">
                            <nav class="nav-table">
                            </nav>
                            <table class="table dashboardtable tablemyads">
                                <thead>
                                <tr>
                                    <th></th>
                                    <th>訂單編號</th>
                                    <th>創建時間</th>
                                    <th>訂單總額</th>
                                    <th>購買狀態</th>
                                    <th>刪除</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr data-category="active">
                                    <td>
                                        <a class="btn btn-primary" data-bs-toggle="collapse"
                                           href="#collapseExample${result[i].orderId}"
                                           role="button" aria-expanded="false"
                                           aria-controls="collapseExample${result[i].orderId}">
                                            商品詳細
                                        </a>
                                    </td>
                                    <td>${result[i].orderId}</td>
                                    <td>${result[i].createdDate}</td>
                                    <td data-title="Title">
                                        <h3>${result[i].totalAmount}</h3>
                                        <th style="Color: #66DD00">已付款</th>
                                    </td>
                                    <td data-title="Action">
                                        <div class="btns-actions">
                                            <button onclick="deleteOrderId(${backstagedata.results[i].orderId})"
                                                    class="btn btn-danger"><i class="lni-trash" >刪除清單</i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                                   </div>
                                 </div>
                </div>
            </div>
        </div>
    </div>
</div>
            `
        $("#selectOrderItem").append(html);
        for (j = 0; j < result[i].orderItemList.length; j++) {
            html = `
<div id="content" class="section-padding">
    <div class="container">
        <div class="row mx-0 justify-content-center">
            <div class="collapse col-sm-12 col-md-8 col-lg-9 row mx-auto justify-content-center "
                 id="collapseExample${result[i].orderId}">
                <div class="page-content">
                    <div class="inner-box">
                        <div class="dashboard-wrapper">
                            <nav class="nav-table">
                            </nav>

                            <div class="card card-body">
                            
                                <table style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>景點名稱</th>
                                            
                                            <th>門票價格</th>
                                     
                                            <th>門票張數</th>
                                         
                                            
                                        </tr>
                                       
                                        
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td style="width: 256px">${result[i].orderItemList[j].tourName}</td>
                                        <td>${result[i].orderItemList[j].amount}</td>
                                        <td>${result[i].orderItemList[j].quantity}</td>
                                    
                                    </tr>
                                        
                                    
                                    </tbody>
      
                                </table>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



                                                            
`


            $("#selectOrderItem").append(html);
        }

    }

}