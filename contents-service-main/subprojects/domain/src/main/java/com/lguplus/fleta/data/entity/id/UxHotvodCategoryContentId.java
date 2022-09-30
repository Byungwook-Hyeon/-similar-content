package com.lguplus.fleta.data.entity.id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class UxHotvodCategoryContentId implements Serializable {

    private static final long serialVersionUID = 0L;

    private String categoryId;
    private String contentId;
}
