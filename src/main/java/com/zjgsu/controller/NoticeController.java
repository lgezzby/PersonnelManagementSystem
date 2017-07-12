package com.zjgsu.controller;

import com.zjgsu.entity.Notice;
import com.zjgsu.service.HrmService;
import com.zjgsu.util.tag.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zby on 2017/7/11.
 */
@Controller
public class NoticeController {
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * @param pageIndex
     * @param model
     * @param notice
     * @return
     */
    // TODO 查询时点击下一页 参数不会传递过来
    @RequestMapping("/notice/selectNotice")
    private String selectNotice(Integer pageIndex, Model model, Notice notice){
        System.out.println("notice = " + notice);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        List<Notice> notices = hrmService.findNotice(notice,pageModel);
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
        return "notice/notice";
    }

    @RequestMapping("/notice/previewNotice")
    private String previewNotice(Integer id, Model model){
        Notice notice = hrmService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "notice/previewNotice";
    }

    @RequestMapping("/notice/removeNotice")
    private String removeNotice(String ids){
        String[] idArray = ids.split(",");
        for (String s : idArray){
            hrmService.removeNoticeById(Integer.parseInt(s));
        }
        return "redirect:/notice/selectNotice";
    }

    @RequestMapping("/notice/addNotice")
    private String addNotice(String flag, Notice notice){
        if (flag.equals("1")){
            return "notice/showAddNotice";
        } else {
            hrmService.addNotice(notice);
            return "redirect:/notice/selectNotice";
        }
    }

    @RequestMapping("/notice/updateNotice")
    private String updateNotice(String flag, Notice notice, Model model){
        if (flag.equals("1")){
            Notice target = hrmService.findNoticeById(notice.getId());
            model.addAttribute("notice",target);
            return "notice/showUpdateNotice";
        } else {
               hrmService.modifyNotice(notice);
               return "redirect:/notice/selectNotice";
        }
    }
}
