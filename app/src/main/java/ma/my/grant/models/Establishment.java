package ma.my.grant.models;

public class Establishment {
    private int id;
    private int university;
    private String name;
    private String city;
    private String address;

    public Establishment() {
    }

    public Establishment(int id, int university, String name, String city, String address) {
        this.id = id;
        this.university = university;
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUniversity() {
        return university;
    }

    public void setUniversity(int university) {
        this.university = university;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
