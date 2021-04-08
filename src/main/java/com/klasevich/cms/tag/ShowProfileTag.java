package com.klasevich.cms.tag;

import com.klasevich.cms.command.SessionAttribute;
import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.entity.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ShowProfileTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user != null) {
            String profile = user.getName() + " " + user.getSurname() + "</p></h3>";
            String header;
            if (user.getRole() == Role.USER) {
                header = "<h3><p style=\"color: red\">";
            } else {
                header = "<h3><p style=\"color: blue\">";
            }
            String outputLine = header + profile;
            try {
                JspWriter out = pageContext.getOut();
                out.write(outputLine);
            } catch (IOException e) {
                throw new JspException(e.getMessage());
            }
        }
        return SKIP_BODY;
    }
}
