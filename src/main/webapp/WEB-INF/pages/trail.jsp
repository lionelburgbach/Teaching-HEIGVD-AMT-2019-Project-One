<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="icon" type="image/png" sizes="64x64" href="./assets/paper_img/logo.png">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>GBLB</title>

  <base href="${pageContext.request.contextPath}/"/>

  <!-- Bootstrap core CSS -->
  <link href="./grayscale/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="./grayscale/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="./grayscale/css/grayscale.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
  <div class="container">
    <% if (session.getAttribute("user") != null){ %>
    <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="${pageContext.request.contextPath}/user/home">Home</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      Menu
      <i class="fas fa-bars"></i>
    </button>
    <% } else { %>
    <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="login?action=login">Login</a>
    <% } %>
    <div class="collapse navbar-collapse" id="navbarResponsive">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="trail">Trails</a>
        </li>
        <% if (session.getAttribute("user") != null){ %>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="${pageContext.request.contextPath}/user/profile">Profile</a>
        </li>
        <li class="nav-item">
          <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="login?action=logout">Logout</a>
        </li>
        <% } %>
      </ul>
    </div>
  </div>
</nav>

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
          </tr>
          </thead>
          <tbody>
          <c:forEach items="${trails}" var="trail">
            <tr>
              <td>${trail.name}</td>
              <td>${trail.distance} KM</td>
              <td>${trail.upAndDown} M</td>
              <td>${trail.description}</td>
              <td>${trail.date}</td>
              <td>${trail.capacity - trail.nbIn}</td>
              <form method="post" action="${pageContext.request.contextPath}/user/registration?action=enroll">
                <td><button type="submit" name ="trail_id" value="${trail.id}" class="btn btn-outline-warning">Enroll Me</button></td>
              </form>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</section>

<!-- Footer -->
<footer class="bg-black small text-center text-white-50">
  <div class="container">
    GBLB
  </div>
</footer>

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
