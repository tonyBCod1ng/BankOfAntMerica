<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../Includes/Header.jsp"/>

    <section>

        <div class="row">
            <div class="col col-12" style="height: 10vh"></div>
        </div>
        <div class="row justify-content-center text-center">
            <div class="col col-8">
                <form>
                    <div>
                        <label for="name" class="form-label"><h4>Admin Search Tool</h4></label>
                        <input name="term" id="name" type="text" class="form-control" value="${term}" placeholder="Enter Search Term">
                    </div>
                    <button type="submit" class="btn btn-primary m-3">Search</button>
                </form>
            </div>
        </div>
    </section>
    <section>
        <div class="row justify-content-center">

            <table class="table table-striped table-primary col col-5" >
                <c:choose>
                    <c:when test="${accountView != null}">
                    <tr>
                        <th>Account Id</th>
                        <th>User Id</th>
                        <th>Account Amount</th>
                        <th>Branch Id</th>
                    </tr>
                </c:when>
                    <c:otherwise>
                        <tr>
                            <th>User Id</th>
                            <th>Home Branch</th>
                            <th>E-mail</th>
                            <th>Name</th>
                            <th></th>
                        </tr>
                    </c:otherwise>
                </c:choose>
                <c:forEach items="${foundAccounts}" var="account">

                    <tr>
                        <td>${account.id}</td>
                        <td>${account.user.id}</td>
                        <td>${account.accountAmount}</td>
                        <td>${account.branchId}</td>
                    </tr>

                </c:forEach>
                <security:authorize access="hasAuthority('ADMIN')">
                    <c:forEach items="${users}" var="user">

                    <tr>
                        <td>${user.id}</td>
                        <td>${user.homeBranch}</td>
                        <td>${user.email}</td>
                        <td>${user.lastName}, ${user.firstName}</td>
                        <td><a href="http://localhost:8080/admin/edit/${user.id}">Edit</a></td>
                    </tr>

                </c:forEach>
                </security:authorize>
            </table>

        </div>
    </section>


<jsp:include page="../Includes/Footer.jsp"/>