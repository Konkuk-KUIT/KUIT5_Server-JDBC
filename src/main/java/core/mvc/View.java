package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class View {
    private final String viewName;

    public View(String viewName) {
        this.viewName = viewName;
    }

    public void render(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        // 모델에 저장된 데이터를 request 객체에 설정
        Map<String, Object> attributes = model.getAttributes();
        for (Map.Entry<String, Object> entry : attributes.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        // JSP 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewName);
        dispatcher.forward(request, response);
    }

    public abstract void render(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public abstract boolean isRedirect();

    public abstract String getPath();
}
