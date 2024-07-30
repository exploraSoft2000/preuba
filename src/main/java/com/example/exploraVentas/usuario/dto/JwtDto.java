package com.example.exploraVentas.usuario.dto;

import com.example.exploraVentas.usuario.entity.Usuario;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.Base64URL;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtDto {
	@Value("${jwt.secret}")
	private String secret;
	
	private String token;
    private String bearer = "Bearer";
    private RSAKey publicKey; // Clave pública para cifrado
    private RSAKey privateKey; // Clave privada para descifrado

    public JwtDto() {
		super();
	}

	public JwtDto(String token, RSAKey publicKey, RSAKey privateKey) {
        this.token = token;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
    
    
    
 // Método para cifrar el token JWT
    public String encryptToken() throws JOSEException {
        // Crear el payload del JWT
        Payload payload = new Payload(token);

        // Crear el encabezado JWE con RSA_OAEP_256
        JWEHeader header = new JWEHeader(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM);

        // Crear el objeto JWE
        JWEObject jweObject = new JWEObject(header, payload);

        // Cifrar el JWT
        jweObject.encrypt(new RSAEncrypter(publicKey));

        // Retornar el JWT cifrado como una cadena
        return jweObject.serialize();
    }
   /* // Método para descifrar el token JWT
    public String decryptToken(String encryptedToken) throws JOSEException, ParseException {
        JWEObject jweObject = JWEObject.parse(encryptedToken);
        jweObject.decrypt(new RSADecrypter(privateKey));
        return jweObject.getPayload().toString();
    }*/
 // Método para descifrar el token JWT
    public String decryptToken(String encryptedToken) throws JOSEException, ParseException {
        // Parsear el token cifrado
        JWEObject jweObject = JWEObject.parse(encryptedToken);

        // Verifica el encabezado del JWE
        JWEHeader header = jweObject.getHeader();
        if (!header.getAlgorithm().equals(JWEAlgorithm.RSA_OAEP_256) || !header.getEncryptionMethod().equals(EncryptionMethod.A256GCM)) {
            throw new JOSEException("Algoritmo o método de cifrado incorrecto");
        }

        // Descifrar el JWT
        jweObject.decrypt(new RSADecrypter(privateKey));

        // Retornar el payload del JWT como una cadena
        return jweObject.getPayload().toString();
    }
    
   
}
