<jsp:include page="include/head.jsp" />

<body id="page-top">

<jsp:include page="include/nav.jsp" />

<!-- Header -->
<header class="masthead">
  <div class="container d-flex h-100 align-items-center">
    <div class="mx-auto text-center">
      <div class="container">
        <div class="row">
          <div class="text-white col-sm" style="padding-top: 30px;">
            <div>
              <img src="./grayscale/img/pict.jpg" style="width:400px;" class="img-fluid" alt="">
            </div>
            <div style="padding-top: 20px;">
              <form method="post" action="" enctype="multipart/form-data">
                <div class="custom-file" style="width: 400px;">
                  <input type="file" class="custom-file-input" id="customFile">
                  <label class="custom-file-label" for="customFile">File</label>
                  <button type="submit" class="btn btn-outline-warning" value="updatePerson">Update</button>
                </div>
              </form>
            </div>
          </div>
          <div class="text-white col-sm" style="padding-bottom: 40px; width: 400px;">
            <form method="post" action="${pageContext.request.contextPath}/user/profile">
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
                <input type="email" class="form-control" id="Email" name="email" value="${user.email}" disabled>
              </div>
              <div class="form-group">
                <label for="Passowrd">Password</label>
                <input type="password" class="form-control" id="Passowrd" name="password" placeholder="Password">
              </div>
              <div style="padding-top: 20px;">
                <button type="submit" class="btn btn-outline-warning" value="updatePerson">Update</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</header>

<jsp:include page="include/footer.jsp" />

<!-- Bootstrap core JavaScript -->
<script src="./grayscale/vendor/jquery/jquery.min.js"></script>
<script src="./grayscale/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Plugin JavaScript -->
<script src="./grayscale/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for this template -->
<script src="./grayscale/js/grayscale.min.js"></script>

<script>
  // Add the following code if you want the name of the file appear on select
  $(".custom-file-input").on("change", function() {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
  });
</script>

</body>

</html>
