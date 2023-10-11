package br.com.gabriellilima.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// @Data -> getters e setters  @Getter -> só getters e @Setter -> só setters
// pode colocar em cima d apenas um atributo @Getter e @Setter

@Data
@Entity(name = "tb_users")
public class UserModel {
    // getters e setters -> como esses atributos podem ser acessados de outras clases
    // get -> buscar inofrmacao do que tem dentro do atributo
    // set -> atualizar/inserir/definir um valor par aum atributo privado de uma classe
    
    // orm -> pega o objeto/classe transforma para uma forma que o o banco de dados consiga entender(SQL)
    // hibernate é o orm do java

    // entidade -> obejto que representa uma tabela

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    // @Column(name =  "usuario") -> se quiser mudar o nome da coluna username para "usuario"
    // @Column(unique = true) -> se tiver um valor igual vai dar um erro / para não repetir

    @Column(unique = true)
    private String username;
    private String name, password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    
}
