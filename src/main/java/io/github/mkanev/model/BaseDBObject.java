package io.github.mkanev.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.github.mkanev.common.LoggedClass;

/**
 * @author Maksim Kanev
 */
@MappedSuperclass
public abstract class BaseDBObject extends BaseObject implements Serializable {

    public static final String FIELD_DELETED = "deleted";
    private static final long serialVersionUID = -7254989743482726486L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean disabled) {
        this.deleted = disabled;
    }

    public boolean isBlank() {
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object from) {

        return from instanceof BaseDBObject
               && id != null
               && id.equals(((BaseDBObject) from).id);
    }

    @Override
    public String toString() {
        return LoggedClass.getStaticInstance()
            .getNewProtocolBuilder()
            .append("id", id)
            .append("class", getClass().getCanonicalName())
            .toString();
    }

    public abstract void deleteFull();

    public abstract void loadFull();
}
