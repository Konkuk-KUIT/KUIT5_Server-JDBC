package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// jsp 파일에 대해서 매칭이 됨. 어떠너 jsp 파일을 가지고 있는지에 대한 속성 (=viewName)을 가지고 있어야 함.
public class JspView implements View{
    private static final String REDIRECT_PREFIX = "redirect:";
    private String viewName;

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }
        for (String key : model.keySet()) {
            request.setAttribute(key, model.get(key));
        }
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
