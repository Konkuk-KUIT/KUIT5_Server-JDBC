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

public class JsonView implements View{
    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper(); // Java 객체를 JSON 문자열로 변환
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter(); // 서버에서 브라우저로 데이터를 직접 쓰는 스트림.

//        Enumeration<String> attributeNames = request.getAttributeNames();
//        while (attributeNames.hasMoreElements()){
//            String attributeName = attributeNames.nextElement();
//            model.put(attributeName, request.getAttribute(attributeName));
//        }
        out.print(mapper.writeValueAsString(model));
    }
}
