package com.example.exploraVentas.usuario.controller;
import java.util.Optional;

import javax.security.sasl.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exploraVentas.general.entity.Mensaje;
import com.example.exploraVentas.usuario.dto.JwtDto;
import com.example.exploraVentas.usuario.dto.KeyGeneratorUtil;
import com.example.exploraVentas.usuario.dto.LoginUsuario;
import com.example.exploraVentas.usuario.entity.Usuario;
import com.example.exploraVentas.usuario.jwt.JwtProvider;
import com.example.exploraVentas.usuario.service.UsuarioService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
	   @Autowired
	    private AuthenticationManager authenticationManager;
		@Value("${jwt.secret}")
		private String secret;
	    @Autowired
		UsuarioService usuarioService;
	    @Autowired
		JwtProvider jwtProvider;
	    @Autowired
		PasswordEncoder passwordEncoder;
	    @Autowired
	    private RSAKey rsaKey; // Clave RSA inyectada
	    @Autowired
	    private JwtDto jwtDto; // Asegúrate de que este bean esté configurado correctamente
	    @PermitAll
		@PostMapping("/nuevo")
		public ResponseEntity<?> nuevo(@Valid @RequestBody Usuario nuevoUsuario, BindingResult bindingResult) {
			try {

				nuevoUsuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
				usuarioService.save(nuevoUsuario);

				return new ResponseEntity(new Mensaje("Usuario creado"), HttpStatus.CREATED);
			} catch (Exception e) {
				
				return new ResponseEntity(new Mensaje(e.getMessage()), HttpStatus.NOT_FOUND);
			}

		}

	    
	    @PermitAll
		@PostMapping("/login")
		public ResponseEntity<?> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
			if (bindingResult.hasErrors())
				return new ResponseEntity("campos mal puestos", HttpStatus.BAD_REQUEST);
			try {
				Usuario user = usuarioService.getByNombreUsuario(loginUsuario.getNombreUsuario()).get();
				 
				if (user != null && user.getEstado().equals("1")) {
					
					
					Authentication authentication = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
									loginUsuario.getPassword()));
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String jwt = jwtProvider.generateToken(authentication);
					UserDetails userDetails = (UserDetails) authentication.getPrincipal();
					jwtDto = new JwtDto(jwt,rsaKey,rsaKey);
					// Token cifrado
					String encryptedToken = jwtDto.encryptToken();
					System.out.println("Token cifrado: " + encryptedToken);
					jwtDto.setToken(encryptedToken);
					 // Descifrar el token
		          String decryptedToken = jwtDto.decryptToken(encryptedToken);
		            System.out.println("Token descifrado: " + decryptedToken);
					return new ResponseEntity(jwtDto, HttpStatus.OK);

				} else {
					return new ResponseEntity<>(new Mensaje("Login incorrecto. Verifica tus credenciales."),
							HttpStatus.UNAUTHORIZED);
				}
			} catch (Exception e) {
				
				return new ResponseEntity<>(new Mensaje("Login incorrecto. Verifica tus credenciales."),
						HttpStatus.UNAUTHORIZED);
			}

		}
	    
	    @GetMapping("/obtenerUsuarioToken")
	    public ResponseEntity<?> obtenerIdUsuario(@RequestHeader("Authorization") String token) {
	        try {
	            if (token.startsWith("Bearer ")) {
	                token = token.substring(7);
	            }
	            
	            String tokenDescifrado = jwtDto.decryptToken(token);
	            System.out.println("Token descifrado: " + tokenDescifrado);

	            Integer idUsuario = jwtProvider.getClaims(tokenDescifrado,"idUsuario");
	            System.out.println("ID de usuario extraído: " + idUsuario);

	            if (idUsuario != null) {
	            	Usuario usuario= new Usuario();
	            	usuario= usuarioService.getByIdUsuario(idUsuario).get();
	            	return new ResponseEntity(usuario, HttpStatus.OK);
	            } else {
	                return new ResponseEntity<>(new Mensaje("No se pudo obtener el ID de usuario"), HttpStatus.BAD_REQUEST);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(new Mensaje("Error al procesar el token: " + e.getMessage()), HttpStatus.UNAUTHORIZED);
	        }
	    }
}
