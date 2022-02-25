package ltd.newbee.mall.controller;

import ltd.newbee.mall.entity.User;
import ltd.newbee.mall.service.UserService;
import ltd.newbee.mall.util.PageResult;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.utils.DownExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class PageController {

    @Autowired
    private UserService userService;

    /**
     * 分页功能测试
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        Result result = new Result();
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            // 返回错误码
            result.setResultCode(500);
            // 错误信息
            result.setMessage("参数异常！");
            return result;
        }
        // 封装查询参数
        PageQueryUtil queryParamList = new PageQueryUtil(params);
        // 查询并封装分页结果集
        PageResult userPage = userService.getUserPage(queryParamList);
        // 返回成功码
        result.setResultCode(200);
        result.setMessage("查询成功");
        // 返回分页数据
        result.setData(userPage);
        return result;
    }

    @GetMapping("/simpleDown")
    public void simpleDown(HttpServletResponse response) throws IOException {
        List<User> list = userService.getUserAll();
        DownExcel.download(response, User.class, list);
    }

    @GetMapping("/simpleDown2")
    public void simpleDown2(HttpServletResponse response) throws IOException {
        List<User> list = userService.getUserAll();
        DownExcel.download(response, User.class, list);
    }

    @GetMapping("/pageDown")
    public void pageDown(HttpServletResponse response) {


    }
}