<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>编辑试题</title>
    <script type="text/javascript">
        $(function () {
            $(".tiHeader .navspace li>a:eq(0)").addClass("active");
            $selectMenu('tiku_header');
            $.ajax({
                url: rootPath + "/question/edit",
                type: "post",
                data: {
                    "types": $("#questionTypes").val(),
                    "id": $("#questionId").val(),
                    "categoryId": $("#cateId").val(),
                    "subId": $("#subjectId").val(),
                    "btn": $("#questionBtn").val(),
                    "status": $("#status").val()
                },
                dataType: "html",
                beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (data) {
                        $("#questionEdit").html(data);
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
            });
        });
    </script>
</head>

<body>
    <jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
    <input type="hidden" value="${categoryId }" id="tikuId" />
    <input type="hidden" id="questionTypes" value="${types }" />
    <input type="hidden" id="questionId" value="${id }" />
    <input type="hidden" id="cateId" value="${categoryId }" />
    <input type="hidden" id="subjectId" value="${subId }" />
    <input type="hidden" id="questionBtn" value="${btn }" />
    <input type="hidden" id="status" value="${status }" />
    <input type="hidden" id="pageNo" value="${pageNo }" />
    <div id="questionEdit">

    </div>
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg add-subs-layer-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->
</body>

</html>