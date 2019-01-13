package com.cheer.controller;

import com.cheer.model.ItemsCustom;
import com.cheer.model.ItemsQueryVo;
import com.cheer.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 商品的controller
 */
@Controller
// 为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
// 比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //商品的查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(HttpServletRequest request,ItemsQueryVo itemsQueryVo) throws Exception {
         //测试forward后request是否可以共享
        //System.out.println(itemsQueryVo.getItemsCustom().getName());

        // 调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        // 返回ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // 相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        // 指定视图
        // 下边的路径，如果在视图解析器中配置jsp路径的前缀和jsp路径的后缀，修改为
         //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        // 上边的路径配置可以不在程序中指定jsp路径的前缀和jsp路径的后缀
        modelAndView.setViewName("items/itemsList");

        return modelAndView;

    }
    //商品信息修改页面
    //RequestMapping可以post和get
    @RequestMapping(value = "/editItems" ,method = {RequestMethod.GET,RequestMethod.POST})
    //@RequestParam里面指定request传入参数名称和形参绑定
    // 通过required设置是否必须传入,
    // 通过defaultValue可以设定默认值，如果没有传入，将默认值与参数绑定
    public String editItems(Model model,@RequestParam(value = "id") Integer items_id) throws Exception{

        //调用Seivice根据商品id查询商品信息
        ItemsCustom itemsCustom = itemsService.findItemsById(items_id);

        // 返回ModelAndView
        // ModelAndView modelAndView = new ModelAndView();

        //将商品信息放到model
        model.addAttribute("itemsCustom",itemsCustom);

        //商品修改页面
        //modelAndView.setViewName("items/editItems");

        return "items/editItems";
    }

    //商品信息提交
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Integer id, ItemsCustom itemsCustom) throws Exception{

        //调用service更新商品信息，页面需要将商品信息传入此页面
       ;

        itemsService.updateItems(id,itemsCustom);
        //重定向到商品列表
        return "redirect:queryItems.action";

        //页面转发forward 地址栏不变
        //return "forward:queryItems.action";
        //return "success";

    }
}
