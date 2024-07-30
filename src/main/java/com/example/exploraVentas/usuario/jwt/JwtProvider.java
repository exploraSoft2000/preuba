package com.example.exploraVentas.usuario.jwt;
import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.exploraVentas.empresa.entity.Empresa;
import com.example.exploraVentas.empresa.service.EmpresaService;
import com.example.exploraVentas.usuario.entity.Usuario;
import com.example.exploraVentas.usuario.entity.UsuarioPrincipal;
import com.example.exploraVentas.usuario.service.UsuarioService;

import java.util.Date;

@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
	
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;
    @Autowired
	EmpresaService empresaService;
    @Autowired
   	UsuarioService usuarioService;

    public String generateToken(Authentication authentication){
    	//No puedo usar la clase Usuario porque UsuarioPrincipal esta con los atributos mapeados con una clase de Authentication ya establecida
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        Usuario usuario=usuarioService.getByIdUsuario(usuarioPrincipal.getId()).get();
        Empresa empresa=obtenerEmpresaPorId(usuario.getEmpresa().getIdEmpresa());
        return Jwts.builder()
        		.setSubject(usuarioPrincipal.getUsername())
        		.claim("idUsuario", usuarioPrincipal.getId())
        		.claim("rol", usuarioPrincipal.getAuthorities())
        		.claim("idEmpresa", empresa.getIdEmpresa())
        		// nombre de la empresa
        		//.claim("nombreEmpresa", empresa.getNombreEmpresa())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    
    
    public Empresa obtenerEmpresaPorId(int idEmpresa) {
    	try {
    		Empresa empresa=empresaService.getByIdEmpresa(idEmpresa).get();
			return empresa;
		} catch (Exception e) {
			// TODO: handle exception
			 logger.error(e.getMessage());
			 return null;
		}
    }
    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("token expirado");
        }catch (IllegalArgumentException e){
            logger.error("token vacío");
        }catch (SignatureException e){
            logger.error("fail en la firma");
        }
        return false;
    }
    
    public Integer getClaims(String token,String claim) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            Integer idUsuario = claims.get(claim, Integer.class);
            System.out.println("Claims del token: " + claims);
            System.out.println("ID de usuario extraído: " + idUsuario);
            return idUsuario;
        } catch (Exception e) {
            logger.error("Error al obtener el ID de usuario del token", e);
            e.printStackTrace();
            return null;
        }
    }
}
