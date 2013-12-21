package io.github.mkanev.model;

import com.google.common.base.Objects;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Maksim Kanev
 */
@MappedSuperclass
@EntityListeners({GenericEntityListener.class})
public abstract class GenericEntity extends BaseDBObject {

    public static final String FIELD_UPDATE_DATE = "updateDate";
    private static final long serialVersionUID = -974110180786730119L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date")
    private Date insertDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "uuid", nullable = false, unique = true, length = 36)
    private String uuid;

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                   .add("insertDate", insertDate)
                   .add("updateDate", updateDate)
                   .add("uuid", uuid)
                   .toString() + super.toString();
    }
}
