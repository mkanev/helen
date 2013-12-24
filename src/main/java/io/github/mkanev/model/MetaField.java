package io.github.mkanev.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@Entity
@Table(name = "hel_meta_field")
public class MetaField extends MetaModelEntity {

    public static final String FIELD_COLUMN_NAME = "columnName";
    public static final String FIELD_DATA_TYPE = "dataType";
    public static final String FIELD_OWNER_CLASS = "ownerClass";
    //Наименование колонке в таблице - получается на основе наименования поля fieldName
    @Column(name = "column_name")
    private String columnName;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "data_type")
    private MetaClass dataType;
    @ManyToOne(cascade = CascadeType.REFRESH, optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_class_id")
    private MetaClass ownerClass;

    @Override
    public void deleteFull() {
    }

    @Override
    public void loadFull() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public MetaClass getDataType() {
        return dataType;
    }

    public void setDataType(MetaClass dataType) {
        this.dataType = dataType;
    }

    public MetaClass getOwnerClass() {
        return ownerClass;
    }

    public void setOwnerClass(MetaClass ownerClass) {
        this.ownerClass = ownerClass;
    }
}
