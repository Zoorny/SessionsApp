import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

@WebServlet("login")
public class MyServlet extends HttpServlet {

    protected boolean loginExists(String login){
        String users = readUsers();
        return users.contains('"' + login + "@");
    }

    protected boolean checkPassword(String login, String password){
        String users = readUsers();
        return users.contains('"'+login+ "@" + password + '"');
    }

    protected String readUsers(){
        String out = "";
        try {
            FileInputStream fstream = new FileInputStream("C:\\Users\\Zoorny\\IdeaProjects\\SessionsApp\\users.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                out+= strLine;
            }
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
        return out;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sLogin = null;
        String sPassword = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(false);
        if (session != null) {
            sLogin = (String)session.getAttribute("login");
            sPassword = (String)session.getAttribute("password");
        }


        if(login != "" || loginExists(sLogin)){
            if (loginExists(login) || loginExists(sLogin)){
                if (checkPassword(login, password) || (checkPassword(sLogin, sPassword)  && login=="" && password=="")){
                    if (!checkPassword(sLogin, sPassword)) {
                        session.setAttribute("login", login);
                        session.setAttribute("password", password);
                    }
                    try(PrintWriter out = resp.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet MyServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<img src=\"images/hello.jpg\" />");
                        if (login != "") out.println("<h1>" + "Welcome "+ login + "</h1>");
                        else out.println("<h1>" + "Welcome "+ sLogin + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
                else {
                    session.setAttribute("login", null);
                    session.setAttribute("password", null);
                    try (PrintWriter out = resp.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet MyServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>" + "Invalid password for " + login + "</h1>");
                        out.println("<form action=\"Redirect\" method=\"POST\">" + "<input type=\"submit\" value=\"ToLogin\" />" + "</form>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                }
            }
            else {
                resp.sendRedirect("register.html");
            }
        }
        else
            resp.sendRedirect("login.html");
    }
}