package com.nnk.springboot;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;

@WebMvcTest(BidListController.class)
@WithMockUser(username = "testuser", password = "testpassword", roles = "USER")
public class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListRepository bidListRepository;

    @Test
    public void testBidList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/list"));
    }

    @Test
    public void testAddBidForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/add"));
    }

    @Test
    public void testShowUpdateForm() throws Exception {
        BidList bidList = new BidList();
        when(bidListRepository.findById(1)).thenReturn(java.util.Optional.of(bidList));

        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/update/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("bidList/update"));
    }

}
