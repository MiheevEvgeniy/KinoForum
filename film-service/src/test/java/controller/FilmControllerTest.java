//package controller;
//
//import com.MyProject.enums.FilmRating;
//import com.MyProject.controller.FilmController;
//import com.MyProject.dto.FilmDto;
//import com.MyProject.dto.NewFilm;
//import com.MyProject.dto.UpdateFilmDto;
//import com.MyProject.service.FilmService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.hamcrest.CoreMatchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.time.LocalTime;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@WebMvcTest(controllers = FilmController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class FilmControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private FilmService service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private FilmDto filmDto;
//
//    private final String URL = "/films";
//    @BeforeEach
//    public void init(){
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
//    public void FilmController_AddFilm_ReturnCreated() throws Exception {
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
//        when(service.addFilm(any(NewFilm.class))).thenReturn(filmDto);
//
//        ResultActions response = mockMvc.perform(post(URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newFilm)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(filmDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(filmDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear", CoreMatchers.is(filmDto.getReleaseYear())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.directorId").value(filmDto.getDirectorIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(filmDto.getCategoryIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value(filmDto.getDuration().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(filmDto.getRating().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(filmDto.getCountry())));
//    }
//    @Test
//    public void FilmController_GetFilm_ReturnFoundFilmDto() throws Exception {
//        when(service.getFilm(anyLong())).thenReturn(filmDto);
//
//        ResultActions response = mockMvc.perform(get(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(filmDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(filmDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(filmDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear", CoreMatchers.is(filmDto.getReleaseYear())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.directorId").value(filmDto.getDirectorIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(filmDto.getCategoryIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value(filmDto.getDuration().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(filmDto.getRating().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(filmDto.getCountry())));
//    }
//    @Test
//    public void FilmController_GetAllFilms_ReturnFilmDtoList() throws Exception{
//        List<FilmDto> filmDtoList = List.of(filmDto,filmDto,filmDto);
//        when(service.search(
//                anyInt(),
//                anyInt(),
//                isNull(),
//                isNull(),
//                isNull(),
//                isNull(),
//                isNull(),
//                isNull())).thenReturn(filmDtoList);
//
//        ResultActions response = mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(filmDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(filmDtoList.size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title", CoreMatchers.is(filmDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", CoreMatchers.is(filmDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].releaseYear", CoreMatchers.is(filmDto.getReleaseYear())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].directorId").value(filmDto.getDirectorIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].categoryId").value(filmDto.getCategoryIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].duration").value(filmDto.getDuration().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].rating").value(filmDto.getRating().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].country", CoreMatchers.is(filmDto.getCountry())));
//    }
//    @Test
//    public void FilmController_PatchFilm_ReturnUpdatedFilmDto() throws Exception{
//        when(service.patchFilm(any(UpdateFilmDto.class), anyLong())).thenReturn(filmDto);
//
//        ResultActions response = mockMvc.perform(patch(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(filmDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(filmDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(filmDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.releaseYear", CoreMatchers.is(filmDto.getReleaseYear())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.directorId").value(filmDto.getDirectorIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.categoryId").value(filmDto.getCategoryIds().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.duration").value(filmDto.getDuration().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(filmDto.getRating().toString()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(filmDto.getCountry())));
//    }
//    @Test
//    public void FilmController_DeleteFilm_FilmDeleted() throws Exception{
//        doNothing().when(service).deleteFilm(anyLong());
//
//        ResultActions response = mockMvc.perform(delete(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//
//}
