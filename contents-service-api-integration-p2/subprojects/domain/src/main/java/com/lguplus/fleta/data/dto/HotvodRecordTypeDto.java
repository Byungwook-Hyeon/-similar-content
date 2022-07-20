package com.lguplus.fleta.data.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class HotvodRecordTypeDto implements Serializable {

	private static final long serialVersionUID = 3292221480760903155L;
	private String type;
	private List<Integer> badgeIndexList;
	private List<NewHotVodInfoDto> recordList;

}
