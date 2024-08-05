<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>
<section class="login-block">
    <div class="row" style="height: 5vh"></div>
    <h1>Bank Of AntMerica</h1>
<c:if test="${param = 'error' eq ''}"><h2>Test</h2></c:if>
    <div class="row justify-center col-4">
        <div class="col">
            <form action="/auth/login/loginSubmit" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <label for="username">Email address</label>
                    <input name="username" type="email" class="form-control" id="username" aria-describedby="username"
                           placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password">
                </div>

                <button type="submit" class="btn btn-primary">Submit</button>
                <a href="/account/create-account">Join the Colony!</a>
            </form>
        </div>
    </div>
</section>
<jsp:include page="../Includes/Footer.jsp"/>

