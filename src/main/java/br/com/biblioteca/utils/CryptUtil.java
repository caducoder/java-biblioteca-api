package br.com.biblioteca.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CryptUtil {

	public static String criptografarSenha(String senha) {
		return BCrypt.hashpw(senha, BCrypt.gensalt());
	}
	
	public static Boolean checkPass(String senhaTexto, String senhaCriptografada) {
		return BCrypt.checkpw(senhaTexto, senhaCriptografada);
	}
}
