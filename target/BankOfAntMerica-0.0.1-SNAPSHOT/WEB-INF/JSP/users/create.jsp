<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"/>

<section style="margin-top: -22px">
    <div  class="row justify-content-center align-items-center">

        <div  class="col p-4 col-10 justify-content-center align-items-center">
            <div style="background-color: rgb(123, 238, 233, 75%); border-radius: 80px;">
<%--                <div class="create-back"></div>--%>
                <div  class="row justify-content-center text-center pt-4">
                    <c:choose>
                        <c:when test="${form == null}">
                            <h4>Create Account</h4>
                        </c:when>
                        <c:otherwise>
                            <h4>Update Account Info</h4>
                        </c:otherwise>
                    </c:choose>
                </div>
                <form class="form"
                        <sec:authorize access="hasAuthority('ADMIN')">
                            <c:choose>
                                <c:when test="${form.id == null}">
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

                        <div class="col-4">
                            <input value="${form.email}" id="email" name="email"
                                   class="form-control
                                    <c:if test='${bindingResult.hasFieldErrors("email")}'>
                                    is-invalid
                                    </c:if>
                                    <c:if test='${!bindingResult.hasFieldErrors("email") && form != null}'>
                                    is-valid
                                    </c:if>"
                                   type="text"
                                   placeholder="E-mail"
                                   aria-description="email input">
                            <c:if test="${bindingResult.hasFieldErrors('email')}">

                                <div class="invalid-feedback">
                                    <c:forEach items="${bindingResult.getFieldErrors('email')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>

                            </c:if>
                            <c:if test="${!bindingResult.hasFieldErrors('email') && form.email != null}">
                                <div class=" col-3 valid-feedback ">
                                    Looks Great! <img style="width: 53px" src="../../public/images/thumbsUp.png"/></div>
                            </c:if>
                        </div>
                        <c:if test="${form == null || currentPage != 'edit'}">

                            <div class="col-4">
                                <input id="password" name="password"
                                       class="form-control
                                    <c:if test='${bindingResult.hasFieldErrors("password")}'>
                                        is-invalid
                                    </c:if>
                                    <c:if test='${!bindingResult.hasFieldErrors("password") && form.password !=null}'>
                                           is-valid
                                    </c:if>"
                                       type="password"
                                       value="${form.password}"
                                       placeholder="Password"
                                       aria-description="password input"/>
                                <c:if test="${bindingResult.hasFieldErrors('password')}">

                                    <div class=" col-3 invalid-feedback ">
                                        Try again, ya donut! <img style="width: 40px"
                                                                  src='https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fblog.joypixels.com%2Fcontent%2Fimages%2F2019%2F12%2Fthumbs_down.gif&f=1&nofb=1&ipt=12b75481775a95e00888c1b7e770de734b30d35e7f0c700f54b0a28c43411dbf&ipo=images'>
                                        <br/>
                                        <c:forEach items="${bindingResult.getFieldErrors('password')}" var="error">
                                            ${error.defaultMessage} <br>
                                        </c:forEach>
                                    </div>

                                </c:if>
                                <c:if test="${!bindingResult.hasFieldErrors('password') && form.password != null}">
                                    <div class=" col-3 valid-feedback ">
                                        Passwordification Complete! <img style="width: 53px"
                                                                         src="../../public/images/thumbsUp.png"/>
                                    </div>
                                </c:if>
                            </div>
                            </se>
                        </c:if>

                    </div>

                    <div class="row justify-content-center m-4">

                    </div>

                    <div class="row justify-content-center m-4">

                        <div class="col col-3">
                            <input value="${form.firstName}" id="firstName" name="firstName"
                                   placeholder="First Name"
                                   class="form-control
                               <c:if test='${bindingResult.hasFieldErrors("firstName")}'>
                                    is-invalid
                               </c:if>
                            <c:if test='${!bindingResult.hasFieldErrors("firstName") && form != null}'>
                                    is-valid
                               </c:if>"
                                   type="text"
                                   aria-description="first name input">
                            <c:if test="${bindingResult.hasFieldErrors('firstName')}">
                                <div class="invalid-feedback">
                                    <c:forEach items="${bindingResult.getFieldErrors('firstName')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${!bindingResult.hasFieldErrors('firstName') && form.password != null}">
                                <div class=" col-3 valid-feedback ">
                                    Looks Great! <img style="width: 53px" src="../../public/images/thumbsUp.png"/></div>
                            </c:if>
                        </div>


                        <div class="col col-3">
                            <input value="${form.lastName}" id="lastName" name="lastName"
                                   placeholder="Last Name"
                                   class="form-control
                                <c:if test='${bindingResult.hasFieldErrors("lastName")}'>
                                    is-invalid
                               </c:if>
                                <c:if test='${!bindingResult.hasFieldErrors("lastName") && form != null}'>
                                    is-valid
                               </c:if>"
                                   type="text"
                                   aria-description="last name input">
                            <c:if test="${bindingResult.hasFieldErrors('lastName')}">
                                <div class="invalid-feedback">
                                    <c:forEach items="${bindingResult.getFieldErrors('lastName')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </c:if>
                            <c:if test="${!bindingResult.hasFieldErrors('lastName') && form.password != null}">
                                <div class=" col-3 valid-feedback ">
                                    Looks Great! <img style="width: 53px" src="../../public/images/thumbsUp.png"/></div>
                            </c:if>
                        </div>
                        <sec:authorize access="hasAuthority('ADMIN')">
                            <div class="col col-2 form-check">
                                <input name="role" class="form-check-input" type="checkbox" value="ADMIN" id="role"
                                       <c:if test="${roles.contains('ADMIN')}">checked</c:if> >
                                <label class="form-radio-label" for="role">
                                    Admin
                                </label>
                            </div>
                        </sec:authorize>
                    </div>


                    <div class="row justify-content-center m-4 cols-4">
                        <div class="col-4">
                            <input value="${form.addressLine1}" id="addressLine1" name="addressLine1"
                                   class="form-control"
                                   type="text"
                                   placeholder="Address Line 1"
                                   aria-description="address line 1 input">
                        </div>
                        <div class="col-2">
                            <input value="${form.addressLine2}" id="addressLine2" name="addressLine2"
                                   class="form-control"
                                   type="text"
                                   placeholder="Address Line 2"
                                   aria-description="address line 1 input">
                        </div>
                        <div class="col-2">
                            <input placeholder="City" value="${form.city}" id="city" name="city"
                                   class="form-control"
                                   type="text"
                                   aria-description="city input">
                        </div>
                    </div>

                    <div class="row justify-content-center m-4 cols-3">

                        <div class="col-2">
                            <input placeholder="State" value="${form.state}" id="state" name="state"
                                   class="form-control"
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

                        <div class="row justify-content-center text-center align-items-center m-4 cols-2">
                            <h5>Banking</h5>
                            <div class="col-2">
                                <div class="row justify-content-center align-items-center">
                                    <div class="col">
                                        <label for="debit" class="form-label">Debit</label>
                                    </div>
                                    <div class="col">
                                        <input
                                                id="debit"
                                                name="accountType"
                                                class="form-check"
                                                type="radio"
                                                value="Debit"
                                                aria-description="account type input">
                                    </div>
                                </div>

                                <div class="row justify-content-center align-items-center">
                                    <div class="col">
                                        <label for="credit" class="form-label">Credit</label>
                                    </div>
                                    <div class="col">
                                        <input
                                                id="credit"
                                                name="accountType"
                                                class="form-check"
                                                type="radio"
                                                value="CREDIT"
                                                aria-description="account type input">
                                    </div>
                                </div>

                                <div class="row justify-content-center align-items-center">
                                    <div class="col">
                                        <label for="savings" class="form-label">Savings</label>
                                    </div>
                                    <div class="col">
                                        <input
                                                id="savings"
                                                name="accountType"
                                                class="form-check"
                                                type="radio"
                                                value="SAVINGS"
                                                aria-description="account type input">
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <input placeholder="Initial Amount" value="" id="initial-amount"
                                       name="accountAmount"
                                       class="form-control" type="number"
                                       aria-description="account amount input">
                            </div>
                        </div>

                    </sec:authorize>

                    <div class="row m-4 justify-content-center text-center cols-1">
                        <div class="col col-1" style="margin-right: 30px">
                            <button type="submit" class="btn btn-primary m-1">Submit</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>

</section>

<jsp:include page="../Includes/Footer.jsp"/>