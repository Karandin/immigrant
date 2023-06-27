package karandin.rest.model;

import karandin.rest.entity.UserEntity;

public class User {
    private Long id;

    private String email;

    public static User toModel(UserEntity entity) {
        System.out.println("here");
        User model = new User();
        model.setId(entity.getId());
        model.setEmail(entity.getEmail());
        return model;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setEmail(String email) { this.email = email;}

    public String getEmail() {
        return email;
    }
}
