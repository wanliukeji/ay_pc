var app = angular.module('myApp', []);
app.controller('relCtrl', function ($scope, $http, $q) {

    $scope.show_num_prv = 0;
    $scope.show_num_next = 0;
    $scope.info = {
        prv: '上一步',
        next: '下一步'
    }

    $scope.prve = function () {
        $scope.show_num_next -= 1;

        if ($scope.show_num_next < 3) {
            $scope.info.next = '下一步';
        }
    }

    $scope.next = function () {
        $scope.show_num_next += 1;


        //提交
        if ($scope.show_num_next >= 2) {
            $scope.info.next = '发  布';

            if ($scope.show_num_next == 3) {
                let def = $q.defer();
                $http({
                    url: '/static/data/release.json',
                    method: "POST",
                    // data: data,
                    headers: {"Content-Type": "application/json;charset=utf-8"}
                }).success(function (res) {
                    def.resolve(res);
                }).error(function (res) {
                    def.reject(res);
                });
            }
            if ($scope.show_num_next > 3) {
                $scope.show_num_next = 0;
            }
        }
    }
});

