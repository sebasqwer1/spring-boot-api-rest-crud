package com.demo.demo.Features.Param.User.Controllers;
import com.demo.demo.Entities.CityModel;
import com.demo.demo.Entities.UserModel;
import com.demo.demo.Features.Param.User.Models.*;
import com.demo.demo.Features.Param.User.Services.UserService;
import com.demo.demo.Features.Param.User.Validations.UserValidations;
import com.demo.demo.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @Tag(name = "Usuarios", description = "Controlador de usuarios")
    @Operation(summary = "Obtender todos los usuarios",
            responses = {
            @ApiResponse(responseCode = "200", description = "usuarios obtenidos exitosamente"),
            @ApiResponse(responseCode = "400", description = "no hay usuarios para mostrar")
        }
    )
    public ResponseEntity<Response<ArrayList<GetUserResponseAll>>> getUsers(
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "apellido", required = false) String apellido
    ){

        ArrayList<GetUserResponseAll> result = this.userService.getUsers(nombre,apellido);
        Response<ArrayList<GetUserResponseAll>> dataResponse = new Response<>();
        if(result.isEmpty()){
            dataResponse.setStatus("info");
            dataResponse.setData(null);
            dataResponse.setMessage("no hay usuarios para mostrar");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataResponse);
        }

        dataResponse.setStatus("Ok");
        dataResponse.setData(result);
        dataResponse.setMessage("usuarios obtenidos exitosamente");
        return ResponseEntity.ok(dataResponse);
    }


    @GetMapping(path = "/{id}")
    @Tag(name = "Usuarios", description = "Controlador de usuarios")
    @Operation(
        summary = "Obtender un usuario por su id",
        responses = {
            @ApiResponse(responseCode = "200", description = "usuario obtenido correctamente"),
            @ApiResponse(responseCode = "400", description = "el usuario no existe")
        }
    )
    public ResponseEntity<Response<GetUserResponseById>> getUserById(@PathVariable Long id){

        Response<GetUserResponseById> dataResponse = new Response<>();

        GetUserResponseById response  = this.userService.getUserById(id);

        if(response == null){
            dataResponse.setStatus("info");
            dataResponse.setData(null);
            dataResponse.setMessage("el usuario no existe");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataResponse);
        }

        dataResponse.setStatus("Ok");
        dataResponse.setData(response);
        dataResponse.setMessage("usuario obtenido correctamente");
        return ResponseEntity.ok(dataResponse);
    }

    @PostMapping
    @Tag(name = "Usuarios", description = "Controlador de usuarios")
    @Operation(summary = "Registrar usuario",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public ResponseEntity<Response<SaveUserResponse>> saveUser(@Valid @RequestBody SaveUserRequest user, BindingResult bindingResult){
        Response<SaveUserResponse> dataResponse = new Response<>();

        UserModel userModel = new UserModel();
        userModel.setFirsName(user.getFirsName());
        userModel.setLastName(user.getLastName());
        userModel.setEmail(user.getEmail());

        CityModel cityModel = new CityModel();
        cityModel.setId(user.getCiudad());

        userModel.setCityId(cityModel);

        List<String> Errors = UserValidations.handleValidationErrors(bindingResult);

        if(!Errors.isEmpty()){
            dataResponse.setStatus("Error");
            dataResponse.setData(null);
            dataResponse.setMessage("" + Errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataResponse);
        }

        try {

            SaveUserResponse result = this.userService.saveUser(userModel);

            dataResponse.setStatus("Ok");
            dataResponse.setData(result);
            dataResponse.setMessage("usuario creado correctamente");

            return ResponseEntity.ok(dataResponse);
        } catch (Exception e) {
            dataResponse.setStatus("error");
            dataResponse.setData(null);
            dataResponse.setMessage("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dataResponse);
        }

    }

    @PutMapping(path = "/{id}")
    @Tag(name = "Usuarios", description = "Controlador de usuarios")
    @Operation(summary = "Modificar usuario",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public ResponseEntity<Response<UpdateUserResponse>> updateUserById(@Valid @RequestBody UpdateUserRequest request, BindingResult bindingResult, @PathVariable Long id) {

        Response<UpdateUserResponse> dataResponse = new Response<>();

        List<String> Errors = UserValidations.handleValidationErrors(bindingResult);
        System.out.println("HOLA");

        if(!Errors.isEmpty()){
            dataResponse.setStatus("Error");
            dataResponse.setData(null);
            dataResponse.setMessage("" + Errors);
            System.out.println("" + Errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dataResponse);
        }

        try {
            System.out.println(id);
            UpdateUserResponse updatedUser = this.userService.updateUserById(request, id);
            dataResponse.setStatus("Ok");
            dataResponse.setData(updatedUser);
            dataResponse.setMessage("usuario actualizado correctamente");
            return ResponseEntity.ok(dataResponse);
        } catch (Exception e) {
            dataResponse.setStatus("error");
            dataResponse.setData(null);
            dataResponse.setMessage("Error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dataResponse);
        }
    }

    @DeleteMapping(path = "/{id}")
    @Tag(name = "Usuarios", description = "Controlador de usuarios")
    @Operation(summary = "Eliminar usuario",
            responses = {@ApiResponse(responseCode = "200", description = "Operación exitosa")})
    public String deleteUserById (@PathVariable Long id){
        boolean ok = this.userService.deleteUserById(id);

        if(ok){
            return "Usuario" + id +" eliminado exitosamente";
        }else{
            return "No de puso eliminar el usuario" + id;
        }
    }
}
