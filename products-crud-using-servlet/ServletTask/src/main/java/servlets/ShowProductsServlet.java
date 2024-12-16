    package servlets;

    import entity.Product;
    import repository.ProductRepository;

    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.util.List;

    @WebServlet("/showProducts")
    public class ShowProductsServlet extends HttpServlet {
            @Override
            protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                List<Product> allProducts = ProductRepository.getAllProducts();

                resp.setContentType("text/html");
                if (!allProducts.isEmpty()) resp.getWriter().println("<h1>All Products</h1>");
                else resp.getWriter().println("<h1>There are no Products</h1>");
                resp.getWriter().println("<ul>");
                for (Product product : allProducts) {
                    resp.getWriter().println("<li>Product ID: " + product.getId() + " | Name: " + product.getName() + " | Price: $" + product.getPrice() + "</li>");
                }
                resp.getWriter().println("</ul>");
                resp.getWriter().println("<a href='index.html'>Back to Home</a>");
            }

    }
