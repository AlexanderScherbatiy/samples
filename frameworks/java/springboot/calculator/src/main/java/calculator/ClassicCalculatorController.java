package calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/classic")
public class ClassicCalculatorController {

    List<String> results = new ArrayList<>();

    @GetMapping
    public String calculateForm(Model model) {
        model.addAttribute("expression", new Expression());
        model.addAttribute("results", results);
        return "classic";
    }

    @PostMapping
    public String calculateSubmit(@ModelAttribute Expression expression, Model model) {
        results.add(Double.toString(expression.calculate()));
        model.addAttribute("expression", new Expression());
        model.addAttribute("results", results);
        return "classic";
    }
}
