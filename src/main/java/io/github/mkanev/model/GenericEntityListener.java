package io.github.mkanev.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import io.github.mkanev.common.LoggedClass;

/**
 * @author Maksim Kanev
 */
public class GenericEntityListener extends LoggedClass {

    @PreUpdate
    public void preUpdate(GenericEntity s) {
        s.setUpdateDate(Calendar.getInstance().getTime());
    }

    @PrePersist
    public void prePersist(GenericEntity s) {
        Date now = Calendar.getInstance().getTime();
        s.setInsertDate(now);
        s.setUpdateDate(now);
        s.setUuid(UUID.randomUUID().toString());
    }

}
