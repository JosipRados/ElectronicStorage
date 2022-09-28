package electronicstorage;

import electronicstorage.BussinesLogic.Models.RoleDTO;
import electronicstorage.BussinesLogic.Models.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyUserDetails implements UserDetails {

    private UserDTO _user;
    private RoleDTO _role;

    public MyUserDetails(UserDTO user, RoleDTO role) {
        _user = user;
        _role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(_role.getRoleName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return _user.getPassword();
    }

    @Override
    public String getUsername() {
        return _user.getUsername();
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
        return _user.getEnabled();
    }

}

