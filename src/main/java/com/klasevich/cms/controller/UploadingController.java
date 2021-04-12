package com.klasevich.cms.controller;

import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.UserService;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.validator.FilePathValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

import static com.klasevich.cms.command.command_parameter.PagePath.PROFILE;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.UrlPattern.UPLOADING_CONTROLLER;

@WebServlet(urlPatterns = {UPLOADING_CONTROLLER})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadingController extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_PATH = "C:\\\\Users\\\\USER\\\\IdeaProjects\\\\computer_maintenance_service\\\\src\\\\main\\\\webapp\\\\images\\\\uploaded\\\\";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserService service = new UserServiceImpl();
        request.getParts().stream().forEach(part -> {
            try {
                String path = part.getSubmittedFileName();
                logger.debug("part's path {}", path);
                String randFileName = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
                logger.debug("random file's name {}", randFileName);
                String pathToImage = DEFAULT_PATH + path;
                part.write(pathToImage);
                User user = (User) session.getAttribute(SessionAttribute.USER);
                String validPath = FilePathValidator.findPath(pathToImage);
                if (user != null) {
                    if (service.addAvatar(user.getMail(), validPath)) {
                        user.setAvatar(validPath);
                    } else {
                        request.setAttribute("upload_result", MessageManager.getProperty("error.upload.failed"));
                    }
                }
            } catch (StringIndexOutOfBoundsException e) {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.choose.file"));
            } catch (IOException | ServiceException e) {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("error.upload.failed"));
            }
        });
        request.getRequestDispatcher(PROFILE).forward(request, response);
    }
}
