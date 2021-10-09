package format;

import console.exceptions.InvalidInputValueException;

import java.io.Serializable;

/**
 * This class contains numeric parameters of MusicBand.FrontMans' location
 */
public class Location implements Serializable {
    private Integer x; //Поле не может быть null
    private int y;
    private int z;

    /**
     * Setter for Y paremeter
     *
     * @param value - string representation of Y paremeter
     * @throws InvalidInputValueException if value does not feat the conditions written in the Task
     */
    public void setY(String value) throws InvalidInputValueException {
        if (!value.equals("")) {
            y = Integer.parseInt(value);
        } else {
            throw new InvalidInputValueException("Недопустимое значение Person.Location.Y");
            //примитивный тип int не может принимать значение null
        }
    }

    /**
     * Setter for X paremeter
     *
     * @param value - string representation of X paremeter
     * @throws InvalidInputValueException if value does not feat the conditions written in the Task
     */
    public void setX(String value) throws InvalidInputValueException {
        if (!value.equals("")) {
            x = Integer.valueOf(value);
        } else {
            throw new InvalidInputValueException("Недопустимое значение Person.Location.X");
        }
    }

    /**
     * Setter for Y paremeter
     *
     * @param value - string representation of Y paremeter
     * @throws InvalidInputValueException if value does not feat the conditions written in the Task
     */
    public void setZ(String value) throws InvalidInputValueException {
        if (!value.equals("")) {
            z = Integer.parseInt(value);
        } else {
            throw new InvalidInputValueException("Недопустимое значение Person.Location.Z");
            //примитивный тип int не может принимать значение null
        }
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Integer getX() {
        return x;
    }

    /**
     * Method used to check if all of nessessary fileds are given a value
     *
     * @return true if all of fields are given a value and false if not
     */
    public boolean Complete() {
        return this.x != null;
    }
}