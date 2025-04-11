/*
 * @(#)Usuario.java
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
package cl.bci.ms_usuarios.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Usuario.
 *
 * @author Yasniel Montano.
 * @version 1.0.0, 10-04-2025
 */
@Entity
@Getter
@Setter
public class Usuario {

    /** id. */
    @Id
    private String id;
    /** name. */
    private String name;
    /** email. */
    private String email;
    /** password. */
    private String password;
    /** phones. */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;
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

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.lastLogin = this.createdAt;
        this.isActive = true;
    }

    /**
     * Pre update.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
