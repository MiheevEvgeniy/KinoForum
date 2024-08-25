package com.MyProject.service;

import com.MyProject.dto.NewDirector;
import com.MyProject.dto.DirectorDto;
import com.MyProject.dto.UpdateDirectorDto;
import com.MyProject.mapper.DirectorMapper;
import com.MyProject.model.Director;
import com.MyProject.repository.DirectorRepository;
import com.MyProject.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
