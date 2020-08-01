public class Input {

    @Valid(pattern = "\\S+")
    public String name;
    @Valid(pattern = "\\d+")
    public Integer age;
    @Valid(pattern = "\\S+:\\d+")
    public String ip;

    public String toString(){
        return "name:"+name+",age:"+age.toString()+",ip"+ip;
    }
}

