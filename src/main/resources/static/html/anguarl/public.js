//变量区
var method_post = 'POST';
var method_get = 'GET';


//用户区
function getUser() {
    var user = localStorage.getItem('user');
    if (null != user) {
        var user = typeof user == 'string' ? JSON.parse(user) : user;
    }
    return user;
}

function is_Exist() {
    var user = localStorage.getItem('user');
    if (null != user) {
        return true;
    }
    return false;
}

function setUser(obj) {
    localStorage.setItem('user', JSON.stringify(obj));
}

function setitems(obj) {
    localStorage.setItem('items', JSON.stringify(obj));
}

function removeUser(name) {
    localStorage.removeItem(name);
}

function href(url) {
    window.location.href = url;
}

//通用请求
function ajax_http(url, method, data) {
    var msg = null;
    $.ajax({
        url: url,
        method: method,
        data: data,
        async:false,
        dataType:'JSON',
        // headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (res) {
            msg = res;
        },
        error: function (err) {
            msg = err;
        }
    });
    return msg;
}