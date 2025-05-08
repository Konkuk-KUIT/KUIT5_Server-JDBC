package core.mvc;

public class ModelAndView {
    private final Model model;
    private final String view;

    public ModelAndView(String view) {
        this.view = view;
        this.model = new Model();
    }

    public ModelAndView(JspView view, Model model, String view1) {
        this.model = model;
        this.view = view1;
    }

    public String getView() {
        return view;
    }

    public Model getModel() {
        return model;
    }

    public void addAttribute(String key, Object value) {
        model.addAttribute(key, value);
    }
}
