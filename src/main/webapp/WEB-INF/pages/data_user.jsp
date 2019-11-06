<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<!-- section -->
<section class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center">
            <div class="container">
                <div class="row">
                    <div class="text-white col-sm" style="padding-top: 30px;">
                        <img src="./grayscale/img/pict.jpg" style="width:400px;" class="img-fluid" alt="">
                    </div>
                    <div class="text-white col-sm" style="padding-bottom: 40px; width: 400px;">
                            <div class="form-group">
                                <label for="firstName">Firstname</label>
                                <input type="text" class="form-control" id="firstName" name="firstname" aria-describedby="emailHelp" value="${user.firstName}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Lastname</label>
                                <input type="text" class="form-control" id="lastName" name="lastname" value="${user.lastName}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="dateOfBirth">Date of Birth</label>
                                <input type="text" class="form-control" id="dateOfBirth" name="date" value="${user.dateOfBirth}" disabled>
                            </div>
                            <div class="form-group">
                                <label for="Email">Email</label>
                                <input type="email" class="form-control" id="Email" name="email" value="${user.email}" disabled>
                            </div>
                            <div style="font-size: large;"><p>And More if you Scroll!</p></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- About Section -->
<section class="about-section text-center">
    <div class="container" style="color: white;">
        <div class="row">
            <div class="table-responsive-lg">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Distance</th>
                        <th scope="col" style="white-space: nowrap;">Up and Down</th>
                        <th scope="col">Description</th>
                        <th scope="col">Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${regs}" var="reg">
                        <tr>
                            <td>${reg.trail.name}</td>
                            <td>${reg.trail.distance} KM</td>
                            <td>${reg.trail.upAndDown} M</td>
                            <td>${reg.trail.description}</td>
                            <td style="white-space: nowrap;">${reg.trail.date}</td>
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
