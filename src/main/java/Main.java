import model.JournalRanking;
import model.Paper;
import model.PaperStatus;
import vn.edu.epu.quanlyhoso.controller.PaperController;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo Controller (đóng vai trò như Postman gọi API)
        PaperController api = new PaperController();

        System.out.println("--- BẮT ĐẦU TEST HỆ THỐNG ---");

        // 1. TEST GIẢNG VIÊN KHAI BÁO BÀI BÁO
        System.out.println("\n[1] Giảng viên tiến hành khai báo bài báo mới...");
        Paper newPaper = new Paper();
        newPaper.setTitle("Nghiên cứu ứng dụng Apache Lucene trong tìm kiếm");
        newPaper.setJournalName("Tạp chí Khoa học Máy tính");
        newPaper.setPublicationYear(2025);
        newPaper.setIssnIsbn("1234-5678");
        newPaper.setJournalRanking(JournalRanking.DOMESTIC);
        newPaper.setCreatorId(1); // Giả sử ID giảng viên là 1
        
        api.declareNewPaper(newPaper);

        // 2. TEST ADMIN XEM DANH SÁCH CHỜ DUYỆT
        System.out.println("\n[2] Admin lấy danh sách bài báo đang chờ duyệt...");
        // Lưu ý: Trong PaperController, bạn phải viết hàm in ra danh sách này
        api.getAllPendingPapers(); 

        // 3. TEST ADMIN DUYỆT BÀI BÁO
        // (Giả sử bài báo vừa thêm vào DB được cấp ID tự động là 1)
        System.out.println("\n[3] Admin tiến hành xác thực (Approve) bài báo số 1...");
        api.verifyPaper(1, PaperStatus.APPROVED);

        System.out.println("\n--- HOÀN TẤT TEST ---");
    }
}