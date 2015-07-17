package ua.kiev.prog.security;

/**
 * Created by Bogdan on 24.06.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.kiev.prog.database.main.entity.Type;
import ua.kiev.prog.database.main.entity.User;
import ua.kiev.prog.database.main.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Khemrayev A.K. on 12.05.2015.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
//    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        CustomUserDetailsUser customUserDetailsUser = new CustomUserDetailsUser(
                user.getName(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAutorities(user.getType()),
                user.getSalt(),
                user.getEmail(),
                user.getId()
        );

        return customUserDetailsUser;
    }
    public Collection<? extends GrantedAuthority> getAutorities(Type type) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(type));
        return authList;
    }

    public List<String> getRoles(Type type) {
        List<String> roles = new ArrayList<String>();

        if(type != null && type.getName().equals("admins")) {
            roles.add("ROLE_ADMIN");
            roles.add("ROLE_USER");
        } else if (type != null && type.getName().equals("users")){
            roles.add("ROLE_USER");
        }

        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for(String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }

        return authorities;
    }
//    public Collection<? extends GrantedAuthority> getAutorities(List<Group> groups) {
//        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(groups));
//        return authList;
//    }
//
//    public List<String> getRoles(List<Group> groups) {
//        List<String> roles = new ArrayList<String>();
//
//        if(groups != null)
//            roles.add("ROLE_USER");
//
//        for (Group group :groups) {
//            if(group != null && group.getName().equals("Admins"))
//                roles.add("ROLE_ADMIN");
//        }
//
//        return roles;
//    }
//
//    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//
//        for(String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//
//        return authorities;
//    }
}
