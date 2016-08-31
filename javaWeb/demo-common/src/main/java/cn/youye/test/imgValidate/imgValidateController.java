package cn.youye.test.imgValidate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pc on 2016/7/15.
 */
@Controller
@RequestMapping("/img")
public class imgValidateController {

    @Resource
    RandomValidateCode code;

    @RequestMapping(value = "/vCode")
    public void vCode(HttpServletRequest request, HttpServletResponse response) {
        code.getRandcode(request, response);
    }

    @RequestMapping(value = "/test1")
    public String test1(HttpServletRequest request, HttpServletResponse response, @RequestParam("vcode") String vcode) {
        String sessionCode = (String) request.getSession().getAttribute(Constants.RANDOM_CODE_KEY);
        if (vcode.equalsIgnoreCase(sessionCode)) {
            request.setAttribute("msg", "验证成功");
            return "modules/imgValidate/imgtest1";
        } else {
            request.setAttribute("msg", "验证码错误");
            return "modules/imgValidate/imgtest1";
        }
    }
}
