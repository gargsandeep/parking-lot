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
    public Ticket park(String registrationNo, String colour) {
        return null;
    }

    @Override
    public Slot leave(Ticket ticket) {
        return null;
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
