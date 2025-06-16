package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import web.backend.repository.ChiTietDonHangRepository;
import web.backend.repository.DonHangRepository;

import java.util.List;
import java.util.Map;

@Service
public class AdminReportServiceImpl implements IAdminReportService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Autowired
    private ChiTietDonHangRepository chiTietDonHangRepository;

    @Override
    public Double getTotalRevenue() {
        return donHangRepository.tinhTongDoanhThu();
    }

    @Override
    public Long getTotalOrders() {
        return donHangRepository.demSoDonHang();
    }

    @Override
    public List<Map<String, Object>> getTopSellingProducts() {
        return chiTietDonHangRepository.findTopProductsByOrderCountOrQuantity(PageRequest.of(0, 5));
    }

}
