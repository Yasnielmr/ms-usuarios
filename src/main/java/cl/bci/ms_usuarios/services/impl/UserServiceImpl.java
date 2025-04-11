/*
 * @(#)UserServiceImpl.java
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
import cl.bci.ms_usuarios.dtos.UserRs;
import cl.bci.ms_usuarios.entities.Phone;
import cl.bci.ms_usuarios.entities.Usuario;
import cl.bci.ms_usuarios.properties.UserProperties;
import cl.bci.ms_usuarios.repository.UserRepository;
import cl.bci.ms_usuarios.services.UserService;
import cl.bci.ms_usuarios.utils.JwtUtil;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 11-04-2025
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /** userRepository. */
    private final UserRepository userRepository;
    /** userProperties. */
    private final UserProperties userProperties;

    // -------------------------------------------------------------------
    // -- Métodos Sobrescritos -------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public UserRs createUser(final UserRq userRq) {
        final var pattern = Pattern.compile(this.userProperties.getEmailRegex());
        final var matcher = pattern.matcher(userRq.getEmail());
        if (!matcher.matches()) {
            throw new RuntimeException("El formato de correo no es valido");
        }
        if (this.userRepository.existsByEmail(userRq.getEmail())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        final var user = this.userRepository.save(this.getUser(userRq));
        return this.getUserRs(user);
    }

    // -------------------------------------------------------------------
    // -- Métodos Privados -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Metodo que registra usuario en la base de datos.
     *
     * @param userRq {@link UserRq}
     * @return {@link Usuario}
     */
    private Usuario getUser(UserRq userRq) {
        final var user = new Usuario();
        user.setName(userRq.getName());
        user.setEmail(userRq.getEmail());
        user.setPassword(userRq.getPassword());
        user.setToken(JwtUtil.generateToken(userRq.getEmail()));
        user.setPhones(userRq.getPhones().stream().map(p -> {
            final var phone = new Phone();
            phone.setNumber(p.getNumber());
            phone.setCitycode(p.getCitycode());
            phone.setCountrycode(p.getCountrycode());
            return phone;
        }).collect(Collectors.toList()));
        return user;
    }

    /**
     * Metodo que convierte una entidad usuario a un usuario response.
     *
     * @param usuario {@link Usuario}
     * @return {@link UserRs}
     */
    private UserRs getUserRs(Usuario usuario) {
        final var userRs = new UserRs();
        userRs.setId(usuario.getId());
        userRs.setToken(usuario.getToken());
        userRs.setCreatedAt(usuario.getCreatedAt());
        userRs.setUpdatedAt(usuario.getUpdatedAt());
        userRs.setLastLogin(usuario.getLastLogin());
        userRs.setToken(usuario.getToken());
        userRs.setIsActive(usuario.getIsActive());
        return userRs;
    }

}
