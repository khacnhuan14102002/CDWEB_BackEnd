package web.backend.service;

import java.util.List;
import java.util.Map;

public interface IAdminReportService {
    Double getTotalRevenue();
    Long getTotalOrders();
    List<Map<String, Object>> getTopSellingProducts();

}
