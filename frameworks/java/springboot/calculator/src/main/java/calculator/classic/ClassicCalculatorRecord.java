package calculator.classic;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "classic_calculator_records")
public class ClassicCalculatorRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double value1;
    private String operation;
    private double value2;
    private double result;
}
