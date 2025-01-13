package com.challenge.forum_hub.forum_hub.controller;

import com.challenge.forum_hub.forum_hub.domain.response.*;
import com.challenge.forum_hub.forum_hub.domain.topics.Topics;
import com.challenge.forum_hub.forum_hub.domain.user.User;
import com.challenge.forum_hub.forum_hub.repository.ResponseRepository;
import com.challenge.forum_hub.forum_hub.repository.TopicsRepository;
import com.challenge.forum_hub.forum_hub.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import java.util.List;


@RestController
@RequestMapping("/resposta")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {

    @Autowired
    private ResponseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicsRepository topicsRepository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity<ResponseCreateData> createResponse(@RequestBody @Valid ResponseCreateData dataResponse, UriComponentsBuilder uribuilder) {
//        // Cria uma nova entidade User usando os dados fornecidos
//        System.out.println(dataResponse.message());
//        Response newResponse = new Response();
//
//        System.out.println(newResponse.getMessage());
//
//        // Salva a entidade no banco de dados
//        //        repository.save(newResponse);
//
//        // Retorna uma resposta com status 201 e o recurso criado
//        //        return ResponseEntity
//        //                .status(201)
//        //                .body(newTopic);
//        var uri = uribuilder.path("/resposta/{id}").buildAndExpand(newResponse.getId()).toUri();
//
//        return ResponseEntity
//                .created(uri)
//                .body(new ResponseCreateData(newResponse));
//    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCreateData> createResponse(
            @RequestBody ResponseCreateData dataResponse,
            UriComponentsBuilder uribuilder) {

        // Buscar autor(es) no banco de dados
        List<User> authors = userRepository.findByName(dataResponse.author());
        if (authors.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Retorna 400 se o autor não for encontrado
        }
        if (authors.size() > 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Retorna 409 se houver duplicatas
        }
        User author = authors.get(0);

        // Buscar tópico(s) no banco de dados
        List<Topics> topics = topicsRepository.findFirstByTitle(dataResponse.topics());
        if (topics.isEmpty()) {
            return ResponseEntity.badRequest().build(); // Retorna 400 se o tópico não for encontrado
        }
        if (topics.size() > 1) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Retorna 409 se houver duplicatas
        }
        Topics topic = topics.get(0);

        // Criar uma nova entidade Response
        Response newResponse = new Response();
        newResponse.setMessage(dataResponse.message());
        newResponse.setCreationDate(dataResponse.creationDate());
        newResponse.setAuthor(author);
        newResponse.setTopics(topic);
        newResponse.setSolution(dataResponse.solution());

        // Salvar a entidade no banco de dados
        repository.save(newResponse);

        // Criar URI para o recurso criado
        var uri = uribuilder.path("/resposta/{id}").buildAndExpand(newResponse.getId()).toUri();

        // Retornar a resposta criada
        return ResponseEntity
                .created(uri)
                .body(new ResponseCreateData(newResponse));
    }


    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ResponseListData>>> listResponse(
            @PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            PagedResourcesAssembler<ResponseListData> assembler) {

        // Busca os tópicos no repositório e os transforma em DTO
        Page<ResponseListData> responsePage = repository.findAll(pageable).map(ResponseListData::new);

        // Converte a página de dados em um modelo paginado HATEOAS
        PagedModel<EntityModel<ResponseListData>> responsePagedModel = assembler.toModel(responsePage);

        System.out.println(responsePagedModel);
        return ResponseEntity.ok(responsePagedModel);

    }

    //    @GetMapping
    //    public ResponseEntity<List<ResponseListData>> listResponse() {
    //        // Busca todos os registros no repositório e os transforma em DTO
    //        List<ResponseListData> responseList = repository.findAll().stream()
    //                .map(ResponseListData::new)
    //                .toList();
    //
    //        // Retorna a lista de dados como resposta
    //        System.out.println(responseList);
    //        return ResponseEntity.ok(responseList);
    //    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDetailsData> getResponseDetails(@PathVariable Long id) {
        // Verifica se o tópico existe no banco de dados
        return repository.findById(id)
                .map(response -> ResponseEntity.ok(new ResponseDetailsData(response)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ResponseUpdateData> updateResponse(
            @PathVariable Long id,
            @RequestBody  Response updateData) {

        return repository.findById(id)

                .map(existingResponse -> {
                    existingResponse.setMessage(updateData.getMessage());
                    existingResponse.getTopics().setTitle(updateData.getTopics().getTitle());
                    existingResponse.getAuthor().setName(updateData.getAuthor().getName());
                    existingResponse.setSolution(updateData.getSolution());
                    repository.save(existingResponse);
                    return ResponseEntity.ok(new ResponseUpdateData(existingResponse));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity<Object> deleteResponse(@PathVariable Long id) {
        return repository.findById(id)
                .map(response -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build(); // Retorna 204 No Content
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    public ResponseController() {
    }

    public ResponseController(ResponseRepository repository) {
        this.repository = repository;
    }
}