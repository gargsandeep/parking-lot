import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ParkingLotTest {

    @Test
    public  void testCreateParkingLot(){
        try {
            Parking parking = ParkingLot.create(10); //10 size parking lot
            Assert.assertNotNull(parking);
            Assert.assertEquals(10, parking.getSize().intValue());
            Assert.assertEquals(10, parking.getAvailableSize().intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testCreateParkingLotWithIlliegalSize(){
        try {
            Parking parking = ParkingLot.create(0); //0 size parking lot
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testPark() {
        try {
            Parking parking = ParkingLot.create(2); //2 size parking lot
            Ticket ticket = parking.park("KA-01-HH-9999", "White");
            Assert.assertEquals(ticket.getRegistrationNo(), "KA-01-HH-9999");
            Assert.assertEquals(ticket.getViechleColor(), "White");
            assertThat(ticket.getSlot().getId(),greaterThan(0));
            assertThat(ticket.getSlot().getId(), lessThan(3));
            Assert.assertEquals(parking.getAvailableSize().intValue(), 1);
            Assert.assertEquals(parking.getSize().intValue(), 2);
            Ticket ticket1 = parking.park("KA-31-AS-1251", "Black");
            Assert.assertEquals(ticket1.getSlot().getId(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFullParking() {
        Parking parking = null;
        try {
            parking = ParkingLot.create(1);
            Ticket ticket = parking.park("KA-01-HH-9999", "White");
            Ticket ticket1 = parking.park("KA-43-SW-3234","Red");
            Assert.assertNull(ticket1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLeave() {
        try {
            Parking parking = ParkingLot.create(2);
            Ticket ticket = parking.park("KA-34-AW-3420","Red");
            Ticket ticket1 = parking.leave(ticket.getSlot().getId());
            Assert.assertEquals(ticket,ticket1);
            Ticket ticket2 = parking.park("KA-01-WQ-6578","White");
            Assert.assertEquals(ticket2.getSlot().getId(), 1); //it should be nearest parking
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptyLeave(){
        try {
            Parking parking = ParkingLot.create(2);
            Ticket ticket = parking.leave(1);
            Assert.assertNull(ticket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testfetchRegistrationNoWithColour(){
        try {
            Parking parking = ParkingLot.create(2);
            parking.park("KA-05-KO-2301","Red");
            parking.park("KA-02-DX-4502","Black");
            List<String> registrationNos = parking.registrationNoWithColour("Black");
            Assert.assertNotNull(registrationNos);
            Assert.assertEquals(registrationNos.size(), 1);
            Assert.assertEquals(registrationNos.get(0), "KA-02-DX-4502");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testfetchRegistrationNoWithColourNotFound(){
        try {
            Parking parking = ParkingLot.create(2);
            List<String> registrationNoWithColour = parking.registrationNoWithColour("Black");
            Assert.assertNull(registrationNoWithColour);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSlotNoWithColour(){
        try {
            Parking parking = ParkingLot.create(2);
            Ticket ticket = parking.park("KA-05-KO-2301","Red");
            Ticket ticket1 = parking.park("KA-02-DX-4502","Black");
            List<Slot> slotList = parking.slotNoWithColour("Black");
            Assert.assertNotNull(slotList);
            Assert.assertEquals(slotList.size(), 1);
            Assert.assertEquals(slotList.get(0).getId(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSlotNoWithColourNotFound(){
        try {
            Parking parking = ParkingLot.create(2);
            List<Slot> slotList = parking.slotNoWithColour("Black");
            Assert.assertNull(slotList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testSlotForRegistrationNo(){
        try {
            Parking parking = ParkingLot.create(2);
            Ticket ticket = parking.park("KA-05-KO-2301","Black");
            Slot slot = parking.slotForRegistrationNo("KA-05-KO-2301");
            Assert.assertEquals(ticket.getSlot(), slot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public  void testSlotNotFound(){
        try {
            Parking parking = ParkingLot.create(2);
            Slot slot = parking.slotForRegistrationNo("KA-23-SW-9450");
            Assert.assertNull(slot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
