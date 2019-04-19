package com.smartreception.serviceImpl;

import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartreception.dao.EmployeeDao;
import com.smartreception.entity.Employee;
import com.smartreception.exception.NotFoundException;
import com.smartreception.util.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

  @InjectMocks
  private EmployeeServiceImpl employeeServiceImpl;

  @Mock
  private EmployeeDao employeeDao;

  @Test
  public void testDeleteEmployeeShouldPassWhenNotNull() throws Exception {
    // data
    long id = 1L;
    Employee employeeToDelete = TestUtils.createEmployee(id);

    when(employeeDao.getEmployeeById(id)).thenReturn(employeeToDelete);
    employeeServiceImpl.delete(id);

    verify(employeeDao).delete(id);
  }

  @Test(expected = NotFoundException.class)
  public void testDeleteEmployeeShouldThrowWhenNull() throws Exception {
    Long id = 1L;

    when(employeeDao.getEmployeeById(id)).thenReturn(null);
    employeeServiceImpl.delete(id);
  }
}
