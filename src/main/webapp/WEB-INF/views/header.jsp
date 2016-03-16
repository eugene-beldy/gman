
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#collapse">
                    <span class="sr-only"> Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
        </div>
    </nav>
</div>
<script>
    $("#orderNavForm").submit(function (e) {
        e.preventDefault();
        var orderNo = $("#orderNo").val();
        window.location = "/orders/" + orderNo;
    });
</script>
<div class="row">
    <div class="col-md-12" id="alertsBox"></div>
</div>
<!-- end .header -->


