package com.api.cossc.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
