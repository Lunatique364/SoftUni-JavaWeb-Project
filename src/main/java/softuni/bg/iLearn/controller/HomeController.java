package softuni.bg.iLearn.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.bg.iLearn.dto.NewsletterSubscriptionDTO;
import softuni.bg.iLearn.service.SubscriptionService;
import softuni.bg.iLearn.service.WeeklyNewsletterService;

@Controller
public class HomeController {

    private final SubscriptionService subscriptionService;
    private final WeeklyNewsletterService newsletterService;

    @Autowired
    public HomeController(SubscriptionService subscriptionService, WeeklyNewsletterService newsletterService) {
        this.subscriptionService = subscriptionService;
        this.newsletterService = newsletterService;
    }

    @ModelAttribute("newsletterSubscriptionDTO")
    public NewsletterSubscriptionDTO initSubscription() {
        return new NewsletterSubscriptionDTO();
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/about")
    public String aboutUsPage() {
        return "about-us";
    }

    @PostMapping("/")
    public String postRegister(@Valid NewsletterSubscriptionDTO subscriptionDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || subscriptionService.addSubscription(subscriptionDTO)) {
            redirectAttributes.addFlashAttribute("subscriptionDTO", subscriptionDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.subscriptionDTO", bindingResult);
            redirectAttributes.addFlashAttribute("error", "Already subscribed or invalid email!");
        }
        return "redirect:/";
    }

}
