<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="shared/header.jsp">
    <jsp:param name="pageName" value="registrationForm"/>
</jsp:include>
<html>
<body>

<div id="main" class="container">

    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 ">
            <h1>Właśnie zostałeś zarejestrowany w systemie!</h1>
            Twoje konto nie jest jeszcze aktywne (flaga w bazie enabled=false),<br/>
            Po aktywacji konta możesz się  <a href="/login">zalogować</a>
        </div>
    </div>

</div>
</body>
</html>
<jsp:include page="shared/footer.jsp"></jsp:include>