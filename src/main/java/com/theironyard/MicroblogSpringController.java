package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class MicroblogSpringController {

    //@Autowired
    //UserRepo UserRepo;

    @Autowired
    MessageRepository MessageRepository;

    @RequestMapping(path = "/", method = RequestMethod.GET)

    //It should take the model and the session as arguments
    public String home(Model model, HttpSession session) {

        //It should read the username from the session
        String userName = (String) session.getAttribute("userName");

        //add it to the model
        //if(userName != null && userName.length()>0){
            model.addAttribute("userName", userName);
        //}

        model.addAttribute("message", MessageRepository.findAll());

        //return the home template
        return "home";
    }

    //In your controller, create a route for /login
    @RequestMapping(path = "/login", method = RequestMethod.POST)

    //It should take the session and the username as arguments
    public String login(String userName, HttpSession session) {

        /*User user = UserRepo.findFirstByName(userName);

        if(user == null){
            user = new User();
            user.name = userName;
            UserRepo.save(user);
        }*/

        session.setAttribute("userName", userName);

        //It should return a redirect to /
        return "redirect:/";
    }

    //In your controller, create a route for /add-message
    @RequestMapping(path = "/addMessage", method = RequestMethod.POST)

    //It should take the message text as an argument
    public String addMessage(String text) {
    //It should create a Message object and save it using the CrudRepository.
        Message message = new Message();

        message.text = text;

        MessageRepository.save(message);

        return "redirect:/";
    }

    //In your controller, create a route for /edit-message
    @RequestMapping("/editMessage")

    //takes id and message text
    public String editMessage(Integer id, String text) {

        Message message = MessageRepository.findOne(id);

        message.text = text;

        //save the message
        MessageRepository.save(message);
        return "redirect:/";
    }

    //In your controller, create a route for /delete-message
    @RequestMapping(path = "/deleteMessage", method = RequestMethod.POST)

    //It should take the message id as an argument
    public String deleteMessage(Integer id) {
        MessageRepository.delete(id);

        return "redirect:/";
    }
}

