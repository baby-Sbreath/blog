<#include "../inc/layout.ftl">

<@layout "博客分类">

<#include "../inc/header-panel.ftl">

<div class="layui-container">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8 content detail">
            <div class="fly-panel detail-box">
                <h1>${onePost.title}</h1>
                <div class="fly-detail-info">
                    <!-- <span class="layui-badge">审核中</span> -->
                    <#--<span class="layui-badge layui-bg-green fly-detail-column">动态</span>

                    <span class="layui-badge" style="background-color: #999;">未结</span>-->
                    <!-- <span class="layui-badge" style="background-color: #5FB878;">已结</span> -->

                    <#if onePost.level gt 0>
                        <span class="layui-badge layui-bg-black">置顶</span>
                    </#if>
                    <#if onePost.recommend>
                        <span class="layui-badge layui-bg-red">精帖</span>
                    </#if>

                    <div class="fly-admin-box" data-id="123">
                        <span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>

                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> -->

                        <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>
                        <!-- <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> -->
                    </div>
                    <span class="fly-list-nums">
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> ${onePost.commentCount}</a>
            <i class="iconfont" title="人气">&#xe60b;</i> ${onePost.viewCount}
          </span>
                </div>
                <div class="detail-about">
                    <a class="fly-avatar" href="../user/home.html">
                        <img src="${'../../../static'+onePost.authorAvatar}" alt="${onePost.authorName}">
                    </a>
                    <div class="fly-detail-user">
                        <a href="../user/home.html" class="fly-link">
                            <cite>${onePost.authorName}</cite>

                        </a>
                        <span>${onePost.created?string('yyyy-MM-dd hh:mm:ss')}</span>
                    </div>
                    <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
                        <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>
                    </div>
                </div>
                <div class="detail-body photos">
                    <p>
                        ${onePost.content}
                    </p>

                </div>
            </div>

            <div class="fly-panel detail-box" id="flyReply">
                <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
                    <legend>回帖</legend>
                </fieldset>

                <ul class="jieda" id="jieda">
                    <#if comments.records ??>
                        <#list comments.records as item>
                    <li data-id="111" class="jieda-daan">
                        <a name="item-1111111111"></a>
                        <div class="detail-about detail-about-reply">
                            <a class="fly-avatar" href="">
                                <img src="${'../../../static'+item.authorAvatar}" alt="${item.authorName}">
                            </a>
                            <div class="fly-detail-user">
                                <a href="" class="fly-link">
                                    <cite>${item.authorName}</cite>
                                </a>

                                <#if onePost.authorName == item.authorName>
                                <span>(楼主)</span>
                                </#if>
                                <!--
                                <span style="color:#5FB878">(管理员)</span>
                                <span style="color:#FF9E3F">（社区之光）</span>
                                <span style="color:#999">（该号已被封）</span>
                                -->
                            </div>

                            <div class="detail-hits">
                                <span>${item.created?string('yyyy-MM-dd hh:mm:ss')}</span>
                            </div>

                            <i class="iconfont icon-caina" title="最佳答案"></i>
                        </div>
                        <div class="detail-body jieda-body photos">
                            <p>${item.content}</p>
                        </div>
                        <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>${item.voteUp}</em>
              </span>
                            <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
                            <div class="jieda-admin">
                                <span type="edit">编辑</span>
                                <span type="del">删除</span>
                                <!-- <span class="jieda-accept" type="accept">采纳</span> -->
                            </div>
                        </div>
                    </li>
                        </#list>
                    </#if>
                    <#if !(comments.records ??)>
                    <!-- 无数据时 -->
                    <li class="fly-none">消灭零回复</li>
                    </#if>
                </ul>

                <div class="layui-form layui-form-pane">
                    <form action="/jie/reply/" method="post">
                        <div class="layui-form-item layui-form-text">
                            <a name="comment"></a>
                            <div class="layui-input-block">
                                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="hidden" name="jid" value="123">
                            <button class="layui-btn" lay-filter="*" lay-submit>提交回复</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <#include "../inc/right.ftl">
    </div>
</div>

</@layout>