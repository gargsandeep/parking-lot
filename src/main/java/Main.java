import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Read from Stdin
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        Parking parking = null;
        try {
            if(args.length >0){
                String fileName = args[0];
                //read from file
                br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            }
            while((line = br.readLine()) != null){
                //System.out.println(line);
                parking = handleCommand(line, parking);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Parking handleCommand(String commandWithArgument, Parking parking){
        String args[] = commandWithArgument.trim().split(" ");
        String command;
        try {
            command = args[0];
            if (Command.create_parking_lot.name().equals(command)) {
                parking = ParkingLot.create(Integer.parseInt(args[1]));
            }
            else if(parking != null){
                if(Command.park.name().equals(command))
                    parking.park(args[1], args[2]);
                else if(Command.leave.name().equals(command))
                    parking.leave(Integer.parseInt(args[1]));
                else if(Command.status.name().equals(command))
                    parking.status();
                else if(Command.registration_numbers_for_cars_with_colour.name().equals(command))
                    parking.registrationNoWithColour(args[1]);
                else if(Command.slot_numbers_for_cars_with_colour.name().equals(command))
                    parking.slotNoWithColour(args[1]);
                else if(Command.slot_number_for_registration_number.name().equals(command))
                    parking.slotForRegistrationNo(args[1]);
                else
                    System.out.println(command+" not found");
            }
            else
                System.out.println("No parking exist, first create parking");
        }
        catch (Exception e){
           // System.out.println(e.getMessage()+" "+commandWithArgument);
            e.printStackTrace();
        }
        return  parking;
    }
}
