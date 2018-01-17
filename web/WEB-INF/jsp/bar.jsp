<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:import url="shared/header.jsp">
    <c:param name="pageName" value="barForm"></c:param>
</c:import>
<html>
<head>
    <title>Bar</title>
</head>
<body>
<div id="main" class="container">
    <H1>Menu</H1>




    <%--<form:form id="asearchForm" modelAttribute="searchCommand">--%>
        <%--<div class="row">--%>
            <%--&lt;%&ndash;<div class="form-group col-md-6">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<label for="phrase">Szukana fraza:</label>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:input path="phrase" cssClass="form-control" cssErrorClass="form-control is-invalid"/>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<form:errors path="phrase" cssClass="error text-danger" element="div"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

            <%--<div class="form-group col-md-3">--%>
               <%--<label for="phrase">Cena od:</label>--%>
                <%--<form:input path="minPrice" cssClass="form-control" cssErrorClass="form-control is-invalid"/>--%>
                <%--<form:errors path="minPrice" cssClass="error text-danger" element="div"/>--%>
            <%--</div>--%>
            <%--<div class="form-group col-md-3">--%>
                <%--<label for="phrase">Cena do:</label>--%>
                <%--<form:input path="maxPrice" cssClass="form-control" cssErrorClass="form-control is-invalid"/>--%>
                <%--<form:errors path="maxPrice" cssClass="error text-danger" element="div"/>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="row">--%>

            <%--<div class="form-group col-md-8"></div>--%>

                <%--<div class="form-group col-md-2">--%>
                <%--<c:if test="${searchCommand.isEmpty() eq false}">--%>
                    <%--<a href="/vehicleList.html?all" class="btn btn-success">--%>
                        <%--<span class="glyphicon glyphicon-refresh"></span> Pokaż wszystko--%>
                    <%--</a>--%>
                <%--</c:if>--%>
            <%--</div>--%>

                <%--<div class="form-group col-md-2">--%>
                <%--<button type="submit" class="btn btn-info">--%>
                    <%--<span class="glyphicon glyphicon-search"></span> Wyszukaj--%>
                <%--</button>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</form:form>--%>

    <%--<c:if test="${empty barForm.content}">--%>
        <%--${searchCommand.isEmpty() ? 'Lista jedzenia jest pusta':'Brak wyników wyszukiwania'}--%>
    <%--</c:if>--%>

    <c:if test="${not empty barForm.content}">

        <c:choose>
            <c:when test="${searchCommand.isEmpty()}">
                Lista zawiera ${barForm.totalElements} pozycji
            </c:when>
            <c:otherwise>
                Wynik wyszukiwania: ${barForm.totalElements} pozycji
            </c:otherwise>
        </c:choose>

        <c:set var="boundMin" value="${20000}"/>
        <c:set var="boundMax" value="${40000}"/>

        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Nazwa</th>

                <th>Cena</th>


                <security:authorize access="hasAnyRole('ADMIN','USER')">
                <th>Opcje</th>
            </security:authorize>

            </tr>
            </thead>
            <tbody>

            <c:forEach items="${barForm.content}" var="food">

                <%--<c:forEach items="${barForm.content}" var="users">--%>
                <tr>
                    <td>
                        <security:authorize access="isAuthenticated()">
                            <a href="?id=${food.id}">${food.name}</a>
                        </security:authorize>
                        <security:authorize access="isAnonymous()">
                            <c:out value="${food.name}" escapeXml="true"/>
                        </security:authorize>
                    </td>
                        <%--<td>--%>
                        <%--<c:out value="${empty room.model?'Brak danych': room.model}" escapeXml="true"/>--%>

                        <%--</td>--%>
                    <%--<td>${room.roomType.name}</td>--%>
                        <%--<td><fmt:formatDate value="${room.productionDate}" type="date" timeStyle="medium"/></td>--%>
                    <td><fmt:formatNumber type="CURRENCY" value="${food.price}" currencySymbol="PLN"/></td>
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
                    <security:authorize access="permitAll">

                        <td>
                            <%--{/room/add/{id}(id=${room.id})}--%>
                            <c:url var="buyUrl" value="/bar.html?id_food=${food.id}" />
                            <%--<c:url var="editUrl" value="/vehicleForm.html?id=${room.id}" />--%>
                            <a class="btn btn-primary" href="${buyUrl}">Kup</a>
                            <%--<a class="btn btn-success" href="${editUrl}">Edytuj</a>--%>
                            </security:authorize>


                            <security:authorize access="hasRole('ADMIN')">
                            <c:url var="deleteUrl" value="/bar.html?did=${food.id}" />
                            <%--<c:url var="editUrl" value="/vehicleForm.html?id=${room.id}" />--%>
                            <c:url var="editUrl" value="/foodForm.html?id=${food.id}" />
                            <a class="btn btn-warning" href="${editUrl}">Edytuj</a>
                            <a class="btn btn-danger" href="${deleteUrl}">Usuń</a>

                            <%--<a class="btn btn-success" href="${editUrl}">Edytuj</a>--%>
                        </td>
                        </security:authorize>

                </tr>
            </c:forEach>
            <%--</c:forEach>--%>
            </tbody>
        </table>

        <c:set var="page" value="${vehicleListPage}" scope="request"/>
        <c:set var="mainUrl" value="bar.html" scope="request"/>
        <%--<jsp:include page="shared/pagination.jsp"/>--%>

    </c:if>
    <security:authorize access="hasRole('ADMIN')">
        <a class="btn btn-success" href="foodForm.html">Dodaj Nowy</a>
    </security:authorize>

</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"/>
