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
        <div class="table-responsive">
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
        <!-- PAGINATION -->
        <div id="pagination" class="text-center" style="padding-bottom: 20px;">
            <nav aria-label="Navigation for Trail" style="padding-top: 20px;">
                <ul class="pagination justify-content-center pagination-sm">
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=user&id_user=${user.id}&currentPage=${1}">Begin</a></li>
                    </c:if>
                    <c:if test="${currentPage != 1}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=user&id_user=${user.id}&currentPage=${currentPage-1}">Previous</a></li>
                    </c:if>

                    <c:choose>
                        <c:when test="${noOfPages le nbelem}">

                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="page-item active"><a class="page-link">
                                                ${i} <span class="sr-only">(current)</span></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=user&id_user=${user.id}&currentPage=${i}">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </c:when>
                        <c:otherwise>

                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="page-item active"><a class="page-link">
                                                ${i} <span class="sr-only">(current)</span></a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>

                                        <!-- NAVIGATION PAGES PAS OUF -->
                                        <c:choose>
                                            <c:when test="${i gt currentPage && i lt currentPage+5}">
                                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=user&id_user=${user.id}&currentPage=${i}">${i}</a>
                                                </li>
                                            </c:when>
                                            <c:when test="${i eq currentPage+5}">
                                                <li class="page-item"><a class="page-link" style="color: #0f0f0f">...</a>
                                                </li>
                                            </c:when>
                                            <c:when test="${i gt noOfPages-5}">
                                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=user&id_user=${user.id}&currentPage=${i}">${i}</a>
                                                </li>
                                            </c:when>
                                        </c:choose>
                                        <!-- NAVIGATION PAGES PAS OUF -->

                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${currentPage lt noOfPages}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=user&id_user=${user.id}&currentPage=${currentPage+1}">Next</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
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
