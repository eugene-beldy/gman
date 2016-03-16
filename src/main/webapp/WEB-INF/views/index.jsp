<%-- 
    Document   : helloworld
    Created on : Aug 19, 2014, 3:53:41 PM
    Author     : Eugene
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <jsp:include page="head.jsp"/>
    </head>

    <body>
        <div class="container">
            <header class="header">
                <jsp:include page="header.jsp"/>       
            </header>

            <div class="row">
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:choose>
                                <c:when test="${tfpWorking}">
                                    <span class="glyphicon glyphicon-ok-circle"/> Tracing files processor
                                </c:when>
                                <c:otherwise>
                                    <span class="glyphicon glyphicon-ban-circle"/> <a href="/gman/tfprestart">Tracing files processor</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:choose>
                                <c:when test="${lopWorking}">
                                    <span class="glyphicon glyphicon-ok-circle"/> Lens orders processor
                                </c:when>
                                <c:otherwise>
                                    <span class="glyphicon glyphicon-ban-circle"/> <a href="/gman/lorestart">Lens orders processor</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <c:choose>
                                <c:when test="${wsuWorking}">
                                    <span class="glyphicon glyphicon-ok-circle"/> Webstock height updater
                                </c:when>
                                <c:otherwise>
                                    <span class="glyphicon glyphicon-ban-circle"/> <a href="/gman/wsurestart">Webstock height updater</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row"><br></div>

            <div class="row">
                <ul class="nav nav-tabs">
                    <li>
                        <a href="#tab_glazing" data-toggle="tab">
                            <span class="glyphicon glyphicon-barcode"/> Glazing
                        </a>
                    </li>
                    <li>
                        <a href="#tab_lens_orders" data-toggle="tab">
                            <span class="glyphicon glyphicon-shopping-cart"/> Lens orders
                        </a>
                    </li>
                    <li>
                        <a href="#tab_ws_update" data-toggle="tab">
                            <span class="glyphicon glyphicon-share-alt"/> Webstock update
                        </a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade active in" id="tab_glazing">
                        <div class="row">
                        </div>
                    </div>
                    <div class="tab-pane fade" id="tab_lens_orders">

                    </div>
                    <div class="tab-pane fade" id="tab_ws_update">

                    </div>
                </div>
            </div>
        </div>


        <script>

            $(function () {
                $('.nav-tabs a:first').tab('show');
            });

            /* $(document).ready(function () {
             requestSentDateMsec = 0;
             firstlaunCh = true;
             
             }); */
        </script>

        <footer class="footer">
            <jsp:include page="footer.jsp"/>

            <script>
                // epoch dates in msec of last message
                var tracingReceivedDateMsec = 0;
                var lensOrdersReceivedDateMsec = 0;
                var wsReceivedDateMsec = 0;
                // message html to display on view
                function getMessageHtml(date, jobNo, message, errorLevel) {
                    var messageStyle;
                    var messageDate = new Date(date).toLocaleDateString() + "<br/>";
                    messageDate += new Date(date).toLocaleTimeString();
                    switch (errorLevel) {
                        case 'Error':
                            messageStyle = 'alert-danger';
                            break;
                        case 'Warning':
                            messageStyle = 'alert-warning';
                            break;
                        case 'Success':
                            messageStyle = 'alert-success';
                            break;
                        case 'Message':
                            messageStyle = 'alert-info';
                            break;
                        default:
                            messageStyle = 'alert-info';
                            break;
                    }
                    return '<div class="alert ' + messageStyle + '">' +
                            '<div class="row show-grid">' +
                            '<div class="col-md-2"><p class="h5">' +
                            messageDate + '</p></div>' +
                            '<div class="col-md-4"><p class="h2">' +
                            jobNo + '</p></div>' +
                            '<div class="col-md-4"><p>' +
                            message + '</p></div>' +
                            '</div></div>';
                }
                // get messages every 1000 ms
                setInterval(function () {
                    // get tracing messages
                    $.getJSON('/gman/tracing', {fromDate: tracingReceivedDateMsec}).done(
                            function (glazingMessages) {
                                $.each(glazingMessages, function (i, messageObj) {
                                    jQuery(getMessageHtml(
                                            messageObj.date,
                                            messageObj.jobNo,
                                            messageObj.message,
                                            messageObj.errorLevel))
                                            .prependTo("#tab_glazing");
                                    tracingReceivedDateMsec = messageObj.date;
                                });
                            });
                    // get lens orders messages
                    $.getJSON('/gman/lensorders', {fromDate: lensOrdersReceivedDateMsec}).done(
                            function (glazingMessages) {
                                $.each(glazingMessages, function (i, messageObj) {
                                    jQuery(getMessageHtml(
                                            messageObj.date,
                                            messageObj.jobNo,
                                            messageObj.message,
                                            messageObj.errorLevel))
                                            .prependTo("#tab_lens_orders");
                                    lensOrdersReceivedDateMsec = messageObj.date;
                                });
                            });
                    // get web stock height update messages
                    $.getJSON('/gman/wsupdate', {fromDate: wsReceivedDateMsec}).done(
                            function (glazingMessages) {
                                $.each(glazingMessages, function (i, messageObj) {
                                    jQuery(getMessageHtml(
                                            messageObj.date,
                                            messageObj.jobNo,
                                            messageObj.message,
                                            messageObj.errorLevel))
                                            .prependTo("#tab_ws_update");
                                    wsReceivedDateMsec = messageObj.date;
                                });
                            });
                }, 1000);
            </script>
        </footer>
    </div>
    <!-- javascript -->
    <jsp:include page="footerjs.jsp"/>
</body>
</html>