<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="Includes/Header.jsp"/>
<section>
    <div class="row" style="height: 5vh"></div>
    <h1>Transfer Funds</h1>

    <form class="form" action="/" method="post" >
        <div class="row justify-content-center m-4 cols-3">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="col col-4">
                <select id="sender" name="sender" class="form-select">
                    <option disabled selected hidden value="">Select Sending Account</option>
                    <c:forEach items="${accounts}" var="account">
                        <option value="${account.id}">${account.id}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-2">
                <input value="" id="transferAmount" name="transferAmount" class="form-control"
                       type="number"
                       placeholder="Transfer Amount"
                       aria-description=" transfer amount input">
            </div>
            <div class="col col-4">
                <select id="receiver" name="receiver" class="form-select">
                    <option disabled selected hidden value="">Select Receiving Account</option>
                    <c:forEach items="${accounts}" var="account">
                        <option
                                value="${account.id}">${account.id}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <button type="submit">Submit</button>
    </form>

</section>
<jsp:include page="Includes/Footer.jsp"/>

