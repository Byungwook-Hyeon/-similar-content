package com.lguplus.fleta.data.vo;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.GroupSequence;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.lguplus.fleta.data.annotation.ParamAlias;
import com.lguplus.fleta.data.type.PagingType;
import com.lguplus.fleta.exception.ParameterOutOfRangeException;
import com.lguplus.fleta.exception.ParameterUnderBoundsException;
import com.lguplus.fleta.validation.Groups;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 *
 * @author Minwoo Lee
 * @since 1.0
 */
@Getter
@CommonNewPagingVo.StartNumberBoundsConstraint(payload = ParameterUnderBoundsException.class, groups = Groups.R8.class)
@GroupSequence({Groups.R1.class, Groups.R2.class, Groups.R7.class, Groups.R3.class, Groups.R4.class, Groups.R8.class,
        Groups.R6.class, CommonNewPagingVo.class})
public class CommonNewPagingVo extends CommonPagingVo {

    /**
     * 페이징 타입
     */
    @ApiModelProperty(hidden=true)
    @Pattern(regexp = "^(\\s*|N)$", message = "파라미터 paging_type는 값의 범위가 null|N 이어야 함", payload = ParameterOutOfRangeException.class, groups = Groups.R7.class)
    @ParamAlias("paging_type")
    private String pagingTypeText;

    @ApiModelProperty(hidden=true)
    public PagingType getPagingType() {

        return PagingType.asValue(pagingTypeText);
    }

    @Override
    protected boolean isRequestCountNeeded() {

        try {
            Integer startNumber = getStartNumber();
            if (startNumber == null) {
                return false;
            }
            return "N".equals(pagingTypeText) ? startNumber != 0 : startNumber < -1 || startNumber > 0;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    @Target(TYPE)
    @Retention(RUNTIME)
    @Constraint(validatedBy = StartNumberBoundsConstraint.Validator.class)
    @interface StartNumberBoundsConstraint {

        String message() default "파라미터 start_num는 값이 -1 이상이어야 함";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};

        class Validator implements ConstraintValidator<StartNumberBoundsConstraint, CommonNewPagingVo> {

            @Override
            public boolean isValid(CommonNewPagingVo requestVo, ConstraintValidatorContext context) {

                try {
                    return "N".equals(requestVo.pagingTypeText) || requestVo.getStartNumber() >= -1;
                } catch (NumberFormatException e) {
                    return true;
                }
            }
        }
    }
}
