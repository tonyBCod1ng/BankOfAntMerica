<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../Includes/Header.jsp"/>

<section>
    <div class="row justify-content-center text-center">
        <h2>Transaction Details</h2>
    </div>
</section>

<div class="row justify-content-center">
    <div class="col col-4">
        <table class="table table-striped">
            <tr>
                <th>Transaction Id:</th>
                <td>${transaction.id}</td>
            </tr>
            <tr>
                <th>Account Id:</th>
                <td>${transaction.accountId}</td>
            </tr>
            <tr>
                <th>Amount:</th>
                <td>${transaction.amount}</td>
            </tr>
            <tr>
                <th>Create Date:</th>
                <td>${transaction.createDate}</td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="../Includes/Footer.jsp"/>
