import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class TestMap {
    /*
    todo 不同遍历方式性能的测试
     */
    public static void main(String[] args) {
        Integer integer=100000;//测试基数
        Map<String,Integer> map=inputMap(integer);
        showMap1(map);
        showMap2(map);
        showMap3(map);
        showMap4(map);
//        showMap5(map);
    }
    /*
    keySet()todo 获取key 再通过key 获取值
     */
    public static void showMap1(Map<String,Integer> userMap){
        Integer value;
        long start=System.currentTimeMillis();
        for (String key:userMap.keySet()){
            value=userMap.get(key);
        }
        long end=System.currentTimeMillis();
        System.out.println("KeySet()耗时"+(end-start));
    }
    /*
    values() todo 只能获取值
    */
    public static void showMap2(Map<String,Integer> userMap){
        Integer value;
        long start=System.currentTimeMillis();
        for (Integer val:userMap.values()){
            value=val;
        }
        long end=System.currentTimeMillis();
        System.out.println("values耗时"+(end-start));
    }
    /*
    entrySet()// todo 节点node
    */
    public static void showMap3(Map<String,Integer> userMap){
        Integer value;
        long start=System.currentTimeMillis();
        for (Map.Entry<String,Integer> entry:userMap.entrySet()){
            value=entry.getValue();
        }
        long end=System.currentTimeMillis();
        System.out.println("entrySet()耗时"+(end-start));
    }
    /*
    iterator迭代器
    */
    public static void showMap4(Map<String,Integer> userMap){
        Integer value;
        long start=System.currentTimeMillis();
        Iterator<Map.Entry<String,Integer>> iterator=userMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,Integer> entry=iterator.next();
            value=entry.getValue();
        }
        long end=System.currentTimeMillis();
        System.out.println("Iterator耗时"+(end-start));
    }
    /*
    lambda //todo 兰姆达
    */
//    public static void showMap5(Map<String,Integer> userMap){
//        Object values;
//        long start=System.currentTimeMillis();
//        userMap.forEach((key,value)-> {
//            ;
//        });
//        long end=System.currentTimeMillis();
//        System.out.println("耗时"+(end-start));
//    }
    /*
    新建一个map 静态方法
     */
    public static Map<String,Integer> inputMap(Integer integer){
        Map<String,Integer> userMap =new HashMap<>();//初始化一个map
        String[] str =new String[]{"a","b","c","d","e","f","g","h","i","j"};
        String key;
        int value;
        for (int i=0;i<integer;i++){
            int m= (int) (Math.random()*10);
            key=String.valueOf(str[m]+i*100);
            value=i;
            userMap.put(key,value);
        }
        return userMap;
    }
}
