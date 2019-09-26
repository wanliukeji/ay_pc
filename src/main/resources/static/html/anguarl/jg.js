var app = angular.module('myJg', []).controller('jgCtrl', function ($scope, $http, $q) {

    var url = '/api/jg/getPage';

    $scope.user = getUser();
    $scope.items = [];

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
        $scope.items = msg.data.records;
    };

    var ids = getIds();

    $scope.export = function () {
        if (is_item_Empty(ids)) {
            alert('请选择导入的数据');
            return;
        } else {
            export_file();
        }
    };

    function export_file() {
        var items = stringIds(ids);
        console.log(getIds());
        var msg = ajax_http('/api/jg/export', method_get, {"ids": getIds()});
    }

});

