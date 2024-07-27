<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"></jsp:include>


<div class="row text-center">
    <div class="col col-1"><a class="btn btn-primary" href="/../customers/">Back</a></div>
    <div class="col col-10"><h2>${customer.customerName}</h2></div>
    <div class="col col-1"><a class="btn btn-warning" href="/../customers/edit/${customer.id}">Edit</a></div>
</div>
</div>
<div class="row justify-content-center text-center">
    <div class="col col-4">
        <table class="table">
            <tr>
                <th style="border-style: none">Id</th>
                <td style="border-style: none">${customer.id}</td>
            </tr>
            <tr>
                <th style="border-style: none">Contact</th>
                <td style="border-style: none">${customer.contactLastname}, ${customer.contactFirstname}</td>
            </tr>
            <tr>
                <th style="border-style: none">Phone</th>
                <td style="border-style: none">${customer.phone}</td>
            </tr>
            <tr>
                <th style="border-style: none">Sales Rep</th>
                <td style="border-style: none">
                    <a href="http://localhost:8080/employees/employee/${customer.salesRepEmployeeId}"> ${customer.employee.lastname}, ${customer.employee.firstname}</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<div class="row justify-content-center text-center">
    <h2>${customer.customerName}'s Orders ( ${customer.orders.size()} )</h2>
</div>
<div class="row justify-content-center">
    <div class="col col-10">
        <table class="table">
            <tr>
                <th>Order Id</th>
                <th>Order Date</th>
                <th>Required Date</th>
                <th>Order Comment</th>
            </tr>
            <c:forEach items="${customer.orders}" var="order">
                <tr>
                    <td><a href="http://localhost:8080/orders/order/${order.id}">${order.id}</a></td>
                    <td>${order.orderDate}</td>
                    <td>${order.requiredDate}</td>
                    <td>${order.comment}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../Includes/Footer.jsp"></jsp:include>