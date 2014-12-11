package server.logic;

public class Place {
    private int Id;
    private String Type;
    private String Street;
    private int HouseNumber;
    private int HouseCorp;
    private String Geo;
    private String About;

    public Place(int id, String type, String street, int houseNumber, int houseCorp, String geo, String about) {
        Id = id;
        Type = type;
        Street = street;
        HouseNumber = houseNumber;
        HouseCorp = houseCorp;
        Geo = geo;
        About = about;
    }

    public int getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public String getStreet() {
        return Street;
    }

    public int getHouseNumber() {
        return HouseNumber;
    }

    public int getHouseCorp() {
        return HouseCorp;
    }

    public String getGeo() {
        return Geo;
    }

    public String getAbout() {
        return About;
    }
}
