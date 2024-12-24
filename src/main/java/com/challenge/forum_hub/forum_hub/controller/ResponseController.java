package com.challenge.forum_hub.forum_hub.controller;

import com.challenge.forum_hub.forum_hub.domain.response.*;
import com.challenge.forum_hub.forum_hub.repository.ResponseRepository;
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
@RequestMapping("/resposta")
public class ResponseController {

    @Autowired
    private ResponseRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseCreateData> createResponse(@RequestBody ResponseCreateData dataResponse, UriComponentsBuilder uribuilder) {
        // Cria uma nova entidade User usando os dados fornecidos
        Response newResponse = new Response(dataResponse);

        // Salva a entidade no banco de dados
        repository.save(newResponse);

        // Retorna uma resposta com status 201 e o recurso criado
//        return ResponseEntity
//                .status(201)
//                .body(newTopic);
        var uri = uribuilder.path("/resposta/{id}").buildAndExpand(newResponse.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new ResponseCreateData(newResponse));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<ResponseListData>>> listResponse(
        @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
        PagedResourcesAssembler<ResponseListData> assembler) {

        // Busca os tópicos no repositório e os transforma em DTO
        Page<ResponseListData> responsePage = repository.findAll(pageable).map(ResponseListData::new);

        // Converte a página de dados em um modelo paginado HATEOAS
        PagedModel<EntityModel<ResponseListData>> responsePagedModel = assembler.toModel(responsePage);

        return ResponseEntity.ok(responsePagedModel);
    }

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
        @RequestBody @Valid Response updateData) {

        return repository.findById(id)
            .map(existingResponse -> {
                existingResponse.setMessage(updateData.getMessage());
                existingResponse.setTopics(updateData.getTopics());
                existingResponse.setAuthor(updateData.getAuthor());
                existingResponse.setSolution(updateData.getSolution());
//                System.out.println(existingUser.getUserTopicStatus());
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