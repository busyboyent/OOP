public class UserData {
    private String name;
    private String city;

    {
        name = "unknownName";
        city = "unknownCity";
    }

    public void SetName(String name){
        this.name = name;
    }

    public String GetName(){
        return name;
    }

    public void SetCity(String city){
        this.city = city;
    }

    public String GetCity(){
        return city;
    }
}
