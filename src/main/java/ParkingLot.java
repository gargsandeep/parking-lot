
import java.util.*;

public class ParkingLot implements  Parking{
    private int size;
    private Queue<Integer> avaliableSpaces;
    private Map<Slot, Ticket> slotTicketMap;
    private Map<String, List<Ticket>> colourTicketsMap;
    private Map<String, Ticket> registrationNoTicketMap;

    ParkingLot(int size){
        this.size=size;
        this.avaliableSpaces = new PriorityQueue<Integer>(size);
        initailizeAvaliableSpaces(this.avaliableSpaces, size);
        this.slotTicketMap = new TreeMap<Slot, Ticket>();
        this.colourTicketsMap = new HashMap<String, List<Ticket>>();
        this.registrationNoTicketMap = new HashMap<String, Ticket>();
    }

    private void initailizeAvaliableSpaces(Queue<Integer> avaliableSpaces, int size) {
        for(int i =1 ; i<=size; i++)
            avaliableSpaces.add(i);
    }

    public static ParkingLot create(int size) throws Exception {
        if(size <=0 )
            throw new Exception("Iillegal size "+size);
        ParkingLot parkingLot = new ParkingLot(size);
        System.out.println("Created a parking lot with "+size+" slots");
        return parkingLot;
    }

    @Override
    public Ticket park(String registrationNo, String colour) {
        if(!this.isAvailable()) {
            System.out.println("Sorry, parking lot is full");
            return null;
        }
        int slotId = this.avaliableSpaces.poll();
        Slot slot = new Slot(slotId);
        Ticket ticket = new Ticket(registrationNo, colour, slot);
        this.slotTicketMap.put(slot, ticket);
        this.registrationNoTicketMap.put(registrationNo, ticket);
        if(!this.colourTicketsMap.containsKey(colour))
            this.colourTicketsMap.put(colour, new ArrayList<Ticket>());
        this.colourTicketsMap.get(colour).add(ticket);

        System.out.println("Allocated slot number: "+slotId);
        return ticket;
    }

    @Override
    public Ticket leave(int slotId)  {
        Slot slot = new Slot(slotId);
        if(!this.slotTicketMap.containsKey(slot)) {
            System.out.println("Nothing to leave at slot " + slotId);
            return  null;
        }

        Ticket ticket = this.slotTicketMap.remove(slot);
        this.registrationNoTicketMap.remove(ticket.getRegistrationNo());
        this.colourTicketsMap.get(ticket.getViechleColor()).remove(ticket);
        this.avaliableSpaces.add(slotId);
        System.out.println("Slot number "+ slotId +" is free");
        return ticket;
    }

    @Override
    public void status() {
        System.out.println("Slot No. Registration No Colour");
        for(Map.Entry<Slot, Ticket> entry : this.slotTicketMap.entrySet()){
            Slot slot = entry.getKey();
            Ticket ticket = entry.getValue();
            System.out.println(slot.getId()+" "+ticket.getRegistrationNo()+" "+ticket.getViechleColor());
        }
    }

    @Override
    public List<String> registrationNoWithColour(String colour) {
        List<Ticket> tickets = this.colourTicketsMap.get(colour);
        List<String> registrationNoList = new ArrayList<String>();
        if(tickets == null) {
            System.out.println("No car is available for colour " + colour);
            return null;
        }
        for(Ticket ticket : tickets) {
            System.out.print(ticket.getRegistrationNo() + ", ");
            registrationNoList.add(ticket.getRegistrationNo());
        }
        System.out.println();
        return registrationNoList;
    }

    @Override
    public List<Slot> slotNoWithColour(String colour) {
        List<Ticket> tickets = this.colourTicketsMap.get(colour);
        List<Slot> slots  = new ArrayList<Slot>();
        if(tickets == null) {
            System.out.println("No car is available for colour " + colour);
            return  null;
        }
        for(Ticket ticket : tickets) {
            System.out.print(ticket.getSlot().getId() + ", ");
            slots.add(ticket.getSlot());
        }
        System.out.println();
        return slots;
    }

    @Override
    public Slot slotForRegistrationNo(String registrationNo) {
        Ticket ticket = this.registrationNoTicketMap.get(registrationNo);
        if(ticket == null) {
            System.out.println("Not found");
            return null;
        }
        System.out.println(ticket.getSlot().getId());
        return  ticket.getSlot();
    }

    @Override
    public Boolean isAvailable() {
        return !this.avaliableSpaces.isEmpty();
    }
}
