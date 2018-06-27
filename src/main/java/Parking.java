package main.java;

import java.util.List;

public interface Parking {
    Ticket park(String registrationNo, String colour);
    Slot leave(Ticket ticket);
    void status();
    List<String> registrationNoWithColour(String colour);
    List<Slot> slotNoWithColour(String colour);
    Slot slotForRegistrationNo(String registrationNo);
}
