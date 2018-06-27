

public class Slot implements  Comparable{
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

    public int getId() {
        return id;
    }


    @Override
    public int compareTo(Object obj) {
        Slot slot = (Slot)obj;
        if(this.id>slot.id)
            return 1;
        else if(this.id<slot.id)
            return -1;
        return 0;
    }
}
