public class ClusterMessage {
    private String message;
    private String photo;

    ClusterMessage(String message, String photo){
        this.message = message;
        this.photo = photo;
    }

    public String getMessage(){
        return message;
    }

    public String getPhoto(){
        return photo;
    }
}
