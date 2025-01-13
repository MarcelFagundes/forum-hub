package com.challenge.forum_hub.forum_hub.controller;

import com.challenge.forum_hub.forum_hub.domain.topics.*;
import com.challenge.forum_hub.forum_hub.domain.user.User;
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

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicsController {

    @Autowired
    private TopicsRepository TopicsRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicsCreateData> createTopics(
            @RequestBody TopicsCreateData dataTopic,
            UriComponentsBuilder uribuilder) {

        // Buscar o autor no banco de dados ou validar se ele existe
        User authorName = userRepository.findFirstByName(dataTopic.author());
        if (authorName == null) {
            return ResponseEntity.badRequest().build(); // Retorna 400 se o autor não for encontrado
        }

        // Criar um novo tópico usando o autor encontrado
        Topics newTopic = new Topics(dataTopic, authorName);

        // Salvar o tópico no banco de dados
        TopicsRepository.save(newTopic);

        // Criar URI para o recurso criado
        var uri = uribuilder.path("/topicos/{id}").buildAndExpand(newTopic.getId()).toUri();

        // Retornar o tópico criado como resposta
        return ResponseEntity
                .created(uri)
                .body(new TopicsCreateData(newTopic));
    }


    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<TopicsListData>>> listTopics(
            @PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
            PagedResourcesAssembler<TopicsListData> assembler) {

        // Busca os tópicos no repositório e os transforma em DTO
        Page<TopicsListData> topicosPage = TopicsRepository.findAll(pageable).map(TopicsListData::new);

        // Converte a página de dados em um modelo paginado HATEOAS
        PagedModel<EntityModel<TopicsListData>> topicosPagedModel = assembler.toModel(topicosPage);

        return ResponseEntity.ok(topicosPagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicsDetailsData> getTopicDetails(@PathVariable Long id) {
        // Verifica se o tópico existe no banco de dados
        return TopicsRepository.findById(id)
                .map(topic -> ResponseEntity.ok(new TopicsDetailsData(topic)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicsUpdateData> updateTopic(
            @PathVariable Long id,
            @RequestBody Topics updateData) {

        return TopicsRepository.findById(id)

                .map(existingTopic -> {
                    existingTopic.setTitle(updateData.getTitle());
                    existingTopic.setMessage(updateData.getMessage());
                    existingTopic.setTopicStatus(updateData.getTopicStatus());
                    existingTopic.getAuthor().setName(updateData.getAuthor().getName());
                    existingTopic.setCourse(updateData.getCourse());
                    TopicsRepository.save(existingTopic);
                    return ResponseEntity.ok(new TopicsUpdateData(existingTopic));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity<Object> deleteTopic(@PathVariable Long id) {
        return TopicsRepository.findById(id)
                .map(topic -> {
                    TopicsRepository.deleteById(id);
                    return ResponseEntity.noContent().build(); // Retorna 204 No Content
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 se não encontrado
    }

    public TopicsController() {
    }

    public TopicsController(TopicsRepository repository) {
        this.TopicsRepository = repository;
    }
}