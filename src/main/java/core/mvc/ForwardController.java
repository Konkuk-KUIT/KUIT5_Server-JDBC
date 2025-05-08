package core.mvc;

import core.mvc.view.JspView;
import core.mvc.view.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {

    private final String forwardUrl;

    public ForwardController(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null");
        }
    }

    @Override
    public View execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new JspView(forwardUrl);
    }
}
