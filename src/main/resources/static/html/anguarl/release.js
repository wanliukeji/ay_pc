var app = angular.module('myRel', []).controller('relCtrl', function ($scope, $http, $q) {
    $scope.entity = {
        headline: '承接门窗加工',
        user_qq: '789878',
        user_wx: 'jiangxilaoer',
        phone: '1327878909',
        link: 'CHENYI',
        company_name: '宁波阿拉',
        procode: '101',
        citycode: '201',
        countycode: '301',
        address: '大榭开发区',
        video_url: 'http://www.baidu.com',
        gg_src: 'demo.jpg',
        details: '专业加工不锈钢，塑钢，铝合金门窗'
    }

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

            // if ($scope.show_num_next == 3) {
            //     let def = $q.defer();
            //     console.dir($scope.entity);
            //     $http.post({
            //         url: '/api/saveRel',
            //         // method: "POST",
            //         data: $scope.info,
            //         // headers: {"Content-Type": "application/json;charset=utf-8"}
            //     }).success(function (res, status, header, config, statusText) {
            //         console.log(JSON.stringify(res, status, header, config, statusText));
            //     }).error(function (err, status, header, config) {
            //         console.log(JSON.stringify(res, header, config, status));
            //     });
            // }
            if ($scope.show_num_next > 3) {
                $scope.show_num_next = 0;
            }
            $.ajax({
                url: '/api/saveRel',
                type: "POST",
                data: {
                    // $scope.entity
                },
                dataType : 'JSON',
                contentType: "application/json;charset=UTF-8",
                success:function (res) {
                    console.log(res);
                },
                error:function (err) {
                    console.log(err);
                }

            });
        }
    }
});

