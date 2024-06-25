/** 
 * 従業員の情報を表示するクラス
 
 */

import java.util.*;
import java.sql.*;

public class PrintAllEmployeeInformation extends AbstractExecuter {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String getSQLtemplate() {
        return "SELECT employee.staffID, staff.staffName, staff.phoneNumber, staff.address, staff.birthDate, staff.fare, employee.position,employee.salary " +
               "FROM employee " + 
               "LEFT JOIN Staff ON employee.staffID = Staff.staffID";
    }

    @Override
    public void setQuery(PreparedStatement st) throws SQLException {
        
    }

    @Override
    public void showResult(ResultSet r) {
        try {
            System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10s\n", 
                "staffID", "staffName", "phoneNumber", "address", "birthDate", "fare", "position","salary");

            while (r.next()) {
                String staffID = r.getString("staffID");
                String staffName = r.getString("staffName");
                String phoneNumber = r.getString("phoneNumber");
                String address = r.getString("address");
                String birthDate = r.getString("birthDate");
                int fare = r.getInt("fare");
                String position = r.getString("position");
                int salary = r.getInt("salary");

                // フィールドが全て空でない場合にのみ表示
                if (staffID != null && !staffID.isEmpty()) {
                    System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\t%-10s\t%-10d\t%-10s\t%-10d\n",
                            staffID, staffName, phoneNumber, address, birthDate, fare, position, salary);
                }
            }
        } catch (SQLException se) {
            System.out.println("SQL Error: " + se.toString() + " " + se.getErrorCode() + " " + se.getSQLState());
        } catch (Exception e) {
            System.out.println("Error: " + e.toString() + e.getMessage());
        }
    }
}
