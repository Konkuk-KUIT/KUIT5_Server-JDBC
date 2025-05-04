package core.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

// 범용적, 여러개의 데이터를 넘겨줘야 함 --> MAP! & request에 전달하고 싶은 데이터를 넘겨주고, Map에서 직렬화해줌
public class JsonView implements View{
    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // JSON으로 응답 반환하는 로직.
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

//        Enumeration<String> attributeNames = request.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String attributeName = attributeNames.nextElement();// 가져오기
//            model.put(attributeName, request.getAttribute(attributeName));
//        }
        out.print(mapper.writeValueAsString(model));
    }
}
