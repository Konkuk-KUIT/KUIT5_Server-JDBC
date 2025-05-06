package core.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View {
    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

//        // model 오브젝트를 직렬화해서 json 형태로 변환
//        Enumeration<String> attributeNames = request.getAttributeNames();
//        while (attributeNames.hasMoreElements()){
//            String attributeName = attributeNames.nextElement();
//            model.put(attributeName, request.getAttribute(attributeName));
//        }
        out.print(mapper.writeValueAsString(model));
    }
}
