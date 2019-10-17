var app = angular.module('myHome', []).controller('homeCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();
    $scope.stauts = '登录';

    $scope.jgItems = [];
    $scope.dgItems = [];
    $scope.azItems = [];
    $scope.mccItems = [];
    $scope.blcItems = [];
    $scope.pjsItems = [];
    $scope.ddItems = [];
    $scope.dpItems = [];
    $scope.cfItems = [];
    $scope.sbItems = [];

    var userip = getIp();

    var userAddr = getCity();
    angular.element("#city").html('<i style="color: black;">当前城市&nbsp;</i>' + userAddr);

    $scope.init = function () {
        $scope.jgItems = getVos('加工');
        $scope.dgItems = getVos('点工');
        $scope.azItems = getVos('安装');
        $scope.mccItems = getVos('门窗厂');
        $scope.blcItems = getVos('玻璃厂');
        $scope.pjsItems = getVos('配件商');
        $scope.ddItems = getVos('定点');
        $scope.dpItems = getVos('店铺');
        $scope.cfItems = getVos('厂房');
        $scope.sbItems = getVos('设备');
    };

    $scope.sendMsg = function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
        } else {
            href('/release');
        }
    };

    $scope.outlogin = function () {
        removeUser("user");
        href('/login');
    };

    function getVos(type) {
        var url = "/api/fied/getFiedInfoVos?type=" + type;
        var msg = ajax_http(url, method_get, null);
        return msg.data;
    }


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
                href('/home');
            },2000)
        } else {
            alert("账户密码不匹配      " + msg.message);
        }
    }

    $scope.goInfo = function (id) {
        var url = 'api/fied/getInfoVo?id=' + id;
        var msg = ajax_http(url, method_get, null);
        if (msg.code = 200) {
            setSessionObj('entity', msg.data);
            href('/info');
        } else {
            history.go(0);
        }
        // setSessionObj()
    }

    $scope.goGroup = function (type) {
        var url = "/api/fied/getVos?type=" + type;
        var msg = ajax_http(url, method_get, null);
        console.log(msg);
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
        var msg = ajax_http(url, method_get, null);
        $scope.groupList = msg.data;
        setSessionObj('groupList', $scope.groupList);
        // href('/group');
    }

    document.onkeydown = function (e) {
        if ((e.keyCode || e.which) == 13) {
            angular.element("#search-btn").click();
        }
    }

});

