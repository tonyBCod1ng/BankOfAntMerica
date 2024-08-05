<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>
<h2>Welcome, ${user.firstName}!</h2>
<h2>Account Details</h2>

<h3>Total Transactions Completed (${transactions.size()})</h3>
<h3>Total Accounts Open (${managedAccounts.size()}) </h3>

<table class="table">
    <tr>
        <th>Transaction Id</th>
        <th>Transaction Amount</th>
        <th>Transaction Date</th>
    </tr>
    <c:forEach items="${transactions}" var="transaction">
        <tr>
            <td>${transaction.id}</td>
            <td>${transaction.amount}</td>
            <td>${transaction.createDate}</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="../Includes/Footer.jsp"/>
