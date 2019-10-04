package calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculatorMainPageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
