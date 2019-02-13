package com.smartreception.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.smartreception.dao.VisitorDao;
import com.smartreception.entity.Visitor;
import com.smartreception.service.VisitorService;
import com.smartreception.util.TestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VisitorControllerTest {
  
  @InjectMocks
  private VisitorController visitorController;
  
  @Mock
  private VisitorService visitorService;
  
  @Mock
  private VisitorDao visitorDao;
  
  private MockMvc mockMvc;
  
  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(visitorController).build();
  }
  
  @Test
  public void testGetURIShouldReturnStatusOK() throws Exception {
    mockMvc.perform(get("/visitors")).andExpect(status().isOk());
  }
  
  @Test
  public void testGetAllVisitorsShouldReturnVisitorsList() throws Exception {
    // assert
    assertTrue(visitorController.getAllVisitors() instanceof List<?>);
    // verify
    verify(visitorService).getAll();
  }
  
  @Test
  public void testInsertShouldReturnInsertedVisitor() throws Exception {
    // data
    Visitor newFakeVisitor = createVisitor();
    newFakeVisitor.setEmail("visitor@co-graph.com");
    Visitor insertedVisitor = visitorController.insert(newFakeVisitor);
    // assert
    assertEquals(newFakeVisitor, insertedVisitor);
    assertTrue(insertedVisitor.getEmail().equals("visitor@co-graph.com"));
    // verify
    verify(visitorService).insert(newFakeVisitor);
  }
  
  @Test
  public void testPostURIShouldReturnStatusCreated() throws Exception {
    Visitor fakeVisitor = createVisitor();
    mockMvc.perform(post("/visitors")
        .contentType(MediaType.APPLICATION_JSON)
        .content(TestUtils.convertObjectToStringBytes(fakeVisitor)))
        .andExpect(status().isCreated());
    // verify
    verify(visitorService).insert(fakeVisitor);
  }
  
  private Visitor createVisitor() {
    return createVisitor(0L);
  }
  
  private Visitor createVisitor(long id) {
    Visitor visitor = new Visitor();
    visitor.setId(id);
    return visitor;
  }
}