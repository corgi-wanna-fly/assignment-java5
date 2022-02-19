<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>
        label{
            font-weight: bolder;
        }
    </style>
    <title>Đăng nhập</title>
</head>
<body>
<form:form class="col-4 offset-4 mt-5" modelAttribute="admin" action="/admin/signin" method="post">
    <h1 style="color: red; text-align: center">Administrator</h1>
    <c:if test="${not empty error}">
        <div class="mb-3">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </div>
    </c:if>
    <div class="mb-3">
        <label class="form-label">Tài khoản</label>
        <form:input path="id" class="form-control"/>
    </div>
    <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <form:password path="password" class="form-control"/>
    </div>
    <button type="submit" class="btn btn-primary">Đăng nhập</button>
    <div class="alert alert-light" role="alert">
        <a href="/site/user/signin" style="text-decoration: none">Đăng nhập là thành viên!</a>
    </div>
</form:form>

<!-- Optional JavaScript; choose one of the two! -->

<!-- Option 1: Bootstrap Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
-->
</body>
</html>