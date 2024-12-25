package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name ="DownloadToolController", value = "/download-tool")
public class DownloadToolController extends HttpServlet {
    private static final String FILE_PATH = "/tool/projectmain-1.0-SNAPSHOT.jar"; // Đường dẫn tương đối tới file JAR trong thư mục webapp

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = getServletContext().getRealPath(FILE_PATH); // Lấy đường dẫn tuyệt đối tới file
        File file = new File(filePath);

        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
            return;
        }

        // Thiết lập header cho file download
        resp.setContentType("application/java-archive");
        resp.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        // Gửi file tới client
        try (FileInputStream fileInputStream = new FileInputStream(file);
             OutputStream outputStream = resp.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

//        req.getRequestDispatcher("orderSucecssfully.jsp").forward(req, resp);
    }
}