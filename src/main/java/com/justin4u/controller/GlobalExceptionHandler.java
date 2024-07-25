package com.justin4u.controller;

import com.justin4u.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * com.justin4u.config
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-06-09</pre>
 */
@Controller
@ControllerAdvice
@RequestMapping("/globalError")
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private static final String FORWARD_GLOBAL_ERROR = "forward:/globalError";

    private HttpServletRequest request;

    @ExceptionHandler({ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            ServletRequestBindingException.class,
            BindException.class})
    public ModelAndView handleValidationException(Exception e) {
        String logMsg = getErrorLogMsg(e);
        String msg = "";
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException t = (MethodArgumentNotValidException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof BindException) {
            BindException t = (BindException) e;
            msg = getBindingResultMsg(t.getBindingResult());
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolationException t = (ConstraintViolationException) e;
            msg = t.getConstraintViolations().stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(","));
        } else if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException t = (MissingServletRequestParameterException) e;
            msg = t.getParameterName() + " 不能为空";
        } else if (e instanceof MissingPathVariableException) {
            MissingPathVariableException t = (MissingPathVariableException) e;
            msg = t.getVariableName() + " 不能为空";
        } else {
            // 其他类型的错误当成未知异常处理
            return handleUnknownException(e);
        }
        log.warn("参数校验不通过, {}, msg: {}", logMsg, msg);
        return failResultModelAndView(msg);

    }

    /**
     * 统一处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ModelAndView handleBusinessException(BusinessException t) {
        String logMsg = getErrorLogMsg(t);
        log.error("捕获到业务异常, {}, msg: {}", logMsg, t.getMessage());
        return failResultModelAndView(t.getMessage());
    }

    private ModelAndView failResultModelAndView(String msg) {
        Map<String, Object> model = new HashMap<>(4);
        model.put("msg", msg);
        model.put("code", 500);
        return new ModelAndView(FORWARD_GLOBAL_ERROR, model);
    }

    /**
     * 统一处理未知异常
     */
    @ExceptionHandler
    public ModelAndView handleUnknownException(Throwable t) {
        String logMsg = getErrorLogMsg(t);
        // 未知异常
        log.error("捕获到未经处理的未知异常, {}", logMsg, t);
        return new ModelAndView(FORWARD_GLOBAL_ERROR, "msg", "服务发生异常");
    }

    /**
     * 处理浏览器的 html 直接请求
     */
    @RequestMapping(produces = "text/html")
    public String errorHtml() {
        String msg = Objects.toString(request.getAttribute("msg"), "出错啦");
        request.setAttribute("msg", msg);
        return "error";
    }

    /**
     * 其他请求则以 response body 的形式返回
     */
    @ResponseBody
    @RequestMapping
    public JSONObject error() {
        String msg = Objects.toString(request.getAttribute("msg"), "出错啦");
        String code = Objects.toString(request.getAttribute("code"), null);
        return new JSONObject().put("code", code).put("msg", msg);
    }


    private String getErrorLogMsg(Throwable t) {
        StringBuilder errorLogMsg = new StringBuilder();
        // url，包括查询 queryString
        errorLogMsg.append("url: ").append(request.getRequestURL().toString());
        if (StringUtils.isNotBlank(request.getQueryString())) {
            errorLogMsg.append("?").append(request.getQueryString());
        }
        // 获取参数，这里只能拿到查询参数和以表单形式提交的参数，requestBody 的拿不到
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && !params.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            params.forEach((k, v) -> {
                builder.append(",").append(k).append("=").append(Arrays.toString(v));
            });
            errorLogMsg.append(", params:").append(builder.substring(1));
        }
        // 如果能获取到当前登录人信息，则添加到最前面
        String username = getUsername();
        if (StringUtils.isNotBlank(username)) {
            errorLogMsg.insert(0, "username: " + username + ", ");
        }
        return errorLogMsg.toString();
    }

    private String getUsername() {
        return null;
    }

    private String getBindingResultMsg(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }

}


