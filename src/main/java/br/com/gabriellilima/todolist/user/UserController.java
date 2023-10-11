package br.com.gabriellilima.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // @Autowired -> o spring vai gerencia/instanciar todo o ciclo de vida
    @Autowired
    private IUserRepository userRepository;

    // ResponseEntity-> para dar outras respostas e status code ao usuario (200,400,500...)

    // RequestBody -> por baixo os panos gerencia/setta nas variaveis, vai fazer
    // tudo que tem que fazer pra colocar as informações dentro do objeto

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}
