package server.logic;

import java.util.Date;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class PlaceVisit {
    private int IdUser;
    private int idPlace;
    private Date date;

    public PlaceVisit(int idUser, int idPlace, Date date) {
        IdUser = idUser;
        this.idPlace = idPlace;
        this.date = date;
    }

    public int getIdUser() {
        return IdUser;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public Date getDate() {
        return date;
    }
}
