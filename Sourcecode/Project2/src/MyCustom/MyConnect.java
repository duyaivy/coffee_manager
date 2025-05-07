package MyCustom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnect {

    public static Connection conn = null;

    private final String serverName = "127.0.0.1";
    private final String dbName = "coffeeshop";
    private final String userName = "root";
    private final String password = "Password@123";

    public MyConnect() {
        String strConnect = "jdbc:mysql://" + serverName + ":3306/" + dbName
                + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";

        try {
            conn = DriverManager.getConnection(strConnect, userName, password);
            System.out.println("✅ Kết nối thành công!");
        } catch (SQLException ex) {
            new MyDialog("❌ Không kết nối được tới CSDL!\n" + ex.getMessage(), MyDialog.ERROR_DIALOG);
            System.exit(0);
        }
    }
}
