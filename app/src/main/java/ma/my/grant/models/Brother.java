package ma.my.grant.models;

public class Brother {
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private boolean isStudying;
    private boolean isHandicapped;

    public Brother() {
    }

    public Brother(int id, String firstName, String lastName, String birthDate, boolean isStudying, boolean isHandicapped) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.isStudying = isStudying;
        this.isHandicapped = isHandicapped;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isStudying() {
        return isStudying;
    }

    public void setStudying(boolean studying) {
        isStudying = studying;
    }

    public boolean isHandicapped() {
        return isHandicapped;
    }

    public void setHandicapped(boolean handicapped) {
        isHandicapped = handicapped;
    }
}
