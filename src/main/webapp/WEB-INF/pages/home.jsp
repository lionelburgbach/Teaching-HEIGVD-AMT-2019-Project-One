<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<!-- Header -->
<header class="masthead">
  <div class="container d-flex h-100 align-items-center">
    <div class="mx-auto text-center">
      <h1 class="mx-auto my-0 text-uppercase" style="font-size: 60px;">Home</h1>
      <input type="image" src="./assets/paper_img/down.png"  style="padding-top:20px; width:100px; height: auto;" onClick="document.getElementById('trail').scrollIntoView();">
    </div>
  </div>
</header>


<!-- About Section -->
<section id="trail" class="about-section">
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
          <th scope="col">Delete Registration</th>
          <th scope="col">Who is in?</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${regs}" var="reg">
          <tr>
            <td>${reg.trail.name}</td>
            <td>${reg.trail.distance} KM</td>
            <td style="white-space: nowrap;">${reg.trail.upAndDown} M</td>
            <td>${reg.trail.description}</td>
            <td style="white-space: nowrap;">${reg.trail.date}</td>
            <form method="post" action="${pageContext.request.contextPath}/user/registration?action=delReg">
              <td><button type="submit" name ="reg_id" value="${reg.id}" class="btn btn-outline-danger">Delete</button></td>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/user/data?action=registers">
              <td><button type="submit" name ="trail_id" value="${reg.trail.id}" class="btn btn-outline-warning">Show Registers</button></td>
            </form>
          </tr>
        </c:forEach>
        </tbody>
      </table>
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
