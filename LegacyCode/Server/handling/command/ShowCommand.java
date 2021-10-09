package handling.command;

import format.CommandAccomplishment;
import format.MusicBand;
import format.Response;

import java.util.Stack;

public class ShowCommand implements Command {
    private final Stack<MusicBand> mystack;

    public ShowCommand(Stack<MusicBand> mystack) {
        this.mystack = mystack;
    }

    @Override
    public Response execute(String args, MusicBand musicBand) {
        /*
        for (MusicBand band : mystack) {
            String info = "";
            info += band.getId().toString() + " " + band.getName() + "\n";
            info += "\tCoordinates: " + band.getCoordinates().getX() + " " + band.getCoordinates().getY().toString() + "\n";
            info += "\tCreation Date: " + band.getCreationDate().getYear() + " " + band.getCreationDate().getMonth().name() + " " + band.getCreationDate().getDayOfMonth() + "\n";
            info += "\tParticipants: " + band.getNumberOfParticipants() + "\n";
            info += "\tDescription: " + band.getDescription() + "\n";
            if (band.getEstablishmentDate() != null) {
                int year = band.getEstablishmentDate().getYear();
                int month = band.getEstablishmentDate().getMonth();
                if (month != 0) {
                    info += "\tEstablishment Date: " + year + " " + java.time.Month.of(month) + "\n";
                } else {
                    info += "\tEstablishment Date: " + year + " " + java.time.Month.of(12) + "\n";
                }

            }
            info += "\tGenre: " + band.getGenre().name() + "\n";
            if (band.getFrontMan() != null) {
                info += "\tFront man: " + band.getFrontMan().getName() + " " + band.getFrontMan().getPassportID() + "\n";
                info += "\t\tEye Color: " + band.getFrontMan().getEyeColor().name() + "\n";
                info += "\t\tCoordinates: " + band.getFrontMan().getLocation().getX().toString() + " " + band.getFrontMan().getLocation().getY() + " " + band.getFrontMan().getLocation().getZ() + "\n";
            }

            System.out.println(info);

        }
         */
        System.out.println("\t-[show]\t"+CommandAccomplishment.SUCCESSFUL);
        return new Response(CommandAccomplishment.SUCCESSFUL, mystack);
    }
}
