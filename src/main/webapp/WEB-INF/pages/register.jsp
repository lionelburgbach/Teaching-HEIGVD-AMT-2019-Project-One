<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>GBLB</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <link rel="icon" type="image/png" sizes="64x64" href="./assets/paper_img/logo.png">
    <meta name="viewport" content="width=device-width" />

    <base href="${pageContext.request.contextPath}/"/>

    <link href="./bootstrap3/css/bootstrap.css" rel="stylesheet" />
    <link href="./assets/css/ct-paper.css" rel="stylesheet"/>
    <link href="./assets/css/demo.css" rel="stylesheet" />
    <link href="./assets/css/examples.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300' rel='stylesheet' type='text/css'>

</head>
<body>
<nav class="navbar navbar-ct-transparent navbar-fixed-top" role="navigation-demo" id="register-navbar">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="btn btn-simple" style="color: #B33C12; font-size: 22px;" href="login?action=login">Login</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navigation-example-2">
            <div class="nav navbar-nav navbar-right">
                <a class="btn btn-simple" style="color: #B33C12; font-size: 22px;" href="trail">Trails</a>
            </div>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-->
</nav>

<div class="wrapper" id="test">
    <div class="register-background">
        <div class="filter-black"></div>
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1 ">
                    <div class="register-card">
                        <h3 class="title">Welcome</h3>
                        <form action="./login?action=registration" method="post" class="register-form">
                            <c:choose>
                                <c:when test="${not empty error.errors['email']}">
                                    <label style="color: red;">${error.errors['email']}</label>
                                </c:when>
                                <c:otherwise>
                                    <label>Email</label>
                                </c:otherwise>
                            </c:choose>
                            <input name="email" type="text" class="form-control"  placeholder="Email">
                            <c:choose>
                                <c:when test="${not empty error.errors['password']}">
                                    <label style="color: red;">${error.errors['password']}</label>
                                </c:when>
                                <c:otherwise>
                                    <label>Password</label>
                                </c:otherwise>
                            </c:choose>
                            <input name="password" type="password" class="form-control" placeholder="Password">
                            <c:choose>
                                <c:when test="${not empty error.errors['confirmation']}">
                                    <label style="color: red;">${error.errors['confirmation']}</label>
                                </c:when>
                                <c:otherwise>
                                    <label>Confirmation</label>
                                </c:otherwise>
                            </c:choose>
                            <input name="confirmation" type="password" class="form-control" placeholder="Confirmation">
                            <label>Firstname</label>
                            <input name="firstname" type="text" class="form-control" placeholder="Firstname">
                            <label>Lastname</label>
                            <input name="lastname" type="text" class="form-control" placeholder="LastName">
                            <c:choose>
                                <c:when test="${not empty error.errors['date']}">
                                    <label style="color: red;">${error.errors['date']}</label>
                                </c:when>
                                <c:otherwise>
                                    <label>Date</label>
                                </c:otherwise>
                            </c:choose>
                            <input name="date" type="text" class="form-control" placeholder="dd-mm-yyyy">
                            <button class="btn btn-danger btn-block">Register</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer register-footer text-center">
            <h6>GBLB</h6>
        </div>
    </div>
</div>
</body>
</html>
