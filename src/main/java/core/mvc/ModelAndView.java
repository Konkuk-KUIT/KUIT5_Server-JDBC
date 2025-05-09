
package core.mvc;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private Map<String,Object> model;
    private View view;

    public ModelAndView(View view) {
        this.view = view;
        this.model = new HashMap<>();
    }

    public ModelAndView addObject(String key, Object value){
        model.put(key,value);
        return this;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
}
