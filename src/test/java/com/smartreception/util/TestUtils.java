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
  
  public static String convertObjectToStringBytes(Object object) throws IOException {
    return new ObjectMapper().writeValueAsString(object);
    
  }
  
  public static User createUser() {
    return createUser(0L);
  }
  
  public static User createUser(long id) {
    return createUser(id, "user-001", "reception-test");
  }
  
  /**
   * To create {@link User}
   * @param userId
   * @param organizationName
   * @return new {@link User}
   */
  public static User createUser(long id, String userId, String organizationName) {
    User user = new User();
    user.setId(id);
    user.setUserId(userId);
    user.setOrganizationName(organizationName);
    return user;
  }
}
