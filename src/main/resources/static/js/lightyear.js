var lightyear = function(){

    /**
     * 椤甸潰loading
     */
    var pageLoader = function($mode) {
        var $loadingEl = jQuery('#lyear-loading');
        $mode      = $mode || 'show';
        if ($mode === 'show') {
            if ($loadingEl.length) {
                $loadingEl.fadeIn(250);
            } else {
                jQuery('body').prepend('<div id="lyear-loading"><div class="spinner-border text-primary" role="status"><span class="sr-only">Loading...</span></div></div>');
            }
        } else if ($mode === 'hide') {
            if ($loadingEl.length) {
                $loadingEl.fadeOut(250);
            }
        }
        return false;
    };

    /**
     * 椤甸潰灏忔彁绀�
     * @param $msg 鎻愮ず淇℃伅
     * @param $type 鎻愮ず绫诲瀷:'info', 'success', 'warning', 'danger'
     * @param $delay 姣鏁帮紝渚嬪锛�1000
     * @param $icon 鍥炬爣锛屼緥濡傦細'fa fa-user' 鎴� 'glyphicon glyphicon-warning-sign'
     * @param $from 'top' 鎴� 'bottom'
     * @param $align 'left', 'right', 'center'
     * @author CaiWeiMing <314013107@qq.com>
     */
    var tips = function ($msg, $type, $delay, $icon, $from, $align) {
        $type  = $type || 'info';
        $delay = $delay || 1000;
        $from  = $from || 'top';
        $align = $align || 'center';
        $enter = $type == 'danger' ? 'animated shake' : 'animated fadeInUp';

        jQuery.notify({
                icon: $icon,
                message: $msg
            },
            {
                element: 'body',
                type: $type,
                allow_dismiss: true,
                newest_on_top: true,
                showProgressbar: false,
                placement: {
                    from: $from,
                    align: $align
                },
                offset: 20,
                spacing: 10,
                z_index: 10800,
                delay: $delay,
                //timer: 1000,
                animate: {
                    enter: $enter,
                    exit: 'animated fadeOutDown'
                }
            });
    };

    return {
        // 椤甸潰灏忔彁绀�
        notify  : function ($msg, $type, $delay, $icon, $from, $align) {
            tips($msg, $type, $delay, $icon, $from, $align);
        },
        // 椤甸潰鍔犺浇鍔ㄧ敾
        loading : function ($mode) {
            pageLoader($mode);
        }
    };
}();