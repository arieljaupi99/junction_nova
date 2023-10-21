package junction.al.nova_spring.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("echo")
@Tag(name = "echo")
public class EchoController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String echo() {
        return "echo";
    }
}
