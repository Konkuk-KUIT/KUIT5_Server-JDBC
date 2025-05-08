package core.mvc.controller;

import java.sql.SQLException;
import java.util.Map;

public interface ControllerV2 extends Controller {

    String execute(Map<String, String> params, Map<String, Object> model) throws SQLException;
}