/**
*従業員の名前検索

*/

import java.util.*;
import java.sql.*;

public class SearchStaffName extends AbstractExecuter {
    private Scanner scanner = new Scanner(System.in);
    private String searchName;

    @Override
    public String getSQLtemplate() {
        return "SELECT * FROM staff WHERE staffName LIKE ?";
    }

    @Override
    public void preQuery() {
        System.out.print("検索する従業員の名前を入力してください（部分一致）：");
        searchName = scanner.nextLine();
    }

    @Override
    public void setQuery(PreparedStatement st) throws SQLException {
        st.setString(1, "%" + searchName + "%");
    }

    @Override
    public void showResult(ResultSet r) {
        try {

            /*
            配列の大きさより小さい箇所があるため文字列を破壊しまう可能性あり
            今回は横一列で表示するため(データベース内のデータの最大値には考慮済み)、他の表示クラスにそろえるために配列の大きさを調整した
            */

            System.out.printf("%-10s\t%-10s\t%-15s\t%-15s\t%-10s\t%-10s\n", 
                "staffID", "staffName", "phoneNumber", "address", "birthDate", "fare");

            while (r.next()) {
                System.out.printf("%-10s\t%-10s\t%-15s\t%-10s\t%-10s\t%-10d\n",
                        r.getString("staffID"),
                        r.getString("staffName"),
                        r.getString("phoneNumber"),
                        r.getString("address"),
                        r.getString("birthDate"),
                        r.getInt("fare")
                );
            }
        } catch (SQLException se) {
            System.out.println("SQL Error: " + se.toString() + " "
                + se.getErrorCode() + " " + se.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + e.getMessage());
        }
    }

}