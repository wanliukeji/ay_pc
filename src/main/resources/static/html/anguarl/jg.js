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

    $scope.export = function () {

        var items = getIds();
        console.log(items);
        if (is_item_Empty(items)) {
            alert('请选择导入的数据');
            return;
        } else {
            export_file(getIds());
        }
    };

    function export_file(items) {
        console.dir(items);
        var url = '/api/jg/export?ids=' + items;
        // var msg = ajax_http(url, method_get, null);
        location.href = url
    }

});

