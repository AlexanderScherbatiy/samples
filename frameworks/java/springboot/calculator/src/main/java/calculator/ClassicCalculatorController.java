package calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/classic")
public class ClassicCalculatorController {

    @GetMapping
    public String calculateForm(Model model) {

        model.addAttribute("expression", new Expression());
        return "classic";
    }

    @PostMapping
    public String calculateSubmit(@ModelAttribute Expression expression) {
        System.out.printf("expression: %s%n", expression);
        return "classic";
    }

}
