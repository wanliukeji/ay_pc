var app = angular.module('myJg', []).controller('jgCtrl', function ($scope, $http, $q) {

    var url = '/api/jg/getPage';

    $scope.user = getUser();
    $scope.items = [];

    $scope.totalPage = 0;
    $scope.currentPage = 0;


    $scope.ReqParam = {
        pageNo: 0,
        pageSize: 10,
        context: ''
    };

    $scope.init = function () {
        getList();
        // $scope.totalPage = $scope.items.length % $scope.ReqParam.pageSize == 0 ?
        //     $scope.items.length / $scope.ReqParam.pageSize :
        //     $scope.items.length / $scope.ReqParam.pageSize + 1;
    };

    $scope.search = function () {
        getList();
    };

    function getList() {
        $scope.ReqParam.pageNo = $scope.currentPage;
        var msg = ajax_http(url, method_get, $scope.ReqParam);
        $scope.items = msg.data;
    };

    $scope.export = function () {

        var items = getIds();
        console.log(items);
        if (is_item_Empty(items)) {
            alert('请选择导入的数据');
            return;
        } else {
            export_file(getIds());
            alert(1);
            getList();
        }
    };

    //导出EXCLE
    function export_file(items) {
        console.dir(items);
        var url = '/api/jg/export?ids=' + items;
        var msg = ajax_http(url, method_get, null);
    };

    $scope.import = function () {
        uploadfile_txt();
        getList();
        angular.element("#close").click();
    };

    //上传文件
    function uploadfile_txt() {
        var form = new FormData();
        var url = '/api/import/jg';
        var nodes = document.getElementsByName("file_txt");
        form.append("fileName", nodes[0].files[0]);
        upload(url, method_post, form);
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
        $scope.currentPage = $scope.currentPage - 1 >= 1 ? $scope.currentPage - 1 : 1;
        getList();
    };
    $scope.next = function () {
        $scope.currentPage = $scope.currentPage + 1 <= $scope.totalPage ? $scope.currentPage + 1 : $scope.totalPage;
        getList();
    };

    $scope.getPage = function () {
        getList();
    };

});

