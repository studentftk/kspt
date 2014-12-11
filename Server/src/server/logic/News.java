package server.logic;

import java.util.Date;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class News {
    private int ID;
    private String Header;
    private String Annotation;
    private Date date;
    private String text;
    private String Photo;

    public News(int ID, String header, String annotation, Date date, String text, String photo) {
        this.ID = ID;
        Header = header;
        Annotation = annotation;
        this.date = date;
        this.text = text;
        Photo = photo;
    }

    public int getID() {
        return ID;
    }

    public String getHeader() {
        return Header;
    }

    public String getAnnotation() {
        return Annotation;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public String getPhoto() {
        return Photo;
    }
}
