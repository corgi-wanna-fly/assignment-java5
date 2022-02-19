<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link
            href="https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap"
            rel="stylesheet">

    <title>${page.title }</title>

    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!--

    <!- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="/assets/css/fontawesome.css">
    <link rel="stylesheet" href="/assets/css/templatemo-sixteen.css">
    <link rel="stylesheet" href="/assets/css/owl.css">
    <link rel="stylesheet" href="/assets/css/cart.css">
    <link rel="stylesheet" href="/assets/css/order.css">
</head>

<body>

<!-- ***** Preloader Start ***** -->
<div id="preloader">
    <div class="jumper">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
<!-- ***** Preloader End ***** -->

<!-- Header -->
<header class="">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="/site/index"><h2>
                Công Minh<em> Idol</em>
            </h2></a>
            <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" action="/site/product/search" method="get">
                <input type="search" class="form-control form-control-dark"
                       placeholder="Search..." aria-label="Search" name="keyword" onblur="this.form.submit()">
            </form>
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarResponsive" aria-controls="navbarResponsive"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item ${sessionScope.navbar == 'Trang chủ' ? 'active' : '' }"><a
                            class="nav-link" href="/site/index">Trang chủ </a></li>
                    <li class="nav-item ${sessionScope.navbar == 'Sản phẩm' ? 'active' : '' }">
                        <a class="nav-link"
                           href="/site/products">Sản phẩm</a></li>
                    <c:if test="${sessionScope.user == null }">
                    <li class="nav-item"><a class="nav-link" href="/site/user/signin">Đăng nhập
                    </a></li>
                    <li class="nav-item"><a class="nav-link"
                                            href="/site/user/signup">Đăng ký</a>
                        </c:if>
                        <c:if test="${sessionScope.user != null }">
                    <li class="nav-item ${sessionScope.navbar == 'Giỏ hàng' ? 'active' : '' }"><a class="nav-link" href="/site/cart">Giỏ
                        hàng

                    </a></li>
                    <li class="nav-item ${sessionScope.navbar == 'Đơn hàng' ? 'active' : '' }"><a class="nav-link" href="/site/order">Đơn
                        hàng

                    </a></li>
                    <li class="nav-item"><h6 style="color: #ff8080">${sessionScope.user.fullname }</h6>
                        <a class="nav-link" href="/site/user/logout">Đăng xuất</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>


<c:if test="${page != null }">
    <jsp:include page="${page.content }"></jsp:include>
</c:if>
<!-- Banner Starts Here -->
<footer>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="inner-content">
                    <p>Copyright &copy; 2020 Công Minh Idol Co., Ltd.</p>
                </div>
            </div>
        </div>
    </div>
</footer>


<!-- Bootstrap core JavaScript -->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>


<!-- Additional Scripts -->
<script src="/assets/js/custom.js"></script>
<script src="/assets/js/owl.js"></script>
<script src="/assets/js/slick.js"></script>
<script src="/assets/js/isotope.js"></script>
<script src="/assets/js/accordions.js"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script language="text/Javascript">
    cleared[0] = cleared[1] = cleared[2] = 0; //set a cleared flag for each field
    function clearField(t) {                   //declaring the array outside of the
        if (!cleared[t.id]) {                      // function makes it static and global
            cleared[t.id] = 1;  // you could use true and false, but that's more typing
            t.value = '';         // with more chance of typos
            t.style.color = '#fff';
        }
    }
</script>

</body>

</html>
