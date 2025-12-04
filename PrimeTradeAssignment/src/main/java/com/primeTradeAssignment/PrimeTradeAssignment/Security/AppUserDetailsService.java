package com.primeTradeAssignment.PrimeTradeAssignment.Security;




import com.primeTradeAssignment.PrimeTradeAssignment.Repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class  AppUserDetailsService  implements  UserDetailsService {
    private UserRepo userrepo;

    AppUserDetailsService(UserRepo userrepo) {
        this.userrepo = userrepo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  userrepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist!!!: " + email));
    }

}


