public class ClusterMessage {
    private String message;
    private String photo;

    ClusterMessage(String message, String photo){
        this.message = message;
        this.photo = photo;
    }

    ClusterMessage(String message){
        this.message = message;
        this.photo = null;
    }

    public String getMessage(){
        return message;
    }

    public String getPhoto(){
        return photo;
    }

    public boolean haveMessage() { return message != null; }

    public boolean havePhoto() { return photo != null; }
}
