package calculator;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/classic")
public class ClassicCalculatorController {

    List<String> results = new ArrayList<>();

    @GetMapping
    public String getForm(Model model) {
        //System.out.printf("GET%n");
        ClassicCalculatorInput input = new ClassicCalculatorInput();
        initModel(input, model);
        return "classic";
    }

    @PostMapping
    public String postForm(@ModelAttribute ClassicCalculatorInput input,
                           Model model) {
        //System.out.printf("POST%n");
        //System.out.printf("input: %s%n", input);
        Operation op = Operation.valueOf(input.selectedOperation);
        double value = op.calculate(input.value1, input.value2);
        String result = String.format("%f %s %f = %f", input.value1, op, input.value2, value);
        results.add(result);
        initModel(input, model);
        return "classic";
    }

    private void initModel(ClassicCalculatorInput input, Model model) {

        Operation[] values = Operation.values();
        List<String> operations = new ArrayList<>(values.length);
        for (Operation operation : values) {
            operations.add(operation.toString());
        }

        model.addAttribute("operations", operations);
        model.addAttribute("input", input);
        model.addAttribute("results", results);
    }

    @Data
    public static class ClassicCalculatorInput {
        private double value1;
        private double value2;
        private String selectedOperation = Operation.PLUS.toString();
    }
}
