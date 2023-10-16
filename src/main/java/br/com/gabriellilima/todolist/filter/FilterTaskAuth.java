package br.com.gabriellilima.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gabriellilima.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// @Component -> essa anotacao serve para o spring saber que ele tem que gerenciar essa classe / é uma classe generica de gerenciamento / @controller tambem tem  @component

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();
        if (servletPath.startsWith("/tasks/")) {

            // Pegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");

            // substring -> metodo usado para extrair um texto/conteudo
            // trim -> é para remover os espaços
            var authEncoded = authorization.substring("Basic".length()).trim();

            // array de bytes
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            // gabriellilima:1234 -> ["gabriellilima" , "1234"]
            String[] credentials = authString.split(":");
            String userName = credentials[0];
            String password = credentials[1];

            System.out.println("Authorization");
            System.out.println(authorization);

            // Validar usuario
            var user = this.userRepository.findByUsername(userName);
            if (user == null) {
                response.sendError(401);
            } else {
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    // Segue viagem

                    // Vai settar o id usuario / pegar a info de quando ja tam logado e para não ter que ficar passando no body da requisição
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);

                } else {
                    response.sendError(401);
                }

            }
        } else {
            filterChain.doFilter(request, response);

        }

    }

}
