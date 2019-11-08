var app = angular.module('myZZ', []).controller('ZZCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();
    $scope.stauts = '执行';
    $scope.reqUrl = 'https://nb.58.com/jiancai/40077555335973x.shtml?adtype=1&entinfo=40077555335973_q&adact=3&psid=130675282206199536058918698&iuType=q_2&link_abtest=&ClickID=1&PGTID=0d360415-0008-76eb-cfcb-5d9ea22e78ac&slot=1000933';
    $scope.phone = '4008194669';
    $scope.type = '';
    $scope.imgs = [];


    var userip = getIp();

    var userAddr = getCity();
    angular.element("#city").html('<i style="color: black;">当前城市&nbsp;</i>' + userAddr);

    $scope.sendMsg = function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
        } else {
            angular.element("#queryModel").click();
            // href('/release');
        }
    };

    $scope.sendMsg = function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
        } else {
            angular.element("#queryModel").click();
        }
    };
    // 发布安装加工
    $scope.sendAz = function () {
        href('/release')
    };
    // 发布材料配送
    $scope.sendPs = function () {
        href('/relPs')
    };
    // 发布材料
    $scope.sendCz = function () {
        href('/relCz')
    };
    // 发布设备
    $scope.sendSb = function () {
        href('/relSb')
    };

    $scope.outlogin = function () {
        removeUser("user");
        href('/login');
    };

    $scope.login = function () {
        var url = '/api/user/login';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };
        var msg = ajax_http_post_login(url, date);

        if (msg.code == 200) {
            $scope.stauts = '正在登录.....';
            setUser(msg.data);
            setTimeout(function () {
                history.go(0);
            }, 2000)
        } else {
            alert("账户密码不匹配      " + msg.message);
        }
    }

    $scope.goInfo = function (id, type) {
        if (angular.equals('加工', type) || angular.equals('点工', type) || angular.equals('安装', type)) {
            var url = 'api/fied/getInfoVo?id=' + id;
            var msg = ajax_http_get(url);
            setSessionObj('entity', msg.data);
            href('/info');
        } else if (angular.equals('门窗厂', type) || angular.equals('玻璃厂', type) || angular.equals('配件商', type)) {
            var url = 'api/fied/getInfoVo?id=' + id;
            var msg = ajax_http_get(url);
            setSessionObj('entity', msg.data);
            href('/info_cl');
        } else if (angular.equals('定点', type) || angular.equals('店铺', type) || angular.equals('厂房', type)) {
            var url = '/api/rent/getInfo?id=' + Number.parseInt(id);
            var msg = ajax_http_get(url);
            setSessionObj('entity', msg.data);
            href('/info_cz');
        } else if (angular.equals('设备', type)) {
            var url = '/api/rent/getInfo?id=' + Number.parseInt(id);
            var msg = ajax_http_get(url);
            console.log(JSON.stringify(msg.data));
            setSessionObj('entity', msg.data);
            href('/info_sb');
        }
    }

    $scope.goGroup = function (type) {
        var url = "/api/fied/getVos?type=" + type;
        var msg = ajax_http_get(url);
        if (msg.code = 200) {
            setSessionObj('groupList', msg.data);
            setSessionObj('type', type);
            href('/group');
        } else {
            history.go(0);
        }
        // setSessionObj()
    }

    $scope.search = function () {
        var url = '/api/fied/getPageAll?searchVal=' + $scope.searchVal;
        var msg = ajax_http_get(url);
        console.log(msg);
    }

    document.onkeydown = function (e) {
        if ((e.keyCode || e.which) == 13) {
            angular.element("#search-btn").click();
        }
    }

    // is_Exist()

    // $scope.getData = function () {
    //     alert(1);
    //     if (!$scope.reqUrl) {
    //         msg_error("请输入网址")
    //     }
    //     alert(1);
    // }

    $("#btn").click(function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
            return;
        }

        if (!$scope.reqUrl) {
            $("#url_span").text("请输入网址");
            return;
        } else {
            $("#url_span").text("");
        }

        if (!$scope.phone) {
            $("#phone_span").text("请输入电话");
            return;
        } else {
            $("#phone_span").text("");
        }

        $scope.stauts = '执行中....';

        var url = "/api/http/get_WB?reqUrl=" + $scope.reqUrl + "&phone=" + $scope.phone + "&type=" + $scope.type;
        var msg = ajax_http_get(url);
        $scope.info = JSON.stringify(msg);
        $scope.imgs = msg.data[1];
        $scope.stauts = '执行';
        console.log($scope.imgs)
    });


    function getURL(url) {
        $.ajax({
            url: url,
            type: 'GET',
            complete: function (response) {
                if (response.status == 200) {
                    return true;
                } else {
                    return false;
                }
            },
            error: function (err) {
                return false;
            }
        });
    }
});

