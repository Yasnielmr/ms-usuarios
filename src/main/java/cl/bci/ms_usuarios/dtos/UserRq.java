/*
 * @(#)UserRq.java
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

import cl.bci.ms_usuarios.entities.Phone;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * UserRq.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 10-04-2025
 */
@Getter
@Setter
public class UserRq {

    /** name. */
    private String name;
    /** email. */
    private String email;
    /** password. */
    private String password;
    /** phones. */
    private List<Phone> phones;

}
