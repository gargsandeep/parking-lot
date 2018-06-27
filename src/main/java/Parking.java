package main.java;

import java.util.List;

public interface Parking {
    Ticket park(String registrationNo, String colour) throws Exception;
    Ticket leave(int slotId) throws Exception;
    void status();
    List<String> registrationNoWithColour(String colour) throws Exception;
    List<Slot> slotNoWithColour(String colour) throws Exception;
    Slot slotForRegistrationNo(String registrationNo) throws Exception;
    Boolean isAvailable();
}
