import java.util.List;

public interface Parking {
    Ticket park(String registrationNo, String colour);
    Ticket leave(int slotId);
    void status();
    List<String> registrationNoWithColour(String colour);
    List<Slot> slotNoWithColour(String colour);
    Slot slotForRegistrationNo(String registrationNo);
    Boolean isAvailable();
    Integer getSize();
    Integer getAvailableSize();
}
