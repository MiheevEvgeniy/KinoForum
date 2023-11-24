//package service;
//
//import com.MyProject.feign.UserService;
//import com.MyProject.model.Category;
//import com.MyProject.dto.DiscussionDto;
//import com.MyProject.dto.NewDiscussion;
//import com.MyProject.dto.UpdateDiscussionDto;
//import com.MyProject.mapper.DiscussionMapper;
//import com.MyProject.model.Discussion;
//import com.MyProject.repository.DiscussionRepository;
//import com.MyProject.service.DiscussionService;
//import com.MyProject.enums.DiscussionStatus;
//import com.MyProject.model.User;
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
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class DiscussionServiceTest {
//    @Mock
//    private DiscussionRepository repository;
//    @Mock
//    private DiscussionMapper mapper;
//
//    @Mock
//    private UserService userService;
//    @Mock
//    private CategoryService categoryService;
//
//    @InjectMocks
//    private DiscussionService service;
//
//    private Discussion discussion;
//    private DiscussionDto discussionDto;
//    private User author;
//    private Category category;
//    @BeforeEach
//    public void init(){
//        author = User.builder()
//                .id(1L)
//                .name("Todd")
//                .email("Todd@gmail.com")
//                .country("Germany")
//                .build();
//
//        category = Category.builder()
//                .id(1L)
//                .name("Thriller")
//                .build();
//
//        discussion = Discussion.builder()
//                .id(1L)
//                .title("Discussion title")
//                .description("Discussion description")
//                .openedAt(LocalDateTime.MAX)
//                .closedAt(null)
//                .author(author)
//                .categories(List.of(category))
//                .status(DiscussionStatus.OPEN)
//                .commentsAmount(0)
//                .build();
//
//        discussionDto = DiscussionDto.builder()
//                .id(1L)
//                .title("Discussion title")
//                .description("Discussion description")
//                .openedAt(LocalDateTime.MAX)
//                .closedAt(null)
//                .author(author)
//                .categories(List.of(category))
//                .status(DiscussionStatus.OPEN)
//                .commentsAmount(0)
//                .build();
//    }
//
//    @Test
//    public void DiscussionService_AddDiscussion_ReturnSavedDiscussion(){
//        NewDiscussion newDiscussion = NewDiscussion.builder()
//                .title("Discussion title")
//                .description("Discussion description")
//                .author(1L)
//                .categories(List.of(1L))
//                .build();
//
//        when(repository.save(any(Discussion.class))).thenReturn(discussion);
//        when(userService.getUserEntity(anyLong())).thenReturn(author);
//        when(categoryService.getCategoryEntity(anyLong())).thenReturn(category);
//        when(mapper.toEntity(
//                any(NewDiscussion.class),
//                any(User.class),
//                anyList())).thenReturn(discussion);
//        when(mapper.toDto(any(Discussion.class))).thenReturn(discussionDto);
//
//        DiscussionDto result = service.openDiscussion(newDiscussion);
//
//        assertThat(result).isNotNull();
//        assertEquals(discussionDto, result);
//
//    }
//    @Test
//    public void DiscussionService_GetDiscussion_ReturnFoundDiscussionDto(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(discussion));
//        when(mapper.toDto(any(Discussion.class))).thenReturn(discussionDto);
//
//        DiscussionDto result = service.getDiscussion(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(discussionDto, result);
//    }
//    @Test
//    public void DiscussionService_GetAllDiscussions_ReturnDiscussionDtoList(){
//        Page<Discussion> discussions = mock(Page.class);
//        List<DiscussionDto> discussionDtoList = List.of(discussionDto);
//
//        when(repository.findAll(
//                any(Specification.class),
//                any(Pageable.class))).thenReturn(discussions);
//        when(mapper.toDtoList(anyList())).thenReturn(discussionDtoList);
//
//        List<DiscussionDto> result = service.search(
//                10,
//                0,
//                null,
//                null,
//                null,
//                null
//                );
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//        assertThat(result).hasSizeGreaterThan(0);
//        assertEquals(discussionDtoList, result);
//    }
//    @Test
//    public void DiscussionService_PatchDiscussion_ReturnUpdatedDiscussionDto(){
//        UpdateDiscussionDto upd = UpdateDiscussionDto.builder()
//                .title("Updated Discussion Name")
//                .build();
//        Discussion updatedDiscussion = Discussion.builder()
//                .id(1L)
//                .title("Updated Discussion Title")
//                .description("Discussion description")
//                .openedAt(LocalDateTime.MAX)
//                .closedAt(null)
//                .author(author)
//                .categories(new ArrayList<>())
//                .status(DiscussionStatus.OPEN)
//                .commentsAmount(0)
//                .build();
//        discussionDto.setTitle("Updated Discussion Title");
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(discussion));
//        when(repository.save(any(Discussion.class))).thenReturn(discussion);
//        when(mapper.toDto(any(Discussion.class))).thenReturn(discussionDto);
//        when(mapper.updateDiscussion(
//                any(Discussion.class),
//                any(UpdateDiscussionDto.class),
//                any())).thenReturn(updatedDiscussion);
//
//        DiscussionDto result = service.patchDiscussion(upd, anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals("Updated Discussion Title", result.getTitle());
//    }
//    @Test
//    public void DiscussionService_DeleteDiscussion_DiscussionDeleted(){
//        assertAll(()->service.deleteDiscussion(anyLong()));
//    }
//
//}
