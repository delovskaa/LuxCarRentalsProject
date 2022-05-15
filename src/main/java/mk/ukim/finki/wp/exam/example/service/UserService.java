package mk.ukim.finki.wp.exam.example.service;

import mk.ukim.finki.wp.exam.example.model.Role;
import mk.ukim.finki.wp.exam.example.model.User;

public interface UserService {

    User create(String username, String password, String name, String surname, Role role);

    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}
