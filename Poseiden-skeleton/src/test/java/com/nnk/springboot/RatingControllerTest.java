package com.nnk.springboot;

import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(RatingController.class)
@WithMockUser(username = "testuser", password = "testpassword", roles = "USER")
public class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingRepository ratingRepository;

    @Test
    public void testRatingList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/list"));
    }

    @Test
    public void testAddRatingForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/rating/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/add"));
    }

    @Test
    public void testShowUpdateRatingForm() throws Exception {
        Rating rating = new Rating();
        when(ratingRepository.findById(1)).thenReturn(java.util.Optional.of(rating));

        mockMvc.perform(MockMvcRequestBuilders.get("/rating/update/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("rating/update"));
    }

}
