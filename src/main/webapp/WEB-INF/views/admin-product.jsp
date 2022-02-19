<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="container mt-3">
    <div class="d-flex justify-content-center row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-2">
                    <a href="/admin/product/add" class="btn btn-success">Thêm mới</a>
                </div>
            </div>
            <c:if test="${not empty message}">
                <div class="alert alert-primary" role="alert">
                    ${message}
                </div>
            </c:if>
            <hr class="my-4">

            <div class="rounded">
                <div class="table-responsive table-borderless">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Tên sản phẩm</th>
                            <th>Giá(VND)</th>
                            <th>Giảm giá</th>
                            <th>Lượt xem</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody class="table-body">
                        <c:forEach items="${productPage.content }" var="item">
                            <form>
                                <tr class="cell-1 ${item.active == false ? 'table-danger' : '' }">
                                    <td>#${item.id }</td>
                                    <td>${item.name }</td>
                                    <td><fmt:formatNumber
                                            value="${item.price}"
                                            pattern="#,###"></fmt:formatNumber></td>
                                    <td>${item.discount.percent } %</td>
                                    <td>${item.views }</td>
                                    <td>
                                        <c:if test="${item.active == true }">
                                            <a href="/admin/product/update?id=${item.id}" class="btn btn-primary">Cập nhật</a>
                                            <a product-id="${item.id }" onclick="confirmDeleteProduct(this.getAttribute('product-id'))" class="btn btn-danger"
                                            >
                                                Xoá</a>
                                        </c:if>

                                        <c:if test="${item.active == false }">
                                            <a product-id="${item.id }" onclick="confirmRestoreProduct(this.getAttribute('product-id'))" class="btn btn-success">Khôi phục</a>
                                        </c:if>
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>

                        </tbody>
                    </table>
                    <div class="col-md-6 offset-3">
                        <ul class="pagination">
                            <li class="page-item ${productPage.number + 1 == 1 ? 'disabled' : ''}"><a class="page-link" href="/admin/product?page=${productPage.number}">Previous</a></li>
                            <c:forEach begin="1" end="${productPage.totalPages}" var="i">
                                <li class="page-item ${i - 1 == productPage.number ? 'active' : '' }"><a class="page-link"
                                                                                          href="/admin/product?page=${i}">${i}</a></li>
                            </c:forEach>
                            <li class="page-item ${productPage.number + 1 == productPage.totalPages ? 'disabled' : ''}"><a class="page-link" href="/admin/product?page=${productPage.number + 2}">Next</a></li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="deleteProduct" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn chắc chắn muốn xoá tạm thời sản phẩm này?
            </div>
            <div class="modal-footer">
                <a id="yesOption" class="btn btn-primary">Đúng vậy</a>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Khoan, từ từ đã</button>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="restoreProduct" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Bạn chắc chắn muốn khôi phục sản phẩm này?
            </div>
            <div class="modal-footer">
                <a id="yes" class="btn btn-primary">Đúng vậy</a>
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Khoan, từ từ đã</button>
            </div>
        </div>
    </div>
</div>
<script>
    function confirmDeleteProduct(id) {
        $('#yesOption').attr('href', '/admin/product/delete?id=' + id)
        $('#deleteProduct').modal('show');
    }

    function confirmRestoreProduct(id) {
        $('#yes').attr('href', '/admin/product/restore?id=' + id)
        $('#restoreProduct').modal('show');
    }
</script>