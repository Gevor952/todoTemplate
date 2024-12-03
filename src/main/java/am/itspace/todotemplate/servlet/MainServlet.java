package am.itspace.todotemplate.servlet;

import am.itspace.todotemplate.model.ToDo;
import am.itspace.todotemplate.model.User;
import am.itspace.todotemplate.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class MainServlet extends HttpServlet {

    ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("user") != null) {
            List<ToDo> toDos = toDoService.getAllToDoByUserId(((User)req.getSession().getAttribute("user")).getId());
            System.out.println(toDos);
            req.setAttribute("toDos", toDos);
        }

        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }
}
