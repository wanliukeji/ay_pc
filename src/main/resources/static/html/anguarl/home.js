var app = angular.module('myHome', []).controller('homeCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();

    $scope.sendMsg = function () {
        if (!is_Exist($scope.user)) {
            angular.element("#showModel").click();
        } else {
            href('/release');
        }
    };

    $scope.outlogin = function () {
        removeUser("user");
        href('/login');
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
            if ($scope.show_num_next == 3) {
                let def = $q.defer();
                $http({
                    url: '/api/saveRel',
                    method: "POST",
                    data: $scope.entity,
                    headers: {"Content-Type": "application/json;charset=utf-8"}
                }).success(function (res, status, header, config) {
                    if (status == 200) {
                        // upload_g_img();
                        upload_z_img();
                        // upload_a_img();
                        console.log(res);
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
            upload(url, method_post, file);
        }
    }


    //上传作品图片
    function upload_z_img(node) {
        var form = new FormData();
        var url = '/uploadfile_z_img';
        var nodes = document.getElementsByName("z_img");
        var files = [];
        for (let i = 0; i < nodes.length; i++) {
            var file = nodes[i].files[i];
            if (null != file) {
                files.push(file);
            }
        }
        console.log(files);

        form.append("fileName", files);

        upload(url, method_post, form);

    }


    //上传案例图片
    function upload_a_img() {
        var form = new FormData();
        var url = '/uploadfile_a_img';
        var nodes = document.getElementsByName("a_img");
        for (let i = 0; i < nodes.length; i++) {
            var file = nodes[i].files[i];
            if (null != file) {
                upload(url, method_post, file);
            }
        }
    }

});

