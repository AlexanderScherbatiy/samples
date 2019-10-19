package calculator.classic;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "classic_calculator_records")
public class ClassicCalculatorRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date createdAt;

    private double value1;

    private String operation;

    private double value2;

    private double result;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public String format() {
        return String.format("%.2f %s %.2f = %.2f", value1, operation, value2, result);
    }
}
