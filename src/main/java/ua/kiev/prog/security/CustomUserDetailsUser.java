package ua.kiev.prog.security;

/**
 * Created by Bogdan on 24.06.2015.
 */
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by Khemrayev A.K. on 19.05.2015.
 */
public class CustomUserDetailsUser extends User {
    private String salt;
    private String email;
    private String username;
    private String password;
    private long id;

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomUserDetailsUser(String username, String password, boolean enabled,
                                 boolean accountNonExpired, boolean credentialsNonExpired,
                                 boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                                 String salt, String email, long id) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.salt = salt;
        this.email = email;
        this.username = username;
        this.password = password;
        this.id=id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
