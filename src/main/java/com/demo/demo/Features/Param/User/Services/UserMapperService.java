package com.demo.demo.Features.Param.User.Services;

import com.demo.demo.Entities.UserModel;
import com.demo.demo.Features.Param.User.Models.GetUserResponseAll;
import com.demo.demo.Features.Param.User.Models.GetUserResponseById;
import com.demo.demo.Features.Param.User.Models.SaveUserResponse;
import com.demo.demo.Features.Param.User.Models.UpdateUserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserMapperService {
    private final ModelMapper modelMapper;

    public UserMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public GetUserResponseAll GetUserResponseAllToDTO(UserModel userModel) {

        GetUserResponseAll userResponse = modelMapper.map(userModel, GetUserResponseAll.class);

        userResponse.setCiudadDescripcion(userModel.getCityId().getDescription());
        userResponse.setPaisDescripcion(userModel.getCityId().getPaisId().getDescription());
        return userResponse;
    }
    public GetUserResponseById GetUserResponseByIdToDTO(UserModel userModel) {
        return modelMapper.map(userModel, GetUserResponseById.class);
    }
    public SaveUserResponse SaveUserResponseToDTO(UserModel userModel) {
        return modelMapper.map(userModel, SaveUserResponse.class);
    }
    public UpdateUserResponse UpdateUserResponseToDTO(UserModel userModel) {
        return modelMapper.map(userModel, UpdateUserResponse.class);
    }
}
