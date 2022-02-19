<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="container-fluid px-4">
    <h1 class="mt-4">Bảng điều khiển</h1>

    <hr class="my-4">

    <div class="row">
        <div class="col-xl-3 col-md-6">
            <div class="card bg-primary text-white mb-4">
                <div class="card-body">Người dùng</div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <span>${dashboard.users}</span>
                </div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-warning text-white mb-4">
                <div class="card-body">Sản phẩm</div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <span>${dashboard.products}</span>
                </div>
            </div>
        </div>
        <div class="col-xl-3 col-md-6">
            <div class="card bg-success text-white mb-4">
                <div class="card-body">Đơn hàng</div>
                <div class="card-footer d-flex align-items-center justify-content-between">
                    <span>${dashboard.orders}</span>
                </div>
            </div>
        </div>
    </div>
</div>