<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"/>

<section>
    <div class="row justify-content-center cols-1">

        <div class="create-back "></div>
        <div class="col p-4 col-10 align-items-center">
            <div class="row justify-content-center text-center">
                <c:choose>
                    <c:when test="${form == null}">
                        <h4>Create Account</h4>
                    </c:when>
                    <c:otherwise>
                        <h4>Edit Account</h4>
                    </c:otherwise>
                </c:choose></div>
            <form class="form"
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <c:choose>
                            <c:when test="${form == null}">
                            action="/admin/create-account"
                        </c:when>
                            <c:otherwise>
                                action="/admin/edit/${form.id}"
                            </c:otherwise>
                        </c:choose>

                    </sec:authorize>
                    <sec:authorize access="hasAuthority('USER')">
                        action="/users/create-account"

                    </sec:authorize>
                  method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="id" value="${form.id}">
                <div class="row justify-content-center m-4">

                    <div class="col col-6">
                        <select id="branches" name="branch" class="form-select">
                            <c:forEach items="${branches}" var="branch">
                                <option
                                        <c:if test="${branch.id == homeBranch.id}">selected</c:if>
                                        value="${branch.id}">${branch.city}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row justify-content-center m-4">

                    <div class="col-6">
                        <input value="${form.email}" id="email" name="username" class="form-control"
                               type="email"
                               placeholder="E-mail"
                               aria-description="email input">
                    </div>
                </div>
                <c:if test="${form == null}">
                    <div class="row justify-content-center m-4">
                        <div class="col-6">
                            <input id="password" name="password" class="form-control"
                                   type="password"
                                   placeholder="Password"
                                   aria-description="password input">
                        </div>
                    </div>
                </c:if>

                <div class="row justify-content-center m-4">

                    <div class="col col-3">
                        <input value="${form.firstName}" id="firstName" name="firstName"
                               placeholder="First Name"
                               class="form-control" type="text"
                               aria-description="first name input">
                    </div>


                    <div class="col col-3">
                        <input value="${form.lastName}" id="lastName" name="lastName"
                               placeholder="Last Name"
                               class="form-control" type="text"
                               aria-description="last name input">
                    </div>
                    <sec:authorize access="hasAuthority('ADMIN')">
                        <div class="col col-2 form-check">
                            <label class="form-radio-label" for="role">
                                Admin:
                            </label>
                            <input name="role" class="form-check-input" type="checkbox" value="ADMIN" id="role"
                                   <c:if test="${roles.contains('ADMIN')}">checked</c:if> >
                        </div>
                    </sec:authorize>
                </div>


                <div class="row justify-content-center m-4 cols-4">
                    <div class="col-4">
                        <input value="${form.addressLine1}" id="addressLine1" name="addressLine1" class="form-control"
                               type="text"
                               placeholder="Address Line 1"
                               aria-description="address line 1 input">
                    </div>
                    <div class="col-2">
                        <input value="${form.addressLine2}" id="addressLine2" name="addressLine2" class="form-control"
                               type="text"
                               placeholder="Address Line 2"
                               aria-description="address line 1 input">
                    </div>
                    <div class="col-2">
                        <input placeholder="City" value="${form.city}" id="city" name="city" class="form-control"
                               type="text"
                               aria-description="city input">
                    </div>
                </div>

                <div class="row justify-content-center m-4 cols-3">

                    <div class="col-2">
                        <input placeholder="State" value="${form.state}" id="state" name="state" class="form-control"
                               type="text"
                               aria-description="state input">
                    </div>
                    <div class="col-2">
                        <input placeholder="Country" value="${USA}" id="country" name="country"
                               class="form-control" type="text"
                               aria-description="country input">
                    </div>
                    <div class="col-2">
                        <input value="${form.zipcode}" id="zipcode" name="zipcode" class="form-control"
                               type="number"
                               placeholder="Zipcode"
                               aria-description="zipcode input">
                    </div>
                </div>
                <sec:authorize access="hasAuthority('ADMIN')">

                    <div class="row justify-content-center text-center m-4 cols-3">
                        <h5>Banking</h5>
                        <div class="col-2">
                            <input placeholder="Account Type" id="account-type" name="accountType" class="form-control"
                                   type="text"
                                   aria-description="account type input">
                        </div>
                        <div class="col-2">
                            <input placeholder="Initial Amount" value="" id="initial-amount" name="accountAmount"
                                   class="form-control" type="number"
                                   aria-description="account amount input">
                        </div>
                    </div>

                </sec:authorize>

                <div class="row m-4 justify-content-center cols-1">
                    <div class="col col-1">
                        <button type="submit" class="btn btn-primary m-1">Submit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</section>

<jsp:include page="../Includes/Footer.jsp"/>