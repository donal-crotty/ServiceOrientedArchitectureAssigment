package dao.details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetailsServiceDAO {

    private Connection con = null;

    public static void main(String[] args) {
        DetailsServiceDAO dao = new DetailsServiceDAO();
//        System.out.println(dao.getNextAccountNumber("123456"));
    }


    public DetailsServiceDAO() {
        try {
            System.out.println("Loading db driver...");
            con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/SOA4_DB",
                    "sean",
                    "sean");
        } catch (SQLException ex) {
            //Logger.getLogger(BankServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("SQLException");
            ex.printStackTrace();
        }
    }
    
    public Details getDetails(int id) {
        Details ba = null;
        try {

            PreparedStatement ps
                    = con.prepareStatement(
                            "SELECT * FROM APP.DETAILS WHERE "
                            + "ID=?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            // move the cursor to the start
            if (!rs.next()) {
                return null;
            }

            // ok here, have at least one record
            ba = new Details(
                    rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getString(4));

        } catch (SQLException ex) {
//            Logger.getLogger(BankServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("SQLException");
            ex.printStackTrace();
        }
        return ba;
    }

    public ArrayList<Details> getAllDetails() {
        ArrayList<Details> details
                = new ArrayList<>();

        try {

            PreparedStatement ps
                    = con.prepareStatement(
                            "SELECT * FROM APP.DETAILS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Details ba = new Details(
                        rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getString(4));
                details.add(ba);
            }

        } catch (SQLException ex) {
//            Logger.getLogger(BankServiceDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("SQLException");
            ex.printStackTrace();
        }

        return details;
    }
}
