package calculator.classic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/classic")
public class ClassicCalculatorController {

    int maxResultsSize = 3;
    List<String> results = new LinkedList<>(); // insert element into head

    @Autowired
    ClassicCalculatorRepository repository;

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
        Operation op = Operation.fromName(input.selectedOperation);
        double value = op.calculate(input.value1, input.value2);

        ClassicCalculatorRecord record = toRecord(input);
        repository.save(record);

        String result = String.format("%.2f %s %.2f = %.2f", input.value1, op, input.value2, value);
        results.add(0, result);
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

        Iterable<ClassicCalculatorRecord> records = repository.findAll();
        for (ClassicCalculatorRecord record : records) {
            System.out.printf("record: %s%n", record);
        }

        int size = Math.min(maxResultsSize, results.size());
        model.addAttribute("results", results.subList(0, size));
    }

    private static ClassicCalculatorRecord toRecord(ClassicCalculatorInput input) {
        ClassicCalculatorRecord record = new ClassicCalculatorRecord();

        record.setValue1(input.value1);
        record.setValue2(input.value2);
        record.setOperation(input.selectedOperation);

        Operation op = Operation.fromName(input.selectedOperation);
        double value = op.calculate(input.value1, input.value2);
        record.setResult(value);

        return record;
    }

    @Data
    public static class ClassicCalculatorInput {
        private double value1;
        private double value2;
        private String selectedOperation = Operation.PLUS.toString();
    }
}
