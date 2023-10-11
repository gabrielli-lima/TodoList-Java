package br.com.gabriellilima.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID>{
    // Interface modelo/contrato que tem dentro da aplicação
    // classe -> tem atributos e metodos e esses metodos de fato tem uma implementação
    // interface -> só tem a representação dos metodos que tem disponivel dentro delas
    // <> -> significa que a classe recebe um generator 
    // generator -> atributos mais dinamicos
    // < , > a primeira coisa qu passa é qual classe que esse repositorio ta representando -> passa a entidade
        // o segundo parametro é qual o tipo de id que a entidade tem
    // extend -> a interface vai extender tudo o que tem dentro do JpaRepository


    // cria um metodo apenas usando findBy / e o um tipo que vai retornar é um UserModel 
    // e automaticamente entende que vai fazer um select buscanco pela coluna Username
    UserModel findByUsername(String username);
}
