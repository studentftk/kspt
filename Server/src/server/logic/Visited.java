package server.logic;

import java.sql.Timestamp;

public class Visited {
    
    public final Long idPlace;
    public final Timestamp timeVisited;
    
    public Visited(long idPlace, Timestamp timeVisited){
        this.idPlace = idPlace;
        this.timeVisited = timeVisited;
    }
    
}
