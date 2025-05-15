package Main;

import MyCustom.MyConnect;
import Manage.GUI.DangNhapGUI;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class chính của ứng dụng
 */
public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Phương thức main của ứng dụng
     * @param args Tham số dòng lệnh
     */
    public static void main(String[] args) {
        try {
            new MyConnect();
            changLNF("Windows");
            DangNhapGUI login = new DangNhapGUI();
            login.showWindow();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Lỗi khởi động ứng dụng", e);
            JOptionPane.showMessageDialog(null, 
                "Không thể khởi động ứng dụng. Vui lòng kiểm tra lại kết nối database.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Thay đổi giao diện của ứng dụng
     * @param nameLNF Tên giao diện cần thay đổi
     */
    public static void changLNF(String nameLNF) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            LOGGER.log(Level.WARNING, "Không thể thay đổi giao diện", ex);
        }
    }
}