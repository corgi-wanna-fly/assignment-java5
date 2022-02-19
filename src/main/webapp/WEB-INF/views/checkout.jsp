<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Checkout example · Bootstrap v5.1</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/checkout/">


    <!-- Bootstrap core CSS -->
    <link href="/docs/5.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Favicons -->
    <link rel="apple-touch-icon" href="/docs/5.1/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
    <link rel="manifest" href="/docs/5.1/assets/img/favicons/manifest.json">
    <link rel="mask-icon" href="/docs/5.1/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
    <link rel="icon" href="/docs/5.1/assets/img/favicons/favicon.ico">
    <meta name="theme-color" content="#7952b3">


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>


    <!-- Custom styles for this template -->
    <link href="form-validation.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container">
    <div class="py-5 text-center">
    </div>

    <div class="row g-5 mt-1">

        <div class="col-md-5 col-lg-4 order-md-last">
            <h4 class="d-flex justify-content-between align-items-center mb-3">
                <span class="text-primary">Giỏ hàng</span>
            </h4>
            <ul class="list-group mb-3">
              <c:forEach items="${cart}" var="item">
                  <li class="list-group-item d-flex justify-content-between lh-sm">
                      <div>
                          <h6 class="my-0">${item.product.name}</h6>
                          <small class="text-muted">Số lượng: ${item.quantity}</small>
                      </div>
                      <span class="text-muted">
                          <fmt:formatNumber
                                  value="${item.product.price * (100 - item.product.discount.percent) / 100 * item.quantity}"
                                  pattern="#,###"></fmt:formatNumber>
                                    USD
                      </span>
                  </li>

              </c:forEach>
                <li class="list-group-item d-flex justify-content-between">
                    <span>Tổng tiền(USD)</span>
                    <strong><fmt:formatNumber
                            value="${amount}"
                            pattern="#,###"></fmt:formatNumber></strong>
                </li>
            </ul>
        </div>
        <div class="col-md-7 col-lg-8">
            <h4 class="mb-3 fw-bolder">Thông tin đơn hàng</h4>
            <form class="needs-validation" novalidate action="/site/checkout" method="post">
                <div class="row g-3">
                    <div class="col-sm-6">
                        <label for="firstName" class="form-label">Họ tên</label>
                        <input type="text" class="form-control" id="firstName"  value="${order.name}" readonly>
                    </div>

                    <div class="col-sm-6">
                        <label for="lastName" class="form-label">Điện thoại</label>
                        <input type="text" name="phone" class="form-control" id="lastName" value="${order.phone}" required>
                        <div class="invalid-feedback">
                            Valid last name is required.
                        </div>
                    </div>

                    <div class="col-md-6">
                        <label for="country" class="form-label">Thành phố</label>
                        <select name="province" class="form-select" id="country" onchange="location=this.value;" required>
                            <option selected disabled hidden>Choose...</option>
                            <c:forEach items="${province}" var="item">
                                <option value="/site/checkout?province=${item.code}" ${order.province == item.code ? 'selected' : ''}>${item.name}</option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid country.
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="country" class="form-label">Quận/Huyện</label>
                        <select name="district" class="form-select" id="district" onchange="location=this.value;" required>
                            <option value="">Choose...</option>
                            <c:if test="${district != null}">
                                <c:forEach items="${district}" var="item">
                                    <option value="/site/checkout?province=${order.province}&district=${item.code}" ${order.district == item.code ? 'selected' : ''}>${item.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid country.
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label for="country" class="form-label">Phường/Xã</label>
                        <select name="ward" class="form-select" id="ward" required>
                            <option value="">Choose...</option>
                            <c:if test="${ward != null}">
                                <c:forEach items="${ward}" var="item">
                                    <option value="${item.code}">${item.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid country.
                        </div>
                    </div>

                    <div class="col-12">
                        <label class="form-label">Địa chỉ</label>
                        <textarea class="form-control" name="address" placeholder="Địa chỉ chi tiết..." id="floatingTextarea">${order.address}</textarea>
                        <div class="invalid-feedback">
                            Please enter your shipping address.
                        </div>
                    </div>

                <h4 class="mb-3">Phương thức thanh toán</h4>

                <div class="my-3">
                    <div class="form-check">
                        <input name="paymentMethod" type="radio" class="form-check-input" value="true" required>
                        <label class="form-check-label">PayPal</label>
                    </div>
                    <div class="form-check">
                        <input  name="paymentMethod" type="radio" class="form-check-input" value="false" checked required>
                        <label class="form-check-label">Thanh toán khi nhận hàng</label>
                    </div>
                </div>

                <button class="w-100 btn btn-primary btn-lg" type="submit">Thanh toán</button>
            </form>
        </div>
    </div>
</div>


<script src="/docs/5.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>


<script src="form-validation.js"></script>
<script>
</script>
</body>
</html>
