package servlets;

import repository.ProductRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deleteType = req.getParameter("deleteType");
        String query = req.getParameter("query");

        resp.setContentType("text/html");

        if (query == null || query.trim().isEmpty()) {
            resp.getWriter().println("<h1>Error: Invalid Input</h1>");
            resp.getWriter().println("<p>Please provide valid ID or Name.</p>");
            return;
        }

        try {
            if ("id".equals(deleteType)) {
                int id = Integer.parseInt(query);
                ProductRepository.deleteProduct(id);
                resp.getWriter().println("<h1>Deleted Product with ID: " + id + "</h1>");
            } else if ("name".equals(deleteType)) {
                ProductRepository.deleteProduct(query);
                resp.getWriter().println("<h1>Deleted Product with Name: " + query + "</h1>");
            } else {
                resp.getWriter().println("<h1>Error: Invalid Delete Type</h1>");
            }
        } catch (NumberFormatException e) {
            resp.getWriter().println("<h1>Error: Invalid ID Format</h1>");
        } catch (RuntimeException e) {
            resp.getWriter().println("<h1>Error: Product Not Found</h1>");
            resp.getWriter().println("<p>" + e.getMessage() + "</p>");
        }

        resp.getWriter().println("<br><a href='deleteProduct.html'>Back to Delete</a>");
    }

}
