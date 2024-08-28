<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="/public/CSS/400.css">
    <title>Error 404</title>
</head>
<body>


<div class="container">
    <section>

        <div class="row text-center">
            <h1 class="page-title">Error 404</h1>
        </div>

        <div class="row justify-content-center">

            <div id="lookinGlass" class="col col-6"></div>

        </div>
    </section>

    <section>
        <sec:authorize access="hasAnyAuthority('ADMIN')">
            <div style="margin-left:30px;" class="pb-5">
                <br><br>
                <c:if test="${not empty requestUrl}">
                    <p>${requestUrl}</p>
                </c:if>
                <h3>Stack Trace</h3>
                <c:if test="${not empty message}">
                    <p>${message}</p>
                </c:if>
                <c:if test="${not empty stackTrace}">
                    <p>${stackTrace}</p>
                </c:if>
                <c:if test="${not empty rootCause}">
                    <h3>Root Cause</h3>
                    <p>${rootCause}</p>
                </c:if>
                <c:if test="${not empty rootTrace}">
                    <p>${rootTrace}</p>
                </c:if>
            </div>
        </sec:authorize>
    </section>
</div>
</body>
</html>



