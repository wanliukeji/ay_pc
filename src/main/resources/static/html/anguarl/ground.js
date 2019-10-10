var app = angular.module('myGroup', []).controller('groupCtrl', function ($scope, $http, $q) {
    var url = '/api/fied/getPageVos';
    $scope.groupList = [];
    $scope.serviceItem = [];

    $scope.totalPage = 0;
    $scope.total = 0;
    $scope.pageNum = 1;

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
        angular.element("#city").html(userAddr);
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

    //获取一级区域
    function getAddr(pid) {
        var id = Number.parseInt(pid);
        var url = '/api/addr/getInfos?pid=' + id;
        var msg = ajax_http(url, method_get, null);
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
        var msg = ajax_http(url, method_post, $scope.ReqParam)
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
});

