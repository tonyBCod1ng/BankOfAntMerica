<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<jsp:include page="Includes/Header.jsp"/>
<section>
    <div class="row" style="height: 5vh"></div>
<h1>Bank Of AntMerica</h1>

    <%--<div class="row justify-center">
        <div class="col col-lg-4 col-md-5">
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
            </form>
        </div>
    </div>--%>
</section>
<jsp:include page="Includes/Footer.jsp"/>

