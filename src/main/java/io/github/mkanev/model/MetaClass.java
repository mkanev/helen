package io.github.mkanev.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@Entity
@Table(name = "hel_meta_class")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "class_category", discriminatorType = DiscriminatorType.STRING, length = 64)
@DiscriminatorValue("abstract")
public class MetaClass extends MetaModelEntity {

    public static final String FIELD_JAVA_TYPE = "javaType";
    public static final String FIELD_READONLY = "readonly";
    // Признак: является ли метакласс системным
    private boolean readonly;
    // Динамический класс
    @Column(name = "java_type")
    private String javaType;
    // Класс, полученный на основе поля javaType
    @Transient
    private Class javaTypeClass;
    // Поле-дискриминатор для определения типа производного класса - entity, simple, collection (текущий - abstract)
    @Column(name = "class_category")
    private String classCategory;
    // Ссылка на родительский элемент в иерархии мета-данных
    @ManyToOne(cascade = CascadeType.REFRESH, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private MetaClass parent;
    // Список дочерних классов
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "parent", fetch = FetchType.LAZY)
    private Collection<MetaClass> childClasses;
    // Список полей генерируемого класса
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ownerClass", fetch = FetchType.LAZY)
    private List<MetaField> fields;

    @Override
    public void deleteFull() {
    }

    @Override
    public void loadFull() {
    }

    public boolean isReadonly() {
        return readonly;
    }

    public void setReadonly(boolean readonly) {
        this.readonly = readonly;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Class getJavaTypeClass() {
        return javaTypeClass;
    }

    public void setJavaTypeClass(Class javaTypeClass) {
        this.javaTypeClass = javaTypeClass;
    }

    public String getClassCategory() {
        return classCategory;
    }

    public void setClassCategory(String classCategory) {
        this.classCategory = classCategory;
    }

    public MetaClass getParent() {
        return parent;
    }

    public void setParent(MetaClass parent) {
        this.parent = parent;
    }

    public Collection<MetaClass> getChildClasses() {
        return childClasses;
    }

    public void setChildClasses(Collection<MetaClass> childClasses) {
        this.childClasses = childClasses;
    }

    public List<MetaField> getFields() {
        return fields;
    }

    public void setFields(List<MetaField> fields) {
        this.fields = fields;
    }
}
