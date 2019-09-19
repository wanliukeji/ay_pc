var app = angular.module('myApp', []);
app.controller('relCtrl', function ($scope, $http) {

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

        if($scope.show_num_next == 1){
           if (!$scope.headline) {
               // this.message('1111111111111');
               // toastr.error('不能为空');
           }

        }


        if ($scope.show_num_next >= 2) {
            $scope.info.next = '发  布';
        }
    }
});