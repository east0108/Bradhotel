$(() => {
    $("#loginForm").on("submit", function() {
        // 檢查帳號
        var account = $("#account").val();
        if(account == "") {
            alert("請輸入帳號");
            $("#account").focus();
            return false;
        }

        // 檢查密碼
        var password = $("#password").val();
        if(password == "") {
            alert("請輸入密碼");
            $("#password").focus();
            return false;
        }
    });
})

function login() {
    // Form fields, see IDs above
    const params = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/tour/login",

        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json;charset:UTF-8",
        success: () => {
            document.location.href = "http://localhost:8080/tour/home";
        },
        error: () => {
            swal("登入錯誤!", "請再次檢查是否輸入正確!", "error");
        }
    });
};

//檢查註冊郵件
const buttonElement = document.getElementById('email');

buttonElement.addEventListener('click', function () {
    const params = {
        email: document.getElementById('email').value
    }

    var xml = new XMLHttpRequest();
    xml.withCredentials = true;
    xml.open('POST', 'http://localhost:8080/tour/user/checkEmail', true);
    xml.onreadystatechange = function () {

        if (xml.status >= 200 && xml.status < 400) {
            if (document.getElementById('email').value === "") {
                var myVar = 4;
                document.body.className = (myVar == 5 ? "yes" : "no");
                document.getElementById("textEmail").innerText = "請輸入郵件/Email";
            } else {
                console.log(document.getElementById('email').value);
                var myVar = 4;
                document.body.className = (myVar == 5 ? "yes" : "no");
                document.getElementById("textEmail").innerText = "此Email尚未註冊";
            }
        } else {
            console.log("XX");
            var myVar = 5;
            document.body.className = (myVar == 5 ? "yes" : "no");
            document.getElementById("textEmail").innerText = "此Email已註冊";
        };
    };
    xml.setRequestHeader("Content-Type", "application/json");
    xml.send(JSON.stringify(params));

});