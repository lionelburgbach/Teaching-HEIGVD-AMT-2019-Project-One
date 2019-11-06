<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<header class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center">
            <h1 class="mx-auto my-0 text-uppercase" >${trail.name}</h1>
            <input type="image" src="./assets/paper_img/down.png" style="padding-top:20px; width:30%;" onClick="document.getElementById('trailer').scrollIntoView();">
        </div>
    </div>
</header>

<!-- About Section -->
<section id="trailer" class="about-section text-center">
    <div class="container" style="color: white;">
        <div class="row">
            <div class="table-responsive">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th scope="col">Firstname</th>
                        <th scope="col">Lastname</th>
                        <th scope="col">Date of birth</th>
                        <th scope="col">More About Him/Her</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="reg" items="${regs}">
                        <tr>
                            <td>${reg.user.firstName}</td>
                            <td>${reg.user.lastName}</td>
                            <td>${reg.user.dateOfBirth}</td>
                            <form method="post" action="${pageContext.request.contextPath}/data?action=user">
                                <td><button type="submit" name ="user_id" value="${reg.user.id}" class="btn btn-outline-warning">See More</button></td>
                            </form>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</section>

<jsp:include page="include/footer.jsp" />

<!-- Bootstrap core JavaScript -->
<script src="./grayscale/vendor/jquery/jquery.min.js"></script>
<script src="./grayscale/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="./grayscale/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for this template -->
<script src="./grayscale/js/grayscale.min.js"></script>

</body>

</html>
