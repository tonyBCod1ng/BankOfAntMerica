<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../Includes/Header.jsp"></jsp:include>

<div class="container">
    <section>

        <div class="row">
            <div class="col col-12" style="height: 10vh"></div>
        </div>
        <div class="row justify-content-center text-center">
            <div class="col col-8">
                <form>
                    <div>
                        <label for="name" class="form-label"><h4>Admin Search Tool</h4></label>
                        <input name="term" id="name" type="text" class="form-control" value="${term}" placeholder="Enter Search Term">
                    </div>
                    <button type="submit" class="btn btn-primary m-3">Search</button>
                </form>
            </div>
        </div>
    </section>
    <section>
        <div class="row justify-content-center">

            <table class="table table-striped table-primary col col-5" >
                <tr>
                    <th>Account Id</th>
                    <th>User Id</th>
                    <th>Account Amount</th>
                    <th>Branch Id</th>
                </tr>
                <c:forEach items="${foundAccounts}" var="account">

                    <tr>

                        <td>${account.id}</td>
                        <td>${account.userId}</td>
                        <td>${account.accountAmount}</td>
                        <td>${account.branchId}</td>
                    </tr>

                </c:forEach>
            </table>

        </div>
    </section>
</div>

<jsp:include page="../Includes/Footer.jsp"></jsp:include>