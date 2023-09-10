package com.MyProject.KinoForum.director.service;

import com.MyProject.KinoForum.director.dto.NewDirector;
import com.MyProject.KinoForum.director.dto.DirectorDto;

import java.util.List;
import java.util.Map;

public interface DirectorService {

    DirectorDto addDirector(NewDirector newDirector);

    DirectorDto getDirector(Long directorId);

    List<DirectorDto> getAllDirectors(int size, int from);

    void deleteDirector(Long directorId);

    DirectorDto patchDirector(Map<String, Object> fields, Long directorId);
}
