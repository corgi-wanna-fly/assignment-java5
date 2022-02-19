<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
    <title>Đăng ký</title>
</head>
<body>
<form:form class="col-4 offset-4 mt-5" modelAttribute="register" action="/site/user/signup" method="post" enctype="multipart/form-data">
    <h1 style="color: red; text-align: center">Đăng ký</h1>
    <div class="mb-3">
        <label class="form-label">Họ tên</label>
        <form:input path="fullname"  class="form-control" />
        <form:errors path="fullname"/>
    </div>
    <div class="mb-3">
        <label class="form-label">Email</label>
        <form:input path="email" class="form-control" />
        <form:errors path="email"/>
    </div>
    <div class="mb-3">
        <label class="form-label">Mật khẩu</label>
        <form:password path="password" class="form-control" />
        <form:errors path="password"/>
    </div>
    <div class="mb-3">
        <label class="form-label">Xác nhận mật khẩu</label>
        <form:password path="confirm" class="form-control" />
        <form:errors path="confirm"/>
    </div>
    <div class="mb-3">
        <label class="form-label">Giới tính</label>
        <div class="form-check form-check-inline">
            <form:radiobutton path="gender" class="form-check-input checked" value="true"/>
            <label class="form-check-label" style="font-weight: lighter">Nam</label>
        </div>
        <div class="form-check form-check-inline">
            <form:radiobutton path="gender" class="form-check-input" value="false"/>
            <label class="form-check-label" style="font-weight: lighter">Nữ</label>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Đăng ký</button>
    <div class="alert alert-light" role="alert">
        <a style="text-decoration: none" href="/site/user/signin">Đã có tài khoản, Đăng nhập ngay!</a>
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