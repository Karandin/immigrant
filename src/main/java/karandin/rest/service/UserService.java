package karandin.rest.service;

import karandin.rest.entity.UserEntity;
import karandin.rest.exception.UserAlreadyExistException;
import karandin.rest.exception.UserNotFoundException;
import karandin.rest.model.User;
import karandin.rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User getOneUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }
        return User.toModel(user);
    }
    public UserEntity registrationUser(UserEntity user) throws UserAlreadyExistException {
        System.out.println("tut");
        if (userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("Пользователь с таким email уже существует");
        }
        return userRepo.save(user);
    }

    public Long deleteUser(Long id){
        userRepo.deleteById(id);
        return id;
    }

    /*public UserEntity updateUser(UserEntity user) {

    }


     */
}
