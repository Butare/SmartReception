package com.smartreception.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartreception.entity.User;

public class TestUtils {
  

  public static final MediaType APPLICATION_JSON_UTF8 = 
      new MediaType(MediaType.APPLICATION_JSON.getSubtype(), 
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
  
  public static String convertObjectToJsonBytes(Object object) throws IOException {
    //ObjectMapper objMapper = new ObjectMapper();
    //objMapper.setSerializationInclusion(Include.NON_NULL);
    return new ObjectMapper().writeValueAsString(object);
    
  }
  
  public static User createUser() {
    return createUser("user-001", "co-graph");
  }
  
  /**
   * To create {@link User}
   * @param userId
   * @param companyName
   * @return new {@link User}
   */
  public static User createUser(String userId, String companyName) {
    User user = new User();
    user.setUserId(userId);
    user.setCompanyName(companyName);
    return user;
  }
}
