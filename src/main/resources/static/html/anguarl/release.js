var app = angular.module('myRel', []).controller('relCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();

    var userip = getIp();

    var userAddr = getCity();

    $scope.ares = getAre();

    $scope.prvList = getAddr(1);

    $scope.selectPrv = function (id) {
        $scope.cityList = getAddr(id);
    }

    $scope.selectCity = function (id) {
        $scope.disList = getAddr(id);
    }

    angular.element("#city").html(userAddr);
    angular.element("#userIp").html(userip);

    $scope.serviceItem = getServiceItem('/getClassField_fw');
    $scope.typeItem = getServiceItem('/getClassField_ty');

    if (!is_Exist()) {
        href('/login');
    }

    $scope.fiedId = '';

    $scope.entity = {
        head_line: '宁波铝合金门窗--新桥门窗加工厂,终身保修',
        telephone: '4008900126',
        msgCode: '',
        user_qq: '1193227758',
        user_wx: 'xilinmen',
        phone: '13278789090',
        link: '陈经理 ',
        createDate: new Date(),
        company_name: '宁波新桥门窗',
        procode: '',
        citycode: '',
        countycode: '',
        address: '宁波市,海曙区,永寿街36号',
        video_url: '',
        gg_src: '',
        details: '\n' +
        '宁波新桥门窗主要从事建筑门窗工程施工设计安装。我们主要生产铝合金、感应门、木铝门窗、节能门窗、阳光房、艺术玻璃采光顶、艺术阳光房玻璃房及附加代理产品壁柜移动门、隔断墙。\n' +
        '上门测量、安装提供样品，可根据用户需要订做、加工、安装。只有想得到没有做不到！\n' +
        '专业制作：钢结构、阳光房、现浇混泥土、封阳台等。\n',
        fw: '',
        service: '',
        type: '',
        price: 0,
        userId: $scope.user.id
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

                if (!$scope.entity.head_line) {
                    msg_error('标题不能为空');
                    return;
                }

                if (!$scope.entity.company_name) {
                    msg_error('公司名不能为空');
                    return;
                }

                if (!$scope.entity.phone) {
                    msg_error('手机号不能为空');
                    return;
                }

                if (!reg_test(reg_phone, $scope.entity.phone)) {
                    msg_error("手机格式有误");
                    return;
                }

                if (!$scope.entity.link) {
                    msg_error('联系人不能为空');
                    return;
                }

                if (!$scope.entity.service) {
                    msg_error('请选择服务类');
                    return;
                }

                if (!$scope.entity.fw) {
                    msg_error('请选择区域');
                    return;
                }

                if (!$scope.entity.procode) {
                    msg_error('请选择省份');
                    return;
                }

                if (!$scope.entity.citycode) {
                    msg_error('请选择城市');
                    return;
                }

                if (!$scope.entity.countycode) {
                    msg_error('请选择县区');
                    return;
                }

                if (!$scope.entity.address) {
                    msg_error('详细地址不能为空');
                    return;
                }

                if (!$scope.entity.details) {
                    msg_error('服务描述不能为空');
                    return;
                }

                if (!$scope.entity.type) {
                    msg_error('请选择类别');
                    return;
                }

                var time = dateFomat_YYMMDDHHFFMM();
                $scope.entity.msgCode = time + $scope.user.id;

                $http({
                    url: '/api/fied/save',
                    method: "POST",
                    data: $scope.entity,
                    headers: {"Content-Type": "application/json;charset=utf-8"}
                }).success(function (res, status, header, config) {
                    $scope.fiedId = res.data.id;
                    console.log($scope.fiedId);
                    if (status == 200) {
                        upload_g_img();
                        upload_z_img();
                        upload_a_img();
                        $scope.info.next = '发布中...';
                        angular.element("#showModel").click();
                        setTimeout(function () {
                            href('/home')
                        }, 3000);
                    } else {
                        msg_error(res.data);
                    }
                }).error(function (err) {
                    msg_error('连接服务器失败,请重试');
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
        form.append('fiedId', $scope.fiedId);
        form.append('userId', $scope.user.id);
        form.append('msgCode', $scope.entity.msgCode);
        var url = 'uploadfile_g_img';
        if (null != file) {
            upload(url, method_post, form);
            form.delete("fileName");
            form.delete("userId");
            form.delete('msgCode');
            form.delete('fiedId');
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
                form.append('fiedId', $scope.fiedId);
                form.append('userId', $scope.user.id);
                form.append('msgCode', $scope.entity.msgCode);
                upload(url, method_post, form);
                form.delete("fileName");
                form.delete("userId");
                form.delete('msgCode');
                form.delete('fiedId');
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
                form.append('userId', $scope.user.id);
                form.append('msgCode', $scope.entity.msgCode);
                upload(url, method_post, form);
                form.delete("fileName");
                form.delete("userId");
                form.delete('msgCode');
            }
        }
    }

    //获取服务范围区域
    function getAre() {
        var city = getCity();
        var url = '/api/addr/getChlid?name=' + city;
        var msg = ajax_http_get(url);
        return msg.data;
    }

    //获取一级区域
    function getAddr(pid) {
        if (pid == 'undefined') {
            pid = 0;
        }
        pid = Number.parseInt(pid);
        var url = '/api/addr/getInfos?pid=' + pid;
        var msg = ajax_http_get(url);
        return msg.data;
    }

});

$(function () {
    // 判断输入的手机格式是否正确
    $("#phone").blur('input porpertychange', function (e) {
        var phone = $(this).val();
        if (!reg_test(reg_phone, phone)) {
            msg_error("您输入的手机格式有误")
        }
    });
});
