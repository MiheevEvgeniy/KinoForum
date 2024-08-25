//package service;
//
//import com.MyProject.dto.CategoryDto;
//import com.MyProject.dto.NewCategory;
//import com.MyProject.dto.UpdateCategoryDto;
//import com.MyProject.mapper.CategoryMapper;
//import com.MyProject.model.Category;
//import com.MyProject.repository.CategoryRepository;
//import com.MyProject.service.CategoryService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CategoryServiceTest {
//    @Mock
//    private CategoryRepository repository;
//    @Mock
//    private CategoryMapper mapper;
//
//    @InjectMocks
//    private CategoryService service;
//
//    private Category category;
//    private CategoryDto categoryDto;
//    @BeforeEach
//    public void init(){
//        category = Category.builder()
//                .id(1L)
//                .name("Horror")
//                .build();
//
//        categoryDto = CategoryDto.builder()
//                .id(1L)
//                .name("Horror")
//                .build();
//    }
//
//    @Test
//    public void CategoryService_AddCategory_ReturnSavedCategory(){
//        NewCategory newCategory = NewCategory.builder()
//                .name("Horror")
//                .build();
//
//        when(repository.save(any(Category.class))).thenReturn(category);
//        when(mapper.toEntityFromNewCategory(any(NewCategory.class))).thenReturn(category);
//        when(mapper.toDto(any(Category.class))).thenReturn(categoryDto);
//
//        CategoryDto result = service.addCategory(newCategory);
//
//        assertThat(result).isNotNull();
//        assertEquals(categoryDto, result);
//
//    }
//    @Test
//    public void CategoryService_GetCategory_ReturnFoundCategoryDto(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(category));
//        when(mapper.toDto(any(Category.class))).thenReturn(categoryDto);
//
//        CategoryDto result = service.getCategory(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(categoryDto, result);
//    }
//    @Test
//    public void CategoryService_GetCategoryEntity_ReturnCategoryEntity(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(category));
//
//        Category result = service.getCategoryEntity(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(category, result);
//    }
//    @Test
//    public void CategoryService_GetAllCategorys_ReturnCategoryDtoList(){
//        Page<Category> categorys = mock(Page.class);
//        List<CategoryDto> categoryDtoList = List.of(categoryDto);
//
//        when(repository.findAll(any(Pageable.class))).thenReturn(categorys);
//        when(mapper.toDtoList(anyList())).thenReturn(categoryDtoList);
//
//        List<CategoryDto> result = service.getAllCategories(10,0);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//        assertThat(result).hasSizeGreaterThan(0);
//        assertEquals(categoryDtoList, result);
//    }
//    @Test
//    public void CategoryService_PatchCategory_ReturnUpdatedCategoryDto(){
//        UpdateCategoryDto upd = UpdateCategoryDto.builder()
//                .name("Comedy")
//                .build();
//        Category updatedCategory = Category.builder()
//                .id(1L)
//                .name("Comedy")
//                .build();
//        categoryDto.setName("Comedy");
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(category));
//        when(repository.save(any(Category.class))).thenReturn(category);
//        when(mapper.toDto(any(Category.class))).thenReturn(categoryDto);
//        when(mapper.updateCategory(any(Category.class), any(UpdateCategoryDto.class))).thenReturn(updatedCategory);
//
//        CategoryDto result = service.patchCategory(upd, anyLong());
//
//        assertThat(result).isNotNull();
//        assertThat(result).hasNoNullFieldsOrProperties();
//        assertEquals("Comedy", result.getName());
//    }
//    @Test
//    public void CategoryService_DeleteCategory_CategoryDeleted(){
//        assertAll(()->service.deleteCategory(anyLong()));
//    }
//
//}
