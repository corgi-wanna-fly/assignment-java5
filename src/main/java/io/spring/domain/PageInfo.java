package io.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {
    private String title;
    private String content;
    private String script;

    private static Map<PageType, PageInfo> map = new HashMap<>();

    static {
        map.put(PageType.SITE_INDEX, new PageInfo("Trang chủ", "index.jsp", null));
        map.put(PageType.SITE_PRODUCT, new PageInfo("Sản phẩm", "product.jsp", null));
        map.put(PageType.SITE_SEARCH, new PageInfo("Kết quả tìm kiếm", "search.jsp", null));
        map.put(PageType.SITE_CART, new PageInfo("Giỏ hàng", "cart.jsp", null));
        map.put(PageType.SITE_ORDER, new PageInfo("Đơn hàng", "order.jsp", null));
        map.put(PageType.SITE_CHECKOUT, new PageInfo("Thanh toán", "checkout.jsp", null));
        map.put(PageType.SITE_ORDER_DETAIL, new PageInfo("Chi tiết hoá đơn", "order-detail.jsp", null));
        map.put(PageType.ADMIN_INDEX, new PageInfo("Admin", "admin-index.jsp", null));
        map.put(PageType.ADMIN_PRODUCT, new PageInfo("Quản lý sản phẩm", "admin-product.jsp", null));
        map.put(PageType.ADMIN_PRODUCT_FORM, new PageInfo("Quản lý sản phẩm", "product-form.jsp", null));
        map.put(PageType.ADMIN_ORDER, new PageInfo("Quản lý đơn hàng", "admin-order.jsp", null));
        map.put(PageType.ADMIN_STATISTICAL, new PageInfo("Thống kê", "admin-statistical.jsp", null));
    }

    public static void routeSite(HttpServletRequest request, PageType type) {
        PageInfo pageInfo = map.get(type);

        request.setAttribute("page", pageInfo);
    }
}
