function register() {
    // Form fields, see IDs above
    const params = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    }

    const http = new XMLHttpRequest();
    http.withCredentials = true;
    http.open('POST', 'http://localhost:8080/tour/register', true);//'https://cors-anywhere.herokuapp.com/'+
    http.setRequestHeader("Content-Type", "application/json");
    http.send(JSON.stringify(params));// Make sure to stringify //JSON.stringify(params)
    http.onreadystatechange = function () {
        if (http.status >= 200 && http.status < 400) {

            document.location.href = "http://localhost:8080/tour/login";

        } else {
            swal("註冊失敗!", "麻煩檢查Email是否已註冊過", "error");
        }
    }
};