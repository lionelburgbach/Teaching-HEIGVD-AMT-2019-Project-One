<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<!-- Header -->
<header class="masthead">
  <div class="container d-flex h-100 align-items-center">
    <div class="mx-auto text-center">
      <h1 class="mx-auto my-0 text-uppercase" style="font-size: 60px;">Trails</h1>
      <input type="image" src="./assets/paper_img/down.png" style="padding-top:20px; width:100px; height: auto;" onClick="document.getElementById('trail').scrollIntoView();">
    </div>
  </div>
</header>

<!-- About Section -->
<section id="trail" class="about-section text-center">

  <% if (session.getAttribute("user") != null){ %>
  <div class="wrapper" id="addTrail" style="padding-bottom: 50px;">
    <button onclick="addTrail()" class="btn btn-outline-warning">Add Trail</button>
  </div>
  <% } %>

  <div class="container" style="color: white;">
    <div class="table-responsive">
      <table class="table table-dark">
        <thead>
        <tr>
          <th scope="col">Name</th>
          <th scope="col">Distance</th>
          <th scope="col">Up and Down</th>
          <th scope="col">Description</th>
          <th scope="col">Date</th>
          <th scope="col">Places Left</th>
          <th scope="col">Registration</th>
          <th scope="col">Who is in?</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="trail" items="${trails}">
          <tr>
            <td>${trail.name}</td>
            <td>${trail.distance} KM</td>
            <td style="white-space: nowrap;">${trail.upAndDown} M</td>
            <td>${trail.description}</td>
            <td style="white-space: nowrap;">${trail.date}</td>
            <td>${trail.capacity - trail.nbIn}</td>
            <form method="post" action="${pageContext.request.contextPath}/user/registration?action=enroll">
              <td><button type="submit" name ="trail_id" value="${trail.id}" class="btn btn-outline-warning">Enroll Me</button></td>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/user/data?action=registers">
              <td><button type="submit" name ="trail_id" value="${trail.id}" class="btn btn-outline-warning">Show Registers</button></td>
            </form>
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
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/trail?currentPage=${1}">Begin</a></li>
          </c:if>
          <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/trail?currentPage=${currentPage-1}">Previous</a></li>
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
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/trail?currentPage=${i}">${i}</a>
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

                    <!-- NAVIGATION PAGES PAS OUF DU TOUT -->
                    <c:choose>
                      <c:when test="${i gt currentPage && i lt currentPage+5}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/trail?currentPage=${i}">${i}</a>
                        </li>
                      </c:when>
                      <c:when test="${i eq currentPage+5}">
                        <li class="page-item"><a class="page-link" style="color: #0f0f0f">...</a>
                        </li>
                      </c:when>
                      <c:when test="${i gt noOfPages-5}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/trail?currentPage=${i}">${i}</a>
                        </li>
                      </c:when>
                    </c:choose>
                    <!-- NAVIGATION PAGES PAS OUF DU TOUT -->

                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </c:otherwise>
          </c:choose>

          <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/trail?currentPage=${currentPage+1}">Next</a>
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

<script>
  function addTrail() {
    document.getElementById("addTrail").innerHTML ='<div class="container" style="color: white; max-width: 500px;">\n' +
            '    <form method="post" action="${pageContext.request.contextPath}/trail">\n' +
            '      <div class="form-group">\n' +
            '        <label for="name">Name</label>\n' +
            '        <input type="text" class="form-control" name="name" id="name" placeholder="Name" required>\n' +
            '      </div>\n' +
            '      <div class="form-group">\n' +
            '        <label for="lastName">Distance</label>\n' +
            '        <input type="text" class="form-control" name="distance" id="distance" placeholder="Distance" required>\n' +
            '      </div>\n' +
            '      <div class="form-group">\n' +
            '        <label for="upAndDown">Up and Down</label>\n' +
            '        <input type="text" class="form-control" name="upAndDown" id="upAndDown" placeholder="Up and Down(sum)" required>\n' +
            '      </div>\n' +
            '      <div class="form-group">\n' +
            '        <label for="description">Description</label>\n' +
            '        <textarea type="text" class="form-control" name="description" id="description" placeholder="Description" required></textarea>\n' +
            '      </div>\n' +
            '      <div class="form-group">\n' +
            '        <label for="date">Date</label>\n' +
            '        <input type="text" class="form-control" name="date" id="date" placeholder="Date" required>\n' +
            '      </div>\n' +
            '      <div class="form-group">\n' +
            '        <label for="capacity">Capacity</label>\n' +
            '        <input type="text" class="form-control" name="capacity" id="capacity" placeholder="Capacity" required>\n' +
            '      </div>\n' +
            '      <button type="submit" class="btn btn-outline-warning">Add Trail</button>\n' +
            '    </form>\n' +
            '  </div>'
  }
</script>

</body>

</html>
