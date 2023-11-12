package com.MyProject.KinoForum.film.service;

import com.MyProject.KinoForum.category.model.Category;
import com.MyProject.KinoForum.category.repository.CategoryRepository;
import com.MyProject.KinoForum.category.service.CategoryService;
import com.MyProject.KinoForum.director.model.Director;
import com.MyProject.KinoForum.director.repository.DirectorRepository;
import com.MyProject.KinoForum.director.service.DirectorService;
import com.MyProject.KinoForum.enums.FilmRating;
import com.MyProject.KinoForum.film.dto.FilmDto;
import com.MyProject.KinoForum.film.dto.NewFilm;
import com.MyProject.KinoForum.film.dto.UpdateFilmDto;
import com.MyProject.KinoForum.film.mapper.FilmMapper;
import com.MyProject.KinoForum.film.model.Film;
import com.MyProject.KinoForum.film.model.FilmSpecifications;
import com.MyProject.KinoForum.film.repository.FilmRepository;
import com.MyProject.KinoForum.film.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {
    @Mock
    private FilmRepository repository;
    @Mock
    private DirectorService directorService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private FilmMapper mapper;

    @InjectMocks
    private FilmService service;

    private Film film;
    private FilmDto filmDto;
    private Category category;
    private Director director;
    @BeforeEach
    public void init(){
        category = Category.builder()
                .name("Drama")
                .build();
        director = Director.builder()
                .name("Hank M")
                .build();

        film = Film.builder()
                .id(1L)
                .title("Film title")
                .description("Film description")
                .releaseYear(2013)
                .duration(LocalTime.MAX)
                .director(director)
                .rating(FilmRating.PG_13)
                .country("Australia")
                .category(category)
                .build();

        filmDto = FilmDto.builder()
                .id(1L)
                .title("Film title")
                .description("Film description")
                .releaseYear(2013)
                .directorId(1L)
                .categoryId(1L)
                .duration(LocalTime.MAX)
                .rating(FilmRating.PG_13)
                .country("Australia")
                .build();
    }

    @Test
    public void FilmService_AddFilm_ReturnSavedFilm(){
        NewFilm newFilm = NewFilm.builder()
                .title("Film title")
                .description("Film description")
                .releaseYear(2013)
                .directorId(1L)
                .categoryId(1L)
                .duration(LocalTime.MAX)
                .rating(FilmRating.PG_13)
                .country("Australia")
                .build();

        when(repository.save(any(Film.class))).thenReturn(film);
        when(directorService.getDirectorEntity(anyLong())).thenReturn(director);
        when(categoryService.getCategoryEntity(anyLong())).thenReturn(category);
        when(mapper.toEntityFromNewFilm(
                any(NewFilm.class),
                any(Director.class),
                any(Category.class))).thenReturn(film);
        when(mapper.toDto(any(Film.class))).thenReturn(filmDto);

        FilmDto result = service.addFilm(newFilm);

        assertThat(result).isNotNull();
        assertEquals(filmDto, result);

    }
    @Test
    public void FilmService_GetFilm_ReturnFoundFilmDto(){
        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(film));
        when(mapper.toDto(any(Film.class))).thenReturn(filmDto);

        FilmDto result = service.getFilm(anyLong());

        assertThat(result).isNotNull();
        assertEquals(filmDto, result);
    }
    @Test
    public void FilmService_GetAllFilms_ReturnFilmDtoList(){
        Page<Film> films = mock(Page.class);
        List<FilmDto> filmDtoList = List.of(filmDto);

        when(repository.findAll(
                any(Specification.class),
                any(Pageable.class))).thenReturn(films);
        when(mapper.toDtoList(anyList())).thenReturn(filmDtoList);

        List<FilmDto> result = service.search(10,
                0,
                null,
                null,
                null,
                null,
                null,
                null
                );

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result).hasSizeGreaterThan(0);
        assertEquals(filmDtoList, result);
    }
    @Test
    public void FilmService_PatchFilm_ReturnUpdatedFilmDto(){
        UpdateFilmDto upd = UpdateFilmDto.builder()
                .title("Film new name")
                .build();
        Film updatedFilm = Film.builder()
                .id(1L)
                .title("Film new name")
                .description("Film description")
                .releaseYear(2013)
                .category(category)
                .director(director)
                .duration(LocalTime.MAX)
                .rating(FilmRating.PG_13)
                .country("Australia")
                .build();
        filmDto.setTitle("Film new name");

        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(film));
        when(repository.save(any(Film.class))).thenReturn(film);
        when(mapper.toDto(any(Film.class))).thenReturn(filmDto);
        when(mapper.updateFilm(
                any(Film.class),
                any(UpdateFilmDto.class),
                any(),
                any())).thenReturn(updatedFilm);

        FilmDto result = service.patchFilm(upd, anyLong());

        assertThat(result).isNotNull();
        assertThat(result).hasNoNullFieldsOrProperties();
        assertEquals("Film new name", result.getTitle());
    }
    @Test
    public void FilmService_DeleteFilm_FilmDeleted(){
        assertAll(()->service.deleteFilm(anyLong()));
    }

}
