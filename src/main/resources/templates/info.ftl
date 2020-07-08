<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui/css/layui.css" />
    <script src="https://www.layuicdn.com/layui/layui.js"></script>
</head>
<body>
<div class="layui-container">
<blockquote class="layui-elem-quote layui-text">
    springBoot测试项目
</blockquote>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>系统信息</legend>
</fieldset>

<div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
    <ul class="layui-tab-title">
        <li class="layui-this">系统参数</li>
        <li>jvm信息</li>
        <li>请求头信息</li>
        <li>qps</li>
    </ul>
    <div class="layui-tab-content" style="height: 100px;">
        <div class="layui-tab-item layui-show">
            <div class="layui-form">
                <table class="layui-table">
                    <colgroup>
                        <col width="150">
                        <col width="150">
                        <col width="200">
                        <col>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>名称</th>
                        <th>值</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list systemInfos as systemInfo>
                        <tr>
                            <td>${systemInfo.name}</td>
                            <td>${systemInfo.value}</td>
                        </tr>
                    </#list>

                    </tbody>
                </table>
            </div>
        </div>
        <div class="layui-tab-item"><table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>值</th>
                </tr>
                </thead>
                <tbody>
                <#list jvms as jvm>
                    <tr>
                        <td>${jvm.name}</td>
                        <td>${jvm.value}</td>
                    </tr>
                </#list>

                </tbody>
            </table> </div>
        <div class="layui-tab-item"><table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>名称</th>
                    <th>值</th>
                </tr>
                </thead>
                <tbody>
                <#list headers as header>
                    <tr>
                        <td>${header.name}</td>
                        <td>${header.value}</td>
                    </tr>
                </#list>

                </tbody>
            </table></div>
        <div class="layui-tab-item"><table class="layui-table">
                <colgroup>
                    <col width="150">
                    <col width="150">
                    <col width="200">
                    <col>
                </colgroup>
                <thead>
                <tr>
                    <th>秒</th>
                    <th>每秒次数</th>
                </tr>
                </thead>
                <tbody>
                <#list qps as q>
                    <tr>
                        <td>${q.name}</td>
                        <td>${q.value}</td>
                    </tr>
                </#list>

                </tbody>
            </table></div>
    </div>
</div>
</div>
<script>
    layui.use('element', function(){
        var $ = layui.jquery
            ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块


    });
</script>
</body>
</html>