package pl.krakow.uek.piotrpegiel.ecommerce.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
    @GetMapping("/hello-world")
    String helloWorld() {
        var name = "Piotr";
        return String.format("Hello %s", name);
    }
}
