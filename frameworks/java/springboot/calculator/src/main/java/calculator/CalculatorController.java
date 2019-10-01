package calculator;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class CalculatorController {

    @RequestMapping("/")
    public String index() {
        return "Welcome to Calculator App!";
    }

}
