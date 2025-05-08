package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectView extends View {
    private final String path;

    public RedirectView(String path) {
        super(path);
        this.path = path;
    }

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 리디렉션을 처리하는 코드
        response.sendRedirect(path);
    }

    @Override
    public boolean isRedirect() {
        return true; // 리디렉션을 처리하는 뷰임을 나타냄
    }

    @Override
    public String getPath() {
        return path;
    }
}
