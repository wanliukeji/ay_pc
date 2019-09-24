var app = angular.module('myRel', []).controller('relCtrl', function ($scope, $http, $q) {
    $scope.entity = {
        head_line: '承接门窗加工',
        sercode: '1001',
        coverage: '1002',
        user_qq: '789878',
        user_wx: 'jiangxilaoer',
        phone: '1327878909',
        link: 'CHENYI',
        createDate: new Date(),
        company_name: '宁波阿拉',
        procode: '101',
        citycode: '201',
        countycode: '301',
        address: '大榭开发区',
        video_url: 'http://www.baidu.com',
        gg_src: 'demo.jpg',
        details: '专业加工不锈钢，塑钢，铝合金门窗',
        z_a_img: '/staus/images/100.jpg',
        z_b_img: '/staus/images/200.jpg',
        z_c_img: '/staus/images/300.jpg',
        a_a_img: '/staus/images/400.jpg',
        a_b_img: '/staus/images/500.jpg',
        a_c_img: '/staus/images/600.jpg'
    }

    $scope.show_num_prv = 0;
    $scope.show_num_next = 0;
    $scope.info = {
        prv: '上一步',
        next: '下一步'
    };

    $scope.prve = function () {
        $scope.show_num_next -= 1;

        if ($scope.show_num_next < 3) {
            $scope.info.next = '下一步';
        }
    };

    $scope.next = function () {
        $scope.show_num_next += 1;

        //提交
        if ($scope.show_num_next >= 2) {
            $scope.info.next = '发  布';
            if ($scope.show_num_next == 3) {
                let def = $q.defer();
                $http({
                    url: '/api/saveRel',
                    method: "POST",
                    data: $scope.entity,
                    headers: {"Content-Type": "application/json;charset=utf-8"}
                }).success(function (res, status, header, config) {
                    if (status == 200) {
                        $scope.upload_gg();
                        console.log(config.data);
                    } else {
                        console.log(JSON.stringify(status));
                    }
                }).error(function (err, status, header, config) {
                    console.log(JSON.stringify(res, header, config, status));
                });

            }
            ;

            if ($scope.show_num_next > 3) {
                $scope.show_num_next = 0;
            }
        }
    }

    //上传文件
    // $scope.upload = function () {
    //     alert(1);
    // };

    $scope.upload_gg = function () {
        var form = new FormData();
        var file = document.getElementById("gg_src").files[0];
        form.append('fileName', file);
        $http({
            method: 'POST',
            url: '/upload_img',
            data: form,
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).success(function (data) {
            console.log('upload success');
        }).error(function (data) {
            console.log('upload fail');
        })
    }


});

