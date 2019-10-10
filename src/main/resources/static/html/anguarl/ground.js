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
        type: $scope.type,
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
        var url = '/api/addr/getInfos?pid=' + pid;
        var msg = ajax_http(url, method_get, null);
        return msg.data;
    }

    $scope.selectPrv = function () {
        $scope.cityList = getAddr($scope.procode);
    }

    $scope.selectCity = function () {
        $scope.disList = getAddr($scope.citycode);
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


});

