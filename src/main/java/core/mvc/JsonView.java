package core.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JsonView implements View {
    private final Object data;

    public JsonView(Object data) {
        this.data = data;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        out.print(mapper.writeValueAsString(data));
    }

    @Override
    public boolean isRedirect() {
        return false;
    }

    @Override
    public String getPath() {
        return "";
    }
}
