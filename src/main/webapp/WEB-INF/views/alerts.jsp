<%-- 
    Document   : alerts
    Created on : Oct 1, 2014, 6:35:52 PM
    Author     : Eugene
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty errorAlert}">
    <div class="alert alert-danger alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <c:out value="${errorAlert}"/>
    </div>
</c:if>
<c:if test="${not empty msgAlert}">
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <c:out value="${msgAlert}"/>
    </div>
</c:if>
<c:if test="${not empty infoAlert}">
    <div class="alert alert-info alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <c:out value="${infoAlert}"/>
    </div>
</c:if>
<c:if test="${not empty successAlert}">
    <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <c:out value="${successAlert}"/>
    </div>
</c:if>