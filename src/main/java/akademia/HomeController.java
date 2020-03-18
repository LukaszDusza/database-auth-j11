package akademia;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomePage(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        String login = context.getAuthentication().getName();
        model.addAttribute("login", "You are logged in as: " + login);
        return "index";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user")
    public String getUserPage() {
        return "user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("errorInfo", error);
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

//    @PostMapping("/signin")
//    public String getFormLogin(@RequestParam String username, @RequestParam String password) {
//        System.out.println(username);
//        return "redirect:/";
//    }

}
