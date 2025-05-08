package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {
    private final String path;

    public JspView(String path) {
        this.path = path;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
    }

    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public String getPath() {
        return path;
    }
}
