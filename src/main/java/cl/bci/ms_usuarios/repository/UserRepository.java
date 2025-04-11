/*
 * @(#)UserRepository.java
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
package cl.bci.ms_usuarios.repository;

import cl.bci.ms_usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 10-04-2025
 */
public interface UserRepository extends JpaRepository<Usuario, String> {

    /**
     * existsByEmail.
     *
     * @param email {@link String}
     * @return {@link Boolean}
     */
    Boolean existsByEmail(String email);

}
