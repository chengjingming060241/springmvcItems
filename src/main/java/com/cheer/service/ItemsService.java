package com.cheer.service;

import com.cheer.model.ItemsCustom;
import com.cheer.model.ItemsQueryVo;

import java.util.List;

/**
 * 商品管理的service
 */
public interface ItemsService {
    //商品查询类别
     List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    /**
     *
     * @param id 根据商品id插叙商品信息
     * @return
     * @throws Exception
     */
    ItemsCustom findItemsById(Integer id) throws Exception;

    /**
     *
     * @param id 查询商品的Id
     * @param itemsCustom
     * @throws Exception
     */
    void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception;
}
