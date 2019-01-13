package com.cheer.service.impl;

import com.cheer.mapper.ItemsMapper;
import com.cheer.mapper.ItemsMapperCustom;
import com.cheer.model.Items;
import com.cheer.model.ItemsCustom;
import com.cheer.model.ItemsQueryVo;
import com.cheer.service.ItemsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品管理
 */
@Service
public class ItemsServiceImpl implements ItemsService {
    private static final Logger LOGGER = LogManager.getLogger(ItemsServiceImpl.class);

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;


    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)
            throws Exception {
        //通过ItemsMapperCustom查询数据
        return this.itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        //这边调用生成接口方法
        Items items = this.itemsMapper.selectByPrimaryKey(id);
        //下面对商品信息业务进行处理
        //----------------
        // 返回扩展类对象
        ItemsCustom itemsCustom = new ItemsCustom();
        //将Items的内容拷贝到itemsCustom里面
        BeanUtils.copyProperties(items,itemsCustom);

        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
        //这边添加业务校验 判断id是否为空等等

        //使用updateByPrimaryKeyWithBLOBs根据id更新商品信息，包括大文本信息
        //调用此方法必须传入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }

}
