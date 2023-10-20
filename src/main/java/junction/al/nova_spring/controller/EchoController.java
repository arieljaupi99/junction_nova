package junction.al.nova_spring.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("echo")
@Tag(name = "echo")
public class EchoController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String echo() {
        return "echo";
    }
}
