package com.loginregister.logincodejavanet;

import com.loginregister.logincodejavanet.RestaurantDishes.Menu;
import com.loginregister.logincodejavanet.RestaurantDishes.MenuRepository;
import com.loginregister.logincodejavanet.orders.Order;
import com.loginregister.logincodejavanet.orders.OrderRepository;
import com.loginregister.logincodejavanet.restaurantdetails.Restaurant;
import com.loginregister.logincodejavanet.restaurantdetails.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private MenuRepository menuRepo;

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
        return "register_success";
    }

    @RequestMapping(value = "/list_users", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView listAllUsers(Model model, Principal principal) {
//        List<User> listUsers = (List<User>) userRepo.findAll();
        String email = principal.getName();
        User newUser = userRepo.findByEmail(email);
        model.addAttribute("listUsers", newUser);
        ModelAndView modelAndView = new ModelAndView("users");
        ModelAndView mav = modelAndView;
        mav.addObject("user", newUser);
        return mav;
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUserPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_user");
        Optional<User> user = userRepo.findById(id);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {
        userRepo.save(user);
        return "index";
    }

    @GetMapping("/list_restaurants")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurantList = restaurantRepo.findAll();
        model.addAttribute("restaurantList", restaurantList);
        return "restaurants_list";
    }

    @GetMapping("/list_menu/{id}")
    public String listMenu(Model model, @PathVariable(name = "id") Long restaurant_id) {
        System.out.println(restaurant_id);
        List<Menu> menuList = menuRepo.findAllByRestaurantId(restaurant_id);
        model.addAttribute("menuList", menuList);
        return "menu";
    }

    @RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
    public String saveOrder(@ModelAttribute("order") Order order) {
        orderRepo.save(order);
        return "index";
    }
}