package akademia;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = {UserRepository.class, RoleRepository.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private CustomUserService customUserService;

    public SecurityConfig(PasswordEncoder passwordEncoder, CustomUserService customUserService) {
        this.passwordEncoder = passwordEncoder;
        this.customUserService = customUserService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            //wyjatki filtra autoryzacyjnego
            .antMatchers("/login", "/register").permitAll()
            //spojnik laczacy builder obiektu
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/signin")
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler((req, res, auth) -> {
                for(GrantedAuthority g: auth.getAuthorities()) {
                    System.out.println(g.getAuthority());
                }
            //    res.addCookie(new Cookie("ale-ciacho", "ciacho z akademii kodu"));
                res.sendRedirect("/");
            })
            .failureUrl("/login?error='incorrect login or password'")
            .failureHandler((req, res, auth) -> {
                System.out.println(req.getQueryString());
                System.out.println(req.getRequestURI());
                System.out.println(req.getUserPrincipal());
            })
            .permitAll() //todo sprawdzic permit
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler((req, res, auth) -> {
                req.getSession().setAttribute("message", "You are logout.");
                res.sendRedirect("/login");
            })
            .permitAll(); //todo sprawdzic permit

//        http.headers().frameOptions().disable();
//        http.cors().disable();
//        http.csrf().disable();


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder);


    }
}
