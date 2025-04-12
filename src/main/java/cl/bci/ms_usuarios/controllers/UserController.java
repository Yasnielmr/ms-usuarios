/*
 * @(#)UserController.java
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
import cl.bci.ms_usuarios.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 11-04-2025
 */
@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    /** userService. */
    private final UserService userService;

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Metodo que registra usuario en la base de datos.
     *
     * @param userRq {@link UserRq}
     * @return {@link ResponseEntity}
     */
    @Operation(summary = "Crear usuario", description = "Enpoint que permite el registro de un nuevo usuario")
    @ApiResponse(responseCode = "201", description = "Usuario creado con exito")
    @ApiResponse(responseCode = "400", description = "Error al intentar crear el usuario")
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody final UserRq userRq) {
        final var user = this.userService.createUser(userRq);
        return ResponseEntity.status(201).body(user);
    }

}
