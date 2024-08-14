<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"></jsp:include>


<div class="row text-center">
    <div class="col col-1"><a class="btn btn-primary" href="../dashboard">Back</a></div>
</div>

<div class="row justify-content-center text-center">
    <div class="col col-4">
        <table class="table">
            <tr>
                <th>Account Id</th>
                <td>${account.id}</td>
            </tr>
            <tr>
                <th>Account Balance</th>
                <td>$${account.accountAmount}.00</td>
            </tr>
        </table>
    </div>
</div>
<div class="row justify-content-center">
    <div class="col col-4">
        <table class="table">
            <tr>
                <th>Recent Transactions</th>
                <th></th>
            </tr>
            <tr>
                <th>Date</th>
                <th>Amount</th>
            </tr>
            <c:forEach items="${transactions}" var="transaction">
                <tr>
                    <td>${transaction.createDate}</td>
                    <td>$${transaction.amount}.00</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../Includes/Footer.jsp"></jsp:include>