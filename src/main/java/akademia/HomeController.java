package akademia;

import akademia.register.UserAppDTO;
import akademia.register.UserAppService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private UserAppService userAppService;

    public HomeController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model, @RequestParam(name = "name", required = false) String name) {
        SecurityContext context = SecurityContextHolder.getContext();
        String login = context.getAuthentication().getName();
        System.out.println(login);
        model.addAttribute("login", "You are logged in as: " + login);
        model.addAttribute("name", name);
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

    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserAppDTO userAppDTO) {
        userAppService.registerUser(userAppDTO);
        return "redirect:/home?name=" + userAppDTO.getName();
    }

}
