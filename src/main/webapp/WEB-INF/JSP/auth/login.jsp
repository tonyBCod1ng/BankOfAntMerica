<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>
<section>
    <div class="row" style="height: 5vh"></div>
    <c:if test="${param['error'] eq ''}">
        <div class="row pt-5 justify-content-center">
            <div class="col-6">
                <div class="alert alert-danger" role="alert">Invalid Username or Password</div>
            </div>
        </div>
    </c:if>
    <div class="row justify-center col-4">
<div class="back"></div>
    <h1>Bank Of AntMerica</h1>
            <div class="col">
                <form action="/auth/login/loginSubmit" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <div class="form-group">
                        <label for="username">Email address</label>
                        <input name="username" type="email" class="form-control" id="username"
                               aria-describedby="username"
                               placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input name="password" type="password" class="form-control" id="password"
                               placeholder="Password">
                    </div>

                    <button type="submit" class="btn btn-primary m-3">Sign In</button>
                    <a href="/users/create-account">Join the Colony!</a>
                </form>
            </div>
    </div>
</section>
<jsp:include page="../Includes/Footer.jsp"/>

