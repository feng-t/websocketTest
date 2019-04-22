package top.hlfpersonal.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hlfpersonal.result.Result;
import top.hlfpersonal.enums.Status;

/**
 * @program: hqf-parent
 * @description: 页面
 * @author: feng
 * @create: 2019-03-28 15:19
 **/
@Controller
public class WebPageController {

    @RequestMapping("login")
    @ResponseBody
    public Result login() {
        return Result.ERROR(Status.USER_NO_LOGIN);
    }

    @RequestMapping("tologin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("ws")
    public String ws() {
        return "ws/ws";
    }

    @RequestMapping("ws2")
    public String ws2() {
        return "ws/ws2";
    }
}
