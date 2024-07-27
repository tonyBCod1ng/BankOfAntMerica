<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"></jsp:include>

<section>

    <div class="row justify-content-center text-center">
        <c:choose>
            <c:when test="${form == null}">
                <h4>Create Customer</h4>
            </c:when>
            <c:otherwise>
                <h4>Edit Customer</h4>
            </c:otherwise>
        </c:choose></div>
</section>
<section>


    <div class="row justify-content-center">
        <div class="col col-10">
            <form action="http://localhost:8080/customers/createSubmit">
                <input class="hidden" name="id" value="${form.id}">
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="reportsTo" class="form-label">Sales Rep</label>
                    </div>
                    <div class="col col-6">
                        <select id="reportsTo" name="reportsTo" class="form-select">
                            <c:forEach items="${reportsTo}" var="employee">
                                <option
                                        <c:if test="${employee.id == form.employee.id}">selected</c:if>
                                        value="${employee.id}">${employee.firstname} ${employee.lastname}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="customerName" class="form-label">Company Name</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.customerName}" id="customerName" name="customerName" class="form-control"
                               type="text"
                               aria-description="company name input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="contactFirstname" class="form-label">First Name</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.contactFirstname}" id="contactFirstname" name="contactFirstname"
                               class="form-control" type="text"
                               aria-description="contact firstname input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="contactLastname" class="form-label">Last Name</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.contactLastname}" id="contactLastname" name="contactLastname"
                               class="form-control" type="text"
                               aria-description="contact firstname input">
                    </div>
                </div>

                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="phone" class="form-label">Phone</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.phone}" id="phone" name="phone" class="form-control" type="tel"
                               aria-description="phone input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col-2">
                        <label for="addressLine1" class="form-label">Address Line 1</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.addressLine1}" id="addressLine1" name="addressLine1" class="form-control"
                               type="text"
                               aria-description="address line 1 input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="addressLine2" class="form-label">Address Line 1</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.addressLine2}" id="addressLine2" name="addressLine2" class="form-control"
                               type="text"
                               aria-description="address line 1 input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="postalCode" class="form-label">Postal Code</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.postalCode}" id="postalCode" name="postalCode" class="form-control"
                               type="number"
                               aria-description="address line 1 input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="city" class="form-label">City</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.city}" id="city" name="city" class="form-control" type="text"
                               aria-description="city input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="state" class="form-label">State</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.state}" id="state" name="state" class="form-control" type="text"
                               aria-description="state input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="country" class="form-label">Country</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.country}" id="country" name="country" class="form-control" type="text"
                               aria-description="state input">
                    </div>
                </div>
                <div class="row justify-content-center cols-1">
                    <div class="col col-1">
                        <button type="submit" class="btn btn-primary m-1">Submit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</section>
<jsp:include page="../Includes/Footer.jsp"></jsp:include>