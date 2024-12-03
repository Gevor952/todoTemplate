package am.itspace.todotemplate.servlet;

import am.itspace.todotemplate.Enum.Status;
import am.itspace.todotemplate.model.ToDo;
import am.itspace.todotemplate.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/finishToDo")
public class FinishToDoServlet extends HttpServlet {

    ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ToDo toDo = toDoService.getAllToDoById(Integer.parseInt(req.getParameter("id")));
        if(toDo.getStatus() == Status.NEW){
            toDo.setStatus(Status.DONE);
        }else{
            toDo.setStatus(Status.NEW);
        }
        toDoService.updateStatus(toDo);
        resp.sendRedirect("/home");
    }
}
