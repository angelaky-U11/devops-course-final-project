package com.telerik

import com.telerik.jpa.Course
import com.telerik.jpa.CourseRepository
import com.telerik.service.CourseService
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath


@AutoConfigureMockMvc
//@ContextConfiguration(classes = [UserController, UserService])
//@WebMvcTest
@SpringBootTest
class CourseControllerIT {

  @Autowired
  MockMvc mockMvc

  @Autowired
  CourseRepository courseRepository

  @Autowired
  CourseService courseService

  Course course

  @BeforeEach
  void setUp() {
    course = new Course(title: 'Telerik DevOps', presenter: 'Dido')


    course = courseRepository.save(course)
  }


  @AfterEach
  void cleanUp() {
    courseRepository.deleteById(course.id)
  }

  @Test
  void userById() {
    mockMvc.perform(MockMvcRequestBuilders.get("/course/${course.id}")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath('$.title', CoreMatchers.is(course.title)))
      .andExpect(jsonPath('$.presenter', CoreMatchers.is(course.presenter)))
  }

  @Test
  void failure() {
    assert true
  }

}
