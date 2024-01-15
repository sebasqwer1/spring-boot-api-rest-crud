package com.demo.demo.Features.Param.User.Services;

import com.demo.demo.Entities.CityModel;
import com.demo.demo.Entities.UserModel;
import com.demo.demo.Features.Param.User.Models.*;
import com.demo.demo.Features.Param.User.Repositories.IUserRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;

@Service
public class UserService {
    @Autowired
    IUserRepository userRepository;
    @Autowired
    private UserMapperService userMapperService;
    public ArrayList<GetUserResponseAll> getUsers(String nombre, String apellido){

        Specification<UserModel> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (nombre != null && !nombre.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firsName")), "%" + nombre.toLowerCase() + "%"));            }
            if (apellido != null && !apellido.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + apellido.toLowerCase() + "%"));            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        List<UserModel> listUsers = userRepository.findAll(spec);

        ArrayList<GetUserResponseAll> userDTOList = new ArrayList<>();

        listUsers.forEach(user -> {
            GetUserResponseAll userDTO = userMapperService.GetUserResponseAllToDTO(user);
            userDTOList.add(userDTO);
        });

        return userDTOList;
    }

    public SaveUserResponse saveUser(UserModel user){
        UserModel savedUser = userRepository.save(user);
        return userMapperService.SaveUserResponseToDTO(savedUser);
    }

    public GetUserResponseById getUserById(Long id){
        Optional<UserModel> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            return userMapperService.GetUserResponseByIdToDTO(user);
        }
        return null;
    }


    public UpdateUserResponse updateUserById(UpdateUserRequest request, Long id ){
        UserModel user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (request.getFirsName() != null) {
            user.setFirsName(request.getFirsName());
        }

        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }

        if (request.getCiudad() != null){
            CityModel cityModel = new CityModel();
            cityModel.setId(request.getCiudad());
            user.setCityId(cityModel);
        }

        userRepository.save(user);

        return userMapperService.UpdateUserResponseToDTO(user);
    }

    public Boolean deleteUserById (Long id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
