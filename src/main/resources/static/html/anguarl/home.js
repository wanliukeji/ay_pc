var app = angular.module('myHome', []).controller('homeCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();

    $scope.jgItems = [];
    $scope.dgItems = [];
    $scope.azItems = [];
    $scope.mccItems = [];
    $scope.blcItems = [];
    $scope.pjsItems = [];
    $scope.ddItems = [];
    $scope.dpItems = [];
    $scope.cfItems = [];
    $scope.sbItems = [];

    var userip = getIp();

    var userAddr = getCity();
    angular.element("#city").html(userAddr);

    $scope.init = function () {
        $scope.jgItems = getVos('加工');
        $scope.dgItems = getVos('点工');
        $scope.azItems = getVos('加工');
        $scope.mccItems = getVos('加工');
        $scope.blcItems = getVos('加工');
        $scope.pjsItems = getVos('加工');
        $scope.ddItems = getVos('加工');
        $scope.dpItems = getVos('加工');
        $scope.cfItems = getVos('加工');
        $scope.sbItems = getVos('加工');
    };

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

    function getVos(type) {
        var url = "/api/fied/getVos?type=" + type;
        var msg = ajax_http(url, method_get, null);
        return msg.data;
    }

    $scope.login = function () {
        var url = '/api/login';
        var date = {
            'account': $scope.account,
            'password': $scope.password
        };
        var msg = ajax_http(url, method_post, date);

        if (msg.code = 200) {
            console.log(msg.message);
            setUser(date);
            // href('/home');
        } else {
            console.error(msg.message)
        }
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

    $scope.goGroup = function (type) {
        var url = "/api/fied/getVos?type=" + type;
        var msg = ajax_http(url, method_get, null);
        if (msg.code = 200) {
            setSessionObj('groupList', msg.data);
            setSessionObj('type', type);
            href('/group');
        } else {
            history.go(0);
        }
        // setSessionObj()
    }

});

