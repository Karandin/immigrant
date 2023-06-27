package karandin.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    public ResponseEntity getHome(){
        try {
            return ResponseEntity.ok("cool");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка");
        }
    }
}
