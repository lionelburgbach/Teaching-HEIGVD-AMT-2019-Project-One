<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<header class="masthead">
    <div class="container d-flex h-100 align-items-center">
        <div class="mx-auto text-center">
            <h1 class="mx-auto my-0 text-uppercase" style="font-size: 60px;" >${trail.name}</h1>
            <input type="image" src="./assets/paper_img/down.png"  style="padding-top:20px; width:100px; height: auto;" onClick="document.getElementById('trailer').scrollIntoView();">
        </div>
    </div>
</header>

<section id="trailer" class="about-section">
    <c:choose>
        <c:when test="${not empty error}">
            <div class="container" style="color: white; text-align: center; padding-bottom: 20px;">
                <p style="font-size: 50px;">${error}</p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="container" style="color: white; padding-bottom: 20px;">
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
                                <form method="post" action="${pageContext.request.contextPath}/user/data?action=user">
                                    <td><button type="submit" name ="user_id" value="${reg.user.id}" class="btn btn-outline-warning">See More</button></td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- PAGINATION -->
            <div id="pagination" class="text-center" style="padding-bottom: 20px;">
                <nav aria-label="Navigation for Trail" style="padding-top: 20px;">
                    <ul class="pagination justify-content-center pagination-sm">
                        <c:if test="${currentPage != 1}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=registers&currentPage=${1}">Begin</a></li>
                        </c:if>
                        <c:if test="${currentPage != 1}">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=registers&currentPage=${currentPage-1}">Previous</a></li>
                        </c:if>

                        <c:choose>
                            <c:when test="${noOfPages lt 10}">

                                <c:forEach begin="1" end="${noOfPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><a class="page-link">
                                                    ${i} <span class="sr-only">(current)</span></a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=registers&currentPage=${i}">${i}</a>
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
                                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=registers&currentPage=${i}">${i}</a>
                                                    </li>
                                                </c:when>
                                                <c:when test="${i eq currentPage+5}">
                                                    <li class="page-item"><a class="page-link" style="color: #0f0f0f">...</a>
                                                    </li>
                                                </c:when>
                                                <c:when test="${i gt noOfPages-5}">
                                                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=registers&currentPage=${i}">${i}</a>
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
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/user/data?action=registers&currentPage=${currentPage+1}">Next</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
        </c:otherwise>
    </c:choose>
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
