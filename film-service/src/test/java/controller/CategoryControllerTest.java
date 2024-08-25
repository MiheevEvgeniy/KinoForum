//package controller;
//
//import com.MyProject.controller.CategoryController;
//import com.MyProject.dto.CategoryDto;
//import com.MyProject.dto.NewCategory;
//import com.MyProject.dto.UpdateCategoryDto;
//import com.MyProject.service.CategoryService;
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
//@WebMvcTest(controllers = CategoryController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class CategoryControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private CategoryService service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private CategoryDto categoryDto;
//
//    private final String URL = "/categories";
//    @BeforeEach
//    public void init(){
//        categoryDto = CategoryDto.builder()
//                .id(1L)
//                .name("Horror")
//                .build();
//    }
//
//    @Test
//    public void CategoryController_AddCategory_ReturnCreated() throws Exception {
//        NewCategory newCategory = NewCategory.builder()
//                .name("Horror")
//                .build();
//
//        when(service.addCategory(newCategory)).thenReturn(categoryDto);
//
//        ResultActions response = mockMvc.perform(post(URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newCategory)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(categoryDto.getName())));
//    }
//    @Test
//    public void CategoryController_GetCategory_ReturnFoundCategoryDto() throws Exception {
//        when(service.getCategory(anyLong())).thenReturn(categoryDto);
//
//        ResultActions response = mockMvc.perform(get(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(categoryDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(categoryDto.getName())));
//    }
//    @Test
//    public void CategoryController_GetAllCategorys_ReturnCategoryDtoList() throws Exception{
//        List<CategoryDto> categoryDtoList = List.of(categoryDto,categoryDto,categoryDto);
//        when(service.getAllCategories(anyInt(), anyInt())).thenReturn(categoryDtoList);
//
//        ResultActions response = mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(categoryDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(categoryDtoList.size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name", CoreMatchers.is(categoryDto.getName())));
//    }
//    @Test
//    public void CategoryController_PatchCategory_ReturnUpdatedCategoryDto() throws Exception{
//        when(service.patchCategory(any(UpdateCategoryDto.class), anyLong())).thenReturn(categoryDto);
//
//        ResultActions response = mockMvc.perform(patch(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(categoryDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(categoryDto.getName())));
//    }
//    @Test
//    public void CategoryController_DeleteCategory_CategoryDeleted() throws Exception{
//        doNothing().when(service).deleteCategory(anyLong());
//
//        ResultActions response = mockMvc.perform(delete(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//
//}
