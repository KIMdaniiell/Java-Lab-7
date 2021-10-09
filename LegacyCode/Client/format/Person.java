package format;

import console.exceptions.InvalidInputValueException;

import java.io.Serializable;


/**
 * Class required for storing Person instances
 */
public class Person implements Serializable {
    private String name;
    private String passportID;
    private Color eyeColor;
    private Location location;

    public void setEyeColor(String value) throws InvalidInputValueException {
        if (Color.contains(value)) {
            this.eyeColor = Color.valueOf(value);
        } else {
            throw new InvalidInputValueException("Недопустимое значение Person.EyeColor");
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setName(String value) throws InvalidInputValueException {
        if (!value.equals("")) {
            name = value;
        } else {
            throw new InvalidInputValueException("Недопустимое значение Person.NAME");
        }
    }

    public void setPassportID(String value) throws InvalidInputValueException {
        if ((value.length() >= 5) || (value.equals(""))) {
            this.passportID = value;
        } else {
            throw new InvalidInputValueException("Недопустимое значение Person.PassportID");
        }
    }

    public String getName() {
        return name;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public String getPassportID() {
        return passportID;
    }

    public Location getLocation() {
        //return new String[] {location.getX().toString(),location.getY() + "",location.getZ() + ""};
        return location;
    }

    /**
     * Method used to check if all of nessessary fileds are given a value
     *
     * @return true if all of fields are given a value and false if not
     */
    public boolean Complete() {
        if ((this.name == null) || (this.eyeColor == null)) {
            return false;
        } else return (this.location == null) || (this.location.Complete());
    }
}
