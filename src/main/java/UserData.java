public class UserData {
    private String name;
    private String city;
    private RegistrationStatus registrationStatus;

    {
        name = "unknownName";
        city = "unknownCity";
        registrationStatus = RegistrationStatus.INACTIVE;
    }

    public void SetName(String name){
        this.name = name;
    }

    public String GetName(){
        return name;
    }

    public void SetCity(String city) { this.city = city; }

    public String GetCity(){
        return city;
    }

    public void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public RegistrationStatus getRegistrationStatus() { return registrationStatus; }

    @Override
    public String toString() {
        return "name: " + name
                + "\ncity: " + city;
    }
}
