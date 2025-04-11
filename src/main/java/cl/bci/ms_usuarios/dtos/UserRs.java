/*
 * @(#)UserRs.java
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
package cl.bci.ms_usuarios.dtos;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * UserRs.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 10-04-2025
 */
@Getter
@Setter
public class UserRs {

    /** id. */
    private String id;
    /** createdAt. */
    private LocalDateTime createdAt;
    /** updatedAt. */
    private LocalDateTime updatedAt;
    /** lastLogin. */
    private LocalDateTime lastLogin;
    /** token. */
    private String token;
    /** isActive. */
    private Boolean isActive;

}
