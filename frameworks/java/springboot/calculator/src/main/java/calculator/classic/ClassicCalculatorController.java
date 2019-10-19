package calculator.classic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    int maxResultsSize = 3;

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

        ClassicCalculatorRecord record = toRecord(input);
        repository.save(record);

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
        model.addAttribute("results", getRecords());
    }

    private List<String> getRecords() {

        Sort sortedByDate = Sort.by("createdAt").descending();
        Pageable paging = PageRequest.of(0, maxResultsSize, sortedByDate);

        Iterable<ClassicCalculatorRecord> records = repository.findAll(paging);

        List<String> results = new ArrayList<>();

        for (ClassicCalculatorRecord record : records) {
            results.add(record.format());
        }

        return results;
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
