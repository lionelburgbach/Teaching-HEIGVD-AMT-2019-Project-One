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
      <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="home">Home</a>
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        Menu
        <i class="fas fa-bars"></i>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="trail">Trails</a>
          </li>
          <li class="nav-item">
            <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="profile">Profile</a>
          </li>
          <li class="nav-item">
              <a class="nav-link js-scroll-trigger" style="color: #B33C12; font-size: 22px;" href="login?action=logout">Logout</a>
            </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Header -->
  <header class="masthead">
    <div class="container d-flex h-100 align-items-center">
      <div class="mx-auto text-center">
        <h1 class="mx-auto my-0 text-uppercase">AMT 2019</h1>
        <input type="image" src="./assets/paper_img/down.png" style="padding-top:20px; width:30%;" onClick="document.getElementById('result').scrollIntoView();">
      </div>
    </div>
  </header>

  <!-- About Section -->

  <!-- Projects Section -->
  <section id="result" class="about-section text-center">
    <div class="container" style="color: white;">
      <div class="row" style="text-align: center;">
        <h2>Results</h2>
        <div class="table-responsive-lg">
          <table class="table table-dark">
            <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Time</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${results}" var="res">
              <tr>
                <td>${res.id}</td>
                <td>${res.registration.trail.name}</td>
                <td>${res.time} M</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </section>

  <!-- Projects Section -->
  <section class="about-section text-center">
    <div class="container" style="color: white;">
      <div class="row" style="text-align: center;">
        <h2>Coming Trails</h2>
        <div class="table-responsive-lg">
          <table class="table table-dark">
            <thead>
              <tr>
                <th scope="col">Name</th>
                <th scope="col">Distance</th>
                <th scope="col">Up and Down</th>
                <th scope="col">Description</th>
                <th scope="col">Date</th>
                <th scope="col">Delete Registration</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${regs}" var="reg">
              <tr>
                <td>${reg.trail.name}</td>
                <td>${reg.trail.distance} KM</td>
                <td>${reg.trail.upAndDown} M</td>
                <td>${reg.trail.description}</td>
                <td>${reg.trail.date}</td>
                <form method="post" action="./registration?action=delReg">
                  <td><button type="submit" name ="reg_id" value="${reg.id}" class="btn btn-outline-danger">Delete</button></td>
                </form>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </section>

  <!-- Projects Section -->
  <section class="about-section text-center">
    <div class="container" style="color: white;">
      <div class="row" style="text-align: center;">
        <h2>Trails Done</h2>
        <div class="table-responsive-lg">
          <table class="table table-dark">
            <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Distance</th>
              <th scope="col">Up and Down</th>
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
                <td>${reg.trail.date}</td>
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

</body>

</html>
