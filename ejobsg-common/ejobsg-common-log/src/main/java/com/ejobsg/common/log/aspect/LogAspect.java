package com.ejobsg.common.log.aspect;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.compress.utils.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson2.JSON;
import com.ejobsg.common.core.utils.ServletUtils;
import com.ejobsg.common.core.utils.StringUtils;
import com.ejobsg.common.core.utils.ip.IpUtils;
import com.ejobsg.common.log.annotation.Log;
import com.ejobsg.common.log.enums.BusinessStatus;
import com.ejobsg.common.log.filter.PropertyPreExcludeFilter;
import com.ejobsg.common.log.service.AsyncLogService;
import com.ejobsg.common.security.utils.SecurityUtils;
import com.ejobsg.system.api.domain.SysOperLog;

/**
 * 操作日志记录处理
 *
 * @author lilu
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 排除敏感属性字段
     */
    public static final String[] EXCLUDE_PROPERTIES = {"password", "oldPassword", "newPassword", "confirmPassword"};

    /**
     * 计算操作消耗时间
     */
    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<Long>("Cost Time");

    /**
     * 允许打印请求日志的环境
     */
    private static final Set<String> profiles = Sets.newHashSet("local", "default", "dev", "prod");

    @Value("${spring.profiles.active}")
    private String active;

    @Autowired
    private AsyncLogService asyncLogService;

    @Pointcut(value = "@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
        "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
        "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
        "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
        "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
        "||@annotation(com.ejobsg.common.log.annotation.Log)"
    )
    public void logPrint() {
    }

    /**
     * 处理请求前执行
     */
    @Before(value = "logPrint()")
    public void boBefore(JoinPoint joinPoint) {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPrint()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "logPrint()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            FeignClient feignClient = AnnotationUtils.findAnnotation(joinPoint.getTarget().getClass(), FeignClient.class);
            if (Objects.isNull(feignClient)) {
                processLog(joinPoint, e, jsonResult);
            }
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        } finally {
            TIME_THREADLOCAL.remove();
        }
    }

    private void processLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) throws Exception {
        // *========数据库日志=========*//
        SysOperLog operLog = new SysOperLog();
        operLog.setStatus(BusinessStatus.SUCCESS.ordinal());
        // 请求的地址
        String ip = IpUtils.getIpAddr();
        operLog.setOperIp(ip);

        HttpServletRequest request = ServletUtils.getRequest();
        if (Objects.nonNull(request)) {
            operLog.setOperUrl(StringUtils.substring(request.getRequestURI(), 0, 255));
            // 设置请求方式
            operLog.setRequestMethod(request.getMethod());
        }
        String username = SecurityUtils.getUsername();
        if (StringUtils.isNotBlank(username)) {
            operLog.setOperName(username);
        }

        if (e != null) {
            operLog.setStatus(BusinessStatus.FAIL.ordinal());
            operLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
        }
        // 设置方法名称
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        operLog.setMethod(className + "." + methodName + "()");

        Log controllerLog = getControllerLog((MethodSignature) joinPoint.getSignature());
        // 处理设置注解上的参数
        getControllerMethodDescription(joinPoint, controllerLog, operLog, jsonResult, request);
        // 设置消耗时间
        operLog.setCostTime(System.currentTimeMillis() - TIME_THREADLOCAL.get());

        if (Objects.nonNull(controllerLog)) {
            // 保存数据库
            if (controllerLog.isSave()) {
                asyncLogService.saveSysLog(operLog);
            }
            // 打印请求日志
            if (profiles.contains(active) && controllerLog.isPrint()) {
                print(operLog);
            }
        } else {
            if (profiles.contains(active)) {
                print(operLog);
            }
        }

    }

    /**
     * 获取日志记录注解
     *
     * @param signature 方法信息
     * @return 自定义操作日志记录注解
     */
    private Log getControllerLog(MethodSignature signature) {
        Log annotation = null;
        if (signature.getMethod().isAnnotationPresent(Log.class)) {
            annotation = signature.getMethod().getAnnotation(Log.class);
        }
        return annotation;
    }

    /**
     * 打印请求日志
     *
     * @param operLog 操作日志
     */
    private void print(SysOperLog operLog) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请求响应日志信息：\n【请求地址】:").append("【").append(StringUtils.format("{}|{}", operLog.getOperUrl(), operLog.getRequestMethod())).append("】");
        if (StrUtil.isNotBlank(operLog.getTitle()) && Objects.nonNull(operLog.getBusinessType())) {
            stringBuilder.append("\n【操作模块】:").append("【").append(StringUtils.format("{}|{}", operLog.getTitle(), operLog.getBusinessType())).append("】");
        }
        stringBuilder.append("\n【登录信息】:").append("【").append(StringUtils.format("{}|{}", Optional.ofNullable(operLog.getOperName()).orElse("--"), operLog.getOperIp())).append("】");
        stringBuilder.append("\n【操作方法】:").append("【").append(operLog.getMethod()).append("】");
        stringBuilder.append("\n【请求参数】:").append("【").append(operLog.getOperParam()).append("】");
        stringBuilder.append("\n【返回参数】:").append("【").append(operLog.getJsonResult()).append("】");
        String status = Objects.equals(0, operLog.getStatus()) ? "正常" : "异常";
        stringBuilder.append("\n【消耗时间】:").append("【").append(StringUtils.format("{}|{} 毫秒", status, operLog.getCostTime())).append("】\n");
        log.info(stringBuilder.toString());
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log     日志
     * @param operLog 操作日志
     * @param request 请求效果
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysOperLog operLog, Object jsonResult, HttpServletRequest request) throws Exception {
        if (Objects.nonNull(log)) {
            // 设置action动作
            operLog.setBusinessType(log.businessType().ordinal());
            // 设置标题
            operLog.setTitle(log.title());
            // 设置操作人类别
            operLog.setOperatorType(log.operatorType().ordinal());
            // 是否需要保存request，参数和值
            if (log.isSaveRequestData()) {
                // 获取参数的信息，传入到数据库中。
                setRequestValue(joinPoint, operLog, log.excludeParamNames(), request);
            }
            // 是否需要保存response，参数和值
            if (log.isSaveResponseData() && StringUtils.isNotNull(jsonResult)) {
                operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
            }
        } else {
            // 获取参数的信息，传入到数据库中。
            setRequestValue(joinPoint, operLog, new String[]{}, request);
            // 是否需要保存response，参数和值
            operLog.setJsonResult(StringUtils.substring(JSON.toJSONString(jsonResult), 0, 2000));
        }
    }

    /**
     * 获取请求的参数，放到log中
     *
     * @param operLog 操作日志
     * @param request 请求对象
     * @throws Exception 异常
     */
    private void setRequestValue(JoinPoint joinPoint, SysOperLog operLog, String[] excludeParamNames, HttpServletRequest request) throws Exception {
        String requestMethod = operLog.getRequestMethod();

        Map<?, ?> paramsMap = new HashMap<>();
        if (Objects.nonNull(request)) {
            paramsMap = ServletUtils.getParamMap(request);
        }
        if (StringUtils.isEmpty(paramsMap)
            && (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod))) {
            String params = argsArrayToString(joinPoint.getArgs(), excludeParamNames);
            operLog.setOperParam(StringUtils.substring(params, 0, 2000));
        } else {
            operLog.setOperParam(StringUtils.substring(JSON.toJSONString(paramsMap, excludePropertyPreFilter(excludeParamNames)), 0, 2000));
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray, String[] excludeParamNames) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (StringUtils.isNotNull(o) && !isFilterObject(o)) {
                    try {
                        String jsonObj = JSON.toJSONString(o, excludePropertyPreFilter(excludeParamNames));
                        params.append(jsonObj).append(" ");
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 忽略敏感属性
     */
    public PropertyPreExcludeFilter excludePropertyPreFilter(String[] excludeParamNames) {
        return new PropertyPreExcludeFilter().addExcludes(ArrayUtils.addAll(EXCLUDE_PROPERTIES, excludeParamNames));
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Object value : collection) {
                return value instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Object value : map.entrySet()) {
                Map.Entry entry = (Map.Entry) value;
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse
            || o instanceof BindingResult;
    }
}
