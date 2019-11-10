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
        $scope.ddItems = getRentVos('定点');
        $scope.dpItems = getRentVos('店铺');
        $scope.cfItems = getRentVos('厂房');
        $scope.sbItems = getRentVos('设备');
    };

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

    function getVos(type) {
        var url = "/api/fied/getFiedInfoVos?type=" + type;
        var msg = ajax_http_get(url);
        return msg.data;
    }

    function getRentVos(type) {
        var url = "/api/rent/getRentInfoVos?type=" + type;
        var msg = ajax_http_get(url);
        console.log(JSON.stringify(msg));
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
            console.log(id);
            var url = '/api/rent/getInfo?id=' + Number.parseInt(id);
            var msg = ajax_http_get(url);
            setSessionObj('entity', msg.data);
            console.log(msg.data);
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
        $scope.groupList = msg.data;
        setSessionObj('groupList', $scope.groupList);
        href('/group');
    }

    document.onkeydown = function (e) {
        if ((e.keyCode || e.which) == 13) {
            angular.element("#search-btn").click();
        }
    }

});

