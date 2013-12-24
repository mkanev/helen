package io.github.mkanev.model;

import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@MappedSuperclass
public abstract class MetaModelEntity extends GenericEntity {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_CAPTION = "caption";
    public static final String FIELD_DESCRIPTION = "description";
    //Наименование элемента метамодели
    private String name;
    //Локализованное наименование элемента метамодели
    private String caption;
    //Описание элемента метамодели
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
