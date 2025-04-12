/*
 * @(#)TestUserController.java
 *
 * Copyright (c) BANCO DE CHILE (Chile). All rights reserved.
 *
 * All rights to this product are owned by BANCO DE CHILE and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by BANCO DE CHILE.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package cl.bci.ms_usuarios.controllers;

import cl.bci.ms_usuarios.dtos.UserRq;
import cl.bci.ms_usuarios.dtos.UserRs;
import cl.bci.ms_usuarios.entities.Phone;
import cl.bci.ms_usuarios.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * TestUserController.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 12-04-2025
 */
@WebMvcTest
class TestUserController {

    /** userService. */
    @MockBean
    private UserService userService;
    /** mockMvc. */
    @Autowired
    private MockMvc mockMvc;
    /** objectMapper. */
    @Autowired
    private ObjectMapper objectMapper;

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test.
     *
     * @throws Exception exception
     */
    @Test
    void testCreateUserReturnStatus201() throws Exception {
        final var request = new UserRq();
        request.setName("Juan");
        request.setEmail("juan@test.com");
        request.setPassword("hunter2");
        final var phone = new Phone();
        phone.setNumber("123456789");
        phone.setCitycode("1");
        phone.setCountrycode("56");
        request.setPhones(Collections.singletonList(phone));
        final var response = new UserRs();
        response.setId(UUID.randomUUID().toString());
        response.setToken("jwt-token-fake");
        Mockito.when(this.userService.createUser(Mockito.any())).thenReturn(response);
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.token", not(emptyString())))
            .andExpect(jsonPath("$.id", not(emptyString())));
    }

    /**
     * Test.
     *
     * @throws Exception exception
     */
    @Test
    void testCreateUserExistEmail() throws Exception {
        final var request = new UserRq();
        request.setName("Juan");
        request.setEmail("juan@test.com");
        request.setPassword("hunter2");
        final var phone = new Phone();
        phone.setNumber("123456789");
        phone.setCitycode("1");
        phone.setCountrycode("56");
        request.setPhones(Collections.singletonList(phone));
        Mockito.when(this.userService.createUser(Mockito.any()))
            .thenThrow(new RuntimeException("El correo ya se encuentra registrado"));
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.mensaje", is("El correo ya se encuentra registrado")));
    }

}