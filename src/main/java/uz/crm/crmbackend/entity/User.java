package uz.crm.crmbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.crm.crmbackend.entity.baseEntities.BaseEntity;
import uz.crm.crmbackend.entity.baseEntities.BaseEntityId;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User extends BaseEntityId implements UserDetails, BaseEntity {
    private String fullName;
    private String phoneNumber;
    @ManyToOne
    private EduCenter eduCenter;
    @ManyToOne
    private File logoFile;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private List<File> file;

    private Boolean isDeleted = false;
    @ManyToMany
    private Set<UserRole> userRoleSet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userRoleSet;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
