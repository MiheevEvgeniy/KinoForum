package com.MyProject.KinoForum.director.controller;


import com.MyProject.KinoForum.director.dto.NewDirector;
import com.MyProject.KinoForum.director.dto.DirectorDto;
import com.MyProject.KinoForum.director.service.DirectorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/directors")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DirectorController {
    private final DirectorService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DirectorDto addDirector(@Valid @RequestBody NewDirector newDirector){
        log.info("addDirector started with body: {}",newDirector);
        DirectorDto result = service.addDirector(newDirector);
        log.info("addDirector finished: {}", result);
        return result;
    }
    @GetMapping("/{id}")
    public DirectorDto getDirector(@PathVariable("id") Long directorId){
        log.info("getDirector started with id: {}",directorId);
        DirectorDto result = service.getDirector(directorId);
        log.info("getDirector finished: {}", result);
        return result;
    }
    @GetMapping
    public List<DirectorDto> getAllDirectors(@RequestParam(value = "size",required = false, defaultValue = "5") @Min(5) int size,
                                         @RequestParam(value = "from",required = false, defaultValue = "1") @Min(1) int from){
        log.info("getAllDirectors started with params: size {}, from {}",size, from-1);
        List<DirectorDto> result = service.getAllDirectors(size,from-1);
        log.info("getAllDirectors finished: {}", result);
        return result;
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDirector(@PathVariable("id") Long directorId){
        log.info("deleteDirector started with id: {}",directorId);
        service.deleteDirector(directorId);
        log.info("deleteDirector finished");
    }
    @PatchMapping("/{id}")
    public DirectorDto patchDirector(@RequestBody Map<String, Object> fields, @PathVariable("id") Long directorId){
        log.info("patchDirector started with fields: {} and id: {}",fields, directorId);
        DirectorDto result = service.patchDirector(fields, directorId);
        log.info("patchDirector finished: {}", result);
        return result;
    }
}
