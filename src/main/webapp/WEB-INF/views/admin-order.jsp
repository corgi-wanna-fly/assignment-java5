<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="container mt-3">
    <div class="d-flex justify-content-center row">
        <div class="col-md-12">
            <div class="rounded">
                <div class="table-responsive table-borderless">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Mã ĐH</th>
                            <th>Tên KH</th>
                            <th>Địa chỉ</th>
                            <th>SDT</th>
                            <th>T.Tiền</th>
                            <th>Ngày tạo</th>
                            <th>Thanh toán</th>
                            <th>Trạng thái</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody class="table-body">
                        <c:forEach items="${orderPage.content }" var="item">
                            <form action="/admin/order/update?id=${item.id}"
                                  method="post">
                                <tr>
                                    <td>#${item.id }</td>
                                    <td>${item.user.fullname }</td>
                                    <td>${item.address }</td>
                                    <td>${item.phone }</td>
                                    <td><fmt:formatNumber
                                            value="${item.amount}"
                                            pattern="#,###"></fmt:formatNumber></td>
                                    <td>${item.created }</td>
                                    <td><span style="color: ${item.payment ? 'green' : 'red'}">${item.payment ? 'Hoàn thành' : 'Chưa hoàn thành' }</span></td>
                                    <td>
                                        <select name="status" ${item.status == 'Giao thành công' or item.status == 'Đã huỷ' ? 'disabled' : ''}>
                                        <c:forEach items="${status }" var="str">
                                            <option value="${str }"
                                                ${str ==  item.status ? 'selected="selected"' : ''}>${str }</option>
                                        </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <c:if test="${item.status != 'Giao thành công' and item.status != 'Đã huỷ' }">
                                            <input type="submit" class="btn btn-primary"
                                                   value="Cập nhật"></input>
                                            <a style="color: white;" order-id="${item.id }"
                                               onclick="confirmCancelOrder(this.getAttribute('order-id'))"
                                            class="btn btn-danger">Huỷ bỏ</a>
                                        </c:if></td>
                                    <c:if test="${item.status == 'Giao thành công' or item.status == 'Đã huỷ' }">
                                    </c:if>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-md-6 offset-3">
                        <ul class="pagination">
                            <li class="page-item ${orderPage.number + 1 == 1 ? 'disabled' : ''}"><a class="page-link" href="/admin/order?page=${orderPage.number}">Previous</a></li>
                            <c:forEach begin="1" end="${orderPage.totalPages}" var="i">
                                <li class="page-item ${i - 1 == orderPage.number ? 'active' : '' }"><a class="page-link"
                                                                                                         href="/admin/order?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${orderPage.number + 1 == orderPage.totalPages ? 'disabled' : ''}"><a class="page-link" href="/admin/order?page=${orderPage.number + 2}">Next</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="cancelOrder" tabindex="-1"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Confirmation</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"
                        aria-label="Close"></button>
            </div>
            <div class="modal-body">Bạn chắc chắn muốn huỷ đơn hàng nàyr?</div>
            <div class="modal-footer">
                <a id="yesOption" class="btn btn-primary">Đúng vậy</a>
                <button type="button" class="btn btn-secondary"
                        data-bs-dismiss="modal">Khoan, từ từ đã
                </button>
            </div>
        </div>
    </div>
</div>

<script>
    function confirmCancelOrder(id) {
        $('#yesOption').attr('href',
            '/admin/order/cancel?id=' + id)
        $('#cancelOrder').modal('show');
    }
</script>