//package controller;
//
//import com.MyProject.controller.UserController;
//import com.MyProject.dto.NewUser;
//import com.MyProject.dto.UpdateUserDto;
//import com.MyProject.dto.UserDto;
//import com.MyProject.service.UserService;
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
//@WebMvcTest(controllers = UserController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private UserDto userDto;
//
//    private final String URL = "/users";
//    @BeforeEach
//    public void init(){
//        userDto = UserDto.builder()
//                .id(1L)
//                .name("Sam")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//    }
//
//    @Test
//    public void UserController_AddUser_ReturnCreated() throws Exception {
//        NewUser newUser = NewUser.builder()
//                .name("Sam")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//
//        when(service.addUser(newUser)).thenReturn(userDto);
//
//        ResultActions response = mockMvc.perform(post(URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newUser)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDto.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDto.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(userDto.getCountry())));
//    }
//    @Test
//    public void UserController_GetUser_ReturnFoundUserDto() throws Exception {
//        when(service.getUser(anyLong())).thenReturn(userDto);
//
//        ResultActions response = mockMvc.perform(get(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDto.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDto.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(userDto.getCountry())));
//    }
//    @Test
//    public void UserController_GetAllUsers_ReturnUserDtoList() throws Exception{
//        List<UserDto> userDtoList = List.of(userDto,userDto,userDto);
//        when(service.getAllUsers(anyInt(), anyInt())).thenReturn(userDtoList);
//
//        ResultActions response = mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(userDtoList.size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is(userDto.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email", CoreMatchers.is(userDto.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].country", CoreMatchers.is(userDto.getCountry())));
//    }
//    @Test
//    public void UserController_PatchUser_ReturnUpdatedUserDto() throws Exception{
//        when(service.patchUser(any(UpdateUserDto.class), anyLong())).thenReturn(userDto);
//
//        ResultActions response = mockMvc.perform(patch(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(userDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(userDto.getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email", CoreMatchers.is(userDto.getEmail())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.country", CoreMatchers.is(userDto.getCountry())));
//    }
//    @Test
//    public void UserController_DeleteUser_UserDeleted() throws Exception{
//        doNothing().when(service).deleteUser(anyLong());
//
//        ResultActions response = mockMvc.perform(delete(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//
//}
