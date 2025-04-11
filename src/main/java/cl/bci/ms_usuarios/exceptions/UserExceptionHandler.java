/*
 * @(#)UserExceptionHandler.java
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
package cl.bci.ms_usuarios.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * UserExceptionHandler.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 11-04-2025
 */
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(final Exception exception) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("mensaje", exception.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

}
