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
public class StructListener extends LoggedClass {

    /**
     * Хук для обновления даты последней модификации
     */
    @PreUpdate
    public void preUpdate(Struct s) {
        s.setUpdateDate(new Date());
    }

    @PrePersist
    public void prePersist(Struct s) {
        /**
         * new Date() не учитывает особенности регионального календаря (високосный год и тд) и легко можно
         * нарваться на трудноотлавливаемые ошибки
         */

        Date now = Calendar.getInstance().getTime();
        s.setInsertDate(now);
        s.setUpdateDate(now);
        s.setUuid(UUID.randomUUID().toString());
    }

}
