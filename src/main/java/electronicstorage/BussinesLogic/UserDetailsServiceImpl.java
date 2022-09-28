package electronicstorage.BussinesLogic;

import electronicstorage.BussinesLogic.Models.RoleDTO;
import electronicstorage.BussinesLogic.Models.UserDTO;
import electronicstorage.MyUserDetails;
import electronicstorage.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository _userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDTO user = _userRepository.GetUser(username);
        RoleDTO role = _userRepository.GetRole(user.getRoleId());

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
                return authorities;
            }

            @Override
            public String getPassword() {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                return (encoder.encode(user.getPassword()));
            }

            @Override
            public String getUsername() {
                return user.getUsername();
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
        };
        //return new MyUserDetails(user, role);
    }
}
