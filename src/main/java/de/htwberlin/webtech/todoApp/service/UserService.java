package de.htwberlin.webtech.todoApp.service;

import de.htwberlin.webtech.todoApp.entity.UserEntity;
import de.htwberlin.webtech.todoApp.repository.UserRepository;
import de.htwberlin.webtech.todoApp.web.api.UserApi;
import de.htwberlin.webtech.todoApp.web.api.UserManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserApi> findAll() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
                    .map(this :: transformEntity)
                .collect(Collectors.toList());
    }

    private UserApi transformEntity(UserEntity userEntity) {
        return new UserApi(
                userEntity.getUserId(),
                userEntity.getName(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getTodos()

        );
    }

    public UserApi findById(long id) {
        var userEntity = userRepository.findById(id);
        return userEntity.map(this :: transformEntity).orElse(null);
    }

    public UserApi create(UserManipulationRequest request) {
        var userEntity = new UserEntity(request.getName(), request.getUsername(), request.getPassword());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public UserApi update(long id, UserManipulationRequest request) {
        var userEntityOptional = userRepository.findById(id);
        if (userEntityOptional.isEmpty()) {
            return null;
        }
        var userEntity = userEntityOptional.get();
        userEntity.setName(request.getName());
        userEntity.setUsername(request.getUsername());
        userEntity.setPassword(request.getPassword());
        userEntity.setTodos(request.getTodos());
        userEntity = userRepository.save(userEntity);
        return transformEntity(userEntity);
    }

    public boolean deleteById(long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;

    }


}
