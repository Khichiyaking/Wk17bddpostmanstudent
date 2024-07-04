package com.student.studentinfo;

import com.student.constants.EndPoints;
import com.student.model.StudentPojo;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import static org.hamcrest.Matchers.hasValue;
import java.util.HashMap;
import java.util.List;

public class StudentStep {

    @Step("Create new student")
    public ValidatableResponse createStudent(String firstName, String lastName, String email, String programme, List<String> courseList) {

       /* ArrayList<String> courses= new ArrayList<>();
        courses.add("selenium");
        courses.add("Restassured");*/   // pass it during run time

        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(studentPojo)
                .when()
                .post()
                .then();
    }

    @Step("Getting response by firstname")
    public HashMap<String, Object> getStudentByFirstname(String firstName) {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + firstName + p2);
    }

    @Step("Getting response by email")
    public HashMap<String, Object> getStudentByEmail(String email) {
        String p1 = "findAll{it.email=='";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + email + p2);
    }

    @Step("update student by email, programme")
    public ValidatableResponse updateStudent(int studentID, String firstName, String lastName, String email, String programme, List<String> courseList) {
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setFirstName(firstName);
        studentPojo.setLastName(lastName);
        studentPojo.setEmail(email);
        studentPojo.setProgramme(programme);
        studentPojo.setCourses(courseList);

        return SerenityRest.given()
                .log().all()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentID)
                .body(studentPojo)
                .when()
                .put(EndPoints.UPDATE_STUDENT_BY_ID)
                .then();
    }


    @Step("delete student by ID")
    public ValidatableResponse deleteStudent(int studentID) {
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .pathParam("studentID", studentID)
                .when()
                .delete(EndPoints.DELETE_STUDENT_BY_ID)
                .then();

    }

    @Step("get student by ID")
    public ValidatableResponse getStudentByID(int studentID) {

        return SerenityRest.given()
                .pathParam("studentID", studentID)
                .when()
                .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
                .then()
                .log().all();

    }

    @Step("getting student all information")
    public ValidatableResponse getAllStudentInfo(){
        return SerenityRest.given()
                .when()
                .get(EndPoints.GET_ALL_STUDENT)
                .then();
    }


    public HashMap<String,?> getStudentInfoByFirstName(String firstName) {
    }

    public ValidatableResponse deleteStudentInfoByID(int studentId) {
    }

    public <R> ExtractableResponse<R> getStudentInfoByStudentId(int studentId) {
    }

    public HashMap<String, Object> getStudentInfoByEmail(String email) {
    }
}
