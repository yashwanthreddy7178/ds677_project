package org.fourfrika.web.rest;

import org.fourfrika.Application;
import org.fourfrika.domain.Terminal;
import org.fourfrika.repository.TerminalRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the TerminalResource REST controller.
 *
 * @see TerminalResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TerminalResourceIntTest {

    private static final String DEFAULT_NAME = "AA";
    private static final String UPDATED_NAME = "BB";
    private static final String DEFAULT_AREA = "AA";
    private static final String UPDATED_AREA = "BB";
    private static final String DEFAULT_COUNTRY_CODE = "AA";
    private static final String UPDATED_COUNTRY_CODE = "BB";
    private static final String DEFAULT_TIMEZONE = "AAA";
    private static final String UPDATED_TIMEZONE = "BBB";

    @Inject
    private TerminalRepository terminalRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTerminalMockMvc;

    private Terminal terminal;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TerminalResource terminalResource = new TerminalResource();
        ReflectionTestUtils.setField(terminalResource, "terminalRepository", terminalRepository);
        this.restTerminalMockMvc = MockMvcBuilders.standaloneSetup(terminalResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        terminal = new Terminal();
        terminal.setName(DEFAULT_NAME);
        terminal.setArea(DEFAULT_AREA);
        terminal.setCountryCode(DEFAULT_COUNTRY_CODE);
        terminal.setTimezone(DEFAULT_TIMEZONE);
    }

    @Test
    @Transactional
    public void createTerminal() throws Exception {
        int databaseSizeBeforeCreate = terminalRepository.findAll().size();

        // Create the Terminal

        restTerminalMockMvc.perform(post("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isCreated());

        // Validate the Terminal in the database
        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeCreate + 1);
        Terminal testTerminal = terminals.get(terminals.size() - 1);
        assertThat(testTerminal.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTerminal.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testTerminal.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testTerminal.getTimezone()).isEqualTo(DEFAULT_TIMEZONE);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = terminalRepository.findAll().size();
        // set the field null
        terminal.setName(null);

        // Create the Terminal, which fails.

        restTerminalMockMvc.perform(post("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isBadRequest());

        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = terminalRepository.findAll().size();
        // set the field null
        terminal.setArea(null);

        // Create the Terminal, which fails.

        restTerminalMockMvc.perform(post("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isBadRequest());

        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = terminalRepository.findAll().size();
        // set the field null
        terminal.setCountryCode(null);

        // Create the Terminal, which fails.

        restTerminalMockMvc.perform(post("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isBadRequest());

        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTimezoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = terminalRepository.findAll().size();
        // set the field null
        terminal.setTimezone(null);

        // Create the Terminal, which fails.

        restTerminalMockMvc.perform(post("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isBadRequest());

        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTerminals() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        // Get all the terminals
        restTerminalMockMvc.perform(get("/api/terminals?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(terminal.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
                .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE.toString())))
                .andExpect(jsonPath("$.[*].timezone").value(hasItem(DEFAULT_TIMEZONE.toString())));
    }

    @Test
    @Transactional
    public void getTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

        // Get the terminal
        restTerminalMockMvc.perform(get("/api/terminals/{id}", terminal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(terminal.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE.toString()))
            .andExpect(jsonPath("$.timezone").value(DEFAULT_TIMEZONE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTerminal() throws Exception {
        // Get the terminal
        restTerminalMockMvc.perform(get("/api/terminals/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

		int databaseSizeBeforeUpdate = terminalRepository.findAll().size();

        // Update the terminal
        terminal.setName(UPDATED_NAME);
        terminal.setArea(UPDATED_AREA);
        terminal.setCountryCode(UPDATED_COUNTRY_CODE);
        terminal.setTimezone(UPDATED_TIMEZONE);

        restTerminalMockMvc.perform(put("/api/terminals")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(terminal)))
                .andExpect(status().isOk());

        // Validate the Terminal in the database
        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeUpdate);
        Terminal testTerminal = terminals.get(terminals.size() - 1);
        assertThat(testTerminal.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTerminal.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testTerminal.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testTerminal.getTimezone()).isEqualTo(UPDATED_TIMEZONE);
    }

    @Test
    @Transactional
    public void deleteTerminal() throws Exception {
        // Initialize the database
        terminalRepository.saveAndFlush(terminal);

		int databaseSizeBeforeDelete = terminalRepository.findAll().size();

        // Get the terminal
        restTerminalMockMvc.perform(delete("/api/terminals/{id}", terminal.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Terminal> terminals = terminalRepository.findAll();
        assertThat(terminals).hasSize(databaseSizeBeforeDelete - 1);
    }
}
