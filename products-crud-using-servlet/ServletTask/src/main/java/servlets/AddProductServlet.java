package servlets;

import entity.Product;
import repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            String priceParam = req.getParameter("price");

            resp.setContentType("text/html");

            try {
                double price = Double.parseDouble(priceParam);
                Product product = ProductRepository.addProduct(name, price);
                System.out.println("Added Product: " + product);
                resp.getWriter().println("<h1>Added Product</h1>");
                resp.getWriter().println(product + "<br>");
            } catch (NumberFormatException | NullPointerException e) {
                resp.getWriter().println("<h1>Error: Invalid input</h1>");
                resp.getWriter().println("<p>Please provide valid 'name' and 'price' parameters.</p>");
            } catch (RuntimeException e) {
                resp.getWriter().println("<h1>Error</h1>");
                resp.getWriter().println("<p>" + e.getMessage() + "</p>");


            }

            resp.getWriter().println("<br><a href='addProduct.html'>Back to Add</a>");
        }

}
