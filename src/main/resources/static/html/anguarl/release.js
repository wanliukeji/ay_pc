var app = angular.module('myRel', []).controller('relCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();

    if (!is_Exist()) {
        href('/login');
    }

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
        fw: '',
        service: ''
    };

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
            $scope.entity.service = itemToString(getService());
            $scope.entity.fw = itemToString(getfws());

            if ($scope.show_num_next == 3) {
                let def = $q.defer();
                $http({
                    url: '/api/saveRel',
                    method: "POST",
                    data: $scope.entity,
                    headers: {"Content-Type": "application/json;charset=utf-8"}
                }).success(function (res, status, header, config) {
                    if (status == 200) {
                        upload_g_img();
                        upload_z_img();
                        upload_a_img();
                    } else {
                        console.log(res);
                    }
                }).error(function (err) {
                    console.log(err);
                });

            }
            ;

            if ($scope.show_num_next > 3) {
                $scope.show_num_next = 0;
            }
        }
    }

    //上传广告图片
    function upload_g_img() {
        var form = new FormData();
        var file = document.getElementById("gg_src").files[0];
        form.append('fileName', file);
        var url = 'uploadfile_g_img';
        if (null != file) {
            upload(url, method_post, form);
            form.delete("fileName");
        }
    }


    //上传作品图片
    function upload_z_img(node) {
        var form = new FormData();
        var url = '/uploadfile_z_img';
        var nodes = document.getElementsByName("z_img");
        for (let i = 0; i < nodes.length; i++) {
            var file = nodes[i].files[0];
            if (null != file) {
                form.append("fileName", file);
                upload(url, method_post, form);
                form.delete("fileName");
            }
        }
    }


    //上传案例图片
    function upload_a_img() {
        var form = new FormData();
        var url = '/uploadfile_a_img';
        var nodes = document.getElementsByName("a_img");
        for (let i = 0; i < nodes.length; i++) {
            var file = nodes[i].files[0];
            if (null != file) {
                form.append("fileName", file);
                upload(url, method_post, form);
                form.delete("fileName");
            }
        }
    }

});

