package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class JspView implements View {
    private static final String REDIRECT_PREFIX = "redirect:";
private String viewName;

public JspView(String viewName){
    this.viewName = viewName;
}
    @Override
    public void render(Map<String, Object> model,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

