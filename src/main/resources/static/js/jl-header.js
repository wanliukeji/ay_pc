document.writeln("<header>");
document.writeln("    <div class=\'tool-bar-ctn\'>");
document.writeln("        <div class=\'toolbar\'>");
document.writeln("            <div class=\'toolbar-link\'>");
document.writeln("                <a class=\'hot-link\' id=\'city\'></a>");
document.writeln("            </div>");
document.writeln("            <ul id=\'user-tools\' class=\'navbar-nav\'>");
document.writeln("                <li ng-show=\'user == null\'>");
document.writeln("                    <a href=\'/login\' rel=\'nofollow\' data-toggle=\'userAuth\' data-type=\'login\'");
document.writeln("                       class=\'no-padding\'>登录</a>/");
document.writeln("                    <a href=\'/register\' rel=\'nofollow\' data-toggle=\'userAuth\' data-type=\'reg\'");
document.writeln("                       class=\'no-padding\'>注册</a>");
document.writeln("                </li>");
document.writeln("");
document.writeln("                <li class=\'dropdown\' ng-show=\'user != null\'>");
document.writeln("                    <a href=\'javascript:;\' target=\'_blank\'>个人中心<i class=\'icon-arrow-down\'></i></a>");
document.writeln("                    <ul class=\'dropdown-menu\'>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>个人账户</a></li>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>设置</a></li>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\' href=\'/admin\'>后台入口</a></li>");
document.writeln("                        <li><a ng-click=\'outlogin()\' target=\'_blank\' rel=\'nofollow\'>注销</a>");
document.writeln("                        </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("                <li style=\'display: none\' class=\'js-separator\'></li>");
document.writeln("                <li class=\'dropdown\'>");
document.writeln("                    <a href=\'javascript:;\' target=\'_blank\'>产品服务<i class=\'icon-arrow-down\'></i></a>");
document.writeln("                    <ul class=\'dropdown-menu\'>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>推广服务</a></li>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>网站建设</a></li>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>软件开发</a>");
document.writeln("                        </li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("                <li class=\'dropdown\'>");
document.writeln("                    <a href=\'javascript:;\' target=\'_blank\'>帮助中心<i class=\'icon-arrow-down\'></i></a>");
document.writeln("                    <ul class=\'dropdown-menu\'>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>密码找回</a></li>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>发新信息</a></li>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>网站协议</a>");
document.writeln("                        <li><a target=\'_blank\' rel=\'nofollow\'>反馈</a></li>");
document.writeln("                    </ul>");
document.writeln("                </li>");
document.writeln("            </ul>");
document.writeln("        </div>");
document.writeln("    </div>");
document.writeln("    <div class=\'header\'>");
document.writeln("        <div class=\'top-banner full-banner\'></div>");
document.writeln("        <div class=\'location\' style=\'height: 115px;\'>");
document.writeln("            <span class=\'breadcrumb\'>");
document.writeln("                <a href=\'#\' class=\'primary-logo\'>");
document.writeln("");
document.writeln("                    <img src=\'/static/image/anny_logo.png\' alt=\'安义分类信息网\'");
document.writeln("                         width=\'300\' height=\'200\' style=\'margin-top:-72px;\'/>");
document.writeln("                    </a>");
document.writeln("            </span>");
document.writeln("            <div class=\'head-search\'>");
document.writeln("                <div class=\'search\' style=\' margin-top: 15px; \'>");
document.writeln("                    <input id=\'keyword\' onfocus=\'this.select();return false;\' placeholder=\'请输入类别名称或关键字\' value=\'\'");
document.writeln("                           x-webkit-speech=\'\' name=\'query\' class=\'input search-query ui-autocomplete-input\'");
document.writeln("                           autocomplete=\'off\' style=\'margin-left: auto; font-size:14px;\'/>");
document.writeln("                    <input type=\'button\' name=\'button\' class=\'frontpage-search-trigger search-trigger\' value=\'搜  索\'");
document.writeln("                           onclick=\'searchList()\' style=\'margin-left: 401px; margin-top: -40px;\'/>");
document.writeln("                    <input type=\'hidden\' id=\'hid_type\' value=\'shenghuo\'/>");
document.writeln("                    <input type=\'hidden\' id=\'hid_address\' value=\'zhejiang\'/>");
document.writeln("                </div>");
document.writeln("            </div>");
document.writeln("            <div class=\'pull-right\'><a ng-click=\'sendMsg();\' rel=\'nofollow\' class=\'post\'");
document.writeln("                                       target=\'_blank\'>免费发布信息</a></div>");
document.writeln("        </div>");
document.writeln("    </div>");
document.writeln("</header>");