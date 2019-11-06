<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<!-- Header -->
<header class="masthead">
  <div class="container d-flex h-100 align-items-center">
    <div class="mx-auto text-center">
      <h1 class="mx-auto my-0 text-uppercase" >Trails</h1>
      <input type="image" src="./assets/paper_img/down.png" style="padding-top:20px; width:30%;" onClick="document.getElementById('trail').scrollIntoView();">
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
      <div class="row">
        <div class="table-responsive-lg">
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
                <form method="post" action="${pageContext.request.contextPath}/data?action=registers">
                  <td><button type="submit" name ="trail_id" value="${trail.id}" class="btn btn-outline-warning">Show Registers</button></td>
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

  <script>
    function addTrail() {
      document.getElementById("addTrail").innerHTML ='<div class="container" style="color: white; max-width: 500px;">\n' +
              '    <form method="post" action="${pageContext.request.contextPath}/trail?action=addTrail">\n' +
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
