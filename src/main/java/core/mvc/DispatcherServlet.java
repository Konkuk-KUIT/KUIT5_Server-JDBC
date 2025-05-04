package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private RequestMapping requestMapping;
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init() throws ServletException {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        Controller controller = requestMapping.getController(url);
        try {
            // viewName을 전달, view에 대한 처리도 DispatchServlet이 같이 하게 됨.
            // 따라서 뷰 네임을 처리한느 인터페이스를 생성해서 통합으로 하게 하도록
            ModelAndView modelAndView = controller.execute(request, response);
            View view = modelAndView.getView();
            view.render(modelAndView.getModel(), request, response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }
}
