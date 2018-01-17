<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="vehicleList"></c:param>
</c:import>
<html>
<head>
    <title>Rezerwacje pokojów</title>
</head>
<body>
<div id="main" class="container">
    <H1>LISTA POKOJÓW</H1>





    <c:if test="${not empty reservationListPage.content}">



        <c:set var="boundMin" value="${20000}"/>
        <c:set var="boundMax" value="${40000}"/>

        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Identyfikator pokoju </th>

                <th>Indentyfikator użytkownika </th>

                <%--<th>Cena</th>--%>
                <%--<th>Opinia</th>--%>
                <security:authorize access="hasRole('ADMIN')">
                    <th>Opcje</th>
                </security:authorize>
            </tr>
            </thead>
            <body>

            <c:forEach items="${reservationListPage.content}" var="reservation">
                <c:if test="${reservation.reserv_id eq 1}">
                <tr>
                    <td>${reservation.id_room}</td>
                    <%--<td>--%>
                    <%--<c:out value="${empty room.model?'Brak danych': room.model}" escapeXml="true"/>--%>

                    <%--</td>--%>
                    <td>${reservation.id_user}</td>
                    <%--<td><fmt:formatDate value="${room.productionDate}" type="date" timeStyle="medium"/></td>--%>
                    <%--<td><fmt:formatNumber type="CURRENCY" value="${room.price}" currencySymbol="PLN"/></td>--%>
                    <%--<td>--%>
                        <%--<c:choose>--%>
                            <%--<c:when test="${room.price lt boundMin}">--%>
                                <%--TANI--%>
                            <%--</c:when>--%>
                            <%--<c:when test="${room.price gt boundMax}">--%>
                                <%--DROGI--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                                <%--ŚREDNI--%>
                            <%--</c:otherwise>--%>
                        <%--</c:choose>--%>
                    <%--</td>--%>
                    <%--<security:authorize access="permitAll">--%>
                        <%--<td>--%>
                                <%--&lt;%&ndash;<c:url var="deleteUrl" value="/vehicleList.html?did=${room.id}&page=${vehicleListPage.number}&size=${vehicleListPage.size}" />&ndash;%&gt;--%>
                            <%--<c:url var="reservationUrl" value="/vehicleList.html?id_reservation=${room.id}" />--%>
                            <%--<a class="btn btn-danger" href="${reservationUrl}">Rezerwuj </a>--%>
                                <%--&lt;%&ndash;<a class="btn btn-success" href="${editUrl}">Edytuj</a>&ndash;%&gt;--%>
                        <%--</td>--%>
                    <%--</security:authorize>--%>
                    <security:authorize access="hasRole('ADMIN')">
                        <td>
                            <c:url var="deleteUrl" value="/reservation.html?id=${reservation.id}&id_room=${reservation.id_room}" />
                            <%--<c:url var="editUrl" value="/vehicleForm.html?id=${room.id}" />--%>
                            <a class="btn btn-danger" href="${deleteUrl}">Usuń</a>
                            <%--<a class="btn btn-success" href="${editUrl}">Edytuj</a>--%>
                        </td>
                    </security:authorize>
                    </tr>
                </c:if>
            </c:forEach>
            </body>
        </table>

        <c:set var="page" value="${reservationListPage}" scope="request"/>
        <c:set var="mainUrl" value="vehicleList.html" scope="request"/>

    </c:if>
    <%--<security:authorize access="hasRole('ADMIN')">--%>
        <%--<a class="btn btn-success" href="vehicleForm.html">Dodaj Nowy</a>--%>
    <%--</security:authorize>--%>
</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>
