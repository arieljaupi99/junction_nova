package junction.al.nova_spring.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class EchoController {

    @GetMapping("/echo")
    @ResponseStatus(HttpStatus.OK)
    public String echo() {
        return "echo";
    }
}
