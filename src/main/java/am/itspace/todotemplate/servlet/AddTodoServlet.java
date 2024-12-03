package am.itspace.todotemplate.servlet;

import am.itspace.todotemplate.Enum.Status;
import am.itspace.todotemplate.model.ToDo;
import am.itspace.todotemplate.model.User;
import am.itspace.todotemplate.service.ToDoService;
import am.itspace.todotemplate.util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/addTodo")
public class AddTodoServlet extends HttpServlet {

    ToDoService toDoService = new ToDoService();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todoText = req.getParameter("todoText");
        String dueDate = req.getParameter("dueDate");
        Date finishD = null;
        if (dueDate.isEmpty()) {
            dueDate = null;
        }else {
            finishD = new DateUtil().jsFromJava(dueDate);
        }
        int id = ((User) req.getSession().getAttribute("user")).getId();
        toDoService.add(ToDo.builder()
                        .title(todoText)
                        .createdDateTime(new Date())
                        .finishedDateTime(finishD)
                        .userId(id)
                        .status(Status.NEW)
                .build());
        resp.sendRedirect("/home");
    }
}
