package firstdemoapp.firstapp.Names;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NamesController.class)
class NamesControllerTest {
@MockBean
NamesService namesService;

@Autowired
private NamesController controller;

ObjectMapper mapper = new ObjectMapper();

@Autowired
private MockMvc mockMvc;
@Test
void TestWelcome()
{
    assertThat(controller).isNotNull();
}
    @Test
    void main() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print())
                .andExpect(view().name("welcome"));
    }

    @Test
    void TestPostHal() throws Exception {
        this.mockMvc.perform(post("/postaction").param("fname", "Hal")).andExpect(view().name("halView"))
                .andExpect(model().attribute("message", "My mind is going. I can feel it"));
    }
    @Test
    void TestPostDavid() throws Exception {
        this.mockMvc.perform(post("/postaction").param("fname", "David")).andExpect(view().name("davidView"))
                .andExpect(model().attribute("message", "David here"));
    }
    @Test
    void TestPostJohny() throws Exception {
        this.mockMvc.perform(post("/postaction").param("fname", "Johny")).andExpect(status().is(418));
    }
    @Test
    void TestDefault() throws Exception {
    this.mockMvc.perform(post("/postaction").param("fname", "Jakub")).andExpect(view().name("Default"))
            .andExpect(model().attribute("message", "Jakub"));
    }
}