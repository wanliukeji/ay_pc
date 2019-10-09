var app = angular.module('myRel', []).controller('relCtrl', function ($scope, $http, $q) {

    $scope.user = getUser();

    var userip = getIp();

    var userAddr = getCity();

    $scope.ares = getAre();

    $scope.prvList = getAddr(1);

    $scope.selectPrv = function () {
        $scope.cityList = getAddr($scope.entity.procode);
    }

    $scope.selectCity = function () {
        $scope.disList = getAddr($scope.entity.citycode);
    }

    angular.element("#city").html(userAddr);
    angular.element("#userIp").html(userip);

    $scope.init = function () {
    }

    $scope.serviceItem = getServiceItem('/getClassField_fw');
    $scope.typeItem = getServiceItem('/getClassField_ty');

    if (!is_Exist()) {
        href('/login');
    }

    $scope.entity = {
        head_line: '铝合金门窗，玻璃隔断，金刚网纱窗',
        sercode: '1001',
        coverage: '1002',
        user_qq: '1193227758',
        user_wx: 'jiangxilaoer',
        phone: '13278789090',
        link: '刘经理',
        createDate: new Date(),
        company_name: '凤铝断桥铝加工厂',
        procode: '101',
        citycode: '201',
        countycode: '301',
        address: '大榭开发区',
        video_url: 'https://pic3.58cdn.com.cn/p1/big/n_v2156bd4fc29394b688ac68f808c21b392.jpg?w=338&h=253',
        gg_src: '',
        details: '断桥铝门窗、阳光房、铝合金门窗、金钢网纱窗、淋浴房 卫生间移门及设计制作和安装一条龙服务。已有十几年的制作加工经验，质量保证。 多年来，我们依靠优质的产品质量，独特的外观设计，标准的制作工艺 我们是一家专门从事制作加工一体化的公司，主要制作加工： 1）铝合金门窗、阳光房、断桥铝门窗； 2）防盗窗工程：锌合金防盗窗、彩钢活动防盗窗、彩铝防盗窗、彩铝护栏。3）推拉门 大折叠门 平开门 百叶门； 4）不锈钢防盗窗、阳台玻璃雨遮； 5）艺术玻璃移动门、（办公、店面）玻璃地弹门； 6）防强风卷帘隐形纱窗门、金钢网纱窗，防蚊又防盗又防小孩爬窗户等7）晾衣架 室内隔断等。服务流程： 1、电话咨询 2、免费上门测量设计方案 3、签订合同 4、预付款 5、下单生产，加工制作 6、上门安装完善的售后服务，在激烈的市场竞争中彰显优势，凭借实力毅力和信誉，先后承接了家庭、别墅、写字楼、住宅小区的大小工程等各种大型高档寓所。实惠的价格做出较有质量保证的产品，防水性 封密性 安全性 美观性 大要点一次性做好！ 一个电话 服务到家 ',
        fw: '',
        service: '',
        type: '加工'
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
                        msg_success("成功发布");

                        setTimeout(function () {
                            href('/home')
                        },2000);
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

    //获取服务范围区域
    function getAre() {
        var city = getCity();
        var url = '/api/addr/getChlid?name=' + city;
        var msg = ajax_http(url, method_get, null);
        return msg.data;
    }

    //获取一级区域
    function getAddr(pid) {
        var url = '/api/addr/getInfos?pid=' + pid;
        var msg = ajax_http(url, method_get, null);
        return msg.data;
    }

});

