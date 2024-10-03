<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>
<section>
    <div style="height: 20vh;" class="row">
        <div class="col mt-4">
            <h2 style="text-align: right;font-size: 48pt; color: rgb(251,232,80)">Welcome, ${currentUser.firstName}!</h2>
        </div>
    </div>

</section>
<section>
    <div class="row justify-content-center align-items-center">

        <div class="col col-6">
            <table class="table table-info table-striped table-hover bg-info">
                <thead >
                <tr >
                    <th>
                        <h3>Open Accounts </h3>
                    </th>
                    <th></th>
                    <th></th>
                </tr>
                <tr>
                    <th>Account Id</th>
                    <th>Account Type</th>
                    <th>Account Amount</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${managedAccounts}" var="account">
                    <tr onclick="window.location.assign('/users/account/${account.id}')">
                        <td>${account.id}</td>
                        <td>${account.accountType}</td>
                        <td><fmt:formatNumber type="currency" >${account.accountAmount}</fmt:formatNumber></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../Includes/Footer.jsp"/>
