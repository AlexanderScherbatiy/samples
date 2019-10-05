package calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classic")
public class ClassicCalculatorController {

    @GetMapping
    public String calculate() {
        return "classic";
    }

}
