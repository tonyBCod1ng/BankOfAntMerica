<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../Includes/Header.jsp"/>


<div class="row" style="height: 30vh">
<div class="row justify-content-center text-center cols-2">
    <div class="col col-6"></div>
    <div class="col col-4">
        <h3 style="font-size: 40pt">Welcome, ${user.firstName}!</h3>
    </div>
</div>
</div>
<div  class="row justify-content-center text-center">
    <div class="col col-4">
        <table class="table table-primary">
            <tr>
                <th style="border-style: none">Total Transactions Completed </th>
                <td style="border-style: none">(${accountTransactions.size()})</td>
            </tr>
            <tr>
                <th style="border-style: none">Total Accounts Open</th>
                <td style="border-style: none">(${managedAccounts.size()})</td>
            </tr>

        </table>
    </div>
</div>
<div class="row justify-content-center text-center">
    <h2>Recent Transactions</h2>
</div>
<div class="row justify-content-center">
    <div class="col col-10">
        <table class="table table-striped table-primary">
            <tr>
                <th>Transaction Id</th>
                <th>Transaction Amount</th>
                <th>Account Id</th>
            </tr>
            <c:forEach items="${accountTransactions}" var="transaction">
                <tr>
                    <td>${transaction.id}</td>
                    <td><fmt:formatNumber type="currency">${transaction.amount}</fmt:formatNumber> </td>
                    <td>${transaction.accountId}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</div>
<jsp:include page="../Includes/Footer.jsp"/>