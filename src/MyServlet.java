import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("login")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = new Date();
        resp.setContentType("text/html;charset=UTF-8");
        try(PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + "Hello, " +req.getParameter("login") +"</h1>");
            if (req.getParameter("password").equals("pass")) {
                out.println("<h2> Time is: " + date.toString() + "</h2>");
                out.println("<h2> Your user agent: " + req.getHeader("User-Agent") + "</h2>");
            } else
                out.println("<h2>" + "Your password is not correct" +"</h2>");
            out.println("</body>");
            out.println("</html>");

        }


    }


}
