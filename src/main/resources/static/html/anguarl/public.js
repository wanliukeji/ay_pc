//变量区
var method_post = 'POST';
var method_get = 'GET';

//正则区
var reg_email = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
var reg_phone = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式

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

function stringIds(item) {
    var ids = '';
    for (let i = 0; i < item.length; i++) {
        ids = item[i] + ',';
    }
    ;
    return ids;
};

//回车键
document.onkeydown = function(e){
    var ev = document.all ? window.event : e;
    if(ev.keyCode==13) {
        $('#search').click();
    }
};

$(function () {
    //通用
    $('.search-bar .dropdown-menu a').click(function () {
        var field = $(this).data('field') || '';
        $('#search-field').val(field);
        $('#search-btn').html($(this).text() + ' <span class="caret"></span>');
    });

    $('#exampleModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var recipient = button.data('whatever')
        var modal = $(this);
        modal.find('.modal-title').text('发送新消息给 ' + recipient)
        modal.find('.modal-body input').val(recipient)
    });
    //通用选中行数
    $('tr').on('mouseover',function () {
        $(this).css({'background-color': '#f3f3f3', 'cursor': 'pointer'});
    }).bind('mouseout',function () {
        $(this).css({'background-color': '#ffffff', 'cursor': 'pointer'});
    }).bind('click',function () {
        $(this).find("input").click();
    });
});