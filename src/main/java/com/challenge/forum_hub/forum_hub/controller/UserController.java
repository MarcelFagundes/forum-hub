package com.challenge.forum_hub.forum_hub.controller;

import com.challenge.forum_hub.forum_hub.domain.user.*;
import com.challenge.forum_hub.forum_hub.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<UserCreateData> createUser(@RequestBody UserCreateData dataUser, UriComponentsBuilder uribuilder) {
        // Cria uma nova entidade User usando os dados fornecidos
        User newUser = new User(dataUser);
        System.out.println(newUser.getId());
        System.out.println(newUser.getName());
        System.out.println(newUser.getEmail());

        // Salva a entidade no banco de dados
        repository.save(newUser);

        // Retorna uma resposta com status 201 e o recurso criado
//        return ResponseEntity
//                .status(201)
//                .body(newTopic);
        var uri = uribuilder.path("/usuario/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new UserCreateData(newUser));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<UserListData>>> listUser(
        @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
        PagedResourcesAssembler<UserListData> assembler) {

        // Busca os tópicos no repositório e os transforma em DTO
        Page<UserListData> userPage = repository.findAll(pageable).map(UserListData::new);

        // Converte a página de dados em um modelo paginado HATEOAS
        PagedModel<EntityModel<UserListData>> userPagedModel = assembler.toModel(userPage);

        return ResponseEntity.ok(userPagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailsData> getUserDetails(@PathVariable Long id) {
        // Verifica se o tópico existe no banco de dados
        return repository.findById(id)
            .map(user -> ResponseEntity.ok(new UserDetailsData(user)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UserUpdateData> updateUser(
        @PathVariable Long id,
        @RequestBody @Valid User updateData) {

        return repository.findById(id)
            .map(existingUser -> {
                existingUser.setName(updateData.getName());
                existingUser.setEmail(updateData.getEmail());
                existingUser.setPassword(updateData.getPassword());
//                System.out.println(existingUser.getUserTopicStatus());
                repository.save(existingUser);
                return ResponseEntity.ok(new UserUpdateData(existingUser));
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return repository.findById(id)
            .map(user -> {
                repository.deleteById(id);
                     return ResponseEntity.noContent().build(); // Retorna 204 No Content
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    public UserController() {
    }

    public UserController(UserRepository repository) {
        this.repository = repository;
    }
}