package com.nnk.springboot;

import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@WebMvcTest(RuleNameController.class)
@WithMockUser(username = "testuser", password = "Testpassword123@", roles = "USER")
public class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameRepository ruleNameRepository;

    @Test
    public void testRuleNameList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/list"));
    }

    @Test
    public void testAddRuleNameForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/add"));
    }

    @Test
    public void testShowUpdateRuleNameForm() throws Exception {
        RuleName ruleName = new RuleName();
        when(ruleNameRepository.findById(1)).thenReturn(java.util.Optional.of(ruleName));

        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/update/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ruleName/update"));
    }
}
