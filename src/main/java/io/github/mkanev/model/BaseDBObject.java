package io.github.mkanev.model;

import com.google.common.base.Objects;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.TableGenerator;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@MappedSuperclass
@TableGenerator(name = "entity_id_generator", table = "hel_sequence", initialValue = 0, allocationSize = 1,
                pkColumnName = "seq_name", pkColumnValue = "system_seq", valueColumnName = "seq_count")
public abstract class BaseDBObject extends BaseObject implements Serializable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DELETED = "deleted";
    private static final long serialVersionUID = -7254989743482726486L;
    @Id
    @GeneratedValue(generator = "entity_id_generator", strategy = GenerationType.TABLE)
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseDBObject that = (BaseDBObject) o;
        return Objects.equal(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("id", id)
            .add("class", getClass().getCanonicalName())
            .toString();
    }

    public abstract void deleteFull();

    public abstract void loadFull();
}
