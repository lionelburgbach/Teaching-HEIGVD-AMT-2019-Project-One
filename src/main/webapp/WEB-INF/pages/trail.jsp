<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
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
        <% if (session.getAttribute("email") != null){ %>
        <a class="navbar-brand js-scroll-trigger" href="home">Home</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fas fa-bars"></i>
        </button>
        <% } else { %>
        <a class="navbar-brand js-scroll-trigger" href="login">Login</a>
        <% } %>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="trail">Trails</a>
            </li>
            <% if (session.getAttribute("email") != null){ %>
            <li class="nav-item">
              <a class="nav-link js-scroll-trigger" href="profile">Profile</a>
            </li>
            <% } %>
            <li class="nav-item">
                <a class="nav-link js-scroll-trigger" href="logout">Logout</a>
              </li>
          </ul>
        </div>
      </div>
    </nav>

  <!-- Header -->
  <header class="masthead">
    <div class="container d-flex h-100 align-items-center">
      <div class="mx-auto text-center">
        <h1 class="mx-auto my-0 text-uppercase">Trail</h1>
      </div>
    </div>
  </header>

  <!-- About Section -->
  <section id="about" class="about-section text-center">
    <div class="container" style="color: white;">
      <div class="row">
        <div class="table-responsive-lg">
          <table class="table table-dark">
            <thead>
            <tr>
              <th scope="col">Name</th>
              <th scope="col">Length</th>
              <th scope="col">Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trails}" var="trail">
              <tr>
                <td>${trail.name}</td>
                <td>${trail.length} KM</td>
                <td>${trail.description}</td>
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
