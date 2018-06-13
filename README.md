### 商城后台管理系统

- http://localhost:8080/

#### 修改过的js

- ajax.js
```
    /**
     *@todo 单击获取switch,select属性值与value值，合并提交
     */
    ajax.prototype.setValue = function(_this, data, type) {
        var _ajax = _this,
            obj = $(data.elem),
            name = obj.attr("name"),
            params = obj.data('params');
        if (undefined == params || null == params) {
            layer.msg('请为当前元素配置data-garams属性');
            return;
        }
        if (typeof params === 'string') {
            params = JSON.parse(params)
        }
        var options = $.extend({}, _ajax.options, params);
        
        // 修改 var val = false; 为 var val = 0;
        var val = 0;
        if (type == "switch") {
            if (data.elem.checked) {

                val = data.elem.value;
            }
        } else if (type == "select") {
            val = data.value;
        }

        if (undefined != options.data && options.data != "") {
            if (options.data.indexOf(name) != -1) {
                var re = new RegExp(name + "=[^&]*", "gim");
                options.data = options.data.replace(re, name + "=" + val);

            } else {
                options.data = options.data + "&" + name + "=" + val;
            }
        } else {
            options.data = name + "=" + val;
        }
        options.content = val;
        _ajax.ajax(options, obj);
    }
```

- dtable.js 修改 分页参数 p 为 pageNum

## 参考资料
- https://stackoverflow.com/questions/28647136/how-to-disable-x-frame-options-response-header-in-spring-security
- http://sparkgis.com/java/2017/10/uber%E5%B7%A5%E7%A8%8B%E5%9B%A2%E9%98%9F%E7%9A%84%E5%BC%80%E6%BA%90%E5%88%86%E5%B8%83%E5%BC%8F%E8%BF%BD%E8%B8%AA%E7%B3%BB%E7%BB%9Fjaeger%EF%BC%88java%E5%AE%9E%E7%8E%B0%EF%BC%89-%E5%8E%9F-uber%E5%B7%A5/