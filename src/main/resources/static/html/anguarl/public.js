//变量区
var method_post = 'POST';
var method_get = 'GET';

//正则区
var reg_email = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
var reg_phone = /^[1][3,4,5,7,8][0-9]{9}$/; //正则表达式

//用户区
function getUser() {
    var user = sessionStorage.getItem('user');
    if (null != user) {
        user = typeof user == 'string' ? JSON.parse(user) : user;
    }
    return user;
}

function is_Exist() {
    var user = sessionStorage.getItem('user');
    if (null != user) {
        return true;
    }
    return false;
}

function setUser(obj) {
    sessionStorage.setItem('user', JSON.stringify(obj));
}

function setitems(obj) {
    localStorage.setItem('items', JSON.stringify(obj));
}

function setlocalObj(key, value) {
    localStorage.setItem(key, JSON.stringify(value));
}

function getlocalObj(key) {
    var entity = localStorage.getItem(key);
    if (null != entity) {
        entity = typeof entity == 'string' ? JSON.parse(entity) : entity;
    }
    return entity
}

function removelocalObj(name) {
   localStorage.removeItem(name);
}

function setSessionObj(key, value) {
    sessionStorage.setItem(key, JSON.stringify(value));
}

function getSessionObj(key) {
    var entity = sessionStorage.getItem(key);
    if (null != entity) {
        entity = typeof entity == 'string' ? JSON.parse(entity) : entity;
    }
    return entity
}

function removeSessionObj(name) {
    sessionStorage.removeItem(name);
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
        async: false,
        dataType: 'JSON',
        headers: {"Content-Type": "application/json;charset=utf-8"},
        success: function (res) {
            msg = res;
        },
        error: function (err) {
            msg = err;
        }
    });
    return msg;
}

//通用POST请求
function ajax_http_post(url, data) {
    var msg = null;
    $.ajax({
        url: url,
        method: method_post,
        data: data,
        async: false,
        dataType: 'JSON',
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

//通用文件上传
function upload(url, method, form) {
    $.ajax({
        url: url,
        method: method,
        data: form,
        dataType: 'JSON',
        processData: false, // 使数据不做处理
        contentType: false, // 不要设置Content-Type请求头
        success: function (res) {
            console.log(res);
        },
        error: function (err) {
            console.log(err);
        }
    });
};

//判断集合是否为空
function is_item_Empty(item) {
    if (null == item || '' == item) {
        return true;
    }


    if (item.length <= 0) {
        return true;
    }

    return false;
};

//获取选中的ids
function getIds() {
    var ids = [];//定义一个数组
    $('input[name="ids[]"]:checked').each(function () {
        ids.push($(this).val());
    });
    return ids;
};

//获取选中的服务
function getService() {
    var sers = [];//定义一个数组
    $('input[name="ser"]:checked').each(function () {
        sers.push($(this).val());
    });
    return sers;
};

//获取选中的服务
function getfws() {
    var fw = [];//定义一个数组
    $('input[name="fw"]:checked').each(function () {
        fw.push($(this).val());
    });
    return fw;
};

function stringIds(item) {
    var ids = '';
    for (let i = 0; i < item.length; i++) {
        ids = item[i] + ',';
    }
    ;
    return ids;
};


function itemToString(item) {
    var str = '';
    if (null != item) {
        str = item.toString();
    }
    return str;
};

//回车键
document.onkeydown = function (e) {
    var ev = document.all ? window.event : e;
    if (ev.keyCode == 13) {
        $('#search').click();
    }
};

// 通用选中
function addCss(e) {
    e.style.backgroundColor = '#f4f4f4';
    e.style.cursor = 'pointer';
};

function delCss(e) {
    e.style.backgroundColor = '#ffffff';
    e.style.cursor = 'pointer';
};

function checked(e) {
    var nodes = e.childNodes;
    var child = nodes[1].childNodes;
    var check = child[1].childNodes;
    var obj = check[1];

    if (obj.checked) {
        obj.removeAttribute('checked')
    } else {
        obj.setAttribute('checked', true);
    }

};

//全局提示框
function msg_success(msg) {
    lightyear.loading('show');
    // 假设ajax提交操作
    setTimeout(function () {
        lightyear.loading('hide');
        lightyear.notify(msg, 'success', 2000);
    }, 2000)
};

function msg_error(msg) {
    lightyear.loading('show');
    // 假设ajax提交操作
    setTimeout(function () {
        lightyear.loading('hide');
        lightyear.notify(msg, 'danger', 1000);
    }, 1000)
};

function getServiceItem(val) {
    var msg = ajax_http(val, method_get, null);
    return msg.data;
}

function getIp() {
    var ip = returnCitySN.cip;
    return ip;
}

function getCity() {
    var city = returnCitySN.cname;
    city = city.substring(city.indexOf("省") + 1 || city.indexOf("区") + 1, city.indexOf("市"));
    return city;
}

function getUserAddr() {
    var userIp = getIp();
    var url = 'http://ip-api.com/json/' + userIp + '?lang=zh-CN';
    var msg = ajax_http(url, method_get, null);
    return msg;
}

function setAddName() {
    var map = new Map();
    var url = '/static/data/address.json?lang=zh-CN';
    var _json = ajax_http(url, method_get, null);
    for (var i = 0; i < _json.length; i++) {
        var obj = _json[i];
        map.set(obj.AddName, JSON.stringify(obj));
    }
    return map;
}

function setTopID() {
    var map = new Map();
    var url = '/static/data/address.json?lang=zh-CN';
    var _json = ajax_http(url, method_get, null);
    for (var i = 0; i < _json.length; i++) {
        var obj = _json[i];
        map.set(obj.TopID, JSON.stringify(obj));
    }
    return map;
}

function getAddrId(name) {
    var city = setAddName().get(name);
    return city;
}

function reg_test(reg, text) {
    if (reg.test(text)) {
        return true;
    }
    return false;
}

/**
 * 返回
 */
function backUrl() {
    history.go(-1);
}

/**
 * 刷新页面
 */
function flush() {
    history.go(0);
}

/**
 * 隐藏
 * @param e
 */
function hidde(e) {
    $(e).css({' display':'none'});
}

/**
 * 隐藏
 * @param e
 */
function show(e) {
    $(e).css({' display':'block'});
}