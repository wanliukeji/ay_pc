var app = angular.module('myInfoCz', []).controller('infoCzCtrl', function ($scope, $http, $q) {

    $scope.hoppyItem = [];
    $scope.init = function () {
        $scope.entity = getSessionObj('entity');
        $scope.files = getFiles($scope.entity.userId);
        $scope.jpgs = getFiles($scope.entity.userId);
        console.log($scope.entity);

        $scope.user = getUser();
        var userAddr = getCity();
        angular.element("#city").html('<i style="color: black;">当前城市&nbsp;</i>' + userAddr);
        if (!$scope.entity) {
            href('/login');
        } else {
            getFiles($scope.entity.userId);
            gethoppyItem("加工")
        }
    }

    function getFiles(userId) {
        var url = '/api/file/getInfos?userId=' + userId;
        var msg = ajax_http_get(url);
        return msg.data;
    }

    function gethoppyItem(type) {
        var url = '/api/fied/getVos?type=' + type;
        var msg = ajax_http_get(url);
        $scope.hoppyItem = msg.data;
    }

    $scope.goInfo = function (id) {
        var url = 'api/fied/getInfoVo?id=' + id;
        var msg = ajax_http_get(url);
        console.log(msg);
        if (msg.code = 200) {
            $scope.entity = msg.date;
            $scope.entity.company_name = '西部门窗';
        } else {
            history.go(0);
        }
    }

    $scope.sendMsg = function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
        } else {
            href('/release');
        }
    };
});

