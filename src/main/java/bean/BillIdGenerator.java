package bean;

import java.util.concurrent.atomic.AtomicInteger;

public class BillIdGenerator {
    private static final AtomicInteger counter = new AtomicInteger(1);
//   Lớp BillIdGenerator chỉ chứa một trường AtomicInteger để tạo billId duy nhất mỗi lần gọi phương thức generateBillId. Mỗi khi bạn cần một billId mới, chỉ cần gọi BillIdGenerator.generateBillId() từ bất kỳ đâu trong ứng dụng của bạn.
    public static int generateBillId() {
        return counter.getAndIncrement();
    }
}
