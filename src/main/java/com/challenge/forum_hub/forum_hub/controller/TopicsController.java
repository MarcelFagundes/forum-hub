package com.challenge.forum_hub.forum_hub.controller;

import com.challenge.forum_hub.forum_hub.model.*;
import com.challenge.forum_hub.forum_hub.repository.TopicsRepository;
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

@RestController
@RequestMapping("/topicos")
public class TopicsController {

    @Autowired
    private TopicsRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Topics> createTopics(@RequestBody @Valid TopicsCreateData dataTopic) {
        // Cria uma nova entidade Topics usando os dados fornecidos
        Topics newTopic = new Topics(dataTopic);

        // Salva a entidade no banco de dados
        repository.save(newTopic);

        // Retorna uma resposta com status 201 e o recurso criado
        return ResponseEntity
                .status(201)
                .body(newTopic);
    }

//    @GetMapping
//    public ResponseEntity<Page<TopicsListData>> listTopics(@PageableDefault(size=10, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable) {
//        var  topicos = repository.findAll(pageable).map(TopicsListData::new);
//        return  ResponseEntity.ok(topicos);
//    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TopicsListData>>> listTopics(
        @PageableDefault(size = 10, sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable,
        PagedResourcesAssembler<TopicsListData> assembler) {

        // Busca os tópicos no repositório e os transforma em DTO
        Page<TopicsListData> topicosPage = repository.findAll(pageable).map(TopicsListData::new);

        // Converte a página de dados em um modelo paginado HATEOAS
        PagedModel<EntityModel<TopicsListData>> topicosPagedModel = assembler.toModel(topicosPage);

        return ResponseEntity.ok(topicosPagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicsDetailsData> getTopicDetails(@PathVariable Long id) {
        // Verifica se o tópico existe no banco de dados
        return repository.findById(id)
            .map(topic -> ResponseEntity.ok(new TopicsDetailsData(topic)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicsDetailsData> updateTopic(
        @PathVariable Long id,
        @RequestBody @Valid Topics updateData) {

        return repository.findById(id)
            .map(existingTopic -> {
                existingTopic.setTitle(updateData.getTitle());
                existingTopic.setMessage(updateData.getMessage());
                existingTopic.setTopic_status(updateData.getTopic_status());
                existingTopic.setAuthor(updateData.getAuthor());
                existingTopic.setCourse(updateData.getCourse());
                repository.save(existingTopic);
                return ResponseEntity.ok(new TopicsDetailsData(existingTopic));
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Object> deleteTopic(@PathVariable Long id) {
        return repository.findById(id)
            .map(topic -> {
                repository.deleteById(id);
                     return ResponseEntity.noContent().build(); // Retorna 204 No Content
            })
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    public TopicsController() {
    }

    public TopicsController(TopicsRepository repository) {
        this.repository = repository;
    }
}