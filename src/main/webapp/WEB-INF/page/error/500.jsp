<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!-- ajax layout which only needs content area -->
<div class="row">
    <div class="col-xs-12">
        <!-- PAGE CONTENT BEGINS -->

        <!-- #section:pages/error -->
        <div class="error-container">
            <div class="well">
                <h1 class="grey lighter smaller">
					<span class="blue bigger-125">
						<i class="ace-icon fa fa-sitemap"></i>
						500
					</span>
                    Something Went Wrong
                </h1>

                <hr/>

                <div>

                    <div class="space"></div>
                    <h4 class="smaller">试一下以下方法:</h4>

                    <ul class="list-unstyled spaced inline bigger-110 margin-15">
                        <li>
                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                            详细看看邮件说明的事项
                        </li>

                        <li>
                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                            仔细核对是否漏了什么，先在现有的功能模块之上修改再新增
                        </li>

                        <li>
                            <i class="ace-icon fa fa-hand-o-right blue"></i>
                            看一下文档里的新增步骤
                        </li>
                    </ul>
                </div>

                <hr/>
                <div class="space"></div>

                <div class="center">
                    <a href="javascript:history.back()" class="btn btn-grey">
                        <i class="ace-icon fa fa-arrow-left"></i>
                        返回
                    </a>

                    <a href="${contextPath}/sys/sysuser/home" class="btn btn-primary">
                        <i class="ace-icon fa fa-tachometer"></i>
                        主页
                    </a>
                </div>
            </div>
        </div>

        <!-- /section:pages/error -->

        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->
</div>
<!-- /.row -->

<!-- page specific plugin scripts -->
<script type="text/javascript">
    var scripts = [null, null]
    $('.page-content-area').ace_ajax('loadScripts', scripts, function () {
        //inline scripts related to this page
    });
</script>
