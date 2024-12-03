package am.itspace.todotemplate.servlet;

import am.itspace.todotemplate.model.ToDo;
import am.itspace.todotemplate.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editToDo")
public class editToDoServlet extends HttpServlet {

    ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo toDo = toDoService.getAllToDoById(Integer.parseInt( req.getParameter("id")));
        req.setAttribute("toDo", toDo);
        req.getRequestDispatcher("/WEB-INF/editToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        toDoService.update(id, title);
        resp.sendRedirect("/home");
    }
}
