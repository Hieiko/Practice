public class ToIndexServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException {
        response.sendRedirect("/photo/index.html");
    }
}
