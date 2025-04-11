/*
 * @(#)UserService.java
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
package cl.bci.ms_usuarios.services;

import cl.bci.ms_usuarios.dtos.UserRq;
import cl.bci.ms_usuarios.dtos.UserRs;

/**
 * UserService.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 11-04-2025
 */
public interface UserService {

    /**
     * Metodo que crea un nuevo usuario.
     *
     * @param userRq {@link UserRq}
     * @return {@link UserRs}
     */
    UserRs createUser(UserRq userRq);

}
