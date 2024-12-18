package dao;

import bean.Supplier;
import db.JDBIConnector;

import java.util.List;
import java.util.stream.Collectors;

public class SupplierDAO {

    public static List<Supplier> getListSupplier() {
        List<Supplier> supplierList = JDBIConnector.me().withHandle(handle ->
                handle.createQuery("select id, name, email, phone from suppliers")
                        .mapToBean(Supplier.class)
                        .collect(Collectors.toList())
        );
        return supplierList;
    }

    public static void addNewSupplier(Supplier supplier) {
        JDBIConnector.me().withHandle(handle ->
                handle.createUpdate("insert into suppliers(name, email, phone) values (:name, :email, :phone)")
                        .bind("name", supplier.getName())
                        .bind("email", supplier.getEmail())
                        .bind("phone", supplier.getPhone())
                        .execute()
                );
    }

    public static boolean removeSupplier(int id) {
        try {
            return JDBIConnector.me().withHandle(handle ->
                    handle.createUpdate("delete from suppliers where id = :id")
                            .bind("id", id)
                            .execute() > 0
            );
        }catch (Exception e) {
            return false;
        }
    }


}
