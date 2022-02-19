<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="container">
    <div class="d-flex justify-content-center row">
        <div class="col-md-10 mt-5">
            <c:if test="${not empty message }">
                <div class="alert alert-danger mt-5" role="alert">${message }</div>
            </c:if>
            <c:if test="${empty message}">
                <div class="rounded mt-5">
                    <div class="table-responsive table-borderless">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Mã ĐH</th>
                                <th>Địa chỉ</th>
                                <th>SDT</th>
                                <th>T.tiền</th>
                                <th>Ngày tạo</th>
                                <th>T.Thái</th>
                                <th>Thanh toán</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <c:forEach items="${orders }" var="item">
                                <tr class="cell-1">
                                    <td><a href="/site/order/detail?id=${item.id}" class="btn btn-primary">${item.id}</a></td>
                                    <td>${item.address }</td>
                                    <td>${item.phone }</td>
                                    <td>
                                        <fmt:formatNumber
                                                value="${item.amount}"
                                                pattern="#,###"></fmt:formatNumber>
                                    </td>
                                    <td>${item.created }</td>
                                    <td><span class="badge badge-success">${item.status }</span></td>
                                    <td><span class="badge ${item.payment ? 'badge-success' : 'badge-danger'}">${item.payment ? 'Hoàn thành' : 'Chưa hoàn thành' }</span></td>
                                    <td>

                                        <c:if test="${item.status != 'Giao thành công' and item.status != 'Đã huỷ'}">
                                            <a style="color: white;" order-id="${item.id }" onclick="confirmCancelOrder(this.getAttribute('order-id'))" class="btn btn-danger">Cancel</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="cancelOrder" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn chắc chắn muốn huỷ đơn hàng?
            </div>
            <div class="modal-footer">
                <a id="yesOption" class="btn btn-primary">Đúng vậy</a>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Khoan, từ từ đã</button>
            </div>
        </div>
    </div>
</div>
<script>
    function confirmCancelOrder(id) {
        $('#yesOption').attr('href', '/site/order/cancel?id=' + id)
        $('#cancelOrder').modal('show');
    }
</script>