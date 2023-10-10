package br.com.gabriellilima.todolist.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Dois jeitos de criar um controller @Controller ou @RestController
// @Controller -> estrutura que retorna páginas, templates -> flexiblidade maior
// @RestController -> retorna o conceito de api rest, rest full

// @RequestMapping -> reponsavel por estruturar a rota

@RestController
@RequestMapping("/users")
public class UserController {
    
    @PostMapping("/")
    public void create(@RequestBody UserModel userModel)  {
        System.out.println(userModel.name);
    }
}
