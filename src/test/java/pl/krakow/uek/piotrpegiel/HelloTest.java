package pl.krakow.uek.piotrpegiel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloTest {
    @Test
    void firstTest() {
        // Arrange / Act / Assert
        // Given / When / Expected

        //Arrange
        int a = 3;
        int b = 4;

        //Act
        int result = a + b;

        //Assert
        assert result == 7;
    }

    @Test
    void testGreeting() {
        // Arrange / Act / Assert
        // Given / When / Expected

        //Arrange
        String name = "Piotr";

        //Act
        String message = String.format("Hello %s", name);

        //Assert
        assertEquals("Hello Piotr", message);
    }
}
