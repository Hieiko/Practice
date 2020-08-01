public class CookieServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException {
        Cookie cookie =new Cookie("name","wusong");
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);
        response.getWriter().append("cookie");
    }
}
