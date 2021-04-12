package com.vgroup.assesment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
 
@Component
public class ProjectDetails {
 

    @Autowired
    private Environment env;
   
    private String dbUrl;

	public String getDbUrl() {
		System.out.println(env.getProperty("db.url"));
		return env.getProperty("db.url");
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
 
 
}