<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bank Of AntMerica</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quantico:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/public/CSS/global.css" >
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script>
        let body = document.getElementsByTagName("body")[0];

        function changeBkgrnd(url){
            body.style.background = url;
        }
    </script>

</head>
<body>

<div class="row justify-content-center cols-1">

    <div class="col">
        <nav class="navbar navbar-expand-lg navbar-light">
            <a style="padding-left: 5px;" class="navbar-brand" href="/">Bank Of AntMerica</a>

            <div class="navbar-expand" id="navbarSupportedContent">

                <ul class="navbar-nav">

                    <sec:authorize access="hasAuthority('ADMIN')">
                        <li class="nav-item">
                            <a class="nav-link" href="/users/create-account">Create Account</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/admin/searchTool">Search</a>
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
                            <button class="nav-link"><sec:authentication property="name"/></button>
                        </li>
                    </sec:authorize>
                </ul>

            </div>
        </nav>
    </div>
</div>
<div class="container">

<div style="height: 10vh"></div>
