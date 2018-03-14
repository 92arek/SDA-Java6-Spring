package com.kchmielewski.sda.java6.spring09java.player.web;

import com.kchmielewski.sda.java6.spring09java.player.model.Player;
import com.kchmielewski.sda.java6.spring09java.player.service.PlayerService;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PlayerControllerTest {
    private final PlayerService service = mock(PlayerService.class);
    private final PlayerController controller = new PlayerController(service);
    private final MockMvc mvc = standaloneSetup(controller).build();

    @Test
    public void display() throws Exception {
        MvcResult result = mvc.perform(get("/players/form")).andExpect(status().isOk()).andReturn();

        assertReturnValueAndModelKey(result, "players", "players");
        verify(service).all();
    }

    @Test
    public void addRedirectsForValidPlayer() throws Exception {
        MvcResult result = mvc.perform(post("/players/form").param("name", "Name").param("surname", "Surname"))
                .andExpect(status().is3xxRedirection()).andReturn();

        assertReturnValueAndModelKey(result, "redirect:/players/form", "players");
        verify(service).all();
        verify(service).add(new Player("Name", "Surname"));
    }

    @Test
    public void remove() throws Exception {
        MvcResult result = mvc.perform(post("/players/remove").param("name", "Name").param("surname", "Surname"))
                .andExpect(status().isOk()).andReturn();

        assertReturnValueAndModelKey(result, "players", "players");
        verify(service).all();
        verify(service).remove(new Player("Name", "Surname"));
    }

    private void assertReturnValueAndModelKey(MvcResult result, String viewName, String modelKey) {
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getModelAndView().getViewName()).isEqualTo(viewName);
        softly.assertThat(result.getModelAndView().getModel()).containsKey(modelKey);
        softly.assertAll();
    }
}