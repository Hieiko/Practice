import java.io.PrintWriter;

public class HelloServlet extends  HttpServlet{
    public void doGet(HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException {
//        Input input=request.getParameter(Input.class);
        String name=request.getParameter("name");
        PrintWriter out=response.getWriter();
//        out.print("hello"+""+input.name+"</br>");
//        out.print("age="+input.age);
        if (name==null||name.trim().isEmpty()){
            out.print("hello");
        }else {
            out.print(name);
        }


    }
}
