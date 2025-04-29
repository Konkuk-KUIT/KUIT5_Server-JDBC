package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JsonView implements View {
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(createModel(request)));
    }

    private Map<String, Object> createModel(HttpServletRequest request) {
        Enumeration<String> attributeNames = request.getAttributeNames();
        Map<String, Object> model = new HashMap<>();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            model.put(name, request.getAttribute(name));
        }
        return model;
    }
}
