package io.github.mkanev.model;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import org.hibernate.annotations.Index;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA. User: Maksim Kanev Date: 10.08.13 Time: 19:42
 */
@Entity
@Table(name = "hel_users")
@NamedQuery(name = "User.DEFAULT_FIND_QUERY", query = "select o from User o where o.username = :username")
public class User extends Person implements Comparable<User> {

    @Pattern(regexp = "[a-zA-Z0-9._-]+")
    @Index(name = "idx_username")
    private String username;
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "hel_users_roles",
               joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
               inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("username", username).toString();
    }

    @Override
    public int compareTo(User that) {
        return ComparisonChain.start().compare(this.username, that.username, Ordering.natural().nullsLast()).result();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return Objects.equal(this.username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }
}
