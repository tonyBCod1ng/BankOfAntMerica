<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bank Of AntMerica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="/public/CSS/global.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>

</head>
<body>

<div class="row justify-content-center cols-1">

    <div class="col">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a style="padding-left: 5px;" class="navbar-brand" href="/">Bank Of AntMerica</a>

            <div class="navbar-expand" id="navbarSupportedContent">

                <ul class="navbar-nav">

                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/account/create-account">Create Account</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="!isAuthenticated()">
                        <li class="nav-item">
                            <a class="nav-link" href="/auth/login">Sign In</a>
                        </li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li class="nav-item">
                            <form action="/auth/logout" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="nav-link" type="submit">Log Out</button>
                            </form>
                        </li>
                        <li>
                            <span class="nav-link">Welcome, <sec:authentication property="name"/>!</span>
                        </li>
                    </sec:authorize>
                </ul>

            </div>
        </nav>
    </div>
</div>
<div class="container">

<div style="height: 10vh"></div>
