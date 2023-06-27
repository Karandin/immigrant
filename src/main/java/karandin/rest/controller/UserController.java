package karandin.rest.controller;

import karandin.rest.entity.UserEntity;
import karandin.rest.exception.UserAlreadyExistException;
import karandin.rest.exception.UserNotFoundException;
import karandin.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity getOneUser(@RequestParam Long id){
        try {
            return ResponseEntity.ok(userService.getOneUser(id));
        } catch(UserNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PostMapping
    public ResponseEntity registration(@RequestBody UserEntity user){
        try {
            userService.registrationUser(user);
            return ResponseEntity.ok("Пользователь сохранён");
        }catch (UserAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }

    @PutMapping
    public ResponseEntity updateUsername(@RequestParam Long id){
        try {

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.deleteUser(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }

    }
}


