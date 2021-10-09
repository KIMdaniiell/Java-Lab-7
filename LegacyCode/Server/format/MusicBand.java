package format;

import format.exceptions.InvalidInputValueException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Stack;

/**
 * Class required for storing MusicBand instances
 */
public class MusicBand implements Comparable<MusicBand>, Serializable {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private LocalDate creationDate;
    private int numberOfParticipants;
    private String description;
    private Date establishmentDate;
    private MusicGenre genre;
    private Person frontMan;

    public void setCreationDate() {
        Date date = new Date();
        this.creationDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidInputValueException {
        if (name.equals("")) {
            throw new InvalidInputValueException("Недопустимое значение NAME.");
        } else {
            this.name = name;
        }

    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date date) {
        this.establishmentDate = date;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer num) {
        this.numberOfParticipants = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Stack<MusicBand> mystack) {
        HashSet<Integer> setofid = new HashSet<Integer>();
        for (MusicBand band : mystack) {
            setofid.add(band.getId());
        }
        int idvalue = mystack.size() + 1;
        for (int i = mystack.size(); i > 0; i--) {
            if (!setofid.contains(Integer.valueOf(i)) && (i <= idvalue)) {
                idvalue = i;
            }
        }
        this.id = idvalue;
    }

    public void setId(Integer id, Stack<MusicBand> mystack) throws InvalidInputValueException {
        for (MusicBand band : mystack) {
            if (band.getId().equals(id)) {
                throw new InvalidInputValueException("Ошибка параметра id. \nЭлементов с таким значением больше одного.");
            }
        }
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public Person getFrontMan() {
        return frontMan;
    }

    public void setFrontMan(Person frontMan) {
        this.frontMan = frontMan;
    }

    public void setFrontMan(String s) {
        this.frontMan = null;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void saveID(Integer defid) {
        this.id = defid;
    }

    /**
     * Method used to check if all of nessessary fileds are given a value
     *
     * @return true if all of fields are given a value and false if not
     */
    public boolean Complete() {
        if ((this.name == null) || (this.coordinates == null) || (this.description == null) || (this.genre == null)) {
            return false;
        } else return (this.frontMan == null) || (this.frontMan.Complete());
    }

    @Override
    public String toString() {
        return getClass().getName() + '-' + name + '-' + id;
    }

    /**
     * Overrided methor nessessary for comparing MusicBand instances
     *
     * @param m
     * @return
     */
    @Override
    public int compareTo(MusicBand m) {
        int result = this.name.compareTo(m.getName());
        if (result == 0) {
            result = this.genre.compareTo(m.genre);
        }
        return result;
    }
}
