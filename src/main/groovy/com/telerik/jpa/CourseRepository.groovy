package com.telerik.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository extends CrudRepository<Course, Long> {

  Course getCourseById(Long id)
  Course getCourseByTitle(String title)
}
