//package service;
//
//import com.MyProject.dto.DirectorDto;
//import com.MyProject.dto.NewDirector;
//import com.MyProject.dto.UpdateDirectorDto;
//import com.MyProject.mapper.DirectorMapper;
//import com.MyProject.model.Director;
//import com.MyProject.repository.DirectorRepository;
//import com.MyProject.service.DirectorService;
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
//public class DirectorServiceTest {
//    @Mock
//    private DirectorRepository repository;
//    @Mock
//    private DirectorMapper mapper;
//
//    @InjectMocks
//    private DirectorService service;
//
//    private Director director;
//    private DirectorDto directorDto;
//    @BeforeEach
//    public void init(){
//        director = Director.builder()
//                .id(1L)
//                .name("Kyle T")
//                .build();
//
//        directorDto = DirectorDto.builder()
//                .id(1L)
//                .name("Kyle T")
//                .build();
//    }
//
//    @Test
//    public void DirectorService_AddDirector_ReturnSavedDirector(){
//        NewDirector newDirector = NewDirector.builder()
//                .name("Horror")
//                .build();
//
//        when(repository.save(any(Director.class))).thenReturn(director);
//        when(mapper.toEntityFromNewDirector(any(NewDirector.class))).thenReturn(director);
//        when(mapper.toDto(any(Director.class))).thenReturn(directorDto);
//
//        DirectorDto result = service.addDirector(newDirector);
//
//        assertThat(result).isNotNull();
//        assertEquals(directorDto, result);
//
//    }
//    @Test
//    public void DirectorService_GetDirector_ReturnFoundDirectorDto(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(director));
//        when(mapper.toDto(any(Director.class))).thenReturn(directorDto);
//
//        DirectorDto result = service.getDirector(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(directorDto, result);
//    }
//    @Test
//    public void DirectorService_GetDirectorEntity_ReturnDirectorEntity(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(director));
//
//        Director result = service.getDirectorEntity(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(director, result);
//    }
//    @Test
//    public void DirectorService_GetAllDirectors_ReturnDirectorDtoList(){
//        Page<Director> directors = mock(Page.class);
//        List<DirectorDto> directorDtoList = List.of(directorDto);
//
//        when(repository.findAll(any(Pageable.class))).thenReturn(directors);
//        when(mapper.toDtoList(anyList())).thenReturn(directorDtoList);
//
//        List<DirectorDto> result = service.getAllDirectors(10,0);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//        assertThat(result).hasSizeGreaterThan(0);
//        assertEquals(directorDtoList, result);
//    }
//    @Test
//    public void DirectorService_PatchDirector_ReturnUpdatedDirectorDto(){
//        UpdateDirectorDto upd = UpdateDirectorDto.builder()
//                .name("Tom K")
//                .build();
//        Director updatedDirector = Director.builder()
//                .id(1L)
//                .name("Tom K")
//                .build();
//        directorDto.setName("Tom K");
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(director));
//        when(repository.save(any(Director.class))).thenReturn(director);
//        when(mapper.toDto(any(Director.class))).thenReturn(directorDto);
//        when(mapper.updateDirector(any(Director.class), any(UpdateDirectorDto.class))).thenReturn(updatedDirector);
//
//        DirectorDto result = service.patchDirector(upd, anyLong());
//
//        assertThat(result).isNotNull();
//        assertThat(result).hasNoNullFieldsOrProperties();
//        assertEquals("Tom K", result.getName());
//    }
//    @Test
//    public void DirectorService_DeleteDirector_DirectorDeleted(){
//        assertAll(()->service.deleteDirector(anyLong()));
//    }
//
//}
