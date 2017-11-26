import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class RegisterServlet extends HttpServlet {

    protected void register(String login, String password){
        String filePath = "/users.txt";
        String out = '"' + login + "@" + password +'"' + " ";
        try {
            FileWriter writer = new FileWriter(filePath, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(out);
            bufferWriter.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") != ""){
            register(req.getParameter("login"), req.getParameter("password"));
            resp.setContentType("text/html;charset=UTF-8");
            try(PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet MyServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" + "Registration complete" + "</h1>");
                out.println("<form action=\"Redirect\" method=\"POST\">" + "<input type=\"submit\" value=\"ToLogin\" />" + "</form>");
                out.println("</body>");
                out.println("</html>");
            }
        }
        else
            resp.sendRedirect("login.html");



    }
}
