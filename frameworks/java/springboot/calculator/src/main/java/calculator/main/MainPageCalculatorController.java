package calculator.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageCalculatorController {

    @GetMapping
    public String index(Model model) {

        List<String> refs = Arrays.asList("classic", "scientific");
        model.addAttribute("refs", refs);
        return "index";
    }
}
