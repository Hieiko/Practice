import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpServletRequest {

    private  String method;
    private  String requestUri;
    private  String protocol;
    // 存放头域键值对的map集合
    private  Map<String, String> headerMap = new HashMap<>();
    private  Map<String, String> paramsMap= new HashMap<>();
    public HttpServletRequest(String requestText) {
        // 完成对请求报文的解析
        String[] lines = requestText.split("\\n");
        String[] items = lines[0].split("\\s");
        method = items[0];
        requestUri = items[1];
        protocol = items[2];

//        String uri=this.getRequestURI();
        Pattern pattern=Pattern.compile("[^&]+=[^&]+");
        String str="?";
        boolean status=requestUri.contains(str);
        if (status){
            String uri = requestUri.split("\\?")[1];
            Matcher m=pattern.matcher(uri);
            while (m.find()){
                String[] temp=m.group().split("=");
                paramsMap.put(temp[0],temp[1]);
            }
        }

        for (int i = 1; i < lines.length; i++) {
            lines[i] = lines[i].trim();
            if (lines[i].isEmpty()) {
                break;
            }
            System.out.println(lines);
            items = lines[i].split(":");
            headerMap.put(items[0], items[1].trim());
        }
    }

    /**
     * 获取请求的方法名
     * @return
     */
    public String getMethod() {
        return method;
    }

    /**
     * 获取请求资源路径
     * @return
     */
    public String getRequestURI() {
        return requestUri;
    }

    /**
     * 获取协议版本
     * @return
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * 获取头域值 键值对
     * @return
     */
    public String getHeader(String name) {
        return headerMap.get(name);
    }

    /**
     * 获取请求参数
     * @return
     */
//    public String getParameter(String name) {
//        String uri=this.getRequestURI();
//        String params=uri.split(name+"=")[1];
//        String str="=";
//        boolean status=params.contains(str);
//        if (status){
//            String param=params.split("&")[0];
//            return param;
//        }else {
//            return params;
//        }
//    }
    public String getParameter(String name) {
        return paramsMap.get(name);
    }

//    public <T> T getParameter(Class<T> paramT)  {
//        try{
//            Pattern p=Pattern.compile("[a-zA-Z0-9_]+=[a-zA-Z0-9_]+");
//            String uri=this.getRequestURI();
//            uri = uri.split("\\?")[1];
//            Matcher m= p.matcher(uri);
//            Map<String,String> map=new HashMap<>();
//            while (m.find()){
//                String[] temp =m.group().split("=");
//                map.put(temp[0],temp[1]);
//            }
//            Field[] fields=paramT.getDeclaredFields();
//            T out =paramT.newInstance();
//            for (Field field:fields){
//                String val=  map.getOrDefault(field.getName(),"");
//                if (field.getAnnotation(Valid.class).required() &&("".equals(val)||null==val)){
//                    return null;
//                }
//                if (!Pattern.matches(field.getAnnotation(Valid.class).pattern(),val)){
//                    if (!field.getAnnotation(Valid.class).required()){
//                        continue;
//                    }
//                    return null;
//                }
//                switch (field.getType().getSimpleName()){
//                    case "Integer":{
//                        field.set(out,Integer.parseInt(val));
//                        break;
//                    }
//                    default:{
//                        field.set(out,val);
//                        break;
//                    }
//                }
//                System.out.println(field.getType().getSimpleName());
//            }
////        map.forEach((key,val)->{
////            try {
////                Field field = paramT.getField(key);
////                switch (field.getType().getSimpleName()){
////                    case "Integer":{
////                        field.set(out,Integer.parseInt((String) val));
////                        break;
////                    }
////                    default:{
////                        field.set(out,val);
////                        break;
////                    }
////                }
////            } catch (NoSuchFieldException | IllegalAccessException e) {
////                e.printStackTrace();
////            }
////
////        });
//            return out;
//        }catch (Exception e){
//            return null;
//        }
//
//    }

    /**
     * 获取请求cookie数据
     * @return
     */
    public Cookie[] getCookies() {
        String cookieString=headerMap.get("Cookie");
        if (cookieString==null){
            return null;
        }else {
            List<Cookie> cookieList =new ArrayList<>();
            String[] sCookies =cookieString.split(";\\S*");
            for (int i=0;i<sCookies.length;i++){
                String[] nv =sCookies[i].split("=");
                cookieList.add(new Cookie(nv[0],nv[1]));
            }
            return cookieList.toArray(new Cookie[0]);
        }
    }


}