package br.com.biblioteca.utils;

import org.mindrot.jbcrypt.BCrypt;

public class CryptUtil {

	public static String criptografarSenha(String senha) {
		return BCrypt.hashpw(senha, BCrypt.gensalt());
	}
	
	public Boolean checkPass(String senhaTexto, String senhaCriptografada) {
		if (BCrypt.checkpw(senhaTexto, senhaCriptografada)) {
			return true;
		} else {
			return false;
		}
	}
}
