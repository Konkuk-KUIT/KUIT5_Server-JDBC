package core.mvc.view;

public class ViewResolver {
    private static final String REDIRECT_PREFIX = "redirect:";
    public static final String JSON_VIEW_PREFIX = "jsonView";

    public View getView(String viewName) {
        if (viewName.equals(JSON_VIEW_PREFIX)) {
            return new JsonView();
        }
        return jspViewResolver(viewName);
    }

    private JspView jspViewResolver(String viewName) {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            return new JspView(viewName);
        }
        return new JspView(viewName);
    }
}
