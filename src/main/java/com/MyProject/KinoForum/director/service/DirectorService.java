package com.MyProject.KinoForum.director.service;

import com.MyProject.KinoForum.director.dto.NewDirector;
import com.MyProject.KinoForum.director.dto.DirectorDto;
import com.MyProject.KinoForum.director.dto.UpdateDirectorDto;
import com.MyProject.KinoForum.director.mapper.DirectorMapper;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.director.repository.DirectorRepository;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorService {
    private final DirectorRepository repository;
    private final DirectorMapper mapper;

    public DirectorDto addDirector(NewDirector newDirector) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewDirector(newDirector)));
    }

    public DirectorDto getDirector(Long directorId) {
        return mapper.toDto(getDirectorEntity(directorId));
    }

    public Director getDirectorEntity(Long directorId) {
        Optional<Director> director = repository.findById(directorId);
        if(director.isEmpty()) throw new NotFoundException("Director not found");
        return director.get();
    }

    public List<DirectorDto> getAllDirectors(int size, int from) {
        return mapper
                .toDtoList(
                        repository
                                .findAll(PageRequest.of(from,size))
                                .toList());
    }

    public void deleteDirector(Long directorId) {
        repository.deleteById(directorId);
    }
    public DirectorDto patchDirector(UpdateDirectorDto upd, Long directorId) {
        return mapper.toDto(
                repository.save(
                        mapper.updateDirector(
                                repository
                                        .findById(directorId)
                                        .orElseThrow(()->new NotFoundException("Director for update not found")), upd)));
    }
}
