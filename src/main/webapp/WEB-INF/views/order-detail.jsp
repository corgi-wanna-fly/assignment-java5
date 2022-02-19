<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="container">
    <div class="d-flex justify-content-center row">
        <div class="col-md-10" style="margin-top: 100px">
            <a class="font-size-sm mt-5" href="/site/order"><svg
                    xmlns="http://www.w3.org/2000/svg" width="24" height="24"
                    viewBox="0 0 24 24" fill="none" stroke="currentColor"
                    stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
                    class="feather feather-chevron-left"
                    style="width: 1rem; height: 1rem;">
                <polyline points="15 18 9 12 15 6"></polyline></svg>Quay lại</a>
            <div class="rounded">
                <div class="table-responsive table-borderless">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Sản phẩm</th>
                            <th>Giá</th>
                            <th>Số lượng</th>
                            <th>Thành tiền</th>
                        </tr>
                        </thead>
                        <tbody class="table-body">
                        <c:forEach items="${order }" var="item" varStatus="loop">
                            <tr class="cell-1">
                                <td>#${loop.index + 1}</td>
                                <td>${item.product.name }</td>
                                <td><fmt:formatNumber
                                        value="${item.price}"
                                        pattern="#,###"></fmt:formatNumber></td>
                                <td>${item.quantity }</td>
                                <td><fmt:formatNumber
                                        value="${item.price * item.quantity }"
                                        pattern="#,###"></fmt:formatNumber></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>