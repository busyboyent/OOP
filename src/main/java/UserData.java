public class UserData {
    private String name;
    private String city;
    private RegistrationStatus registrationStatus;

    {
        name = "unknownName";
        city = "unknownCity";
        registrationStatus = RegistrationStatus.INACTIVE;
    }

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return name;
    }

    void setCity(String city) { this.city = city; }

    public String getCity(){
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
