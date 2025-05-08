package core.mvc.controller;

import core.mvc.view.ModelAndView;
import java.sql.SQLException;
import java.util.Map;

public interface ControllerV1 extends Controller {

    ModelAndView execute(Map<String, String> params) throws SQLException;
}