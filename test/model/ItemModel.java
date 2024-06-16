package lk.ijse.thogakade.model;

import lk.ijse.thogakade.to.Item;
import lk.ijse.thogakade.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    public static boolean save(Item item) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?, ?, ?, ?)";
        return CrudUtil.execute(sql, item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand());
    }

    public static boolean update(Item item) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Item SET description = ?, unitPrice = ?, qtyOnHand = ? WHERE code = ?";
        return CrudUtil.execute(sql, item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode());
    }

    public static boolean delete(String code) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM Item WHERE code = ?";
        return CrudUtil.execute(sql, code);
    }

    public static Item search(String code) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Item WHERE code = ?";
        ResultSet result = CrudUtil.execute(sql, code);

        if (result.next()) {
            return new Item(
                    result.getString("code"),
                    result.getString("description"),
                    result.getDouble("unitPrice"),
                    result.getInt("qtyOnHand")
            );
        }
        return null;
    }

    public static List<Item> getAllItems() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM Item";
        ResultSet result = CrudUtil.execute(sql);

        List<Item> itemList = new ArrayList<>();
        while (result.next()) {
            itemList.add(new Item(
                    result.getString("code"),
                    result.getString("description"),
                    result.getDouble("unitPrice"),
                    result.getInt("qtyOnHand")
            ));
        }
        return itemList;
    }
}
