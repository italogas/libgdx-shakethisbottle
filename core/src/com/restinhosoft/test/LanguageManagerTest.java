package com.restinhosoft.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.restinhosoft.options.LanguageManager;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LanguageManagerTest {
	
	LanguageManager manager;
	
	@Rule
	public ExpectedException thrown  = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		manager = LanguageManager.getInstance();
	}
	
	@Test
	public void creationTest() {
		assertNotNull(manager.loadLanguage());
		assertTrue(manager.started);
	}
	
	@Test
	public void saveLanguageTest() {
		manager.saveLanguage();
		FileHandle local = Gdx.files.local(manager.languageFile);
		assertNotNull(local.readString());
		
		try {
			manager.setLanguage(manager.languageEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(local.readString());
		assertEquals(manager.languageEN, local.readString());
		
		try {
			manager.setLanguage(manager.languagePT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		assertNotNull(local.readString());
		assertEquals(manager.languagePT, local.readString());
	}

	@Test
	public void getLanguageTest() {
		try {
			assertNotNull(manager.getLanguage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getDefaultLanguageTest() {
		assertNotNull(manager.getDefaultLanguage());
	}
	
	@Test 
	public void setLanguageTest1() throws Exception{
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Language selected not suported ");
		manager.setLanguage("");
	}
	
	@Test 
	public void setLanguageTest2() throws Exception{
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Language selected not suported ");
		manager.setLanguage(null);
	}
	
	@Test 
	public void setLanguageTest3() throws Exception{
		thrown.expect(InvalidParameterException.class);
		thrown.expectMessage("Language selected not suported ");
		manager.setLanguage("ptpt");
	}
	
}