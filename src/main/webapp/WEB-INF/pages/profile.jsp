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
                <a class="nav-link js-scroll-trigger"  style="color: #B33C12; font-size: 22px;" href="login?action=logout">Logout</a>
              </li>
          </ul>
        </div>
      </div>
    </nav>

  <!-- Header -->
  <header class="masthead">
    <div class="container d-flex h-100 align-items-center">
      <div class="mx-auto text-center">
        <h1 class="mx-auto my-0 text-uppercase" >Profile</h1>
        <input type="image" src="./assets/paper_img/down.png" style="padding-top:20px; width:30%;" onClick="document.getElementById('profile').scrollIntoView();">
      </div>
    </div>
  </header>

  <!-- About Section -->
  <section id="profile" class="about-section text-center" style="min-height: 850px;">
    <div class="container">
      <div class="row">
        <div class="container">
          <div class="row">
            <div class="text-white col-sm" style="padding-top: 30px;">
              <img src="./grayscale/img/pict.jpg" style="width:80%;" class="img-fluid" alt="">
            </div>
            <div class="text-white col-sm" style="padding-bottom: 40px;">
              <form method="POST" action="./profile" enctype="multipart/form-data">
                <div class="form-group">
                  <label for="firstName">Firstname</label>
                  <input type="text" class="form-control" id="firstName" name="firstname" aria-describedby="emailHelp" value="${user.firstName}">
                </div>
                <div class="form-group">
                  <label for="lastName">Lastname</label>
                  <input type="text" class="form-control" id="lastName" name="lastname" value="${user.lastName}">
                </div>
                <div class="form-group">
                  <label for="dateOfBirth">Date of Birth</label>
                  <input type="text" class="form-control" id="dateOfBirth" name="date" value="${user.dateOfBirth}">
                </div>
                <div class="form-group">
                  <label for="Email">Email</label>
                  <input type="email" class="form-control" id="Email" name="email" value="${user.email}">
                </div>
                <div class="form-group">
                  <label for="Passowrd">Password</label>
                  <input type="password" class="form-control" id="Passowrd" name="password" placeholder="Password">
                </div>
                <div>
                    <label for="profilePicture">Modify your picture</label>
                    <input type="file" class="form-control" id="profilePicture"name="profilePicture" accept="image/png, image/jpeg" >
                </div>
                <button type="submit" class="btn btn-outline-warning" value="updatePerson">Update</button>
              </form>
            </div>

          </div>
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
