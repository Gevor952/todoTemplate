package am.itspace.todotemplate.servlet;




import am.itspace.todotemplate.model.ToDo;
import am.itspace.todotemplate.model.User;
import am.itspace.todotemplate.service.ToDoService;
import am.itspace.todotemplate.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    UserService userService = new UserService();
    ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.grtByEmailAndPassword(req.getParameter("email"), req.getParameter("password"));
        if (user != null) {
            List<ToDo> toDos = toDoService.getAllToDoByUserId(user.getId());
            System.out.println(toDos);
            req.setAttribute("toDos", toDos);
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
        }else {
            req.setAttribute("msg", "Invalid email or password");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        }
    }
}
