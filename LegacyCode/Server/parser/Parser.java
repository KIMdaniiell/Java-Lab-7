package parser;

import format.*;
import format.exceptions.InvalidInputValueException;
import format.exceptions.InvalidXMLInputStructureException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Stack;

/**
 * This class makes conversion between MusicBand instances and XML structure
 */
public class Parser {
    private static Stack<MusicBand> mystack = new Stack<>();
    private static String data_path;

    /**
     * Static method used for convertating XML structure into MusicBand instances
     *
     * @param indata_path - path of XML-document
     */
    public static Stack<MusicBand> serialize(String indata_path) {
        //Конвертирует xml структуру в объекты класса MusicBand и наполняет ими коллекцию mystack
        data_path = indata_path;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File(data_path);
            if (file.exists()) {

                Document document = builder.parse(new File(data_path));
                Element rootelement = document.getDocumentElement();
                NodeList bandList = rootelement.getChildNodes();

                for (int bandcounter = 0; bandcounter < bandList.getLength(); bandcounter++) {
                    if (bandList.item(bandcounter) instanceof Element) {
                        MusicBand band = new MusicBand();
                        NodeList fieldList = bandList.item(bandcounter).getChildNodes();
                        for (int fieldcounter = 0; fieldcounter < fieldList.getLength(); fieldcounter++) {
                            if (fieldList.item(fieldcounter) instanceof Element) {
                                String nodename = fieldList.item(fieldcounter).getNodeName();
                                if (fieldList.item(fieldcounter).hasChildNodes()) {

                                    switch (nodename) {
                                        case "coordinates": {
                                            NodeList coordinatesfields = fieldList.item(fieldcounter).getChildNodes();
                                            Coordinates coordinates = new Coordinates();
                                            for (int coordfieldscounter = 0; coordfieldscounter < coordinatesfields.getLength(); coordfieldscounter++) {
                                                if (coordinatesfields.item(coordfieldscounter) instanceof Element) {
                                                    String cnodevalue = ((Element) coordinatesfields.item(coordfieldscounter)).getAttribute("value");
                                                    String cnodename = coordinatesfields.item(coordfieldscounter).getNodeName();
                                                    if (cnodename.equals("x")) {
                                                        coordinates.setX(cnodevalue);
                                                    } else if (cnodename.equals("y")) {
                                                        coordinates.setY(cnodevalue);
                                                    } else {
                                                        throw new InvalidXMLInputStructureException("Ошибка входных данных. Недопустимое поле.");
                                                    }
                                                }
                                            }
                                            band.setCoordinates(coordinates);
                                        }
                                        break;
                                        case "creationDate": {
                                            band.setCreationDate();
                                        }
                                        break;
                                        case "frontMan": {
                                            Person p = new Person();
                                            NodeList personfields = fieldList.item(fieldcounter).getChildNodes();
                                            for (int pfieldscounter = 0; pfieldscounter < personfields.getLength(); pfieldscounter++) {
                                                if (personfields.item(pfieldscounter) instanceof Element) {
                                                    String personnodename = personfields.item(pfieldscounter).getNodeName();
                                                    String personnodevalue = ((Element) personfields.item(pfieldscounter)).getAttribute("value");
                                                    switch (personnodename) {
                                                        case "name":
                                                            p.setName(personnodevalue);
                                                            break;
                                                        case "passportID":
                                                            p.setPassportID(personnodevalue);
                                                            break;
                                                        case "eyeColor":
                                                            p.setEyeColor(personnodevalue);
                                                            break;
                                                        case "location": {
                                                            Location loc = new Location();
                                                            NodeList locationcoords = personfields.item(pfieldscounter).getChildNodes();
                                                            for (int lcoord = 0; lcoord < locationcoords.getLength(); lcoord++) {
                                                                if (locationcoords.item(lcoord) instanceof Element) {
                                                                    String lcnodename = locationcoords.item(lcoord).getNodeName();
                                                                    String lcnodevalue = ((Element) locationcoords.item(lcoord)).getAttribute("value");
                                                                    switch (lcnodename) {
                                                                        case "x":
                                                                            loc.setX(lcnodevalue);
                                                                            break;
                                                                        case "y":
                                                                            loc.setY(lcnodevalue);
                                                                            break;
                                                                        case "z":
                                                                            loc.setZ(lcnodevalue);
                                                                            break;
                                                                    }
                                                                }
                                                            }
                                                            p.setLocation(loc);
                                                        }
                                                        break;
                                                        default:
                                                            throw new InvalidXMLInputStructureException("Ошибка входных данных. Недопустимое поле.");
                                                    }
                                                }
                                            }
                                            band.setFrontMan(p);
                                        }
                                        break;
                                        default:
                                            throw new InvalidXMLInputStructureException("Ошибка входных данных. Недопустимое поле.");
                                    }
                                } else {
                                    String nodevalue = ((Element) fieldList.item(fieldcounter)).getAttribute("value");

                                    switch (nodename) {
                                        case "id":
                                            try {
                                                Integer idvalue = Integer.valueOf(nodevalue);
                                                if (idvalue <= 0) {
                                                    throw new InvalidInputValueException("Недопустимое значение Id.");
                                                }
                                                band.setId(idvalue, mystack);
                                            } catch (NumberFormatException e) {
                                                throw new InvalidInputValueException("Недопустимое значение Id.");
                                            }
                                            break;
                                        case "name":
                                            band.setName(nodevalue);
                                            break;
                                        case "numberOfParticipants":
                                            int value = Integer.parseInt(nodevalue);
                                            if (value > 0) {
                                                band.setNumberOfParticipants(value);
                                            } else {
                                                throw new InvalidInputValueException("Недопустимое значение NumberOfParticipants.");
                                            }
                                            break;
                                        case "description":
                                            if (!nodevalue.equals("")) {
                                                band.setDescription(nodevalue);
                                            } else {
                                                throw new InvalidInputValueException("Недопустимое значение Description.");
                                            }
                                            break;
                                        case "genre":
                                            if (MusicGenre.contains(nodevalue)) {
                                                band.setGenre(MusicGenre.valueOf(nodevalue));
                                            } else {
                                                throw new InvalidInputValueException("Недопустимое значение Genre.");
                                            }
                                            break;
                                        case "frontMan":
                                            band.setFrontMan(nodevalue);
                                            break;
                                        case "establishmentDate":
                                            if (nodevalue.equals("")) {
                                                band.setEstablishmentDate(null);
                                            } else {
                                                try {
                                                    if (nodevalue.split(" ").length == 2) {
                                                        int year = Integer.parseInt(nodevalue.split(" ")[0]);
                                                        int month = Integer.parseInt(nodevalue.split(" ")[1]);
                                                        if ((month > 12) | (month < 1)) {
                                                            throw new InvalidInputValueException("Недопустимое формат ввода Establishment date.");
                                                        } else if (month == 12) {
                                                            year -= 1;
                                                        }
                                                        Date date = new Date(0);
                                                        date.setYear(year);
                                                        date.setMonth(month);
                                                        band.setEstablishmentDate(date);
                                                    } else {
                                                        throw new InvalidInputValueException("Недопустимое формат ввода Establishment date.");
                                                    }


                                                } catch (NumberFormatException e) {
                                                    throw new InvalidInputValueException("Недопустимое значение Establishment date.");
                                                }
                                            }
                                            break;
                                        default:
                                            throw new InvalidXMLInputStructureException("Ошибка входных данных. Недопустимое поле.");
                                    }
                                }
                            }
                        }
                        try {
                            if (band.Complete()) {
                                mystack.push(band);
                            } else {
                                throw new InvalidXMLInputStructureException("Ошибка при чтении из файла. Не найдены необходимые теги");
                            }
                        } catch (InvalidXMLInputStructureException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                }
            } else {
                System.out.println("Файл не найден. Коллекция пуста.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Коллекция пуста.");
        } catch (ParserConfigurationException | IOException e) {
            System.out.println("ParserConfigurationException/IOException");
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println("Недопустимый формат XML документа");
        } catch (InvalidInputValueException | InvalidXMLInputStructureException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return mystack;
    }

    /**
     * Static method used for convertating main collection (Stack<MusicBand>) content into  XML structure
     *
     * @param indata_path - path of XML-document
     * @param somestack   - main collection (Stack<MusicBand>)
     */
    public static void deserialize(String indata_path, Stack<MusicBand> somestack) {
        data_path = indata_path;
        mystack = somestack;

        String xmlstructure = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        File file = new File(data_path);
        try (PrintWriter pw = new PrintWriter(file)){
            if (!file.exists()) {
                file.createNewFile();
            }
            pw.println(xmlstructure);
            pw.println("<MusicBands>");
            for (MusicBand band : mystack) {
                pw.println("\t<MusicBand>");
                pw.printf("\t\t<id value=\"%s\"/>\n", band.getId().toString());
                pw.printf("\t\t<name value=\"%s\"/>\n", band.getName());
                pw.println("\t\t<coordinates>");
                pw.printf("\t\t\t<x value=\"%s\"/>\n", band.getCoordinates().getX() + "");
                pw.printf("\t\t\t<y value=\"%s\"/>\n", band.getCoordinates().getY().toString());
                pw.println("\t\t</coordinates>");
                pw.println("\t\t<creationDate>");
                pw.printf("\t\t\t<year value=\"%s\"/>\n", band.getCreationDate().getYear() + "");
                pw.printf("\t\t\t<month value=\"%s\"/>\n", band.getCreationDate().getMonth().name());
                pw.printf("\t\t\t<dayofmonth value=\"%s\"/>\n", band.getCreationDate().getDayOfMonth() + "");
                pw.println("\t\t</creationDate>");
                pw.printf("\t\t<numberOfParticipants value=\"%s\"/>\n", band.getNumberOfParticipants() + "");
                pw.printf("\t\t<description value=\"%s\"/>\n", band.getDescription());

                if (band.getEstablishmentDate() == null) {
                    pw.printf("\t\t<establishmentDate value=\"%s\"/>\n", "");
                } else {
                    int year = band.getEstablishmentDate().getYear();
                    int month = band.getEstablishmentDate().getMonth();
                    if (month != 0) {
                        pw.printf("\t\t<establishmentDate value=\"%s\"/>\n", year + " " + month + "");
                    } else {
                        pw.printf("\t\t<establishmentDate value=\"%s\"/>\n", year + " " + 12 + "");
                    }

                }

                pw.printf("\t\t<genre value=\"%s\"/>\n", band.getGenre().name());
                if (band.getFrontMan() != null) {
                    pw.println("\t\t<frontMan>");
                    pw.printf("\t\t\t<name value=\"%s\"/>\n", band.getFrontMan().getName());
                    pw.printf("\t\t\t<passportID value=\"%s\"/>\n", band.getFrontMan().getPassportID());
                    pw.printf("\t\t\t<eyeColor value=\"%s\"/>\n", band.getFrontMan().getEyeColor().name());
                    pw.println("\t\t\t<location>");
                    pw.printf("\t\t\t\t<x value=\"%s\"/>\n", band.getFrontMan().getLocation().getX().toString());
                    pw.printf("\t\t\t\t<y value=\"%s\"/>\n", band.getFrontMan().getLocation().getY() + "");
                    pw.printf("\t\t\t\t<z value=\"%s\"/>\n", band.getFrontMan().getLocation().getZ() + "");
                    pw.println("\t\t\t</location>");
                    pw.println("\t\t</frontMan>");
                } else {
                    pw.println("\t\t<frontMan value=\"\"/>");
                }
                pw.println("\t</MusicBand>");
            }
            pw.println("</MusicBands>");
        } catch (IOException e) {
            System.out.println("Ошибка! " + e);
        }

    }
}
