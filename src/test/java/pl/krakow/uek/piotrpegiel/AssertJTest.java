package pl.krakow.uek.piotrpegiel;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    @Test
    void timeAssert(){
        assertThat(Instant.now())
                .isInThePast();
    }

    @Test
    void listAssertion(){
        assertThat(Collections.emptyList())
                .isEmpty();
    }

    @Test
    void stringAssertion(){
        assertThat("Hello World")
                .contains("Hello")
                .hasSize(11);
    }
}
