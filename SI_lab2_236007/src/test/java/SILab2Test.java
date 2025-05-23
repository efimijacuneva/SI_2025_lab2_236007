import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class SILab2Test {

    SILab2 siLab2 = new SILab2();

    @Test
    void checkCart() {

        RuntimeException exception;
        exception = assertThrows(RuntimeException.class, () ->  siLab2.checkCart(null, "1234567891234567"));
        assertTrue(exception.getMessage().contains("allItems list can't be null!"));

        List<Item> items1 = List.of(new Item(null, 1 , 100, 0));
        exception = assertThrows(RuntimeException.class, () ->  siLab2.checkCart(items1, ""));
        assertTrue(exception.getMessage().contains("Invalid item!"));


        List<Item> items2 = List.of(new Item("Phone", 1 , 1, 0));
        exception = assertThrows(RuntimeException.class, () ->  siLab2.checkCart(items2, "e123456789876543"));
        assertTrue(exception.getMessage().contains("Invalid character in card number!"));

        exception = assertThrows(RuntimeException.class, () ->  siLab2.checkCart(items2, "123123"));
        assertTrue(exception.getMessage().contains("Invalid card number!"));

        assertEquals(1, siLab2.checkCart(items2, "8827389476352617"));
    }


    @Test
    void checkCart_MultipleCondition() {

        // TXX
        List<Item> all = List.of(new Item("Item1", 1, 400, 0));
        assertEquals(370, siLab2.checkCart(all, "8827389476352617"));

        // FTX
        List<Item> all2 = List.of(new Item("Item2", 0, 100, 0.4));
        assertEquals(-30, siLab2.checkCart(all2, "8827389476352617"));

        // FFF
        List<Item> all3 = List.of(new Item("Item3", 2, 3, 0));
        assertEquals(6, siLab2.checkCart(all3, "8827389476352617"));

        // FFT
        List<Item> all4 = List.of(new Item("Item4", 12, 5, 0.2));
        assertEquals(18, siLab2.checkCart(all4, "8827389476352617"));
    }

}

