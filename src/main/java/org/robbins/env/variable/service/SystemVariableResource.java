
package org.robbins.env.variable.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.robbins.env.variable.model.SystemVariable;

@Path("/")
public class SystemVariableResource
{
    @GET
    @Path("/environment-variables")
    @Produces("application/xml")
    public List<SystemVariable> getEnvironmentVariables() {
        List<SystemVariable> envVars = new ArrayList<>();
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            SystemVariable envVar = new SystemVariable();
            envVar.setName(envName);
            envVar.setValie(env.get(envName));
            envVars.add(envVar);
        }
        return envVars;
    }

    @GET
    @Path("/system-properties")
    @Produces("application/xml")
    public List<SystemVariable> getSystemProperties() {
        List<SystemVariable> systemProperties = new ArrayList<>();
        Properties props = System.getProperties();
        Enumeration e = props.propertyNames();
        while (e.hasMoreElements()) {
            SystemVariable systemProperty = new SystemVariable();
            String key = (String) e.nextElement();
            systemProperty.setName(key);
            systemProperty.setValie(props.getProperty(key));
            systemProperties.add(systemProperty);
        }
        return systemProperties;
    }
}
