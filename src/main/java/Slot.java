package main.java;

public class Slot {
    private  int id;

    public Slot(int slotId) {
        this.id = slotId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Slot slot = (Slot) o;
        return id == slot.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
