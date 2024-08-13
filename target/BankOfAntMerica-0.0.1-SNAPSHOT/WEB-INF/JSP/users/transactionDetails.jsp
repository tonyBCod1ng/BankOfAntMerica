<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>

<section>
    <div class="row justify-content-center text-center">
        <h2>Transaction Details</h2>
    </div>
</section>

<table class="table">
    <tr>
        <th>Transaction Id</th>
        <th>Account Id</th>
        <th>Transaction Amount</th>
        <th>Transaction Date</th>
    </tr>
    <tr>
        <td>${transaction.id}</td>
        <td>${transaction.accountId}</td>
        <td>${transaction.amount}</td>
        <td>${transaction.createDate}</td>
    </tr>
</table>

<jsp:include page="../Includes/Footer.jsp"/>
