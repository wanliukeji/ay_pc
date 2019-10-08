var app = angular.module('myJg', []).controller('jgCtrl', function ($scope, $http, $q) {

    var url = '/api/jg/getPage';

    $scope.user = getUser();

    $scope.items = [];

    $scope.totalPage = 0;
    $scope.total = 0;
    $scope.pageNum = 0;

    $scope.currentPage = 0;

    $scope.ReqParam = {
        pageNo: 0,
        pageSize: 10,
        context: ''
    };

    $scope.init = function () {
        getList();
    };

    $scope.search = function () {
        getList();
    };

    function getList() {
        var msg = ajax_http(url, method_get, $scope.ReqParam);
        $scope.items = msg.data.data.list;
        $scope.totalPage = msg.data.data.pages;
        $scope.total = msg.data.data.total;
        $scope.pageNum = msg.data.data.pageNum;
    };

    $scope.export = function () {

        var items = getIds();
        if (is_item_Empty(items)) {
            alert('请选择导入的数据');
            return;
        } else {
            export_file(getIds());
        }
    };

    //导出EXCLE
    function export_file(items) {
        // var url = '/api/jg/export?ids=' + items;
        var url = '/api/export/file';
        href(url);
        angular.element('#export-close').click();
        msg_success('成功导出');
    };

    $scope.import = function () {
        uploadfile_txt();
        angular.element("#close").click();
    };

    //上传文件
    function uploadfile_txt() {
        var form = new FormData();
        var url = '/api/import/jg';
        var nodes = document.getElementsByName("file_txt");
        form.append("fileName", nodes[0].files[0]);
        upload(url, method_post, form);
        getList();
        window.location.href = "#";
    };

    $scope.del = function () {
        var items = getIds();
        var url = '/api/jg/delete?ids=' + items;
        if (is_item_Empty(items)) {
            alert('请选择要删除的数据');
            return;
        } else {
            ajax_http(url, method_get, null);
            getList();
        }
    };

    $scope.aunt = function () {
        var items = getIds();
        var url = '/api/jg/aunt?ids=' + items;
        if (is_item_Empty(items)) {
            alert('请选择要删除的数据');
            return;
        } else {
            ajax_http(url, method_get, null);
            getList();
        }
    };

    $scope.unaunt = function () {
        var items = getIds();
        var url = '/api/jg/unaunt?ids=' + items;
        if (is_item_Empty(items)) {
            alert('请选择要删除的数据');
            return;
        } else {
            ajax_http(url, method_get, null);
            getList();
        }
    };

    $scope.remove = function (id) {
        var url = '/api/jg/delete?ids=' + id;
        ajax_http(url, method_get, null);
        getList();
    };

    $scope.prev = function () {
        $scope.ReqParam.pageNo = $scope.pageNum - 1;
        getList();
    };

    $scope.next = function () {
        $scope.ReqParam.pageNo = $scope.pageNum + 1;
        getList();
    };

    $scope.getPage = function () {
        getList();
    };

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
        details: '专业加工不锈钢，塑钢，铝合金门窗'
    };

});

