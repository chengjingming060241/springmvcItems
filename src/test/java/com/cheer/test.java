package com.cheer;

import com.cheer.model.ItemsCustom;
import com.cheer.model.ItemsQueryVo;
import com.cheer.service.ItemsService;
import com.cheer.service.impl.ItemsServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-*.xml")
public class test {

    private static final Logger LOGGER = LogManager.getLogger(test.class);

    @Autowired
    private ItemsService itemsService;

    @Test
    public void getItems(){

        ItemsQueryVo itemsQueryVo = new ItemsQueryVo();

        try {
            List<ItemsCustom> itemsCustomList = itemsService.findItemsList(itemsQueryVo);
            LOGGER.debug(itemsCustomList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
