package com.lguplus.fleta.data.vo;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.exception.InvalidRequestTypeException;
import com.lguplus.fleta.exception.ParameterMissingException;
import com.lguplus.fleta.exception.ParameterUnderBoundsException;
import com.lguplus.fleta.validation.Groups;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.*;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Minwoo Lee
 * @since 1.0
 */
@Getter
@CommonPagingVo.StartNumberPatternConstraint(payload = InvalidRequestTypeException.class, groups = Groups.R4.class)
@CommonPagingVo.StartNumberBoundsConstraint(payload = ParameterUnderBoundsException.class, groups = Groups.R5.class)
@CommonPagingVo.RequestCountNotBlankConstraint(payload = ParameterMissingException.class, groups = Groups.R6.class)
@CommonPagingVo.RequestCountPatternConstraint(payload = InvalidRequestTypeException.class, groups = Groups.R6.class)
@CommonPagingVo.RequestCountBoundsConstraint(payload = ParameterUnderBoundsException.class, groups = Groups.R6.class)
@GroupSequence({Groups.R1.class, Groups.R2.class, Groups.R3.class, Groups.R4.class, Groups.R5.class, Groups.R6.class,
        CommonPagingVo.class})
public class CommonPagingVo extends CommonVo {

    /**
     *
     */
    @NotBlank(message = "start_num 파라미터값이 전달이 안됨", groups = Groups.R3.class)
    @ParamAlias("start_num")
    private String startNumberText;

    public Integer getStartNumber() {
        if (StringUtils.isEmpty(startNumberText)) {
            return null;
        } else {
            try {
                return Integer.valueOf(startNumberText);
            } catch (final NumberFormatException e) {
                return null;
            }
        }
    }

    /**
     *
     */
    @ParamAlias("req_count")
    private String requestCountText;

    public Integer getRequestCount() {
        if (StringUtils.isEmpty(requestCountText)) {
            return null;
        } else {
            try {
                return Integer.valueOf(requestCountText);
            } catch (final NumberFormatException e) {
                return null;
            }
        }
    }

    protected boolean isRequestCountNeeded() {

        try {
            Integer startNumber = getStartNumber();
            return startNumber != null && (startNumber < -1 || startNumber > 0);
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @Constraint(validatedBy = StartNumberPatternConstraint.Validator.class)
    @interface StartNumberPatternConstraint {

        String message() default "start_num 파라미터는 숫자형 데이터이어야 함";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        class Validator implements ConstraintValidator<StartNumberPatternConstraint, CommonPagingVo> {

            @Override
            public boolean isValid(CommonPagingVo requestVo, ConstraintValidatorContext context) {

                try {
                    return StringUtils.isBlank(requestVo.startNumberText) || requestVo.getStartNumber() != null;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @Constraint(validatedBy = StartNumberBoundsConstraint.Validator.class)
    @interface StartNumberBoundsConstraint {

        String message() default "파라미터 start_num는 값이 -1 이상이어야 함";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        class Validator implements ConstraintValidator<StartNumberBoundsConstraint, CommonPagingVo> {

            @Override
            public boolean isValid(CommonPagingVo requestVo, ConstraintValidatorContext context) {

                try {
                    return StringUtils.isBlank(requestVo.startNumberText) || requestVo.getStartNumber() == null ||
                            requestVo.getStartNumber() >= -1;
                } catch (NumberFormatException e) {
                    return true;
                }
            }
        }
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @Constraint(validatedBy = RequestCountNotBlankConstraint.Validator.class)
    @interface RequestCountNotBlankConstraint {

        String message() default "req_count 파라미터값이 전달이 안됨";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        class Validator implements ConstraintValidator<RequestCountNotBlankConstraint, CommonPagingVo> {

            @Override
            public boolean isValid(CommonPagingVo requestVo, ConstraintValidatorContext context) {

                return !requestVo.isRequestCountNeeded() || StringUtils.isNotBlank(requestVo.requestCountText);
            }
        }
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @Constraint(validatedBy = RequestCountPatternConstraint.Validator.class)
    @interface RequestCountPatternConstraint {

        String message() default "req_count 파라미터는 숫자형 데이터이어야 함";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        class Validator implements ConstraintValidator<RequestCountPatternConstraint, CommonPagingVo> {

            @Override
            public boolean isValid(CommonPagingVo requestVo, ConstraintValidatorContext context) {

                try {
                    return !requestVo.isRequestCountNeeded() || StringUtils.isBlank(requestVo.requestCountText) ||
                            requestVo.getRequestCount() != null;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @Constraint(validatedBy = RequestCountBoundsConstraint.Validator.class)
    @interface RequestCountBoundsConstraint {

        String message() default "파라미터 req_count는 값이 1 이상이어야 함";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        class Validator implements ConstraintValidator<RequestCountBoundsConstraint, CommonPagingVo> {

            @Override
            public boolean isValid(CommonPagingVo requestVo, ConstraintValidatorContext context) {

                try {
                    return !requestVo.isRequestCountNeeded() || StringUtils.isBlank(requestVo.requestCountText) ||
                            requestVo.getRequestCount() == null || requestVo.getRequestCount() >= 1;
                } catch (NumberFormatException e) {
                    return true;
                }
            }
        }
    }
}
