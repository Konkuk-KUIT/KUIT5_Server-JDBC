package core.mvc.controller;

import javax.servlet.http.HttpSession;
import jwp.model.User;

public interface Controller {

    default void setSession(HttpSession session) {
    }

    default void setUserFromSession(User user) {
    }
}
