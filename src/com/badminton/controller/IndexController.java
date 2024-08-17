package com.badminton.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.badminton.containers.Container;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.badminton.containers.Configurations;

import net.sf.json.JSONObject;

@Controller
public class IndexController 
{
	public static Configurations session_Configurations;
	public static String select_formate,session_selected_ip;
	
	@RequestMapping(value = {"/","/initialise"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String initialisePage(ModelMap model) throws JAXBException, IOException  
	{
		model.addAttribute("session_Configurations",session_Configurations);
		return "initialise";
	}

	@RequestMapping(value = {"/output"}, method={RequestMethod.GET,RequestMethod.POST}) 
	public String loggerPage(ModelMap model, MultipartHttpServletRequest request,
			@RequestParam(value = "select_formate", required = false, defaultValue = "") String format,
			@RequestParam(value = "previewIpAddress", required = false, defaultValue = "") String vizIPAddress)
			throws UnknownHostException,JAXBException, IOException,IllegalAccessException,InvocationTargetException, InterruptedException
	{
		session_selected_ip = vizIPAddress;
		select_formate = format;
		
		session_Configurations = new Configurations(session_selected_ip, select_formate);
			JAXBContext.newInstance(Configurations.class).createMarshaller().marshal(session_Configurations, 
					new File("C:/Sports/Preview/Configurations/preview.xml"));

		model.addAttribute("select_formate", select_formate);
		model.addAttribute("session_selected_ip", session_selected_ip);
		return "output";
	}

	@RequestMapping(value = {"/processBadmintonProcedures"}, method={RequestMethod.GET,RequestMethod.POST})    
	public @ResponseBody String processBadmintonProcedures(
			@RequestParam(value = "whatToProcess", required = false, defaultValue = "") String whatToProcess,
			@RequestParam(value = "valueToProcess", required = false, defaultValue = "") String valueToProcess)
					throws IOException, IllegalAccessException, InvocationTargetException, JAXBException, InterruptedException
	{	
		switch (whatToProcess.toUpperCase()) {
		case "GET-CONFIG-DATA":
			return JSONObject.fromObject(session_Configurations).toString();
		case "READ-PREVIEW":
			Container this_container = new Container(); 
			if(session_Configurations.getPreviewIpAddress().equalsIgnoreCase("localhost")) {
				this_container.setFile_data(Files.readAllBytes(Paths.get("C:\\Temp\\Preview."+session_Configurations.getFormat().toLowerCase())));
			}else {
				this_container.setFile_data(Files.readAllBytes(Paths.get("//"+session_Configurations.getPreviewIpAddress()+"//c//Temp//Preview."+session_Configurations.getFormat().toLowerCase())));
			}
			this_container.setContent_type("image/"+session_Configurations.getFormat().toUpperCase());
			return JSONObject.fromObject(this_container).toString();
		default:
			return JSONObject.fromObject(null).toString();
		}
	}
	
}