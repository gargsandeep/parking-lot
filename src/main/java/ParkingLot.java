package main.java;

import java.util.*;

public class ParkingLot implements  Parking{
    private int size;
    private Queue<Integer> avaliableSpaces;
    private Map<Slot, Ticket> slotTicketMap;
    private Map<String, List<Ticket>> colourTicketsMap;
    private Map<String, Ticket> registrationNoTicketMap;

    ParkingLot(int size){
        this.size=size;
        this.avaliableSpaces = new PriorityQueue<>(size);
        initailizeAvaliableSpaces(this.avaliableSpaces, size);
        this.slotTicketMap = new TreeMap<>();
        this.colourTicketsMap = new HashMap<>();
        this.registrationNoTicketMap = new HashMap<>();
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
    public Ticket park(String registrationNo, String colour) throws Exception {
        if(!this.isAvailable())
            throw new Exception("Sorry, parking lot is full");

        int slotId = this.avaliableSpaces.poll();
        Slot slot = new Slot(slotId);
        Ticket ticket = new Ticket(registrationNo, colour, slot);
        this.slotTicketMap.put(slot, ticket);
        this.registrationNoTicketMap.put(registrationNo, ticket);
        if(!this.colourTicketsMap.containsKey(colour))
            this.colourTicketsMap.put(colour, new ArrayList<>());
        this.colourTicketsMap.get(colour).add(ticket);

        System.out.println("Allocated slot number: "+slotId);
        return ticket;
    }

    @Override
    public Ticket leave(int slotId) throws Exception {
        Slot slot = new Slot(slotId);
        if(!this.slotTicketMap.containsKey(slot))
            throw new Exception("Nothing to leave at slot "+slotId);

        Ticket ticket = this.slotTicketMap.remove(slot);
        this.registrationNoTicketMap.remove(ticket.getRegistrationNo());
        this.colourTicketsMap.get(ticket.getViechleColor()).remove(ticket);

        System.out.println("Slot number "+ slotId +" is free");
        return ticket;
    }

    @Override
    public void status() {

    }

    @Override
    public List<String> registrationNoWithColour(String colour) {
        return null;
    }

    @Override
    public List<Slot> slotNoWithColour(String colour) {
        return null;
    }

    @Override
    public Slot slotForRegistrationNo(String registrationNo) {
        return null;
    }

    @Override
    public Boolean isAvailable() {
        return !this.avaliableSpaces.isEmpty();
    }
}
