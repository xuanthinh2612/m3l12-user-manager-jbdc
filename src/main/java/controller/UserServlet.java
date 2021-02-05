package controller;

import model.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                showUserList(request, response);
                break;
            case "update":
                showUpdate(request, response);
                break;
            case "create":
                showCreate(request,response);
                break;
            case "delete":
                showDelete(request,response);
                break;
            case "search":
                showFindResult(request,response);
                break;
            case "sort":
                showSortedList(request,response);
                break;
        }
    }

    private void showSortedList(HttpServletRequest request, HttpServletResponse response)  {
        try {
            List<User> userList = userService.findAll();
            Collections.sort(userList, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    if (o1.getName().charAt(0)>o2.getName().charAt(0)){
                        return 1;
                    } else if (o1.getName().charAt(0)<o2.getName().charAt(0)) {
                        return -1;
                    } else {
                        return 0;

                    }
                }
            });
            request.setAttribute("userList",userList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/userList.jsp");
            requestDispatcher.forward(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showFindResult(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String country = request.getParameter("country");
        List<User> userList =  userService.findByCountry(country);
        if (userList==null){
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/notFound");
            requestDispatcher.forward(request,response);
        } else {
            request.setAttribute("userList",userList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/searchResult.jsp");
            requestDispatcher.forward(request,response);
        }
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findByIdy(id);
        request.setAttribute("user",user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/delete.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/create.jsp");
        requestDispatcher.forward(request,response);
    }

    private void showUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = userService.findByIdy(id);
        RequestDispatcher requestDispatcher = null;
        if (user==null){
             requestDispatcher = request.getRequestDispatcher("notFound.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("user", user);
             requestDispatcher = request.getRequestDispatcher("user/update.jsp");
            requestDispatcher.forward(request, response);

        }
    }

    private void showUserList(HttpServletRequest request, HttpServletResponse response) {
        List<User> userList = null;
        try {
            userList = userService.findAll();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("userList", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/userList.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "update":
                update(request, response);
                break;
            case "create":
                create(request,response);
                break;
            case "delete":
                delete(request,response);
                break;

        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        userService.delete(id);
        response.sendRedirect("/users");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = (int) Math.random()*1000000;
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User  user = new User(id,name,email,country);

        userService.create(user);
        response.sendRedirect("/users");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");

        User user = new User(id, name, email, country);
        userService.update(id, user);
        response.sendRedirect("/users");
    }

}

