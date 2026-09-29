package com.afsaneh.cimebook;

import com.afsaneh.cimebook.config.ActivityDataInitializer;
import com.afsaneh.cimebook.model.Activity;
import com.afsaneh.cimebook.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:cimebook_api_test;DB_CLOSE_DELAY=-1",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@AutoConfigureMockMvc
class ActivityApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private ActivityDataInitializer initializer;

    @Test
    void listReturnsSeededActivities() throws Exception {
        mockMvc.perform(get("/activities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[*].title", hasItem("Hiking at Semnoz")));
    }

    @Test
    void existingActivityReturnsItsDetails() throws Exception {
        Activity activity = activityRepository.findAll().stream()
                .filter(item -> item.getTitle().equals("Hiking at Semnoz"))
                .findFirst()
                .orElseThrow();

        mockMvc.perform(get("/activities/{id}", activity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(activity.getId()))
                .andExpect(jsonPath("$.title").value("Hiking at Semnoz"))
                .andExpect(jsonPath("$.price").value(45.00));
    }

    @Test
    void missingActivityReturnsNotFound() throws Exception {
        mockMvc.perform(get("/activities/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void runningInitializerAgainDoesNotDuplicateActivities() {
        assertThat(activityRepository.count()).isEqualTo(3);

        initializer.run();

        assertThat(activityRepository.count()).isEqualTo(3);
    }
}
