//package controller;
//
//import com.MyProject.controller.DirectorController;
//import com.MyProject.dto.DirectorDto;
//import com.MyProject.dto.NewDirector;
//import com.MyProject.dto.UpdateDirectorDto;
//import com.MyProject.service.DirectorService;
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
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@WebMvcTest(controllers = DirectorController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class DirectorControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DirectorService service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private DirectorDto directorDto;
//
//    private final String URL = "/directors";
//    @BeforeEach
//    public void init(){
//        directorDto = DirectorDto.builder()
//                .id(1L)
//                .name("Tedd M")
//                .build();
//    }
//
//    @Test
//    public void DirectorController_AddDirector_ReturnCreated() throws Exception {
//        NewDirector newDirector = NewDirector.builder()
//                .name("Tedd M")
//                .build();
//
//        when(service.addDirector(newDirector)).thenReturn(directorDto);
//
//        ResultActions response = mockMvc.perform(post(URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newDirector)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(directorDto.getName())));
//    }
//    @Test
//    public void DirectorController_GetDirector_ReturnFoundDirectorDto() throws Exception {
//        when(service.getDirector(anyLong())).thenReturn(directorDto);
//
//        ResultActions response = mockMvc.perform(get(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(directorDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(directorDto.getName())));
//    }
//    @Test
//    public void DirectorController_GetAllDirectors_ReturnDirectorDtoList() throws Exception{
//        List<DirectorDto> directorDtoList = List.of(directorDto,directorDto,directorDto);
//        when(service.getAllDirectors(anyInt(), anyInt())).thenReturn(directorDtoList);
//
//        ResultActions response = mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(directorDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(directorDtoList.size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is(directorDto.getName())));
//    }
//    @Test
//    public void DirectorController_PatchDirector_ReturnUpdatedDirectorDto() throws Exception{
//        when(service.patchDirector(any(UpdateDirectorDto.class), anyLong())).thenReturn(directorDto);
//
//        ResultActions response = mockMvc.perform(patch(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(directorDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(directorDto.getName())));
//    }
//    @Test
//    public void DirectorController_DeleteDirector_DirectorDeleted() throws Exception{
//        doNothing().when(service).deleteDirector(anyLong());
//
//        ResultActions response = mockMvc.perform(delete(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//
//}
