package br.senac.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.senac.backend.handler.HandlerLogin;
import br.senac.backend.model.Token;
import br.senac.backend.model.User;
import br.senac.backend.request.LoginRequest;
import br.senac.backend.response.LoginResponse;
import br.senac.backend.response.ResponseAPI;
import br.senac.backend.service.TokenService;
import br.senac.backend.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private HandlerLogin handlerLogin;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/logon", method = RequestMethod.POST)
	public ResponseEntity<ResponseAPI> login(@RequestBody LoginRequest loginRequest) {

		LoginResponse login = new LoginResponse();
		ResponseAPI responseAPI = new ResponseAPI();

		try {
			User user = userService.getByLoginPassword(loginRequest.getEmail(), loginRequest.getPassword());
			if (user != null) {
				Token t = tokenService.getNewTokenPersisted(user);
				login.setEmail(user.getEmail());
				login.setToken(t.getToken());
				login.setUserGuid(user.getGuid());
				login.setUserName(user.getName());
				handlerLogin.handleLoginMessages(responseAPI, 200, login);
				return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.OK);
			}

			handlerLogin.handleLoginMessages(responseAPI, 401, login);
			return new ResponseEntity<ResponseAPI>(responseAPI, HttpStatus.UNAUTHORIZED);

		} catch (Exception ex) {
			ex.printStackTrace();
			handlerLogin.handleLoginMessages(responseAPI, 400, null);
			return new ResponseEntity<ResponseAPI>(HttpStatus.UNAUTHORIZED);
		}
	}
}