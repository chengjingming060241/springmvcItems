package com.cheer.mapper;

import com.cheer.model.ItemsCustom;
import com.cheer.model.ItemsQueryVo;

import java.util.List;

public interface ItemsMapperCustom {
    //商品查询列表
    List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}
