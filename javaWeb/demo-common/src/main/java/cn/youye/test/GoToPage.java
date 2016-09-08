package cn.youye.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pc on 2016/9/2.
 */
@Controller
public class GoToPage {

    @RequestMapping("/gotoPage")
    public String gotoPage(String pageName){
        if (pageName != null && pageName != "") {
            return "modules/"+pageName;
        }
        return "redirect:"+"gotoPage?repage";
    }
}
