$(document).ready(function(){

    Checklogin();
});
function Checklogin() {
    $.ajax({
        url: "http://localhost:8080/tour/home/checklogin",
        success:function() {


            var html='<div  id="toMember" class="nav-item nav-link">'
                +  '<a href="#" class="nav-item nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="bi bi-person-circle fa-2x"></i></a>'
                +  '<span class="dropdown-menu m-0">'
                +  '<a href="orderhistory" class="dropdown-item">購買紀錄 </a>'
                +  '<a href="http://localhost:8080/tour/logout" class="dropdown-item">登出</a>'
                + '</span>'
                + '</div>';

            $("#navbarCollapse").append(html);
        },error:function (){
            var html='<a href="register" class="nav-item nav-link" >註冊</a>';

            $("#registerToBack").append(html);
            var html2= '<a href="login" class="nav-item nav-link" >登入</a>';
            $("#navbarCollapse").append(html2);
        }
    });
}