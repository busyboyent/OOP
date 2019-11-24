public class UserData {
    private String name;
    private String city;
    private RegistrationStatus registrationStatus;

    {
        name = "unknownName";
        city = "unknownCity";
        registrationStatus = RegistrationStatus.INACTIVE;
    }

    void SetName(String name){
        this.name = name;
    }

    String GetName(){
        return name;
    }

    void SetCity(String city) { this.city = city; }

    public String GetCity(){
        return city;
    }

    void setRegistrationStatus(RegistrationStatus registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    RegistrationStatus getRegistrationStatus() { return registrationStatus; }

    @Override
    public String toString() {
        return "name: " + name
                + "\ncity: " + city;
    }
}
