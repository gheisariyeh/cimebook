package com.afsaneh.cimebook;

import com.afsaneh.cimebook.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:cimebook_api_test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureMockMvc
@Transactional
class ActivityCreationIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @Test
    void validRequestCreatesActivity() throws Exception {
        long countBefore = activityRepository.count();

        String requestBody = """
                {
                  "title": "Sunset Hike",
                  "description": "A guided evening hike near Lake Annecy.",
                  "category": "HIKING",
                  "difficulty": "BEGINNER",
                  "duration": "3h",
                  "price": 40.00,
                  "image": "images/activities/hiking-semnoz.png"
                }
                """;

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.title").value("Sunset Hike"))
                .andExpect(jsonPath("$.price").value(40.00));

        assertThat(activityRepository.count()).isEqualTo(countBefore + 1);
    }

    @Test
    void negativePriceIsRejectedWithoutSaving() throws Exception {
        long countBefore = activityRepository.count();

        String requestBody = """
                {
                  "title": "Invalid Hike",
                  "description": "An activity with an invalid price.",
                  "category": "HIKING",
                  "difficulty": "BEGINNER",
                  "duration": "3h",
                  "price": -5.00,
                  "image": "images/activities/hiking-semnoz.png"
                }
                """;

        mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());

        assertThat(activityRepository.count()).isEqualTo(countBefore);
    }
}