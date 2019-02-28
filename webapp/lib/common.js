define("commonFunction", ["jquery", "layui"], function() {
    return {
        /**
         * 验证URL链接
         * @param  {string} str_url 需要验证的URL
         * @return {boolean}        返回是否URL合法
         */
        isURL: function(str_url) {
            var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
            var re = new RegExp(strRegex);
            //re.test()
            return re.test(str_url);
        },
        /**
         * 删除数据
         * @param url
         * @param id
         * @param token
         */
        deleteObject: function(url, id, token) {
            if (id !== null && id !== "") {
                layui.use("layer", function() {
                    var layer = layui.layer;
                    layer.ready(function() {
                        layer.confirm("是否真的删除此条目？", {
                            title: "注意",
                            btn: ["是", "否"]
                        }, function() {
                            $.ajax({
                                url: url,
                                type: "POST",
                                data: {
                                    "id": id,
                                    "_token": token,
                                    _method: "DELETE"
                                },
                                success: function(data) {
                                    if (data !== null && data.state) {
                                        layer.msg(data.message, {icon: 6});
                                        setTimeout(function() {
                                            location.reload();
                                        }, 3000);
                                    } else {
                                        layer.msg(data.message, {icon: 5});
                                    }
                                }
                            });
                        });
                    });
                });
            }
        },
        /**
         * 去除字符串中所有空格、制表符、换行符
         * @returns {string | void | *}
         */
        trim: function(string) {
            return string.replace(/[\s\n\t]+/g, "");
        },
        /**
         * 检查联系方式的类型
         * @param phoneNumber 需要检测的联系方式
         * @returns {number} 联系方式的类型
         */
        checkPhoneType: function(phoneNumber) {
            if (phoneNumber === "") {
                return 0;
            } else if (/^1[34578]\d{9}$/.test(phoneNumber)) {
                return 1;
            } else if (/^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/.test(phoneNumber)) {
                return 2;
            } else {
                return -1;
            }
        },
        /**
         * 判断用户是否使用PC端打开
         * @return {boolean}
         */
        browserType: function() {
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) === "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) === "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) === "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) === "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) === "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) === "android";
            var bIsCE = sUserAgent.match(/windows ce/i) === "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) ===
                "windows mobile";
            return bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc ||
                bIsAndroid || bIsCE || bIsWM;
        }
    };
});
