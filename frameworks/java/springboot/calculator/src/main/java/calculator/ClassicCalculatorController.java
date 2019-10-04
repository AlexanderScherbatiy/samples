package calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassicCalculatorController {

    @RequestMapping("/classic")
    public String index() {
        return "Welcome to Classic calculator!";
    }

}
