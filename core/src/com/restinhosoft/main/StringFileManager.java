package com.restinhosoft.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class StringFileManager {
	private String folderName;
	
	public StringFileManager(){
		this.folderName = "internal/";
	}
	
	public boolean setFolder(String folderName){
		if(folderName!=null && folderName!=""){
			this.folderName = folderName+"/";
			return true;
		}return false;
	}
	
	public boolean saveFile(String fileName, String fileContent, boolean addContent){
		FileHandle local = Gdx.files.internal(folderName+fileName);
		String previousContent ="";
		if(local.exists()){
			previousContent = loadFile(folderName+fileName);
		}
		try{
			if(fileName!= null && fileName!="" && addContent){
				local.writeString(previousContent+"\n"+fileContent,false);
				return true;
			}else if(fileName!= null && fileName!="" && !addContent){
				local.writeString(fileContent,false);
				return true;
			}
		}  catch (RuntimeException re){
			System.err.println(re.getMessage());
		}
		return false;
	}
	
	public String loadFile(String fileName){
		if(fileName!= null && fileName !=""){	
			FileHandle call = Gdx.files.internal(folderName+fileName);
			if(!call.exists()){
				call.writeString("",false);
			}
			String readString = null;
			try {
				FileHandle local = Gdx.files.internal(folderName+fileName);
				readString = local.readString();
			} catch (RuntimeException re){
				System.err.println(re.getMessage());
			}
			if(readString == null) readString = "";
			return readString;	
		}return "";
	}
}
