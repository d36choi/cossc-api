package com.api.cossc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
@Table(name = "USER_QUIZ")
public class UserQuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_quiz_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private QuizEntity quizEntity;


//    @Entity
//class CourseRegistration {
//
//    @Id
//    Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    Student student;
//
//    @ManyToOne
//    @JoinColumn(name = "course_id")
//    Course course;
//
//    LocalDateTime registeredAt;
//
//    int grade;
//
//    // additional properties
//    // standard constructors, getters, and setters
//}
}
