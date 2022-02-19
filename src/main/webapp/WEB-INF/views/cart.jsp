<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<div class="container pb-5 mt-n2 mt-md-n3 ml-5">
    <div class="row">
        <div class="col-xl-9 col-md-8 mt-5 ml-5" style="margin-top: 100px; margin-left: 400px">
            <h2
                    class="h6 d-flex flex-wrap justify-content-between align-items-center px-4 py-3 bg-secondary mt-5 fw-bolder">
                <span>Products</span><a class="font-size-sm fw-bolder" href="/site/products">
                <svg
                        xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                        viewBox="0 0 24 24" fill="none" stroke="currentColor"
                        stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                        class="feather feather-chevron-left"
                        style="width: 1rem; height: 1rem;">
                    <polyline points="15 18 9 12 15 6"></polyline>
                </svg>
                Tiếp tục mua sắm</a>
            </h2>
            <!-- Item-->
            <c:if test="${count > 0 }">
                <c:forEach items="${cart}" var="item">
                    <form
                            action="/site/cart/update?id=${item.id}"
                            method="post">
                        <div
                                class="d-sm-flex justify-content-between my-4 pb-4 border-bottom">
                            <div class="media d-block d-sm-flex text-center text-sm-left">
                                <a class="cart-item-thumb mx-auto mr-sm-4" href="#"><img
                                        src="${item.product.image }" alt="Product"></a>
                                <div class="media-body pt-3">
                                    <h3
                                            class="product-card-title font-weight-semibold border-0 pb-0">
                                        <a href="#">${item.product.name }</a>
                                    </h3>
                                    <div class="font-size-lg text-primary pt-2">
                                        <fmt:formatNumber
                                                value="${item.product.price * (100 - item.product.discount.percent) / 100 }"
                                                pattern="#,###"></fmt:formatNumber>
                                        USD
                                    </div>
                                </div>
                            </div>
                            <div
                                    class="pt-2 pt-sm-0 pl-sm-3 mx-auto mx-sm-0 text-center text-sm-left"
                                    style="max-width: 10rem;">
                                <div class="form-group mb-2">
                                    <label>Quantity</label> <input
                                        class="form-control form-control-sm" type="number"
                                        name="quantity" onblur="this.form.submit()" min="1"
                                        value="${item.quantity }">
                                </div>

                                <a class="btn btn-outline-danger btn-sm btn-block mb-2"
                                   href="/site/cart/delete?id=${item.id}">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                                         viewBox="0 0 24 24" fill="none" stroke="currentColor"
                                         stroke-width="2" stroke-linecap="round"
                                         stroke-linejoin="round" class="feather feather-trash-2 mr-1">
                                        <polyline points="3 6 5 6 21 6"></polyline>
                                        <path
                                                d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
                                        <line x1="10" y1="11" x2="10" y2="17"></line>
                                        <line x1="14" y1="11" x2="14" y2="17"></line>
                                    </svg>
                                    Remove
                                </a> <label for="">Total</label>
                                <div class="font-size-lg text-primary pt-2">
                                    <fmt:formatNumber
                                            value="${item.product.price * (100 - item.product.discount.percent) / 100 * item.quantity}"
                                            pattern="#,###"></fmt:formatNumber>
                                    USD
                                </div>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${count == 0 }">
                <div class="alert alert-danger" role="alert">${message }</div>
            </c:if>
        </div>
        <div class="col-xl-2 col-md-3 pt-md-0 mt-5" style="margin-top: 2000px">
            <form action="/site/checkout" method="get">
                <h2 class="h6 px-4 py-3 bg-secondary text-center mt-5 fw-bolder" style="color: red; font-size: 20px">Tổng tiền</h2>
                <div class="h3 font-weight-semibold text-center py-3" style="font-size: 24px">
                    <fmt:formatNumber value="${amount}" pattern="#,###"></fmt:formatNumber>
                    USD
                </div>
                <hr>
                <button type="submit" class="btn btn-primary btn-block"> <svg
                        xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                        viewBox="0 0 24 24" fill="none" stroke="currentColor"
                        stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                        class="feather feather-credit-card mr-2">
                    <rect x="1" y="4" width="22" height="16" rx="2"
                          ry="2"></rect>
                    <line x1="1" y1="10" x2="23" y2="10"></line>
                </svg>Thanh toán
                </button>
            </form>
        </div>
    </div>
</div>