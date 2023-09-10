package com.MyProject.KinoForum.director.service;

import com.MyProject.KinoForum.director.dto.NewDirector;
import com.MyProject.KinoForum.director.dto.DirectorDto;
import com.MyProject.KinoForum.director.mapper.DirectorMapper;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.director.repository.DirectorRepository;
import com.MyProject.KinoForum.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository repository;
    private final DirectorMapper mapper;

    @Override
    public DirectorDto addDirector(NewDirector newDirector) {
        return mapper.toDto(repository.save(mapper.toEntityFromNewDirector(newDirector)));
    }

    @Override
    public DirectorDto getDirector(Long directorId) {
        Optional<Director> user = repository.findById(directorId);
        if(!user.isPresent()) throw new NotFoundException("Director not found");
        return mapper.toDto(user.get());
    }

    @Override
    public List<DirectorDto> getAllDirectors(int size, int from) {
        PagedListHolder<DirectorDto> page = new PagedListHolder<>(repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList()));
        page.setPageSize(size);
        page.setPage(from);
        return page.getPageList();
    }

    @Override
    public void deleteDirector(Long directorId) {
        repository.delete(mapper.toEntityFromDirectorDto(getDirector(directorId)));
    }

    @Override
    public DirectorDto patchDirector(Map<String, Object> fields, Long directorId) {
        Optional<Director> user = repository.findById(directorId);
        if(!user.isPresent()) throw new NotFoundException("Director not found");
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(Director.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, user.get(), v);
        });
        return mapper.toDto(repository.save(user.get()));
    }
}
