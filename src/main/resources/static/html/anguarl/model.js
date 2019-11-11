$(function () {
    $('#btn').click(function () {
        upload_img();
    });

    //上传案例图片
    function upload_img() {
        var form = new FormData();
        var url = '/uploadfile_save';
        var nodes = document.getElementsByName("jpg");
        for (let i = 0; i < nodes.length; i++) {
            var file = nodes[i].files[0];
            if (null != file) {
                form.append("fileName", file);
                console.log('>>>>>>>>>>>>>' + UPLOAD_POST(url, form));
                // $scope.jpg = msg.responseText;
                form.delete("fileName");
            }
        }
    }

});



