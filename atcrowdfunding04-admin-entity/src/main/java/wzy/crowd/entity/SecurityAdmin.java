package wzy.crowd.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author 网中鱼
 * @date 2021/9/1-23:19
 */
public class SecurityAdmin extends User {

    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin,Collection<? extends GrantedAuthority> authorities){
        super(originalAdmin.getUserName(),originalAdmin.getUserPswd(),authorities);

        originalAdmin.setUserPswd(null);
        this.originalAdmin = originalAdmin;
    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }

    public void setOriginalAdmin(Admin originalAdmin) {
        this.originalAdmin = originalAdmin;
    }

    @Override
    public String toString() {
        return "SecurityAdmin{" +
                "originalAdmin=" + originalAdmin +
                '}';
    }
}
