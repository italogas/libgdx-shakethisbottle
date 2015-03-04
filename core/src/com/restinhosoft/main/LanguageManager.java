package com.restinhosoft.main;

import java.security.InvalidParameterException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.restinhosoft.exception.LanguageManagerNotStartedException;

public class LanguageManager {
	
	public boolean started = false;
	
	public final String languageFile = "language.txt";
	public final String languageEN = "engl";
	public final String languagePT = "ptbr";
	
	private static LanguageManager instance = null;

	private String language;

	private String defaultLanguage = languageEN;
	
	private LanguageManager() {
		if(Gdx.files.isLocalStorageAvailable() && Gdx.files.local(languageFile) != null){
			language = loadLanguage();
		}else {
			language = defaultLanguage;	
		}
		started = true;
	}

	/**
	 * It returns the single instance of the LanguageManager.
	 * @return
	 */
	public static synchronized LanguageManager getInstance() {
		if(instance == null){
			instance = new LanguageManager();
		}
		return instance;
	}
	
	public String getLanguage() throws Exception{
		if(!started){
			throw new LanguageManagerNotStartedException();
		}
		return language;
	}
	
	public void setLanguage(String language) throws Exception{
		if(!started){
			throw new LanguageManagerNotStartedException();
		}
		if(language == null || language.equals("")){
			throw new InvalidParameterException("Language selected not suported ");
		} else if(!language.equalsIgnoreCase(languageEN) && !language.equalsIgnoreCase(languagePT)){
			throw new InvalidParameterException("Language selected not suported ");
		} else {
			this.language = language;
			saveLanguage();
		}
		return;
	}
	
	public void saveLanguage(){
		try{
			FileHandle local = Gdx.files.local(languageFile);
			local.writeString(language,false);
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
	}
	
	public String loadLanguage(){
		String readString = null;
		try {
			FileHandle local = Gdx.files.local(languageFile);
			readString = local.readString();
		} catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
		return readString;
		
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}
	
}
