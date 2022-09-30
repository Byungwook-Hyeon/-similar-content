package com.lguplus.fleta.data.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class CategoryAlbumsDto implements Serializable{

	private String categoryId;

	private String seriesYn;

	private String testSbc;

	private String contentsId;

	private String contentsName;

	private String seriesDesc;

	private String seriesNo;

}
