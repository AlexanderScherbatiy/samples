package calculator.classic;

import org.hamcrest.core.StringContains;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ClassicCalculatorProperties.class, ClassicCalculatorRepository.class})
@WebMvcTest(ClassicCalculatorController.class)
public class ClassicCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Ignore
    @Test
    public void testClassicCalculatorPage() throws Exception {
        mockMvc.perform(get("/classic"))
                .andExpect(status().isOk())
                .andExpect(view().name("classic"))
                .andExpect(content().string(
                        StringContains.containsString("Classic calculator")));
    }
}
