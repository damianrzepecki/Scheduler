package pl.sda.scheduler.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/app/loggedIn")
    public String getLoggedIn() {
        return "loggedIn";
    }

    @PostMapping("/app/loggedIn")
    public String loggedIn() {
        return "redirect:/app/loggedIn";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/error/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @GetMapping("/app/logout")
    public String logout() {
        return "redirect:/";
    }

    @GetMapping("/app/calendar")
    public String calendar() {
        return "/calendar/calendar";
    }
}
