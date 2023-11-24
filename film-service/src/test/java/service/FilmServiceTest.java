//package service;
//
//import com.MyProject.enums.FilmRating;
//import com.MyProject.dto.FilmDto;
//import com.MyProject.dto.NewFilm;
//import com.MyProject.dto.UpdateFilmDto;
//import com.MyProject.mapper.FilmMapper;
//import com.MyProject.model.Film;
//import com.MyProject.repository.FilmRepository;
//import com.MyProject.service.FilmService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class FilmServiceTest {
//    @Mock
//    private FilmRepository repository;
//    @Mock
//    private FilmMapper mapper;
//
//    @InjectMocks
//    private FilmService service;
//
//    private Film film;
//    private FilmDto filmDto;
//    @BeforeEach
//    public void init(){
//        film = Film.builder()
//                .id(1L)
//                .title("Film title")
//                .description("Film description")
//                .releaseYear(2013)
//                .duration(LocalTime.MAX)
//                .directorIds(List.of(1L))
//                .rating(FilmRating.PG_13)
//                .country("Australia")
//                .categoryIds(List.of(1L))
//                .build();
//
//        filmDto = FilmDto.builder()
//                .id(1L)
//                .title("Film title")
//                .description("Film description")
//                .releaseYear(2013)
//                .directorIds(List.of(1L))
//                .categoryIds(List.of(1L))
//                .duration(LocalTime.MAX)
//                .rating(FilmRating.PG_13)
//                .country("Australia")
//                .build();
//    }
//
//    @Test
//    public void FilmService_AddFilm_ReturnSavedFilm(){
//        NewFilm newFilm = NewFilm.builder()
//                .title("Film title")
//                .description("Film description")
//                .releaseYear(2013)
//                .directorIds(List.of(1L))
//                .categoryIds(List.of(1L))
//                .duration(LocalTime.MAX)
//                .rating(FilmRating.PG_13)
//                .country("Australia")
//                .build();
//
//        when(repository.save(any(Film.class))).thenReturn(film);
//        when(mapper.toEntityFromNewFilm(
//                any(NewFilm.class))).thenReturn(film);
//        when(mapper.toDto(any(Film.class))).thenReturn(filmDto);
//
//        FilmDto result = service.addFilm(newFilm);
//
//        assertThat(result).isNotNull();
//        assertEquals(filmDto, result);
//
//    }
//    @Test
//    public void FilmService_GetFilm_ReturnFoundFilmDto(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(film));
//        when(mapper.toDto(any(Film.class))).thenReturn(filmDto);
//
//        FilmDto result = service.getFilm(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(filmDto, result);
//    }
//    @Test
//    public void FilmService_GetAllFilms_ReturnFilmDtoList(){
//        Page<Film> films = mock(Page.class);
//        List<FilmDto> filmDtoList = List.of(filmDto);
//
//        when(repository.findAll(
//                any(Specification.class),
//                any(Pageable.class))).thenReturn(films);
//        when(mapper.toDtoList(anyList())).thenReturn(filmDtoList);
//
//        List<FilmDto> result = service.search(10,
//                0,
//                null,
//                null,
//                null,
//                null,
//                null,
//                null
//                );
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//        assertThat(result).hasSizeGreaterThan(0);
//        assertEquals(filmDtoList, result);
//    }
//    @Test
//    public void FilmService_PatchFilm_ReturnUpdatedFilmDto(){
//        UpdateFilmDto upd = UpdateFilmDto.builder()
//                .title("Film new name")
//                .build();
//        Film updatedFilm = Film.builder()
//                .id(1L)
//                .title("Film new name")
//                .description("Film description")
//                .releaseYear(2013)
//                .categoryIds(List.of(1L))
//                .directorIds(List.of(1L))
//                .duration(LocalTime.MAX)
//                .rating(FilmRating.PG_13)
//                .country("Australia")
//                .build();
//        filmDto.setTitle("Film new name");
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(film));
//        when(repository.save(any(Film.class))).thenReturn(film);
//        when(mapper.toDto(any(Film.class))).thenReturn(filmDto);
//        when(mapper.updateFilm(
//                any(Film.class),
//                any(UpdateFilmDto.class),
//                any(),
//                any())).thenReturn(updatedFilm);
//
//        FilmDto result = service.patchFilm(upd, anyLong());
//
//        assertThat(result).isNotNull();
//        assertThat(result).hasNoNullFieldsOrProperties();
//        assertEquals("Film new name", result.getTitle());
//    }
//    @Test
//    public void FilmService_DeleteFilm_FilmDeleted(){
//        assertAll(()->service.deleteFilm(anyLong()));
//    }
//
//}
