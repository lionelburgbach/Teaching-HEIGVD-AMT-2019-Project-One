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
