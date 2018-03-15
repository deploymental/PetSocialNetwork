package old;

import com.getjavajob.AccountDAO;
import com.getjavajob.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class ImageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("accId"));
        if (id > 0) {
            AccountService as = new AccountService(new AccountDAO());
            byte[] content = new byte[0];
            try {
                content = as.getAccount(id).getImage();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.getOutputStream().write(content);
            resp.getOutputStream().flush();
            resp.getOutputStream().close();
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}