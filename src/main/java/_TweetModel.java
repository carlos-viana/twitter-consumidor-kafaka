import java.util.Date;
import java.util.Objects;

public class _TweetModel {

    private String userName;
    private String text;
    private Date creationDate;

    public _TweetModel(String userName, String text, Date creationDate) {
        this.userName = userName;
        this.text = text;
        this.creationDate = creationDate;
    }

    public String getUserName() {
        return userName;
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
        _TweetModel that = (_TweetModel) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(text, that.text) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, text, creationDate);
    }

    @Override
    public String toString() {
        return "Tweet: " +
                "\nData criação: " + creationDate +
                "\nNome do usuario:' " + userName +
                "\nMensagem: \n" + text + "\n";
    }
}
