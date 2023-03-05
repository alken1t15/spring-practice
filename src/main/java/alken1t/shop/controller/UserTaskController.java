package alken1t.shop.controller;


import alken1t.shop.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user_task_controller")
public class UserTaskController {

    private static final User[] USERS = new User[]{
            new User("Bill", 66),
            new User("Jeff", 45),
            new User("Max", 17),
            new User("Leon", 25)
    };

    @GetMapping(path = "/get_user_list")
    public List<User> getUsersList(@RequestParam(required = false) Integer from, @RequestParam(required = false) Integer to) {
        List<User> users = new ArrayList<>();
        if (from == null && to == null) {
            users = List.of(UserTaskController.USERS);
        } else if (from != null && to != null) {
            for (User user : UserTaskController.USERS) {
                if (user.getAge() > from && user.getAge() < to) {
                    users.add(user);
                }
            }
        } else if (from != null) {
            for (User user : UserTaskController.USERS) {
                if (user.getAge() > from) {
                    users.add(user);
                }
            }
        } else {
            for (User user : UserTaskController.USERS) {
                if (user.getAge() < to) {
                    users.add(user);
                }
            }
        }
        return users;
    }
}