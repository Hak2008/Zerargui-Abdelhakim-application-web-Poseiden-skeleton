package com.nnk.springboot;

import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@WebMvcTest(CurveController.class)
@WithMockUser(username = "testuser", password = "testpassword", roles = "USER")
public class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointRepository curvePointRepository;

    @Test
    public void testCurvePointList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/list"));
    }

    @Test
    public void testAddCurveForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/add"));
    }

    @Test
    public void testShowUpdateCurveForm() throws Exception {
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointRepository.findById(1)).thenReturn(java.util.Optional.of(curvePoint));

        mockMvc.perform(MockMvcRequestBuilders.get("/curvePoint/update/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("curvePoint/update"));
    }

}

