package io.github.mkanev.model;

import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@MappedSuperclass
public abstract class MetaModelEntity extends GenericEntity {

    public static final String FIELD_NAME = "name";
    public static final String FIELD_CAPTION = "caption";
    public static final String FIELD_DESCRIPTION = "description";
    //������������ �������� ����������
    private String name;
    //�������������� ������������ �������� ����������
    private String caption;
    //�������� �������� ����������
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
