import java.util.Date;
import java.util.Objects;

public class Tweet {
    private String name;
    private String text;
    private Date creationDate;

    public Tweet() {}

    public Tweet(String name, String text, Date creation) {
        this.name = name;
        this.text = text;
        this.creationDate = creation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
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
        return Objects.equals(name, that.name) &&
                Objects.equals(text, that.text) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text, creationDate);
    }

    @Override
    public String toString() {
        return "Tweet: " +
                "\nData criação: " + creationDate +
                "\nNome do usuario:' " + name +
                "\nMensagem: \n" + text + "\n";
    }
}
