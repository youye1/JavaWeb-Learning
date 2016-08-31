package cn.youye.test.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pc on 2016/7/18.
 */
@Controller
public class ToPageController {

    @RequestMapping("/toPage")
    public String topage(String pageName) {
        return "modules/" + pageName;
    }

}
