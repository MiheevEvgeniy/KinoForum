//package service;
//
//import com.MyProject.dto.NewUser;
//import com.MyProject.dto.UpdateUserDto;
//import com.MyProject.dto.UserDto;
//import com.MyProject.mapper.UserMapper;
//import com.MyProject.model.User;
//import com.MyProject.repository.UserRepository;
//import com.MyProject.service.UserService;
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
//public class UserServiceTest {
//    @Mock
//    private UserRepository repository;
//    @Mock
//    private UserMapper mapper;
//
//    @InjectMocks
//    private UserService service;
//
//    private User user;
//    private UserDto userDto;
//    @BeforeEach
//    public void init(){
//        user = User.builder()
//                .id(1L)
//                .name("Sam")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//
//        userDto = UserDto.builder()
//                .id(1L)
//                .name("Sam")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//    }
//
//    @Test
//    public void UserService_AddUser_ReturnSavedUser(){
//        NewUser newUser = NewUser.builder()
//                .name("Sam")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//
//        when(repository.save(any(User.class))).thenReturn(user);
//        when(mapper.toEntityFromNewUser(any(NewUser.class))).thenReturn(user);
//        when(mapper.toDto(any(User.class))).thenReturn(userDto);
//
//        UserDto result = service.addUser(newUser);
//
//        assertThat(result).isNotNull();
//        assertEquals(userDto, result);
//
//    }
//    @Test
//    public void UserService_GetUser_ReturnFoundUserDto(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
//        when(mapper.toDto(any(User.class))).thenReturn(userDto);
//
//        UserDto result = service.getUser(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(userDto, result);
//    }
//    @Test
//    public void UserService_GetUserEntity_ReturnUserEntity(){
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
//
//        User result = service.getUserEntity(anyLong());
//
//        assertThat(result).isNotNull();
//        assertEquals(user, result);
//    }
//    @Test
//    public void UserService_GetAllUsers_ReturnUserDtoList(){
//        Page<User> users = mock(Page.class);
//        List<UserDto> userDtoList = List.of(userDto);
//
//        when(repository.findAll(any(Pageable.class))).thenReturn(users);
//        when(mapper.toDtoList(anyList())).thenReturn(userDtoList);
//
//        List<UserDto> result = service.getAllUsers(10,0);
//
//        assertThat(result).isNotNull();
//        assertThat(result).isNotEmpty();
//        assertThat(result).hasSizeGreaterThan(0);
//        assertEquals(userDtoList, result);
//    }
//    @Test
//    public void UserService_PatchUser_ReturnUpdatedUserDto(){
//        UpdateUserDto upd = UpdateUserDto.builder()
//                .name("John")
//                .build();
//        User updatedUser = User.builder()
//                .id(1L)
//                .name("John")
//                .email("sam@gmail.com")
//                .country("UK")
//                .build();
//        userDto.setName("John");
//
//        when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(user));
//        when(repository.save(any(User.class))).thenReturn(user);
//        when(mapper.toDto(any(User.class))).thenReturn(userDto);
//        when(mapper.updateUser(any(User.class), any(UpdateUserDto.class))).thenReturn(updatedUser);
//
//        UserDto result = service.patchUser(upd, anyLong());
//
//        assertThat(result).isNotNull();
//        assertThat(result).hasNoNullFieldsOrProperties();
//        assertEquals("John", result.getName());
//    }
//    @Test
//    public void UserService_DeleteUser_UserDeleted(){
//        assertAll(()->service.deleteUser(anyLong()));
//    }
//
//}
