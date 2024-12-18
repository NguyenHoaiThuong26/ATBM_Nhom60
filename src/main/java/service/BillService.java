package service;

import bean.Bill;
import dao.BillDAO;
import dao.ProductDAO;

public class BillService {
    public static BillService instance;
    private BillDAO billDAO;



    public static BillService getInstance() {
        if (instance == null) instance = new BillService();
        return instance;
    }

    public static Bill getBillById(int id) {
        return BillDAO.getInstance().getBillById(id);
    }

    public static void main(String[] args) {
        BillService billService = new BillService();
        System.out.println(billService.getBillById(1));
    }
}
