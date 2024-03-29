var app = angular.module('myGroup', []).controller('groupCtrl', function ($scope, $http, $q) {
    $scope.groupList = [];
    $scope.serviceItem = [];

    $scope.totalPage = 0;
    $scope.total = 0;
    $scope.pageNum = 1;
    $scope.btnText = '登录';

    $scope.currentPage = 0;

    $scope.ReqParam = {
        pageNo: 0,
        pageSize: 6,
        procode: null,
        citycode: null,
        countycode: null,
        type: null,
        fw: null
    };

    $scope.init = function () {
        $scope.groupList = getSessionObj('groupList');
        $scope.type = getSessionObj('type');
        $scope.serviceItem = getServiceItem('/getClassField_fw');
        $scope.prvList = getAddr(1);
        $scope.cityList = getAddr(31);
        $scope.disList = getAddr(388);
        $scope.user = getUser();

        var userAddr = getCity();
        angular.element("#city").html('<i style="color: black;">当前城市&nbsp;</i>' + userAddr);
    }

    $scope.goInfo = function (id) {
        var url = 'api/fied/getInfoVo?id=' + id;
        var msg = ajax_http_get(url);
        if (msg.code = 200) {
            setSessionObj('entity', msg.data);
            href('/info');
        } else {
            history.go(0);
        }
        // setSessionObj()
    }

    //获取一级区域
    function getAddr(pid) {
        // var id = Number.parseInt(pid);
        var url = '/api/addr/getInfos?pid=' + pid;
        var msg = ajax_http_get(url);
        return msg.data;
    }

    $scope.selectPrv = function (id) {
        $scope.cityList = getAddr(id);
        $scope.ReqParam.procode = id;
        getList();
    }

    $scope.selectCity = function (id) {
        $scope.disList = getAddr(id);
        $scope.ReqParam.citycode = id;
        getList();
    }

    $scope.selectDis = function (id) {
        $scope.ReqParam.countycode = id;
        getList();
    }

    function getList() {
        var url = '/api/fied/getPageVos';
        var msg = ajax_http_post(url, JSON.stringify($scope.ReqParam))
        $scope.groupList = msg.data.data.list;
        $scope.totalPage = msg.data.data.pages;
        $scope.total = msg.data.data.total;
        $scope.pageNum = msg.data.data.pageNum;
    }

    $scope.prev = function () {
        $scope.ReqParam.pageNo = $scope.pageNum - 1;
        getList();
    };

    $scope.next = function () {
        $scope.ReqParam.pageNo = $scope.pageNum + 1;
        getList();
    };

    $scope.selectFw = function (val) {
        $scope.ReqParam.fw = val;
        getList();
    }

    $scope.removeFw = function () {
        $scope.ReqParam.fw = null;
        getList();
    }

    $scope.removePrv = function () {
        $scope.ReqParam.procode = null;
        getList();
    }

    $scope.removeCity = function () {
        $scope.ReqParam.citycode = null;
        getList();
    }

    $scope.removeDis = function () {
        $scope.ReqParam.countycode = null;
        getList();
    }

    $scope.sendMsg = function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
        } else {
            angular.element("#queryModel").click();
            // href('/release');
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

    $scope.login = function () {
        var url = '/api/user/login';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };
        var msg = ajax_http_post_login(url, date);

        if (msg.code == 200) {
            $scope.btnText = '正在登录.....';
            setUser(msg.data);
            setTimeout(function () {
                history.go(0);
            }, 2000)
        } else {
            alert("账户密码不匹配");
        }
    }


    $scope.outlogin = function () {
        removeUser("user");
        href('/login');
    };

    $scope.search = function () {
        var url = '/api/fied/getPageAll?searchVal=' + $scope.searchVal;
        var msg = ajax_http_get(url);
        $scope.groupList = msg.data;
        href('/group');
    }

    document.onkeydown = function (e) {
        if ((e.keyCode || e.which) == 13) {
            angular.element("#search-btn").click();
        }
    }

});

