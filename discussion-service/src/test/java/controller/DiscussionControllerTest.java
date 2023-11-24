//package controller;
//
//import com.MyProject.model.Category;
//import com.MyProject.controller.DiscussionController;
//import com.MyProject.dto.DiscussionDto;
//import com.MyProject.dto.NewDiscussion;
//import com.MyProject.dto.UpdateDiscussionDto;
//import com.MyProject.service.DiscussionService;
//import com.MyProject.enums.DiscussionStatus;
//import com.MyProject.model.User;
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
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//
//@WebMvcTest(controllers = DiscussionController.class)
//@AutoConfigureMockMvc(addFilters = false)
//@ExtendWith(MockitoExtension.class)
//public class DiscussionControllerTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private DiscussionService service;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//    private DiscussionDto discussionDto;
//
//    private User user;
//    private Category category;
//    private final String URL = "/discussions";
//    @BeforeEach
//    public void init(){
//        user = User.builder()
//                .id(1L)
//                .name("Sam")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//
//        category = Category.builder()
//                .id(1L)
//                .name("Horror")
//                .build();
//        discussionDto = DiscussionDto.builder()
//                .id(1L)
//                .title("Discussion title")
//                .description("Discussion description")
//                .openedAt(LocalDateTime.MAX)
//                .closedAt(null)
//                .author(user)
//                .categories(List.of(category))
//                .status(DiscussionStatus.OPEN)
//                .commentsAmount(0)
//                .build();
//    }
//
//    @Test
//    public void DiscussionController_OpenDiscussion_ReturnCreated() throws Exception {
//        NewDiscussion newDiscussion = NewDiscussion.builder()
//                .title("Discussion title")
//                .description("Discussion description")
//                .author(1L)
//                .categories(List.of(1L))
//                .build();
//
//        when(service.openDiscussion(any(NewDiscussion.class))).thenReturn(discussionDto);
//
//        ResultActions response = mockMvc.perform(post(URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newDiscussion)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(discussionDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(discussionDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author.name", CoreMatchers.is(discussionDto.getAuthor().getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.commentsAmount").value(discussionDto.getCommentsAmount()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.categories.size()", CoreMatchers.is(discussionDto.getCategories().size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(discussionDto.getStatus().name()));
//    }
//    @Test
//    public void DiscussionController_GetDiscussion_ReturnFoundDiscussionDto() throws Exception {
//        when(service.getDiscussion(anyLong())).thenReturn(discussionDto);
//
//        ResultActions response = mockMvc.perform(get(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(discussionDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(discussionDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(discussionDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author.name", CoreMatchers.is(discussionDto.getAuthor().getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.commentsAmount").value(discussionDto.getCommentsAmount()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.categories.size()", CoreMatchers.is(discussionDto.getCategories().size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(discussionDto.getStatus().name()));
//    }
//    @Test
//    public void DiscussionController_GetAllDiscussions_ReturnDiscussionDtoList() throws Exception{
//        List<DiscussionDto> discussionDtoList = List.of(discussionDto,discussionDto,discussionDto);
//        when(service.search(
//                anyInt(),
//                anyInt(),
//                isNull(),
//                isNull(),
//                isNull(),
//                isNull())).thenReturn(discussionDtoList);
//
//        ResultActions response = mockMvc.perform(get(URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(discussionDto)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(discussionDtoList.size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title", CoreMatchers.is(discussionDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description", CoreMatchers.is(discussionDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].author.name", CoreMatchers.is(discussionDto.getAuthor().getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].commentsAmount").value(discussionDto.getCommentsAmount()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].categories.size()", CoreMatchers.is(discussionDto.getCategories().size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].status").value(discussionDto.getStatus().name()));
//    }
//    @Test
//    public void DiscussionController_PatchDiscussion_ReturnUpdatedDiscussionDto() throws Exception{
//        UpdateDiscussionDto upd = UpdateDiscussionDto.builder()
//                .title("Upd title")
//                .build();
//        discussionDto.setTitle("Upd title");
//
//        when(service.patchDiscussion(any(), anyLong())).thenReturn(discussionDto);
//
//        ResultActions response = mockMvc.perform(patch(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(upd)));
//
//        response
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(discussionDto.getTitle())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(discussionDto.getDescription())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.author.name", CoreMatchers.is(discussionDto.getAuthor().getName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.commentsAmount").value(discussionDto.getCommentsAmount()))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.categories.size()", CoreMatchers.is(discussionDto.getCategories().size())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(discussionDto.getStatus().name()));
//    }
//    @Test
//    public void DiscussionController_DeleteDiscussion_DiscussionDeleted() throws Exception{
//        doNothing().when(service).deleteDiscussion(anyLong());
//
//        ResultActions response = mockMvc.perform(delete(URL+"/1")
//                .contentType(MediaType.APPLICATION_JSON));
//
//        response.andExpect(MockMvcResultMatchers.status().isNoContent());
//    }
//
//
//}
