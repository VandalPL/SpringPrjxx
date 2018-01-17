<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="shared/header.jsp">
    <jsp:param name="pageName" value="vehicleDetails"/>
</jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Szczegóły pojazdu</title>
</head>
<body>
<div id="main" class="container">
<H1>Dane pojazdu</H1>
Id: <b>${rooms.id}</b><br/>
  <%--Czas utworzenia wpisu: <b><fmt:formatDate value="${vehicle.createdDate}" type="both" timeStyle="medium"/></b><br/>--%>
    Nazwa: <b>${empty rooms.name?'Brak danych': rooms.name}</b><br/>
    Typ pojazdu: <b>${rooms.room_types.name}</b><br/>

    Cena: <b><fmt:formatNumber type="CURRENCY" value="${rooms.price}"  currencySymbol="PLN"/></b><br/>

    <%--Wyposażenie pojazdu:--%>
    <%--<c:choose>--%>
        <%--<c:when test="${empty vehicle.accessories}">--%>
            <%--Brak<br/>--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
            <%--<ul>--%>
                <%--<c:forEach items="${vehicle.accessories}" var="accessory">--%>
                    <%--<li>${accessory.name}</li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</c:otherwise>--%>
    <%--</c:choose>--%>

<a class="btn btn-success" href="/vehicleList.html">Wstecz</a>
</div>
</body>
</html>

<jsp:include page="shared/footer.jsp"/>