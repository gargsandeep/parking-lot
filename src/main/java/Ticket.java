package main.java;

public class Ticket {
    private String  registrationNo;
    private String viechleColor;
    private Slot slot;

    public Ticket(String registrationNo, String viechleColor, Slot slot) {
        this.registrationNo = registrationNo;
        this.viechleColor = viechleColor;
        this.slot = slot;
    }
}
