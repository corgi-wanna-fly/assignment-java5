<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<div class="">
    <div class="container">
        <div class="row">
            <div class="col-md-12 mt-5">
                <header class="d-flex justify-content-center py-3" style="background-color: #EEEEEE; margin-top: 50px">
                        Tag: #${tag}
                </header>
            </div>
            <div class="col-md-12" style="margin-top: 150px">
                <div class="filters-content">
                    <c:if test="${not empty message}">
                        <div class="col-6 offset-3 mb-5">
                            <h2>${message}</h2>
                        </div>
                    </c:if>
                    <div class="row grid">
                        <c:forEach items="${products }" var="item">
                            <div class="col-lg-3 col-md-3 all gra">
                                <div class="product-item">
                                    <div class="col-2 mt-1" style="background-color: red; color: white;">
                                            ${item.discount.percent}%
                                    </div>
                                    <a href=""><img src="${item.image }" alt=""></a>
                                    <div class="down-content">
                                        <a href=""><h4>${item.name }</h4></a>
                                            <%--                                        <h6><strike>${item.price }</strike></h6>--%>
                                        <h5 style="margin-left: 10px; color: red"><fmt:formatNumber
                                                pattern="###,###">${item.price * (100 - item.discount.percent)/100 }</fmt:formatNumber>
                                            USD</h5>
                                        <p>${item.description }</p>
                                        <a href="/site/cart/add?id=${item.id}" class="btn btn-primary ${item.active == false ? 'disabled' : '' }">Thêm vào giỏ</a>
                                        <span>Views (${item.views })</span>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <c:if test="${empty message}">
                <div class="col-md-12 offset-4">
                    <ul class="pagination">
                        <li class="page-item ${current == 1 ? 'disabled' : ''}"><a class="page-link" href="/site/product/search?keyword=${tag}&page=${current - 1}">Previous</a></li>
                        <c:forEach begin="1" end="${pages}" var="i">
                            <li class="page-item ${i == current ? 'active' : '' }"><a class="page-link"
                                                                                      href="/site/product/search?keyword=${tag}&page=${i}">${i}</a></li>
                        </c:forEach>
                        <li class="page-item ${current == pages ? 'disabled' : ''}"><a class="page-link" href="/site/product/search?keyword=${tag}&page=${current + 1}">Next</a></li>
                    </ul>
                </div>
            </c:if>
        </div>
    </div>
</div>


