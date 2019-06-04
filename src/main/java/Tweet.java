import java.util.Date;
import java.util.Objects;

public class Tweet {
    private String username;
    private String text;
    private Date creationDate;

    public Tweet() { }

    public Tweet(String username, String text, Date creationDate) {
        this.username = username;
        this.text = text;
        this.creationDate = creationDate;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getUserName() {
        return username;
    }

    public String getText() {
        return text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet that = (Tweet) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(text, that.text) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, text, creationDate);
    }

    @Override
    public String toString() {
        return "Tweet: " +
                "\nData criação: " + creationDate +
                "\nNome do usuario: " + username +
                "\nMensagem: \n" + text + "\n";
    }

}
