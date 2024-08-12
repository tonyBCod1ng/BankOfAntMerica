<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>
<section>
    <div style="height: 20vh;" class="row justify-content-right">
        <div class="col">
            <h2>Welcome, ${currentUser.firstName}!</h2>
        </div>
    </div>

</section>
<table class="table table-info table-striped bg-info">
    <tr>
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
    <c:forEach items="${managedAccounts}" var="account">
        <tr>
            <td>${account.id}</td>
            <td>${account.accountType}</td>
            <td>${account.accountAmount}</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../Includes/Footer.jsp"/>
