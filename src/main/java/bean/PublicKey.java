package bean;
import java.time.LocalDateTime;

public class PublicKey {
    private int id;
    private int idUser;
    private String publicKey;
    private LocalDateTime startTime;
    private LocalDateTime expiredTime;
    private boolean isValid;

    // Constructor
    public PublicKey(int id, int idUser, String publicKey, LocalDateTime startTime, LocalDateTime expiredTime, boolean isValid) {
        this.id = id;
        this.idUser = idUser;
        this.publicKey = publicKey;
        this.startTime = startTime;
        this.expiredTime = expiredTime;
        this.isValid = isValid;
    }

    // Default Constructor
    public PublicKey() {}

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "PublicKey{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", publicKey='" + publicKey + '\'' +
                ", startTime=" + startTime +
                ", expiredTime=" + expiredTime +
                ", isValid=" + isValid +
                '}';
    }
}
