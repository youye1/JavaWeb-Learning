package cn.youye.test.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by pc on 2016/7/15.
 */
@Controller
@RequestMapping("/global")
public class GlobalController {

    /**
     * 基于浏览器请求的国际化，
     * 浏览器切换语言首选项实现国际化
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/test1", method = {GET})
    public String test1(HttpServletRequest request, Model model) {
        if (!model.containsAttribute("contentModel")) {

            //从后台代码获取国际化信息
            RequestContext requestContext = new RequestContext(request);
            model.addAttribute("money", requestContext.getMessage("money"));
            model.addAttribute("date", requestContext.getMessage("date"));

            FormatModel formatModel = new FormatModel();
            Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

            formatModel.setMoney(12345.678);
            formatModel.setDate(sdf.format(date));

            model.addAttribute("contentModel", formatModel);
        }
        return "modules/i18n/globalTest";
    }

    @RequestMapping(value = "/test2")
    public String test2(HttpServletRequest request, Model model,
                        @RequestParam(value = "langType", defaultValue = "zh") String langType) {

        if (!model.containsAttribute("contentModel")) {
            //根据条件设置locale
            if (langType.equals("zh")) {
                Locale locale = new Locale("zh", "CN");
                request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            } else if (langType.equals("en")) {
                Locale locale = new Locale("en", "US");
                request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, locale);
            } else {
                request.getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
            }

            //从后台代码获取国际化信息
            RequestContext requestContext = new RequestContext(request);
            model.addAttribute("money", requestContext.getMessage("money"));
            model.addAttribute("date", requestContext.getMessage("date"));

            FormatModel formatModel = new FormatModel();
            Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

            formatModel.setMoney(12345.678);
            formatModel.setDate(sdf.format(date));

            model.addAttribute("contentModel", formatModel);
        }
        return "modules/i18n/globalTest2";
    }

    @RequestMapping(value = "/test3")
    public String test3(HttpServletRequest request, HttpServletResponse response, Model model,
                        @RequestParam(value = "langType", defaultValue = "zh") String langType) {
        if (!model.containsAttribute("contentModel")) {
            if (langType.equals("zh")) {
                Locale locale = new Locale("zh", "CN");
                (new CookieLocaleResolver()).setLocale(request, response, locale);
            } else if (langType.equals("en")) {
                Locale locale = new Locale("en", "US");
                (new CookieLocaleResolver()).setLocale(request, response, locale);
            } else {
                (new CookieLocaleResolver()).setLocale(request, response, LocaleContextHolder.getLocale());
            }
            //从后台代码获取国际化信息
            RequestContext requestContext = new RequestContext(request);
            model.addAttribute("money", requestContext.getMessage("money"));
            model.addAttribute("date", requestContext.getMessage("date"));

            FormatModel formatModel = new FormatModel();
            Date date = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

            formatModel.setMoney(12345.678);
            formatModel.setDate(sdf.format(date));

            model.addAttribute("contentModel", formatModel);
        }
        return "modules/i18n/globalTest3";
    }
}
