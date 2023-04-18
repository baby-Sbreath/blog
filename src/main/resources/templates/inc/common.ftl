<#macro page paging>
<div style="text-align: center">
    <div id="test1"></div>

    <script src="../../static/res/layui/layui.js"></script>
    <script>
        layui.use('laypage', function(){
            var laypage = layui.laypage;

            //执行一个laypage实例
            laypage.render({
                elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
                ,count: ${paging.total} //数据总数，从服务端得到
                ,limit: ${paging.size} //每页显示的条数
                ,curr: ${paging.current} //当前页号
                ,jump: function(obj, first){
                    //当分页被切换时触发，函数返回两个参数：obj（当前分页的所有选项值）、first（是否首次，一般用于初始加载的判断）

                    //首次不执行
                    if(!first){
                        location.href = "?pn=" + obj.curr;
                    }
                }
            });
        });
    </script>
</div>
</#macro>

<#macro pageList list>
    <li>
        <a href="/user/${list.authorId}" class="fly-avatar">
            <img src="${'../../../static'+list.authorAvatar}" alt="${list.authorName}">
        </a>
        <h2>
            <a class="layui-badge">${list.categoryName}</a>
            <a href="/detail/${list.id}">${list.title}</a>
        </h2>
        <div class="fly-list-info">
            <a href="user/home.html" link>
                <cite>${list.authorName}</cite>
                <!--
                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                <i class="layui-badge fly-badge-vip">VIP3</i>
                -->
            </a>
            <span>${list.created?string('yyyy-MM-dd hh:mm:ss')}</span>

            <#--                            <span class="fly-list-kiss layui-hide-xs" title="悬赏飞吻"><i class="iconfont icon-kiss"></i> </span>-->
            <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
            <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> ${list.commentCount}
              </span>
        </div>
        <div class="fly-list-badge">
            <#if list.level gt 0>
            <span class="layui-badge layui-bg-black">置顶</span>
            </#if>
            <#if list.recommend>
            <span class="layui-badge layui-bg-red">精帖</span>
            </#if>
        </div>
    </li>
</#macro>