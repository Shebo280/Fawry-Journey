package servlets;

import entity.Product;
import repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchType = req.getParameter("searchType");
        String query = req.getParameter("query");

        resp.setContentType("text/html");

        if (query == null || query.trim().isEmpty()) {
            resp.getWriter().println("<h1>Invalid Search Query</h1>");
            return;
        }

        Optional<Product> product = Optional.empty();
        if ("id".equals(searchType)) {
            try {
                int id = Integer.parseInt(query);
                product = ProductRepository.getProductById(id);
            } catch (NumberFormatException e) {
                resp.getWriter().println("<h1>Error: Invalid ID format</h1>");
                return;
            }
        } else if ("name".equals(searchType)) {
            product = ProductRepository.getProductByName(query);
        }


        if (product.isPresent()) {
            resp.getWriter().println("<h1>Search Results</h1>");
            resp.getWriter().println(product + "<br>");
        } else {
            resp.getWriter().println("<h1>No Product Found</h1>");
        }

        resp.getWriter().println("<a href='searchProduct.html'>Back to Search</a>");
    }

}
