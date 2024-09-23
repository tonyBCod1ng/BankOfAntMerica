<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="Includes/Header.jsp"/>
<script>
    function removePopup(){
        let target = document.getElementById('transfer-popup');
        target.hidden = true;
    }
</script>
<section style="margin-top: 100px">
    <c:if test="${!bindingResult.hasErrors() && form != null}">
        <div id="transfer-popup" onclick="removePopup()" class="row pt-5 justify-content-center" style="opacity: 0.8">
            <div class="col-6">
                <div class="alert alert-success text-success" role="alert">Transfer Successful: Transferred <fmt:formatNumber type="currency">${senderTransaction.amount * -1}</fmt:formatNumber>  from Account Id ${senderTransaction.accountId} to Account Id:${receiverTransaction.accountId} on ${senderTransaction.createDate}</div>
            </div>
        </div>
    </c:if>
    <div class="row p-2" >
        <div style="background-color: yellow; opacity: 0.95;background-color: rgb(107,117,132); border-radius: 20px; height: 27%; width: 85%;object-fit: contain;   position: absolute; z-index: -1"></div>
        <div class="row justify-content-center text-center" style="height: 5vh"></div>
        <h2 style="position: absolute; z-index:1">Transfer Funds</h2>

        <form class="form" action="/" method="post">
            <div class="row justify-content-center m-4 cols-3">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="col col-4">
                    <select id="sender" name="sender" class="form-select
                    <c:if test='${bindingResult.hasFieldErrors("sender")}'>
                                    is-invalid
                                    </c:if>
                                    <c:if test='${!bindingResult.hasFieldErrors("sender") && form != null}'>
                                    is-valid
                                    </c:if>">
                        <option disabled selected hidden value="-1">Select Sending Account</option>
                        <c:forEach items="${accounts}" var="account">

                        <option <c:if test="${form.sender == account.id}">selected</c:if> value="${account.id}">${account.accountType} <fmt:formatNumber type="currency">${account.accountAmount}</fmt:formatNumber></option>
                        </c:forEach>
                    </select>
                    <c:if test="${bindingResult.hasFieldErrors('sender')}">

                        <div class="invalid-feedback">
                            <c:forEach items="${bindingResult.getFieldErrors('sender')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>

                    </c:if>
                </div>
                <div class="col-2">

                    <input value="${form.transferAmount}" min="0.00" max="10000.00" step="0.01" id="transferAmount" name="transferAmount"
                           class="form-control
                           <c:if test='${bindingResult.hasFieldErrors("transferAmount")}'>
                                    is-invalid
                                    </c:if>
                                    <c:if test='${!bindingResult.hasFieldErrors("transferAmount") && form != null}'>
                                    is-valid
                                    </c:if>>"
                           type="number"
                           placeholder="Transfer Amount"
                           aria-description=" transfer amount input">
                    <c:if test="${bindingResult.hasFieldErrors('transferAmount')}">

                        <div class="invalid-feedback">
                            <c:forEach items="${bindingResult.getFieldErrors('transferAmount')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>

                    </c:if>
                </div>
                <div class="col col-4">
                    <select id="receiver" name="receiver"
                            class="form-select
                            <c:if test='${bindingResult.hasFieldErrors("receiver")}'>
                                    is-invalid
                                    </c:if>
                                    <c:if test='${!bindingResult.hasFieldErrors("receiver") && form != null}'>
                                    is-valid
                                    </c:if>">
                        <option disabled selected hidden value="">Select Receiving Account</option>
                        <c:forEach items="${accounts}" var="account">
                            <option <c:if test="${form.receiver == account.id}">selected</c:if> value="${account.id}">${account.accountType} <fmt:formatNumber type="currency">${account.accountAmount}</fmt:formatNumber> </option>

                        </c:forEach>
                    </select>
                    <c:if test="${bindingResult.hasFieldErrors('receiver')}">

                        <div class="invalid-feedback">
                            <c:forEach items="${bindingResult.getFieldErrors('receiver')}" var="error">
                                ${error.defaultMessage}<br>
                            </c:forEach>
                        </div>

                    </c:if>
                </div>
            </div>
            <button class="btn btn-primary" type="submit">Submit</button>
        </form>
    </div>

</section>
<jsp:include page="Includes/Footer.jsp"/>

