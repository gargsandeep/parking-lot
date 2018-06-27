package main.java;

import java.util.List;

public class ParkingLot implements  Parking{
    private int size;

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
}
