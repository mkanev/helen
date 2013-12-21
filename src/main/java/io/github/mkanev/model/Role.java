package io.github.mkanev.model;

import com.google.common.base.Objects;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Maksim Kanev
 */
@Entity
@Table(name = "hel_roles")
public class Role extends GenericEntity implements GrantedAuthority {

    @Column(nullable = false)
    @NotEmpty
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", name).toString();
    }

    @Override
    public void deleteFull() {
    }

    @Override
    public void loadFull() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
