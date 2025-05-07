package Manage.BUS;

import java.util.ArrayList;
import Manage.DAO.ThongKeDAO;
import Manage.DTO.ThongKe;
public class ThongKeBUS {

    public ThongKeDAO thongKeDAO = new ThongKeDAO();
    private ArrayList<Double> doanhThuThang;

    public ThongKe thongKe(int nam) {
        return thongKeDAO.getThongKe(nam);
    }

    public double getDoanhThuThang(int thang, int nam) {
        return thongKeDAO.getDoanhThuThang(thang, nam);
    }
}

