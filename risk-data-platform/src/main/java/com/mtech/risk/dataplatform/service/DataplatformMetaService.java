package com.mtech.risk.dataplatform.service;

import com.mtech.risk.dataplatform.dto.DataCalcMetaDTO;

/**
 * 数据平台元数据获取入口
 * @param
 */
public interface DataplatformMetaService {
    DataCalcMetaDTO queryAllMeta();
    DataCalcMetaDTO queryVariableMeta();
}
