import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TraverseMap {
    /*
    todo 不同遍历方式性能的测试
     */
    public static void main(String[] args) {
        Map<String,Integer> map=inputMap();
        showMap1(map);
        System.out.println();
        showMap2(map);
        System.out.println();
        showMap3(map);
        System.out.println();
        showMap4(map);
        System.out.println();
        showMap5(map);
    }
    /*
    keySet()todo 获取key 再通过key 获取值
     */
    public static void showMap1(Map<String,Integer> userMap){
        for (String key:userMap.keySet()){
            System.out.println(key+"的值为"+userMap.get(key));
        }
    }
    /*
    values() todo 只能获取值
    */
    public static void showMap2(Map<String,Integer> userMap){
        for (Integer val:userMap.values()){
            System.out.println(val);
        }
    }
    /*
    entrySet()// todo 节点node
    */
    public static void showMap3(Map<String,Integer> userMap){
        for (Map.Entry<String,Integer> entry:userMap.entrySet()){
            System.out.println(entry.getKey()+"的值为"+entry.getValue());
        }
    }
    /*
    iterator迭代器
    */
    public static void showMap4(Map<String,Integer> userMap){
        Iterator<Map.Entry<String,Integer>> iterator=userMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> entry=iterator.next();
//            System.out.println(iterator.hashCode());//todo 迭代器hashcode一致
            System.out.println(entry.getKey()+"的值为"+entry.getValue());
        }
    }
    /*
    lambda //todo 兰姆达
    */
    public static void showMap5(Map<String,Integer> userMap){
//        userMap.forEach((key,value)->{
//            System.out.println(key+"的值为"+value);//todo 仅有单独输出语句时 无需花括号
//        });
        userMap.forEach((key,value)-> System.out.println(key+"的值为"+value));
    }
    /*
    新建一个map 静态方法
     */
    public static Map<String,Integer> inputMap(){
        Map<String,Integer> userMap =new HashMap<>();//初始化一个map
        userMap.put("user1", 1);
        userMap.put("user2", 2);
        userMap.put("user3", 3);
        userMap.put("user4", 4);
        userMap.put("user5", 5);
        return userMap;
    }
}
