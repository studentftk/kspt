package server.logic;

/**
 * Created by Alexander Ulitin on 11.12.2014.
 */
public class PlaceComment {
    private int ID;
    private int PlaceId;
    private String Comment;

    public PlaceComment(int ID, int placeId, String comment) {
        this.ID = ID;
        PlaceId = placeId;
        Comment = comment;
    }

    public int getID() {
        return ID;
    }

    public int getPlaceId() {
        return PlaceId;
    }

    public String getComment() {
        return Comment;
    }
}
