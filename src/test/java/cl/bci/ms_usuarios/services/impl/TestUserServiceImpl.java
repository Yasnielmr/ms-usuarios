/*
 * @(#)TestUserServiceImpl.java
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
package cl.bci.ms_usuarios.services.impl;

import cl.bci.ms_usuarios.dtos.UserRq;
import cl.bci.ms_usuarios.entities.Phone;
import cl.bci.ms_usuarios.entities.Usuario;
import cl.bci.ms_usuarios.properties.UserProperties;
import cl.bci.ms_usuarios.repository.UserRepository;
import cl.bci.ms_usuarios.utils.JwtUtil;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * TestUserServiceImpl.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 12-04-2025
 */
class TestUserServiceImpl {

    /** userService. */
    @InjectMocks
    private UserServiceImpl userService;
    /** userRepository. */
    @Mock
    private UserRepository userRepository;
    /** jwtUtil. */
    @Mock
    private JwtUtil jwtUtil;
    /** userProperties. */
    @Mock
    private UserProperties userProperties;
    /** pattern. */
    @Mock
    private Pattern pattern;

    // -------------------------------------------------------------------
    // -- Setup ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Setup.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // -------------------------------------------------------------------
    // -- Test -----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test
     */
    @Test
    void testCreateUserSuccess() {
        final var request = new UserRq();
        request.setName("Juan");
        request.setEmail("juan@test.com");
        request.setPassword("1234");
        final var phone = new Phone();
        phone.setNumber("123456789");
        phone.setCitycode("1");
        phone.setCountrycode("56");
        request.setPhones(Collections.singletonList(phone));
        Mockito.when(this.userProperties.getEmailRegex()).thenReturn("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Mockito.when(this.pattern.matcher(ArgumentMatchers.anyString())).thenReturn(Mockito.mock(Matcher.class));
        Mockito.when(this.pattern.matcher(ArgumentMatchers.anyString()).matches()).thenReturn(Boolean.TRUE);
        Mockito.when(this.userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(Boolean.FALSE);
        Mockito.when(this.userRepository.save(ArgumentMatchers.any())).thenAnswer(
            invocation -> invocation.getArgument(0));
        Assertions.assertNotNull(this.userService.createUser(request));
    }

    /**
     * Test
     */
    @Test
    void testCreateUserEmailDuplicated() {
        final var request = new UserRq();
        request.setName("Juan");
        request.setEmail("juan@test.com");
        request.setPassword("1234");
        final var usuarioExistente = new Usuario();
        usuarioExistente.setEmail("repetido@test.com");
        Mockito.when(this.userProperties.getEmailRegex()).thenReturn("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Mockito.when(this.pattern.matcher(ArgumentMatchers.anyString())).thenReturn(Mockito.mock(Matcher.class));
        Mockito.when(this.pattern.matcher(ArgumentMatchers.anyString()).matches()).thenReturn(Boolean.TRUE);
        Mockito.when(this.userRepository.existsByEmail(ArgumentMatchers.anyString())).thenReturn(Boolean.TRUE);
        Assertions.assertThrows(RuntimeException.class, () -> {
            this.userService.createUser(request);
        });
    }

    /**
     * Test
     */
    @Test
    void testCreateUserEmailNoValid() {
        final var request = new UserRq();
        request.setName("Juan");
        request.setEmail("juan@");
        request.setPassword("1234");
        Mockito.when(this.userProperties.getEmailRegex()).thenReturn("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Mockito.when(this.pattern.matcher(ArgumentMatchers.anyString())).thenReturn(Mockito.mock(Matcher.class));
        Mockito.when(this.pattern.matcher(ArgumentMatchers.anyString()).matches()).thenReturn(Boolean.FALSE);
        Assertions.assertThrows(RuntimeException.class, () -> {
            this.userService.createUser(request);
        });
    }

}