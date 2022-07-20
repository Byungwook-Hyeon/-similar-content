package com.lguplus.fleta.advice.exhandler;

import com.lguplus.fleta.data.dto.response.ErrorResponseDto;
import com.lguplus.fleta.data.vo.error.ErrorResponseVo;
import com.lguplus.fleta.data.vo.error.GenericRecordsetErrorResponseVo;
import com.lguplus.fleta.exhandler.CustomErrorResponseConverter;
import com.lguplus.fleta.exhandler.ErrorResponseResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice("com.lguplus.fleta.api.outer")
public class OuterControllerAdvice {

    private static final Map<String, CustomErrorResponseConverter> CUSTOM_ERROR_RESPONSE_CONVERTERS = new HashMap<>();

    static {
        CUSTOM_ERROR_RESPONSE_CONVERTERS.put("GET /mims/watcha/comment/lists",
                new CustomErrorResponseConverter(ErrorResponseVo.class, "errorResponseBuilder"));

        CUSTOM_ERROR_RESPONSE_CONVERTERS.put("GET /mims/v1/watcha/comment/lists",
                new CustomErrorResponseConverter(ErrorResponseVo.class, "errorResponseBuilder"));

        CUSTOM_ERROR_RESPONSE_CONVERTERS.put("GET /smartux/comm/ad",
                new CustomErrorResponseConverter(GenericRecordsetErrorResponseVo.class, "genericRecordsetErrorResponseBuilder"));

    }


    /**
     *
     */
    private final ErrorResponseResolver errorResponseResolver;


    /**
     *
     */
    public OuterControllerAdvice(final ErrorResponseResolver errorResponseResolver) {

        this.errorResponseResolver = errorResponseResolver;
    }


    @InitBinder
    public void initBinder(final WebDataBinder binder) {

        binder.initDirectFieldAccess();
    }


    /**
     *
     */
    @ExceptionHandler(BindException.class)
    public <T> T handleBindException(final HttpServletRequest request,
                                     final BindException ex) {
        log.info(ex.getMessage(), ex);
        return (T) ResponseEntity.ok()
                .body(getCustomErrorResponse(request, errorResponseResolver.resolve(ex)));
    }


    /**
     *
     */
    @ExceptionHandler(Throwable.class)
    public <T> T  handleThrowable(final HttpServletRequest request,
                                  final Throwable th) {
        log.error(th.getMessage(), th);
        return (T) ResponseEntity.ok()
                .body(getCustomErrorResponse(request, errorResponseResolver.resolve(th)));
    }


    /**
     *
     */
    private <T> T getCustomErrorResponse(final HttpServletRequest request,
                                                     final ErrorResponseDto response) {

        String reqUrl = request.getMethod() +" " + request.getAttribute(
                HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);

        CustomErrorResponseConverter converter = CUSTOM_ERROR_RESPONSE_CONVERTERS.keySet().stream()
                .filter( convertKey -> {
                            UriTemplate template = new UriTemplate(convertKey);
                            return template.matches(reqUrl);
                        }
                )
                .findFirst()
                .map(CUSTOM_ERROR_RESPONSE_CONVERTERS::get)
                .orElse(null);

        if (converter == null) {
            return (T) response;
        }

        try {
            return (T) converter.convert(response);
        } catch (final Exception e) {
            log.warn(e.getMessage(), e);
            return (T) response;
        }
    }
}
