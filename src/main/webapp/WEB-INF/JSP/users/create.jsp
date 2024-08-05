<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"/>
<section>

    <div class="row justify-content-center text-center">
        <c:choose>
            <c:when test="${form == null}">
                <h4>Create Account</h4>
            </c:when>
            <c:otherwise>
                <h4>Edit Account</h4>
            </c:otherwise>
        </c:choose></div>
</section>
<section>
    <div class="row justify-content-center">
        <div class="col col-10">
            <form action="/account/create-account" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${form.id}">
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="branches" class="form-label">Home Branch</label>
                    </div>
                    <div class="col col-6">
                        <select id="branches" name="reportsTo" class="form-select">
                            <c:forEach items="${branches}" var="branch">
                                <option
                                        <c:if test="${branch.id == form.branch.id}">selected</c:if>
                                        value="${branch.id}">${branch.city}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="email" class="form-label">Email</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.email}" id="email" name="username" class="form-control"
                               type="email"
                               aria-description="email input">
                    </div>
                </div>
                <div class="row justify-content-center m-4 cols-2">
                    <div class="col col-2">
                        <label for="password" class="form-label">Password</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.password}" id="password" name="password" class="form-control"
                               type="password"
                               aria-description="password input">
                    </div>
                </div>

                <div style="background-color: orangered" class="row justify-content-center gx-4 col-8">

                    <div class="col col-2">
                        <input value="${form.firstName}" id="firstName" name="firstName"
                               placeholder="First Name"
                               class="form-control" type="text"
                               aria-description="first name input">
                    </div>


                    <div class="col-2">
                        <input value="${form.lastName}" id="lastName" name="lastName"
                               placeholder="Last Name"
                               class="form-control" type="text"
                               aria-description="last name input">
                    </div>
                </div>
                <sec:authorize access="hasAuthority('ADMIN')">
                    <div class="row justify-content-center m-4 cols-2">
                        <div class="col-2">
                            <label for="role" class="form-label">Role</label>
                        </div>
                        <div class="col-6">
                            <input value="${form.role}" id="role" name="role" class="form-control"
                                   type="text"
                                   aria-description="role input">
                        </div>
                    </div>
                </sec:authorize>

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
                        <label for="zipcode" class="form-label">Postal Code</label>
                    </div>
                    <div class="col-6">
                        <input value="${form.zipcode}" id="zipcode" name="zipcode" class="form-control"
                               type="number"
                               aria-description="zipcode input">
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
                               aria-description="country input">
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
<jsp:include page="../Includes/Footer.jsp"/>