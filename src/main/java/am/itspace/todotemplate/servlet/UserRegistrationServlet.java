package am.itspace.myfriend.servlet;


import am.itspace.myfriend.model.User;
import am.itspace.myfriend.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/registration")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5, //5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1
)
public class UserRegistrationServlet extends HttpServlet {


    private final String IMG_FOLDER = "C:\\Users\\NITRO\\IdeaProjects\\My friend\\images\\";

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        StringBuilder msgBuilder = new StringBuilder();

        if (name == null || name.trim().isEmpty()) {
            msgBuilder.append("Please enter your Name");
        }

        if (surname == null || surname.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Surname");
        }
        if (email == null || email.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Email");
        }


        if (password == null || password.trim().isEmpty()) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please enter your Password");
        } else if (password.length() < 6) {
            msgBuilder.append("<br>");
            msgBuilder.append("Password must be longer than 6 characters");
        }



        Part img = req.getPart("img");
        String imgName = System.nanoTime() + "_" + img.getSubmittedFileName();
        img.write(IMG_FOLDER + imgName);

        if (img == null) {
            msgBuilder.append("<br>");
            msgBuilder.append("Please select an image");
        }


        if (userService.grtByEmail(email) != null) {
            msgBuilder.append("<br>");
            msgBuilder.append("Your account has been registered with this email");
        } else if (!msgBuilder.isEmpty()) {
            req.setAttribute("msg", msgBuilder.toString());
            req.getRequestDispatcher("/WEB-INF/registration.jsp").forward(req, resp);
        } else {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .image_name(imgName)
                    .build();
            userService.add(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        }
    }
}
