package com.afsaneh.cimebook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:cimebook_update_test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureMockMvc
@Transactional
class ActivityUpdateIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String ORIGINAL_ACTIVITY = """
            {
              "title": "Sunset Hike",
              "description": "An evening hike near Lake Annecy.",
              "category": "HIKING",
              "difficulty": "BEGINNER",
              "duration": "3h",
              "price": 45.00,
              "image": "images/activities/hiking-semnoz.png"
            }
            """;

    private static final String UPDATED_ACTIVITY = """
            {
              "title": "Sunset Hike - Updated",
              "description": "An updated evening hike near Lake Annecy.",
              "category": "HIKING",
              "difficulty": "INTERMEDIATE",
              "duration": "4h",
              "price": 55.00,
              "image": "images/activities/hiking-semnoz.png"
            }
            """;

    private String createActivity() throws Exception {
        String location = mockMvc.perform(post("/activities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ORIGINAL_ACTIVITY))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");

        if (location == null) {
            throw new AssertionError("POST /activities did not return a Location header");
        }

        return location;
    }

    @Test
    void updateExistingActivityReturnsUpdatedDataAndPersistsIt() throws Exception {
        String activityUrl = createActivity();

        mockMvc.perform(put(activityUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UPDATED_ACTIVITY))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sunset Hike - Updated"))
                .andExpect(jsonPath("$.price").value(55.00));

        mockMvc.perform(get(activityUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sunset Hike - Updated"))
                .andExpect(jsonPath("$.difficulty").value("Intermediate"));
    }

    @Test
    void updateMissingActivityReturnsNotFound() throws Exception {
        mockMvc.perform(put("/activities/{id}", Long.MAX_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UPDATED_ACTIVITY))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateWithInvalidDataReturnsBadRequestAndKeepsOriginalData() throws Exception {
        String activityUrl = createActivity();

        String invalidActivity = UPDATED_ACTIVITY.replace(
                "\"title\": \"Sunset Hike - Updated\"",
                "\"title\": \"\""
        );

        mockMvc.perform(put(activityUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidActivity))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get(activityUrl))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Sunset Hike"));
    }
}