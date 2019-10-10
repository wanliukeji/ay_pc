var app = angular.module('myGroup', []).controller('groupCtrl', function ($scope, $http, $q) {

    $scope.groupList = [];
    $scope.serviceItem = [];

    $scope.init = function () {
        $scope.groupList = getSessionObj('groupList');
        $scope.type = getSessionObj('type');
        $scope.serviceItem = getServiceItem('/getClassField_fw');
        $scope.prvList = getAddr(1);
        $scope.cityList = getAddr(31);
        $scope.disList = getAddr(388);
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

});

