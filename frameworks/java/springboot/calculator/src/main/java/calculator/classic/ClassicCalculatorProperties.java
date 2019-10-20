package calculator.classic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "calculator.classic")
@Data
public class ClassicCalculatorProperties {
    private int recordsSize;
}
