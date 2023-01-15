package com.example.studentsinformationmanagementclient.request;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
public class HttpRequestUtil {
    private static Gson gson = new Gson();
    private static HttpClient client = HttpClient.newHttpClient();
    public static String serverUrl = "http://localhost:8080";

    public static String login(LoginRequest request){//发送登录请求

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/users/login"))//http://localhost:8080/user/login
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "登陆成功";
            }else if(response.statusCode() == 401){
                return "用户名或密码不存在！";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "登录失败";
    }
    public static String register(RegisterRequest request){//发送注册请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/users/register"))//http://localhost:8080/user/register
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "注册成功";
            }else if(response.statusCode() == 401){
                return "用户名已存在！";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "注册失败";
    }

    public static String insertStudent(InsertStudentRequest request){//发送添加学生请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/students/add"))//http://localhost:8080/students/add
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "添加成功";
            }else{
                return "此学号已存在！";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "添加失败";
    }

    public static String eraseStudent(EraseStudentRequest request){//发送删除学生请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/students/deleteByNumber/" + request.number))//http://localhost:8080/students//deleteByNumber/number
                .DELETE()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "删除成功";
            }else if(response.statusCode() == 401){
                return "删除失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "删除失败";
    }

    public static String modifyStudent(ModifyStudentRequest request){//发送修改学生请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/students/modify"))//http://localhost:8080/students/modify
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "修改成功";
            }else if(response.statusCode() == 401){
                return "修改失败";//不可能修改失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "修改失败";
    }
    public static String queryStudent(){//发送查询所有学生请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/students/all"))//http://localhost:8080/students/all
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();//把所有学生信息以json的格式写到body里
            }else if(response.statusCode() == 401){
                return "查询失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }

    public static String insertTeacher(InsertTeacherRequest request){//发送添加老师请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/teachers/add"))//http://localhost:8080/teachers/add
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "添加成功";
            }else if(response.statusCode() == 401){
                return "此学工号已存在！";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "添加失败";
    }

    public static String eraseTeacher(EraseTeacherRequest request){//发送删除老师请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/teachers/deleteByNumber/"+request.number))//http://localhost:8080/user/eraseTeacher
                .DELETE()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "删除成功";
            }else if(response.statusCode() == 401){
                return "删除失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "删除失败";
    }

    public static String modifyTeacher(ModifyTeacherRequest request){//发送修改老师请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/teachers/modify"))//http://localhost:8080/teachers/modify
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "修改成功";
            }else if(response.statusCode() == 401){
                return "修改失败";//不可能修改失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "修改失败";
    }
    public static String queryTeacher(){//发送查询所有老师请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/teachers/all"))//http://localhost:8080/user/queryTeacher
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();//把所有老师信息以json的格式写到body里
            }else if(response.statusCode() == 401){
                return "查询失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }
    public static String insertCourse(InsertCourseRequest request){//发送添加课程请求
        System.out.println(request.getTeacherNumber());
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/courses/add"))//http://localhost:8080/courses/add
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "添加成功";
            }else if(response.statusCode() == 401){
                return "此学工号已存在！";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "添加失败";
    }

    public static String eraseCourse(EraseCourseRequest request){//发送删除课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/courses/deleteByNumber/" + request.number))//http://localhost:8080/user/eraseCourse
                .DELETE()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "删除成功";
            }else if(response.statusCode() == 401){
                return "删除失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "删除失败";
    }

    public static String modifyCourse(ModifyCourseRequest request){//发送修改课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/courses/modify"))//http://localhost:8080/users/modify
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "修改成功";
            }else if(response.statusCode() == 401){
                return "修改失败";//不可能修改失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "修改失败";
    }
    public static String queryCourse(){//发送查询所有课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/courses/all"))//http://localhost:8080/courses/all
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();//把所有老师信息以json的格式写到body里
            }else if(response.statusCode() == 401){
                return "查询失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }

    public static String modifyUser(ModifyUserRequest request) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/users/modify"))//http://localhost:8080/users/modify
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "修改成功";
            }else if(response.statusCode() == 401){
                return "修改失败";//不可能修改失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "修改失败";
    }

    public static String queryUser(QueryUserRequest request) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/users/getByUsername/" + request.username))//http://localhost:8080/users/modify
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();
            }else if(response.statusCode() == 401){
                return "查询失败";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }

    public static String queryAStudent(QueryStudentRequest request) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/students/findByNumber/" + request.getNumber()))//http://localhost:8080/users/modify
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();
            }else if(response.statusCode() == 401){
                return "查询失败";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }
    public static String queryACourse(QueryCourseRequest request) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/courses/findByNumber/" + request.getNumber()))//http://localhost:8080/users/modify
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();
            }else if(response.statusCode() == 401){
                return "查询失败";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }
    public static String queryStudentScore(String number) {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/scores/findByStudentNumber/" + number))//http://localhost:8080/users/modify
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();
            }else if(response.statusCode() == 401){
                return "查询失败";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }
    public static String eraseStudentScore(String studentNumber,String courseNumber){//发送删除课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/scores/deleteByStudentNumberAndCourseNumber/" + studentNumber + "&"+ courseNumber))//http://localhost:8080/user/eraseCourse
                .DELETE()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "删除成功";
            }else if(response.statusCode() == 401){
                return "删除失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "删除失败";
    }
    public static String queryTeacherCourse(String teacherNumber){//发送删除课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/courses/findByTeacherNumber/" + teacherNumber))//http://localhost:8080/user/eraseCourse
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();
            }else if(response.statusCode() == 401){
                return "查询失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }
    public static String queryCourseStudent(String courseNumber){//发送删除课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/scores/findByCourseNumber/" + courseNumber))//http://localhost:8080/user/eraseCourse
                .GET()
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return response.body();
            }else if(response.statusCode() == 401){
                return "查询失败";//其实不可能删除失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }

    public static String insertScore(InsertScoreRequest request){//发送添加课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/scores/add"))//http://localhost:8080/courses/add
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "添加成功";
            }else if(response.statusCode() == 401){
                return "此成绩已存在！";
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "添加失败";
    }

    public static String modifyScore(ModifyScoreRequest request){//发送修改课程请求
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(serverUrl+"/scores/modify"))//http://localhost:8080/users/modify
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(request)))
                .headers("Content-Type", "application/json")
                .build();
        try {
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            if(response.statusCode() == 200) {
                return "修改成功";
            }else if(response.statusCode() == 401){
                return "修改失败";//不可能修改失败
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "修改失败";
    }
}