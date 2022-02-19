<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div class="container">
    <div class="row col-6 offset-3" style="margin-top: 10px">
        <form:form action="/admin/product/add" method="post" modelAttribute="product">
            <div class="mb-3" hidden>
                <label class="form-label fw-bold">Mã sản phẩm</label> <form:input path="id"
                                                                                   class="form-control"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Tên sản phẩm</label> <form:input path="name"
                                                                                   class="form-control"/>
                <form:errors path="name"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Giá</label> <form:input path="price"
                                                                          class="form-control"/>
                <form:errors path="price"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Mô tả</label>
                <form:textarea class="form-control" placeholder="Leave a comment here"
                               path="description" style="height: 100px"></form:textarea>
                <form:errors path="description"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Thương hiệu</label> <form:select path="brand"
                                                                                   class="form-control"
                                                                                   aria-label="Default select example">
                <option selected disabled hidden>Choose...</option>
                <c:forEach items="${info.brands }" var="item" varStatus="loop">
                    <option  value="${item.id}" ${item.id == product.brand ? 'selected' : ''} >${item.name }</option>
                </c:forEach>
            </form:select>
                <form:errors path="brand"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Loại sản phẩm</label> <form:select path="category"
                                                                                     class="form-control"
                                                                                     name="id_category"
                                                                                     aria-label="Default select example">
                <option selected disabled hidden>Choose...</option>
                <c:forEach items="${info.categories }" var="item" varStatus="loop">
                    <option ${item.id == product.category ? 'selected' : ''} value="${item.id }">${item.name }</option>
                </c:forEach>
            </form:select>
                <form:errors path="category"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Giảm giá</label> <form:select path="discount"
                    class="form-control" aria-label="Default select example">
                <option selected disabled hidden>Choose...</option>
                <c:forEach items="${info.discounts }" var="item" varStatus="loop">
                    <option ${item.id == product.discount ? 'selected' : ''} value="${item.id}">${item.name }</option>
                </c:forEach>
            </form:select>
                <form:errors path="discount"/>
            </div>
            <div class="mb-3">
                <label class="form-label fw-bold">Hình ảnh</label> <form:input path="image"
                                                                          class="form-control" />
                <form:errors path="image"/>
            </div>
            <input type="submit" class="btn btn-primary" value="Thực hiện"></input>
        </form:form>
    </div>
</div>