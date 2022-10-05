
package servlets;

import controller.PuestosController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ServletPuestoDevolver", urlPatterns = {"/ServletPuestoDevolver"})
public class ServletPuestoDevolver extends HttpServlet {
    
    public ServletPuestoDevolver() {
        super();
   
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PuestosController puesto = new PuestosController();

        String username = request.getParameter("username");
        int id = Integer.parseInt(request.getParameter("id"));

        String puestoStr = puesto.retirar(id, username);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(puestoStr);
        out.flush();
        out.close();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }


}
